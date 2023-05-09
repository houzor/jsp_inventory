package com.houzer.servlet;

import com.houzer.pojo.Good;
import com.houzer.pojo.Supplier;
import com.houzer.pojo.User;
import com.houzer.service.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method=req.getParameter("method");
        System.out.println("Supplier的method现在是什么捏"+method);
        if(method.equals("ruku")) {
            this.ruku(req,resp);
        }else if(method.equals("query")){
            this.query(req,resp);   //执行query方法
        }else if(method.equals("querybyname")){
            this.queryByName(req,resp);
        }else if(method.equals("add")){
            this.add(req,resp);
        }else if(method.equals("querybyid")){
            this.queryById(req,resp);
        }else if(method.equals("update"))
        {
            this.update(req,resp);
        }else if (method.equals("delete")) {
            try {
                this.delete(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        SupplierService supplierService=new SupplierServiceImpl();
        int supplierId = Integer.parseInt(req.getParameter("supplierId"));
        //根据supplierId去查询good表中有没有这个供应商的商品
        GoodService goodService=new GoodServiceImpl();
        RecordService recordService=new RecordServiceImpl();
//        因为suppliersurvice里是没有goodsurvice对象的，所以我这里先调用了goodservice,有good不一定要record
        List<Good> goodArrayList =goodService.getBySupplierId(supplierId);
        Boolean deleteflag=false;
        if(goodArrayList.size()==0)
        {
            deleteflag=supplierService.deletesupplier(supplierId);
        }else {

//        遍历数组去找Good里面有没有Record，有的话先删Record，没有的话直接删Good
            for(Good good : goodArrayList){
//        先删record
                Boolean tempflag1=recordService.deleteByGoodId(good.getGoodId());
//        删除完record后再来删除商品才能删
                Boolean tempflag2=goodService.deletegood(good.getGoodId());
            }
//            删完后再来删供应商
            deleteflag=supplierService.deletesupplier(supplierId);
        }

        if(deleteflag==true)
        {
            resp.sendRedirect(req.getContextPath()+"/SupplierServlet?method=query");
        }
        else{
            req.setAttribute("error","删除");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        SupplierService supplierService=new SupplierServiceImpl();
        Supplier supplier=new Supplier();
        supplier.setSupplierId(Integer.parseInt(req.getParameter("supplierId")));//供应商号
        supplier.setSupplierName(req.getParameter("supplierName")); //供应商名称
        supplier.setContacts(req.getParameter("contacts"));//联系人
        supplier.setPhone(String.valueOf(req.getParameter("phone")));//手机号
        supplier.setAddress(req.getParameter("address"));//身份证
        Boolean flag=supplierService.updateSuppliers(supplier);
        if(flag==true)
        {
            resp.sendRedirect(req.getContextPath()+"/SupplierServlet?method=query");
        }
        else{
            req.setAttribute("error","修改");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierService SupplierService=new SupplierServiceImpl();
        int supplierId = Integer.parseInt(req.getParameter("supplierId"));
        Supplier supplier = SupplierService.getById(supplierId);
        req.setAttribute("supplier",supplier);
        //        获取供应商
        SupplierService supplierService=new SupplierServiceImpl();
        List<Supplier> supplierList= supplierService.getAllSuppliers();
        System.out.println(supplierList);

        req.setAttribute("supplierlist",supplierList);
        req.getRequestDispatcher("pages/supplierupdate.jsp").forward(req, resp);

    }

    public void queryByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierService supplierService=new SupplierServiceImpl();
        List<Supplier> supplierList=null;
        String supplierName=req.getParameter("supplierName");
        if(supplierName.equals(""))
        {
            supplierList=supplierService.getAllSuppliers();
        }else{
            supplierList=supplierService.getByName(supplierName);
        }
        req.setAttribute("supplierlist",supplierList);
        req.getRequestDispatcher("pages/supplierlist.jsp").forward(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierService supplierService=new SupplierServiceImpl();
        List<Supplier> supplierList= supplierService.getAllSuppliers();
        req.setAttribute("supplierlist",supplierList);
        req.getRequestDispatcher("pages/supplierlist.jsp").forward(req, resp);
    }
    public void ruku(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        这里查询全部的供应商，将供应商存进request里，以便在后续的入库操作中，我的section能读到
        SupplierService supplierService=new SupplierServiceImpl();
        List<Supplier> supplierList= supplierService.getAllSuppliers();
        System.out.println(supplierList);
        req.setAttribute("supplierslist",supplierList);
        req.getRequestDispatcher("pages/goodadd.jsp").forward(req, resp);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierService supplierService = new SupplierServiceImpl();
        Supplier supplier = new Supplier();

        supplier.setSupplierName(req.getParameter("supplierName")); //供应商名称
        supplier.setContacts(req.getParameter("contacts"));//联系人
        supplier.setPhone(req.getParameter("phone"));//  手机号
        supplier.setAddress(req.getParameter("address"));//  地址
        // 将得到的supplier传进去调用service层
        Boolean flag = supplierService.addSuppliers(supplier);
        if (flag == true) {
            resp.sendRedirect(req.getContextPath() + "/SupplierServlet?method=query");
        } else {
            req.setAttribute("error", "添加联系人");
            req.getRequestDispatcher("pages/error.jsp").forward(req, resp);
        }
    }
}


