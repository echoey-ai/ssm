package com.atguigu.service;

import com.atguigu.bean.Book;
import com.github.pagehelper.Page;


import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();
    public List<Book> queryBooksByPrice(double min, double max);

/*    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);*/
}
