package com.pray.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pray.constants.RabbitMqConstants;
import com.pray.entity.po.Book;
import com.pray.entity.po.BookUser;
import com.pray.entity.po.Borrow;
import com.pray.exception.CloudServiceException;
import com.pray.feign.ServiceClient;
import com.pray.mapper.BookMapper;
import com.pray.mapper.BookUserMapper;
import com.pray.mapper.BorrowMapper;
import com.pray.service.BookService;
import com.pray.service.BookUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Rainy-Heights
* &#064;description  针对表【book_user(图书馆用户)】的数据库操作Service实现
* &#064;createDate  2024-02-19 16:42:23
 */
@Service
@Slf4j
public class BookUserServiceImpl extends ServiceImpl<BookUserMapper, BookUser> implements BookUserService{

    @Resource
    private BookUserMapper bookUserMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private BookService bookService;
    @Resource
    private ServiceClient serviceClient;
    @Resource(name = "loacal-template")
    private RabbitTemplate rabbitTemplate;
//    @GlobalTransactional
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int borrowBook(int userId, int bookId) {
        //Demo只用来测试Seata事务只做一次借一本书的业务
        int userRemainCount = bookUserMapper.getRemainCount(userId);//获取剩余可借阅数量
        int bookRemainCount=bookService.selectBookRestCount(bookId);//获取书籍库存

        if (userRemainCount<=0||bookRemainCount<=0) {
            return 0;
        }

        //初始失败
        int bookBorrow=0;
        int userBorrow=0;
        int borrowExcelBorrow=0;

        //先看看是不是借过这本书了
        List<Borrow> userBorrowList = borrowMapper.selectList(new QueryWrapper<Borrow>().eq("user_id", userId)
                .eq("book_id",bookId));
        //目标借阅书籍
        List<Book> targetBook= bookMapper.selectList(new QueryWrapper<Book>().eq("book_id",bookId));
        if (!userBorrowList.isEmpty()){
            return 2;
        }
        else {
            //下面的事务应该要么全部执行要么都不执行，注意！！！
            //尝试借阅书籍
            bookBorrow=bookService.updateBookCountByBookId(bookRemainCount-1,bookId);
            userBorrow=bookUserMapper.updateBorrowCount(userRemainCount-1,userId);//更新可借阅数量

            //向MQ发送借阅消息，这里使用的是最简单的topic模式
            rabbitTemplate.convertAndSend("amq.direct", RabbitMqConstants.BOOK_BORROW_TOPIC,
                        "产生一条借阅记录，userId="+userId);
            borrowExcelBorrow=serviceClient.insertBorrowRecord(userId,bookId);//用Feign来实现调用，模拟微服务
            log.info("<----------------------- 产生一条借阅记录,借阅用户Id:{},书籍Id:{} ----------------------->",userId,bookId);
            //全部都要成功，否则事务失败
            if ((bookBorrow&userBorrow&borrowExcelBorrow)==1){
                return 1;
            }else {
                log.error("<-----------------------"+this.getClass().getName()+"尝试进行借阅失败，借阅bookId:{},借阅人Id：{} ----------------------->",bookId,userId);
                throw new CloudServiceException("尝试进行借阅失败");
            }
        }
    }
}




