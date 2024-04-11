package com.pray.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pray.entity.po.BookUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* @author Rainy-Heights
* @description 针对表【book_user(图书馆用户)】的数据库操作Mapper
* @createDate 2024-02-19 16:42:23
* @Entity com.pray.mapper.BookUser
*/
@Repository
public interface BookUserMapper extends BaseMapper<BookUser> {
    /**
     * 获取可借阅数量
     * @param userId 用户工号
     * @return int
     */
    @Select("select bootdemo.book_user.borrow_count from bootdemo.book_user where user_id=#{userId}")
    int getRemainCount(int userId);

    /**
     * 根据用户工号修改可借阅数量
     * @param borrowCount 可借阅数量
     * @param userId 用户工号
     * @return int
     */
    @Update("update bootdemo.book_user set book_user.borrow_count=#{borrowCount} where user_id=#{userId}")
    int updateBorrowCount(@Param("borrowCount")int borrowCount,@Param("userId")int userId);
}




