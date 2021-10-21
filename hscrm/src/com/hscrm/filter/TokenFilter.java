package com.hscrm.filter;

import com.hscrm.util.Token;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //会话跟踪和验证
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        ServletContext context = req.getServletContext();

        //获取请求头中的token字符串
        String tokenString = req.getHeader("token");
        String respToken = "";

        //没有token（第一次请求）
        if (tokenString == null) {
            //生成token
            Token token = new Token();
            token.setUuid(UUID.randomUUID().toString());
            context.setAttribute(token.getUuid(), token);

            respToken = token.getUuid();
        } else {
            //验证token是否过期
            Token token = (Token) context.getAttribute(tokenString);

            if (token == null) {
                //重新生成token（新会话）
                token = new Token();
                token.setUuid(UUID.randomUUID().toString());

                context.setAttribute(token.getUuid(), token);
            }

            respToken = token.getUuid();
        }
        //把token绑定到请求对象里面，后面loginServlet利用通过请求对象获取。
        req.setAttribute("token",respToken);

        resp.setHeader("token", respToken);

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
