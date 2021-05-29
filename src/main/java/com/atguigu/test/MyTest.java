package com.atguigu.test;

import com.atguigu.bean.Book;
import com.atguigu.bean.BookExample;
import com.atguigu.controller.UserController;
import com.atguigu.dao.BookMapper;
import com.atguigu.dao.UserMapper;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring.xml","classpath:spring/springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest {

    @Autowired
    protected WebApplicationContext wac;


    @Autowired
    private UserController controller;



    @Before
    public void setup(){
        assertNotNull(controller);
    }


    @Test
    public void CHANGE(){




//        String a = "12345678QWERTYUASDFGHJ0OBCLZMVKN00X00";//CHENGDU
//        String a ="12345678QWERTYUASDFGHJPOBCLZMVKN00X,.";//RUBIA
        String a= "12345678QWERTYUASDFGHJ0OBCLZMVKN00X00";//GOUFUGUI

        char[] A = a.toCharArray();

        String b = "QWERTYUUASDFGHJZXCVBNM00OP000KL000000";//FF14
//        String b = "QWERTYUUASDFGHJZXCVBNM1234567890000000";//元神

        char[] B = b.toCharArray();

        String s = "ET5(Q1}—T25{G3}-T2T{E1}T5(Q1}—\n" +
                "{W1}-QU-JT—G-W[QW]QGS-\n" +
                "ADQY3-{AG1}—AFR1-5-ASGT1-5-AGE-W[EWQ]-\n" +
                "ADQY3-{A1]—AFR1WQ-SGT1WUJTG-\n" +
                "STS{G1}-{H2}-1-1-{H1}-{F3)-1-1-{F1)-{G2)-322-{G1)-(A3}-\n" +
                "A-{JT}-1-{H2}-1-1-{H2}32-1-1-{F1}-(G2}-3-2-{G1)-{A1}-\n" +
                "G-{JT}-{G1}-{H2}-1-1-(H2)32-1-1-F-{G1)-232-(G1)-{A3} –\n" +
                "A-{JT}-1-{H2}-1-1-{H3}-{F2}-1-1-F-{G2)-232-{G1)-{A1}—\n" +
                "2S{G1}-{H5)-1Q1-{H1}52A1Q1-F-{G2)-111-{G5}-{A3}-\n" +
                "AD{G1}-5-{H5)-1Q1-{(H1}52A1Q1-F-(G2)-111-{G2]-{A1}-\n" +
                "2S{G1}-{H5}-1Q1-{H1}52A1Q1-F-{G2}-111-{G5}-{A3} –\n" +
                "AD{G1}-5-{H5}-1Q1-{H1}52A1Q1-F-(G2}-111-(G2}-{A1}-\n" +
                "AD{G2]-1-{H5}-1Q1-{H5}52A1Q1-F-(G2}-1Q1-{G5}-{A3}-\n" +
                "AD{G2}-1-{H5}-1Q1-{H1}52A1Q1-F-{G2}-111-{G2]-{A1}-\n" +
                "AD3210{H1}-5-5-{H2}1{F1}-5-5-{F2}1{G1}-211-{G2}1{A1} –\n" +
                "21121T1-\n" +
                "{HY}-5-5-{H2}1{F1}7521TW{G2}-3-2-{G2}1{A1}-\n" +
                "13211T-\n" +
                "{HY}-5-5-{H2}1{FT}-5-5-\n" +
                "TE{GW}T12 3T14 3T13 {2321}T1WT 1T2T3T 21TW QRT1{G2}-1-1-{G2}-{A1}-\n" +
                "G-{J2]-1-{H5)-1-1-15{F2}-1-1——{G2}-1-1-5-(Q3}—\n" +
                "\n" +
                "{J1}-5-{H5}-1-1-15{F2}-1-1—{G2}-1-1-2-{Q1}—\n" +
                "T123(H2}-11G-D-A-G-\n" +
                "T123{G2}-1-1-{G5}-{A3} –\n" +
                "G-{J2]-1-{H5)-1-1-15{F2}-1-1——{G2}-1-1-5-(Q3}—{J1}-5-{H5}-1-1-15{F2}-1-1—{G2}-1-1-2-{Q1}—T123(H2}-11G-D-A-G-\n" +
                "T123{G2}-1-1-{G5}-{A3} -\n" +
                "AT123{H2)-11G-D-A-G-3-{F1)-{G2}-1-1-{G2}-{A3}-\n" +
                "A-{GW}-Q-{HT)-Q-Q-TT{FW)-Q-Q—\n" +
                "{GW}-Q-Q-T-{GE}—\n" +
                "W-Q-T-Q-Q-QTW-Q-Q—w-QQQ-W-Q—\n" +
                "2-1-{H5}-1-1~5-{F2}-1-1—\n" +
                "{G2}-1-1-2-{G3}-A-G-A-\n" +
                "W-A-T-Q-2-G-5-Q-{58}-----{G1}-----{AGQE}----\n";
/*        String s="ET5(Q1}—T25{G3}-T2T{E1}T5(Q1}—{W1}-QU-JT—G-W[QW]QGS-\n" +
                "ADQY3-{AG1}—AFR1-5-ASGT1-5-AGE-W[EWQ]-\n" +
                "ADQY3-{A1]—AFR1WQ-SGT1WUJTG-\n" +
                "STS{G1}-{H2}-1-1-{H1}-{F3)-1-1-{F1)-{G2)-322-{G1)-(A3}-\n" +
                "A-{JT}-1-{H2}-1-1-{H2}32-1-1-{F1}-(G2}-3-2-{G1)-{A1}-\n" +
                "G-{JT}-{G1}-{H2}-1-1-(H2)32-1-1-F-{G1)-232-(G1)-{A3} –\n" +
                "A-{JT}-1-{H2}-1-1-{H3}-{F2}-1-1-F-{G2)-232-{G1)-{A1}—\n" +
                "2S{G1}-{H5)-1Q1-{H1}52A1Q1-F-{G2)-111-{G5}-{A3}-\n" +
                "AD{G1}-5-{H5)-1Q1-{(H1}52A1Q1-F-(G2)-111-{G2]-{A1}-\n" +
                "2S{G1}-{H5}-1Q1-{H1}52A1Q1-F-{G2}-111-{G5}-{A3} –\n" +
                "AD{G1}-5-{H5}-1Q1-{H1}52A1Q1-F-(G2}-111-(G2}-{A1}-\n" +
                "AD{G2]-1-{H5}-1Q1-{H5}52A1Q1-F-(G2}-1Q1-{G5}-{A3}-\n" +
                "AD{G2}-1-{H5}-1Q1-{H1}52A1Q1-F-{G2}-111-{G2]-{A1}-\n" +
                "AD3210{H1}-5-5-{H2}1{F1}-5-5-{F2}1{G1}-211-{G2}1{A1} -\n";*/

        char[] S = s.toCharArray();

        for (int i = 0; i < S.length; i++)
        {
            for (int k = 0; k < A.length; k++)
            {
                if (S[i] == A[k])
                {
                    S[i] = B[k];
                    break;
                }
            }
        }

        System.out.println(S);


    }

//    测试usermapper
    @Autowired
    UserMapper mapper;
    @Test
    public void test() {

        mapper.selectByPrimaryKey(1);
        System.out.println(mapper.selectByPrimaryKey(1));

    }

    @Autowired
    BookMapper mapper23;
    @Test
    public  void test2(){

        Book book= mapper23.selectByPrimaryKey(1);
        book.setSales(122);
        book.setStock(666);
        /*BookExample example=new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.*/

        System.out.println(mapper23.updateByPrimaryKey(book));
    }

//测试二级缓存
    @Autowired
    BookMapper mapper1;
    @Autowired
    BookMapper mapper2;
    @Test
    public void test1() throws IOException {
        Book book1 = mapper1.selectByPrimaryKey(1);
        System.out.println(book1);
        Book book2 = mapper2.selectByPrimaryKey(1);
        System.out.println(book2);
    }
}
