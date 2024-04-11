package com.pray.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pray.entity.po.Book;
import com.pray.entity.po.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author Rainy-Heights
* @description 针对表【borrow(借阅表)】的数据库操作Service
* @createDate 2024-02-19 16:42:23
*/
public interface BorrowService extends IService<Borrow> {
    /**
     * 返回借阅信息，字段包括 借阅书籍，借阅人，借阅人工号，借阅书籍号
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getBorrowUsers();

    /**
     * 添加借阅记录
     * @param userId  借阅人工号
     * @param bookId  借阅书籍号
     * @return int
     */
    int insertBorrowRecord(int userId,int bookId);

    List<Map<String,Object>> selectBorrowDetails(@Param("userId") int userId, @Param("bookId") int bookId);

    List<Book> listBook();
}
