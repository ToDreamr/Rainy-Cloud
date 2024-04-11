package com.pray.entity.po;



import java.io.Serializable;



/**
* 借阅表
* @TableName borrow
*/
public class Borrow implements Serializable {

    /**
    * 借阅id
    */

    private Integer borrowId;
    /**
    * 书籍id
    */
    private Integer bookId;
    /**
    * 借阅人工号
    */
    private Integer userId;

    /**
    * 借阅id
    */
    public void setBorrowId(Integer borrowId){
    this.borrowId = borrowId;
    }

    /**
    * 书籍id
    */
    public void setBookId(Integer bookId){
    this.bookId = bookId;
    }

    /**
    * 借阅人工号
    */
    public void setUserId(Integer userId){
    this.userId = userId;
    }


    /**
    * 借阅id
    */
    public Integer getBorrowId(){
    return this.borrowId;
    }

    /**
    * 书籍id
    */
    public Integer getBookId(){
    return this.bookId;
    }

    /**
    * 借阅人工号
    */
    public Integer getUserId(){
    return this.userId;
    }

}
