package com.pray.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pray.entity.po.BookUser;

/**
* @author Rainy-Heights
* @description 针对表【book_user(图书馆用户)】的数据库操作Service
* @createDate 2024-02-19 16:42:23
*/
public interface BookUserService extends IService<BookUser> {
    /**
     * 借书服务
     * @param userId
     * @param bookId
     * @return
     */
    int borrowBook(int userId,int bookId);

}
