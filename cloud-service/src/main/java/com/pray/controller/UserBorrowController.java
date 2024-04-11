package com.pray.controller;

import com.pray.feign.ServiceClient;
import com.pray.service.BookService;
import com.pray.service.BookUserService;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * UserBorrowController
 *
 * @author 春江花朝秋月夜
 * @since 2024/2/19 16:50
 */
@RestController
@RequestMapping(path = "/borrow")
public class UserBorrowController {
    @Resource
    private BookService bookService;

    @Resource
    private BookUserService bookUserService;

    @Resource
    private ServiceClient serviceClient;
    @GetMapping
    public Result<?> borrowList() {
        return bookService.borrowList();
    }

    @PostMapping("/{userId}/{bookId}")
    public Result<Map<String, Object>> borrowBook(@PathVariable("userId") int userId,@PathVariable("bookId") int bookId){
        int isBorrowed = bookUserService.borrowBook(userId, bookId);
        List<Map<String, Object>> mapList = serviceClient.selectBorrowDetails(userId, bookId);
        if (isBorrowed==1) {
            return Result.ok(mapList.get(0),"借阅成功");
        } else if (isBorrowed==2) {
            return Result.fail("借阅失败，这本书你已经借过了");
        }
        return Result.fail("借阅失败");
    }
}
