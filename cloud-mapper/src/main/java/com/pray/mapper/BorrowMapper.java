package com.pray.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pray.entity.po.Borrow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author Rainy-Heights
* @description 针对表【borrow(借阅表)】的数据库操作Mapper
* @createDate 2024-02-19 16:42:23
* @Entity com.pray.mapper.Borrow
*/
@Repository
public interface BorrowMapper extends BaseMapper<Borrow> {
     /**
      * 返回借阅信息，字段包括 借阅书籍，借阅人，借阅人工号，借阅书籍号
      * @return List<Map<String,Object>>
      */
//     @Select("select borrow_man.username,book.book_name,borrow.book_id ,borrow_man.user_id ,borrow_man.borrow_count from book_user as borrow_man right join borrow on borrow_man.user_id=borrow.user_id left join book on book.book_id=borrow.book_id")
     //TODO 复合查询的索引优化之后再改
//     @Select("select book.book_name,mid.username , mid.user_id ,mid.book_id  from book inner join (select bu.username,bu.user_id,bw.book_id from book_user bu inner join borrow bw on bu.user_id = bw.user_id )  as mid on mid.book_id=book.book_id")
     List<Map<String,Object>> selectBorrowUser();

     /**
      * 添加借阅记录
      * @param userId  借阅人工号
      * @param bookId  借阅书籍号
      * @return int
      */
//     @Insert(" insert into bootdemo.borrow(user_id,book_id) VALUE (#{userId},#{bookId})")
     int insertBorrowRecord(@Param("userId") int userId, @Param("bookId") int bookId);

     /**
      * 查询借阅 书籍信息
      * @param userId 借阅人工号
      * @param bookId 借阅书籍号
      * @return List<Map<String,Object>>
      */
//     @Select("select bu.username,bn.book_name from book_user as bu right join (select book_name from book where book_id=(select distinct (book_id) from borrow where borrow.book_id=#{bookId} and borrow.user_id=#{userId})) as bn on bu.user_id=#{userId}")
     List<Map<String,Object>> selectBorrowDetails(@Param("userId") int userId, @Param("bookId") int bookId);
}




