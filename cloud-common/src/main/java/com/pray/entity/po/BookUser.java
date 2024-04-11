package com.pray.entity.po;


import java.io.Serializable;



/**
* 图书馆用户
* @TableName book_user
*/
public class BookUser implements Serializable {

    /**
    * 学生工号
    */

    private Integer userId;
    /**
    * 学生姓名
    */

    private String username;

    private Integer borrowCount;

    /**
    * 学生工号
    */
    public void setUserId(Integer userId){
    this.userId = userId;
    }

    /**
    * 学生姓名
    */
    public void setUsername(String username){
    this.username = username;
    }


    /**
    * 学生工号
    */
    public Integer getUserId(){
    return this.userId;
    }

    /**
    * 学生姓名
    */
    public String getUsername(){
    return this.username;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }
}
