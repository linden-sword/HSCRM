package com.hscrm.controller;

import com.hscrm.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/loginOut")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        session.invalidate();

        //退出登录：移除token对象(规范操作应该是更改token对象的登录状态属性值)
//        String tokenString = req.getHeader("token");
        //从请求对象里面获取token值
        String tokenString = (String)req.getAttribute("token");

        req.getServletContext().removeAttribute(tokenString);

        JsonUtil<Integer> jsonUtil = new JsonUtil<>();
        String ok = jsonUtil.toString(1000,"OK",1);
        PrintWriter out = resp.getWriter();
        out.write(ok);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
