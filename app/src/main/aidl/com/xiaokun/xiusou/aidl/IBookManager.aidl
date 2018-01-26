// IBookManager.aidl
package com.xiaokun.xiusou.demo6;
import com.xiaokun.xiusou.aidl.Book;

// Declare any non-default types here with import statements

interface IBookManager {

    List<Book> getBookList();

    void addBook(int Book book);
}
