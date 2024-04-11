package com.pray.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pray.entity.po.Book;
import com.pray.entity.vo.response.BorrowedListVO;
import com.pray.utils.Result;

import java.util.List;

/**
* @author Rainy-Heights
* @description 针对表【book】的数据库操作Service
* @createDate 2024-02-19 16:42:23
*/
public interface BookService extends IService<Book> {
    /**
     * 借阅书籍后根据书籍Id减少库存
     * @param restCount 剩余数量
     * @param bookId 书籍Id
     * @return int
     */
    int updateBookCountByBookId(int restCount,int bookId);

    /**
     * 获取书籍库存
     * @param bookId 书籍Id
     * @return int
     */
    int selectBookRestCount(int bookId);

    Result<List<List<BorrowedListVO>>> borrowList();

}
