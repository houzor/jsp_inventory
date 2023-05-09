package com.houzer.servlet;

import com.houzer.pojo.Attend;
import com.houzer.pojo.User;
import com.houzer.service.AttendService;
import com.houzer.service.AttendServiceImpl;
import com.houzer.service.UserService;
import com.houzer.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取登录界面login.jsp传来的用户名和密码
        int userid = Integer.parseInt(request.getParameter("userid"));
        String password = request.getParameter("password");

        //和数据库中的密码进行对比，调用两个业务层
        UserServiceImpl userService = new UserServiceImpl();
        AttendService attendService=new AttendServiceImpl();
        Attend attend=new Attend();
        User user = userService.login(userid, password);

        System.out.println(user);
        if (user != null) {//有这个人，可以登陆,判断用户类型
            //将用户的信息放到Session中；
            request.getSession().setAttribute("usersession", user);
            attend.setUserId(user.getUserId());
            attend.setAttendType("上班打卡");
            Date date = new Date();
            String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            attend.setAttendTime(nowtime);
            Boolean flag=attendService.addAttend(attend);
            if(flag==true)
            {
                if(user.getUserType().equals("1"))
                {
                    response.sendRedirect(request.getContextPath()+"/pages/index.jsp");
                }
                else if(user.getUserType().equals("2")) {
                    response.sendRedirect(request.getContextPath()+"/pages/userindex.jsp");
                }

            }else{
                request.setAttribute("error", "上班打卡");
                request.getRequestDispatcher("pages/error.jsp").forward(request, response);
            }


        } else {//查无此人，无法登陆
            //转发回登录界面，顺带提示，用户名或密码错误
            request.setAttribute("error", "用户名或者密码错误！");
//            注意服务器跳转，前面不用斜杠，我TM纳闷了半天
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
