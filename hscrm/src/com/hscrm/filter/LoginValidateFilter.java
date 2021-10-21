package com.hscrm.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.ResponseEntity;
import com.hscrm.util.Token;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/user/*")
public class LoginValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req0, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)req0;
        HttpServletResponse resp = (HttpServletResponse) res;

//        HttpSession session = req.getSession();
//        Object username = session.getAttribute("username");

        //通过token,判断是否登录
        //这里不需要通过TokenFilter绑定的请求对象里面的token获取，
        //这里允许token为null
        //况且，按照过滤器执行顺序，LoginValidateFilter在TokenFilter前面执行，这时候请求对象里面还没有绑定token。
        String tokenString = req.getHeader("token");

//        Token token = (Token) req.getServletContext().getAttribute(tokenString);
//当不登录直接请求别的接口时，token为null，tokenString为null
//再通过tokenString获取token对象，会报空指针，不过token是可以为null的，所以加个判断，如下：
        Token token = null;
        if (tokenString != null){
            token = (Token) req.getServletContext().getAttribute(tokenString);
        }

        //没有登录
        if (token == null || !token.getLogin()){
            //json
            JsonUtil<String> jsonUtil = new JsonUtil<>();
            String ok = jsonUtil.toString(1001,"用户登录认证失败","");
            PrintWriter out = resp.getWriter();
            out.write(ok);
            out.close();
        }else {
            //已登录，允许访问
            chain.doFilter(req,resp);
        }


//        if (username == null){
//            //没有登录
//            PrintWriter out = resp.getWriter();
//            //json
//            ResponseEntity<String> responseEntity = new ResponseEntity<>();
//            responseEntity.setCode(1001);
//            responseEntity.setMessage("用户登录认证失败");
//            responseEntity.setData("");
//
//            ObjectMapper mapper = new ObjectMapper();
//            String responseString = mapper.writeValueAsString(responseEntity);
//            out.write(responseString);
//            out.close();
//        }else {
//            //已登录，允许访问
//            chain.doFilter(req,resp);
//        }

    }

    @Override
    public void destroy() {

    }
}
