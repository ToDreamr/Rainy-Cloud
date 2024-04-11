package com.pray.controller;

import com.pray.entity.po.Book;
import com.pray.service.BorrowService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * BorrowController
 *
 * @author 春江花朝秋月夜
 * @since 2024/2/20 10:54
 */
@RestController
@RequestMapping("/borrow")
@Slf4j
public class BorrowController {
    @Resource
    private BorrowService borrowService;
    @GetMapping("/borrowUsers")
    public List<Map<String,Object>> getBorrowUsers()
    {
        log.info("<----------------------- 接收到借阅信息查询服务调用 ----------------------->");
        return borrowService.getBorrowUsers();
    }
    // int insertBorrowRecord(int userId,int bookId);
    @PostMapping("/insertBorrow/{userId}/{bookId}")
    public int insertBorrowRecord(@PathVariable("userId") int userId,@PathVariable("bookId") int bookId)
    {
        log.info("<----------------------- 接收到借书服务调用,借阅用户Id:{},书籍Id:{} ----------------------->",userId,bookId);
        return borrowService.insertBorrowRecord(userId,bookId);
    }

    @GetMapping("/borrowDetails/{userId}/{bookId}")
    public List<Map<String, Object>> selectBorrowDetails(@PathVariable("userId") int userId,@PathVariable("bookId") int bookId){
        log.info("<----------------------- 接收到借书服务详情调用,借阅用户Id:{},书籍Id:{} ----------------------->",userId,bookId);
        return borrowService.selectBorrowDetails(userId, bookId);
    }
    @GetMapping("/booklist")
    public List<Book> listBook(){
        log.info("<----------------------- 接收到借阅表调用---------------------------->");
        return borrowService.listBook();
    }
}
