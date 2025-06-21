package com.pray.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pray.entity.po.Book;
import com.pray.entity.po.BookUser;
import com.pray.entity.po.Borrow;
import com.pray.exception.CloudException;
import com.pray.feign.ServiceClient;
import com.pray.mapper.BookMapper;
import com.pray.mapper.BookUserMapper;
import com.pray.mapper.BorrowMapper;
import com.pray.service.BookService;
import com.pray.service.BookUserService;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public int borrowBook(int userId, int bookId) {
        // 1. 查询用户剩余可借阅数量
        int userRemainCount = bookUserMapper.getRemainCount(userId);

        // 2. 查询书籍库存
        int bookRemainCount = bookService.selectBookRestCount(bookId);

        if (userRemainCount <= 0 || bookRemainCount <= 0) {
            return 0;
        }

        // 3. 检查是否已经借过这本书
        List<Borrow> userBorrowList = borrowMapper.selectList(new QueryWrapper<Borrow>().eq("user_id", userId)
                .eq("book_id", bookId));
        List<Book> targetBook = bookMapper.selectList(new QueryWrapper<Book>().eq("book_id", bookId));
        if (!userBorrowList.isEmpty()) {
            return 2;
        } else {
            // 4. 更新书籍库存（本地事务）
            int bookBorrow = bookService.updateBookCountByBookId(bookRemainCount - 1, bookId);
            if (bookBorrow != 1) {
                log.error("<----------------------- 更新书籍 {} 库存失败 ----------------------->", bookId);
                throw new CloudException("更新书籍库存失败");
            }

            // 5. 更新用户可借阅数量（本地事务）
            int userBorrow = bookUserMapper.updateBorrowCount(userRemainCount - 1, userId);
            if (userBorrow != 1) {
                log.error("<----------------------- 更新用户 {} 可借阅数量失败 ----------------------->", userId);
                throw new CloudException("更新用户可借阅数量失败");
            }

            // 6. 调用远程服务插入借阅记录（属于另一个微服务的事务）
            int borrowExcelBorrow = serviceClient.insertBorrowRecord(userId, bookId);
            if (borrowExcelBorrow != 1) {
                log.error("<----------------------- 远程调用插入借阅记录失败，借阅bookId:{},借阅人Id：{} ----------------------->", bookId, userId);
                throw new CloudException("远程调用插入借阅记录失败");
            }

            // 7. 记录日志，表示借阅成功
            log.info("<----------------------- 产生一条借阅记录,借阅用户Id:{},书籍Id:{} ----------------------->", userId, bookId);

            // 8. 全部成功，返回结果
            return 1;
        }
    }
}




