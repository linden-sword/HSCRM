package com.hscrm.controller;

import com.hscrm.util.VerifyCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/VerifyCode")
public class VerifyCodeServlet   extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage img=new BufferedImage(200,60,BufferedImage.TYPE_INT_RGB);
        //chars保存验证码字符串
        String chars = VerifyCode.drawRandomImg(200, 60, img);

        //保存验证码到会话对象
//        HttpSession session = req.getSession();
//        session.setAttribute("verifyCode",chars);
        //保存验证码到上下文对象
        ServletContext context = req.getServletContext();
        context.setAttribute("verifyCode",chars);

        System.out.println("验证码："+chars);
        System.out.println("验证码from上下文："+context.getAttribute("verifyCode"));

        //输出验证码图片
        resp.setContentType("image/png");
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(img,"png",out);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
