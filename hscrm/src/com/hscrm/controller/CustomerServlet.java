package com.hscrm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hscrm.domain.Customer;
import com.hscrm.service.CustomerService;
import com.hscrm.service.impl.CustomerServiceImpl;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.ResponseEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数action
        String action = req.getParameter("action");
        System.out.println(action);
        //选择处理方法
        switch (action){
            case "add":
                add(req,resp);
                break;
            case "delete":
                delete(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            case "findAll":
                findAll(req,resp);
                break;
            case "find":
                find(req,resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    CustomerService customerService = new CustomerServiceImpl();

    /**
     * 处理添加客户请求
     * @param req
     * @param resp
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        ObjectMapper mapper = new ObjectMapper();
        //获取
        Customer customer = new Customer(0,req.getParameter("c_name"),req.getParameter("c_sex"),req.getParameter("c_tel"),req.getParameter("c_job"),req.getParameter("c_company"));
        //处理
        int flag = customerService.addCustomer(customer);
        //响应
        if (flag > 0){
            //添加客户成功
            responseEntity.setCode(1000);
            responseEntity.setMessage("OK");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }else {
            //添加客户失败
            responseEntity.setCode(1004);
            responseEntity.setMessage("添加客户失败");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }
        out.close();
    }

    /**
     * 处理删除客户请求
     * @param req
     * @param resp
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        ObjectMapper mapper = new ObjectMapper();
        //获取
        String c_id = req.getParameter("c_id");
        //处理
        int flag = customerService.deleteCustomer(Integer.parseInt(c_id));
        //响应
        if (flag > 0){
            //删除客户成功
            responseEntity.setCode(1000);
            responseEntity.setMessage("OK");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }else {
            //删除客户失败
            responseEntity.setCode(1004);
            responseEntity.setMessage("删除客户失败");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }
        out.close();
    }

    /**
     * 处理修改客户请求
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        ObjectMapper mapper = new ObjectMapper();
        //获取
        Customer customer = new Customer(Integer.parseInt(req.getParameter("c_id")),req.getParameter("c_name"),req.getParameter("c_sex"),req.getParameter("c_tel"),req.getParameter("c_job"),req.getParameter("c_company"));
        //处理
        int flag = customerService.updateCustomer(customer);
        //响应
        if (flag > 0){
            //修改客户成功
            responseEntity.setCode(1000);
            responseEntity.setMessage("OK");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }else {
            //修改客户失败
            responseEntity.setCode(1004);
            responseEntity.setMessage("修改客户失败");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }
        out.close();
    }

    /**
     * 处理查询所有客户请求
     * @param req
     * @param resp
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理
        List<Customer> customerList = customerService.findAllCustomer();
        //响应
        JsonUtil<List<Customer>> jsonUtil = new JsonUtil<>();
        String ok = jsonUtil.toString(100,"OK",customerList);
        PrintWriter out = resp.getWriter();
        out.write(ok);
        out.close();
    }

    /**
     * 处理通过c_id查询客户请求
     * @param req
     * @param resp
     */
    public void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取
        String c_id = req.getParameter("c_id");
        //处理
        Customer customer = customerService.findCustomerById(c_id);
        //响应
        JsonUtil<Customer> jsonUtil = new JsonUtil<>();
        String ok = jsonUtil.toString(1000,"OK",customer);
        PrintWriter out = resp.getWriter();
        out.write(ok);
        out.close();
    }
}