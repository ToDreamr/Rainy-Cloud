package com.pray.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pray.entity.po.Book;
import com.pray.entity.vo.response.BorrowedListVO;
import com.pray.entity.vo.response.UserListVO;
import com.pray.feign.ServiceClient;
import com.pray.mapper.BookMapper;
import com.pray.service.BookService;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author Rainy-Heights
* @description 针对表【book】的数据库操作Service实现
* @createDate 2024-02-19 16:42:23
*/
@Service
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService{
    @Resource
    private BookMapper bookMapper;

    /**
     * 根据剩余书籍数量和bookId更新书籍库存
     * @param restCount 剩余数量
     * @param bookId 书籍Id
     * @return
     */
    @Override
    public int updateBookCountByBookId(int restCount, int bookId) {
        return bookMapper.updateBookCountByBookId(restCount, bookId);
    }

    /**
     * 查询书籍库存
     * @param bookId 书籍Id
     * @return
     */
    @Override
    public int selectBookRestCount(int bookId) {
        return bookMapper.selectBookRestCount(bookId);
    }

    @Resource
    private ServiceClient serviceClient;

    @Override
    public Result<List<List<BorrowedListVO>>> borrowList() {
        List<Map<String, Object>> mapList = serviceClient.getBorrowUsers();
        if (mapList.size() == 0) {
            return Result.ok();
        }

        Map<String, BorrowedListVO> borrowedListMap = new HashMap<>();

        for (Map<String, Object> item : mapList) {
            String bookName = (String) item.get("book_name");
            String username = (String) item.get("username");
            Integer userId = (Integer) item.get("user_id");

            String key = bookName.toLowerCase(); // 使用书名的小写形式作为键

            BorrowedListVO borrowedListVO = borrowedListMap.get(key);
            if (borrowedListVO == null) {
                borrowedListVO = new BorrowedListVO();
                borrowedListVO.setBookNames(bookName);
                borrowedListVO.setUsers(new ArrayList<>());
                borrowedListMap.put(key, borrowedListVO);
            }

            UserListVO userListVO = new UserListVO();
            userListVO.setUserId(new int[]{userId});
            userListVO.setUserName(new String[]{username});
            borrowedListVO.getUsers().add(userListVO);
        }

        List<List<BorrowedListVO>> listVo = new ArrayList<>();
        listVo.add(new ArrayList<>(borrowedListMap.values()));

        return Result.ok(listVo);
    }
    /*
     * TreeMap<String, Map<String,Object>> hashMap = new TreeMap<>();//构建返回体{user_id:{data}}
     *         //错误的写法，借阅人键值对可能是重复的，没有考虑到这种情况
     *         for (Map<String,Object> map:mapList)
     *         {
     *             JSONObject write=new JSONObject();
     *             write.set("book_id",map.get("book_id"));
     *             write.set("book_name",map.get("book_name"));
     *             write.set("username",map.get("username"));
     *             write.set("borrow_count",map.get("borrow_count"));
     *             hashMap.put(String.valueOf(map.get("user_id")),write.getRaw());
     *         }
     *         //去除Json转义字符
     *         ObjectMapper objectMapper = new ObjectMapper();
     *         objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
     *         String json = objectMapper.writeValueAsString(hashMap);
     *         // 将 JSON 字符串转换为对象或容器类型
     *         Object jsonOB = objectMapper.readValue(json, Object.class);
     *         log.info("借书服务查询书籍关系结果集：{}",objectMapper.writeValueAsString(jsonOB));
     */
}




