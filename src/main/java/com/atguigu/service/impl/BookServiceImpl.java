package com.atguigu.service.impl;


import com.atguigu.bean.BookExample;
import com.atguigu.dao.BookMapper;

import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper mapper;



    @Override
    public void addBook(Book book) {

         mapper.insert(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBook(Book book) {
        mapper.updateByPrimaryKey(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Book> queryBooks() {
        return  mapper.selectByExample(null);
    }

    @Override
    public List<Book> queryBooksByPrice(double min, double max) {

        BigDecimal a=BigDecimal.valueOf(min);
        BigDecimal b=BigDecimal.valueOf(max);
        BookExample example=new BookExample();
        BookExample.Criteria criteria1 = example.createCriteria();
        criteria1.andPriceBetween(a,b);

        return mapper.selectByExample(example);
    }


}
