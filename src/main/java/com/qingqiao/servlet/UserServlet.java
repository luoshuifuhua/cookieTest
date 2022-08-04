package com.qingqiao.servlet;

import com.alibaba.druid.util.StringUtils;
import com.mysql.cj.Session;
import com.qingqiao.entity.User;
import com.qingqiao.service.UserService;
import com.qingqiao.service.implement.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author H_H
 * @date 2022/08/01 18:46
 **/
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImp();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");

        String m = request.getParameter("m");

        if("list".equals(m)){
            list(request,response);
        }else if ("login".equals(m)){
            login(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始页码为1
        int pageNo = 1;
        //接收参数
        String page = request.getParameter("pageNo");
        if(page!=null){
            //页码赋值
            pageNo = Integer .parseInt(page);
        }
        //保存页码
        HttpSession session = request.getSession();
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("lastPage",userService.tolPage());
        //查询当前页码 的 user们
        List<User> userList = userService.queryAll(pageNo);
        request.setAttribute("list",userList);
        request.getRequestDispatcher("user/list.jsp").forward(request,response);

    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        User user = userService.login(username, password);

        HttpSession session = request.getSession();

        if (user != null) {
            if ("yes".equals(remember)) {
                //编码
                String username_encode = URLEncoder.encode(username, StandardCharsets.UTF_8);
                String password_encode = URLEncoder.encode(password, StandardCharsets.UTF_8);

                Cookie username_cookie = new Cookie("username", username_encode);
                Cookie password_cookie = new Cookie("password", password_encode);
                username_cookie.setMaxAge(60 * 60 * 24 * 7);
                password_cookie.setMaxAge(60 * 60 * 24 * 7);

                response.addCookie(username_cookie);
                response.addCookie(password_cookie);
            }
            //登陆成功, 重置o
            session.setAttribute("login_error", "");
            session.setAttribute("login_info", user);
            response.sendRedirect("user?m=list");
        } else {
            session.setAttribute("login_error", "用户名或密码错误");
            response.sendRedirect("login.jsp");
        }


    }
}
