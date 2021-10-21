package com.hscrm.controller;

import com.hscrm.util.JsonUtil;
import com.hscrm.util.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/getUsername")
public class GetUsernameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = (String) req.getSession().getAttribute("username");
        //获取token，获取username
        String tokenString = (String)req.getAttribute("token");
        Token token = (Token)req.getServletContext().getAttribute(tokenString);
        String username = token.getUsername();

        JsonUtil<String> jsonUtil = new JsonUtil<>();
        String ok = jsonUtil.toString(100,"OK",username);
        PrintWriter out = resp.getWriter();
        out.write(ok);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
