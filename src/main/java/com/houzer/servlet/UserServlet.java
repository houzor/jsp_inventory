package com.houzer.servlet;

import com.houzer.pojo.Good;
import com.houzer.pojo.Supplier;
import com.houzer.pojo.User;
import com.houzer.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        System.out.println("Supplier的method现在是什么捏" + method);
        if(method.equals("update"))
        {
            this.update(req,resp);
        }else if(method.equals("infoupdate")){
            this.updateinfo(req, resp);
        }
        else if(method.equals("query")){
            this.query(req,resp);
        }else if (method.equals("querybyname")) {
            this.queryByName(req,resp);
        } else if (method.equals("querybyid")) {
            this.queryById(req,resp);
        }else if (method.equals("add")) {
            this.addUser(req,resp);
        }else if (method.equals("delete")) {
            try {
                this.delete(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService userService=new UserServiceImpl();
        int userId = Integer.parseInt(request.getParameter("userId"));
        AttendService attendService=new AttendServiceImpl();
        RecordService recordService=new RecordServiceImpl();
        Boolean ishaveattend=attendService.ishaveAttend(userId);
        Boolean ishaverecord=recordService.isHaveRecord(userId);
//        不仅要判断有没有考勤记录，还要判断有没有操作记录
        Boolean flag=userService.deleteUser(userId,ishaveattend,ishaverecord);
        if(flag==true){
            response.sendRedirect(request.getContextPath()+"/UserServlet?method=query");
        }
        else {
            request.setAttribute("error","删除用户");
            request.getRequestDispatcher("page/error.jsp").forward(request,response);
        }
    }
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUserId(Integer.parseInt(req.getParameter("userId")));
        user.setUserName(req.getParameter("userName"));
        user.setUserPwd(req.getParameter("userPwd"));
        user.setUserPhone(req.getParameter("userPhone"));
        user.setUserType(req.getParameter("userType"));
        System.out.println(req.getParameter("userType"));
        boolean flag = userService.addUser(user);
        if(flag==true){
            resp.sendRedirect(req.getContextPath()+"/UserServlet?method=query");
        }
        else {
            req.setAttribute("error","添加用户");
            req.getRequestDispatcher("page/error.jsp").forward(req,resp);
        }
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService=new UserServiceImpl();
        int userId=Integer.parseInt(req.getParameter("userId"));
        User user = userService.getByUserId(userId);
        System.out.println(user);
        req.setAttribute("user",user);
        req.getRequestDispatcher("pages/userupdate.jsp").forward(req,resp);
    }

    private void queryByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        List<User> userList=null;
        String userName=req.getParameter("userName");
        if(userName.equals("")){
            userList=userService.getAllUsers();
        }else {
            userList=userService.getByUserName(userName);
        }
        req.setAttribute("userlist",userList);
        req.getRequestDispatcher("pages/userlist.jsp").forward(req,resp);
    }

    public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getAllUsers();
        request.setAttribute("userlist",userList);
        request.getRequestDispatcher("pages/userlist.jsp").forward(request,response);
    }

    private void updateinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserService userService=new UserServiceImpl();
        User user = new User();
        user.setUserId(Integer.parseInt(req.getParameter("userId")));
        user.setUserName(req.getParameter("userName"));
        user.setUserPwd(req.getParameter("userPwd"));
        user.setUserPhone(req.getParameter("userPhone"));
        user.setUserType(req.getParameter("userType"));
        boolean flag=userService.updateUser(user);
        if (flag==true){
            req.getSession().setAttribute("usersession",null);
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }
        else {
            req.setAttribute("error","修改用户");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }
    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserService userService=new UserServiceImpl();
        User user = new User();
        user.setUserId(Integer.parseInt(req.getParameter("userId")));
        user.setUserName(req.getParameter("userName"));
        user.setUserPwd(req.getParameter("userPwd"));
        user.setUserPhone(req.getParameter("userPhone"));
        user.setUserType(req.getParameter("userType"));
        boolean flag=userService.updateUser(user);
        if (flag==true){
//            这里要更新一下sessino的信息
            User sessionuser= (User) req.getSession().getAttribute("usersession");
            if(sessionuser.getUserId()==user.getUserId())
            {
                req.getSession().setAttribute("usersession",user);
            }
            resp.sendRedirect(req.getContextPath()+"/UserServlet?method=query");
        }
        else {
            req.setAttribute("error","修改用户");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }
    }


}
