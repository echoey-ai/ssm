package com.atguigu.controller;

import com.atguigu.bean.Book;
import com.atguigu.bean.User;
import com.atguigu.dao.BookMapper;
import com.atguigu.service.BookService;

import com.atguigu.service.impl.BookServiceImpl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    BookService bookService;



    @RequestMapping("/page/{min}/{max}/{pn}")
    public String page(@PathVariable(value="pn")Integer pn,
                       @PathVariable(value="min")double min,
                       @PathVariable(value="max")double max, Model model){

        //得到分页内容
        PageHelper.startPage(pn,4);
        List<Book>books=bookService.queryBooksByPrice(min,max);
        //得到分页信息
        PageInfo<Book> info=new PageInfo<>(books,6);

        for (Book itme:books) {
            System.out.println(itme.getName());;
        }
        model.addAttribute("info",info);
        model.addAttribute("min",min);
        model.addAttribute("max",max);
        model.addAttribute("url","page/"+min+"/"+max);
        return "/pages/client/index";
    }

    @RequestMapping("/pagebooks/{pn}")
    public String pagebooks(@PathVariable(value="pn")Integer pn,Model model){

        //得到分页内容
        PageHelper.startPage(pn,4);

        List<Book>books=bookService.queryBooks();
        //得到分页信息
        PageInfo<Book> info=new PageInfo<>(books,6);

        for (Book itme:books) {
            System.out.println(itme.getName());;
        }
        model.addAttribute("info",info);
        model.addAttribute("url","pagebooks");
        return "pages/manager/book_manager";
    }

    @RequestMapping("/pagenull/{pn}")
    public String pagenull(@PathVariable(value="pn")Integer pn,Model model){

        //得到分页内容
        PageHelper.startPage(pn,4);

        List<Book>books=bookService.queryBooks();
        //得到分页信息
        PageInfo<Book> info=new PageInfo<>(books,6);

        for (Book itme:books) {
            System.out.println(itme.getName());;
        }
        model.addAttribute("info",info);
        model.addAttribute("url","pagenull");
        return "/pages/client/index";
    }



    @RequestMapping(value = "/book",method=RequestMethod.POST)
    public String add(Book book){

        bookService.addBook(book);
        System.out.println("增");

        return "redirect:/pagebooks/1";
    }

@RequestMapping(value = "/book/{id}",method=RequestMethod.DELETE)
protected String delete(@PathVariable(value = "id") int id) throws ServletException, IOException {

    bookService.deleteBookById(id);
    System.out.println("删除");
    return "redirect:/pagebooks/1";
}

    @RequestMapping(value = "/book",method=RequestMethod.PUT)
    protected String update(Book book) throws ServletException, IOException {

        bookService.updateBook(book);

        System.out.println("改");

        return "redirect:/pagebooks/1";
    }
    @RequestMapping(value = "/book/{id}",method=RequestMethod.GET)
    protected String getBook(@PathVariable(value = "id") int id, Model model)  throws ServletException, IOException {

        Book book = bookService.queryBookById(id);

        model.addAttribute("book",book);
        System.out.println("查");

        return "/pages/manager/book_edit";
    }











}
