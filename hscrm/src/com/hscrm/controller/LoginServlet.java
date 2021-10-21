package com.hscrm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.util.MD5Util;
import com.hscrm.util.ResponseEntity;
import com.hscrm.util.Token;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final EmpService empService = new EmpServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username =req.getParameter("userName");
        String passwd = req.getParameter("passwd");
        //数据校验

        //登录验证
        int flag = empService.login(username, MD5Util.toMd5(passwd));
        System.out.println("loginFlag:"+flag);

        //==把username保存到上下文对象中
        ServletContext context = req.getServletContext();
        if (flag == 2){
//            String tokenString = req.getHeader("token");这个获取到的是过期的token，不对
            //从请求对象里面获取token值
            String tokenString = (String)req.getAttribute("token");

            Token token = (Token) context.getAttribute(tokenString);
            token.setLogin(true);
            token.setUsername(username);

            //context.setAttribute(tokenString,token);直接对token对象操作的，不需要再设置token
        }

        //响应:json
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(1000);
        responseEntity.setMessage("ok");
        responseEntity.setData(flag);

        ObjectMapper mapper = new ObjectMapper();
        String responseString = mapper.writeValueAsString(responseEntity);

        PrintWriter out = resp.getWriter();
        out.write(responseString);
        System.out.println(responseString);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
