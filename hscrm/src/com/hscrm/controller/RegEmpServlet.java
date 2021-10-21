package com.hscrm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hscrm.domain.Emp;
import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.MD5Util;
import com.hscrm.util.ResponseEntity;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/reg")
public class RegEmpServlet extends HttpServlet {
    private EmpService empService = new EmpServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //响应:json
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        boolean validateFlag = true;
        //获取请求参数
        String e_name = req.getParameter("e_name");
        String e_sex = req.getParameter("e_sex");
        String e_tel = req.getParameter("e_tel");
        String username = req.getParameter("username");
        String passwd = req.getParameter("passwd");
        String verifyCode = req.getParameter("verifyCode");
        System.out.println(verifyCode);
        //数据校验
        //==#验证码
//        HttpSession session = req.getSession();
//        String verifyCodeInSession = (String) session.getAttribute("verifyCode");
        //获取上下文对象中的验证码
        ServletContext context = req.getServletContext();
        String verifyCodeContext = (String) context.getAttribute("verifyCode");
        System.out.println("验证码from上下文inRegServlet："+verifyCodeContext);
        //校验验证码
        if (verifyCode == null || verifyCode.trim().equals("") || !verifyCode.equalsIgnoreCase(verifyCodeContext)){
            //验证码错误
            JsonUtil<String> jsonUtil = new JsonUtil<>();
            String responseString = jsonUtil.toString(1006,"验证码错误","");
            out.write(responseString);
            out.close();
            return;
        }
        //#姓名
        //##必填项
//        if (e_name == null||e_name.trim().equals("")){
//            validateFlag = false;
//        }else {
//            //##格式
//            String e_name_r = "^[\u4e00-\u9fa5]{2,4}$";
//            boolean is_e_name = Pattern.matches(e_name_r,e_name);
//            if (!is_e_name){
//                validateFlag = false;
//            }
//        }
//        //#性别
//        if (e_sex != null && e_sex.trim().length() > 0){
//            if (!(e_sex.trim().equals("男")||e_sex.trim().equals("女"))){
//                validateFlag = false;
//            }
//        }
//        if (!validateFlag){
//            responseEntity.setCode(1003);
//            responseEntity.setMessage("参数格式错误");
//            responseEntity.setData("");
//            //响应:json
//            String responseString = mapper.writeValueAsString(responseEntity);
//            out.write(responseString);
//            out.close();
//            return;
//        }
        //#判断用户名的唯一性
        int unique = empService.usernameUnique(username);
        if (unique != 0){
            responseEntity.setCode(1002);
            responseEntity.setMessage("用户名不唯一");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
            out.close();
            return;
        }
        //注册
        Emp emp = new Emp(0,e_name,e_sex,e_tel,username, MD5Util.toMd5(passwd));
        int flag = empService.register(emp);
        System.out.println("regFlag:"+flag);
        //响应:json
        if (flag > 0){
            //注册成功
            responseEntity.setCode(1000);
            responseEntity.setMessage("OK");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }else {
            //注册失败
            responseEntity.setCode(1004);
            responseEntity.setMessage("注册失败");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
