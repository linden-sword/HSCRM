package com.hscrm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hscrm.dao.impl.CustomerDaoImpl;
import com.hscrm.dao.impl.EmpDaoImpl;
import com.hscrm.domain.Track;
import com.hscrm.service.EmpService;
import com.hscrm.service.TrackService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.service.impl.TrackServiceImpl;
import com.hscrm.util.JsonUtil;
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
import java.util.List;

@WebServlet("/user/TrackServlet")
public class TrackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数action
        String action = req.getParameter("action");
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

    TrackService trackService = new TrackServiceImpl();
    EmpService empService = new EmpServiceImpl();

    /**
     * 处理添加跟踪表请求
     * @param req
     * @param resp
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        ObjectMapper mapper = new ObjectMapper();
        //获取
        //通过下面的findE_id方法查找e_id
        Track track = new Track(0,new CustomerDaoImpl().findCustomerById(Integer.parseInt(req.getParameter("c_id"))),new EmpDaoImpl().findEmpById(findE_id(req)),req.getParameter("record"),req.getParameter("intention"));
        //处理
        int flag = trackService.addTrack(track);
        //响应
        if (flag > 0){
            //添加跟踪表成功
            responseEntity.setCode(1000);
            responseEntity.setMessage("OK");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }else {
            //添加跟踪表失败
            responseEntity.setCode(1004);
            responseEntity.setMessage("添加跟踪表失败");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }
        out.close();
    }

    /**
     * 处理删除跟踪表请求
     * @param req
     * @param resp
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        ObjectMapper mapper = new ObjectMapper();
        //获取
        String t_id = req.getParameter("t_id");
        //处理
        int flag = trackService.deleteTrack(Integer.parseInt(t_id));
        //响应
        if (flag > 0){
            //删除跟踪表成功
            responseEntity.setCode(1000);
            responseEntity.setMessage("OK");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }else {
            //删除跟踪表失败
            responseEntity.setCode(1004);
            responseEntity.setMessage("删除跟踪表失败");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }
        out.close();
    }

    /**
     * 处理修改跟踪表请求
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        ObjectMapper mapper = new ObjectMapper();
        //获取
        Track track = new Track(Integer.parseInt(req.getParameter("t_id")),new CustomerDaoImpl().findCustomerById(Integer.parseInt(req.getParameter("c_id"))),null,req.getParameter("record"),req.getParameter("intention"));
        //处理
        int flag = trackService.updateTrack(track);
        //响应
        if (flag > 0){
            //修改跟踪表成功
            responseEntity.setCode(1000);
            responseEntity.setMessage("OK");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }else {
            //修改跟踪表失败
            responseEntity.setCode(1004);
            responseEntity.setMessage("修改跟踪表失败");
            String responseString = mapper.writeValueAsString(responseEntity);
            out.write(responseString);
        }
        out.close();
    }

    /**
     * 处理查询所有跟踪表请求
     * @param req
     * @param resp
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理
        List<Track> trackList = trackService.findAllTrack(String.valueOf(findE_id(req)));
        //响应
        JsonUtil<List<Track>> jsonUtil = new JsonUtil<>();
        String ok = jsonUtil.toString(100,"OK",trackList);
        PrintWriter out = resp.getWriter();
        out.write(ok);
        out.close();
    }

    /**
     * 处理通过id查询跟踪表请求
     * @param req
     * @param resp
     */
    public void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取
        String t_id = req.getParameter("t_id");
        //处理
        Track track = trackService.findTrackById(Integer.parseInt(t_id));
        //响应
        JsonUtil<Track> jsonUtil = new JsonUtil<>();
        String ok = jsonUtil.toString(100,"OK",track);
        PrintWriter out = resp.getWriter();
        out.write(ok);
        out.close();
    }

    public int findE_id(HttpServletRequest req){
        //在上下文对象里面获取token，然后在token对象里面获取username，然后通过empService里面的方法获取e_id
        ServletContext context = req.getServletContext();
        Token token = (Token) context.getAttribute((String) req.getAttribute("token"));
        String username = token.getUsername();
        return empService.getEmpIdByUsername(username);
    }
}
