package com.pray.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pray.entity.po.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookMapper
 *
 * @author 春江花朝秋月夜
 * @since 2024/2/20 11:43
 */
@Repository
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 借阅书籍后根据书籍Id减少库存
     * @param restCount 剩余数量
     * @param bookId 书籍Id
     * @return int
     */
    @Update("update bootdemo.book set book.book_count=#{restCount} where book.book_id=#{bookId}")
    int updateBookCountByBookId(@Param("restCount") int restCount,@Param("bookId") int bookId);

    /**
     * 获取书籍库存
     * @param bookId 书籍Id
     * @return int
     */
    @Select("select bootdemo.book.book_count from bootdemo.book where book_id=#{bookId}")
    int selectBookRestCount(int bookId);
    @Select("select * from bootdemo.book")
    List<Book> bookList();
}
