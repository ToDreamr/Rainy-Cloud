package com.pray.entity.po;


import java.io.Serializable;


/**
*
* @TableName book
*/
public class Book implements Serializable {

    /**
    * 书籍名称
    */


    private Integer bookId;
    /**
    * 书籍剩余量
    */
    private Integer bookCount;
    /**
    * 书名
    */
    private String bookName;

    /**
    * 书籍名称
    */
    public void setBookId(Integer bookId){
    this.bookId = bookId;
    }

    /**
    * 书籍剩余量
    */
    public void setBookCount(Integer bookCount){
    this.bookCount = bookCount;
    }

    /**
    * 书名
    */
    public void setBookName(String bookName){
    this.bookName = bookName;
    }


    /**
    * 书籍名称
    */
    public Integer getBookId(){
    return this.bookId;
    }

    /**
    * 书籍剩余量
    */
    public Integer getBookCount(){
    return this.bookCount;
    }

    /**
    * 书名
    */
    public String getBookName(){
    return this.bookName;
    }

}
