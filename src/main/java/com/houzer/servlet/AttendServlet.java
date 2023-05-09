package com.houzer.servlet;

import com.houzer.pojo.Attend;
import com.houzer.pojo.Good;
import com.houzer.pojo.Supplier;
import com.houzer.pojo.User;
import com.houzer.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AttendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method.equals("" +
                "query")){
            this.query(request,response);   //执行query方法
        }else if(method.equals("exit")){
            this.exit(request,response);   //执行query方法
        }else if(method.equals("querybyId"))
        {
            this.queryById(request,response);
        }
    }

    private void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AttendService attendService=new AttendServiceImpl();
        List<Attend> attendList=null;
        String attendId=request.getParameter("userId");
        if(attendId.equals(""))
        {
            attendList=attendService.getAllAttend();
        }else{

            attendList=attendService.getById(attendId);
        }
        request.setAttribute("attendlist",attendList);
        request.getRequestDispatcher("pages/attendlist.jsp").forward(request, response);
    }

    private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        AttendService attendService=new AttendServiceImpl();
        Attend attend=new Attend();
        User usersession = (User) request.getSession().getAttribute("usersession");
        attend.setUserId(usersession.getUserId());
            attend.setAttendType("下班打卡");
            Date date = new Date();
            String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            attend.setAttendTime(nowtime);
            Boolean flag=attendService.addAttend(attend);
            if(flag==true)
            {
                request.getSession().setAttribute("usersession",null);
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }else{
                request.setAttribute("error", "下班打卡");
                request.getRequestDispatcher("pages/error.jsp").forward(request, response);
            }
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AttendService attendService=new AttendServiceImpl();
        List<Attend> attendList= attendService.getAllAttend();
        req.setAttribute("attendlist",attendList);
        System.out.println(attendList);
        req.getRequestDispatcher("pages/attendlist.jsp").forward(req, resp);
    }
}
