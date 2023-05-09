package com.houzer.servlet;

import com.houzer.pojo.Record;
import com.houzer.pojo.Supplier;
import com.houzer.service.RecordService;
import com.houzer.service.RecordServiceImpl;
import com.houzer.service.SupplierService;
import com.houzer.service.SupplierServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

public class RecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method.equals("query")){
            this.query(request,response);
        }else if(method.equals("querybyname")){
            this.queryByName(request,response);
        }
    }

    private void queryByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecordService recordService=new RecordServiceImpl();
        List<Record> recordList=null;
        String recordName=request.getParameter("recordName");
        if(recordName.equals(""))
        {
            recordList=recordService.getAllRecords();
        }else{
            recordList=recordService.getByName(recordName);
        }
        request.setAttribute("recordlist",recordList);
        request.getRequestDispatcher("pages/recordlist.jsp").forward(request, response);
    }

    public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecordService recordService = new RecordServiceImpl();
        List<Record> recordList = recordService.getAllRecords();
        request.setAttribute("recordlist",recordList);
        request.getRequestDispatcher("pages/recordlist.jsp").forward(request,response);
    }
}
