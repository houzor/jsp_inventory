package com.houzer.servlet;

import com.houzer.pojo.Good;
import com.houzer.pojo.Record;
import com.houzer.pojo.Supplier;
import com.houzer.pojo.User;
import com.houzer.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String goodName=req.getParameter("goodName");
        System.out.println("goodservlet的method现在是什么捏"+method);

        if(method.equals("query")){
                this.query(req,resp);   //执行query方法
        }
        else if(method.equals("querybyname")){
               this.queryByName(req, resp);
        }
        else if(method.equals("querybyid")){
            this.queryById(req,resp);
        }
        else if(method.equals("add")){
            this.add(req,resp);
        }else if(method.equals("update"))
        {
            this.update(req,resp);
        } else if (method.equals("delete")) {
            this.delete(req,resp);
        }else if(method.equals("out")){
            this.out(req,resp);
        }
    }

    private void out(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User usersession= (User) req.getSession().getAttribute("usersession");
        RecordService recordService=new RecordServiceImpl();
        int outAmount = Integer.parseInt(req.getParameter("amount"));
        String outId = req.getParameter("goodId");
//        要先去查询库里面的有多少库存
        GoodService goodService=new GoodServiceImpl();
        Good good = goodService.getById(outId);

        int finalAmount=good.getAmount()-outAmount;
        System.out.println(finalAmount);
        if(finalAmount<0){
            req.setAttribute("error","出库");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }else if(finalAmount==0){
//            直接调用删除操作，这里并不用记录出入库操作，因为record表依赖good表，good表是主表，，删除good表项必须删除record表的记录才能删除。good表中不存在这个项，record表中也不能插入
            this.delete(req, resp);
        }else if(finalAmount>0){
//            调用修改操作
            Boolean flag=goodService.updateAmount(outId,finalAmount);
            if(flag==true)
            {
                //这里要调用一下recordservice记录出库操作
                Record record=new Record();
                record.setRecordType("出库");
                record.setUserId(usersession.getUserId());
                record.setRecordName(usersession.getUserName());
                Date date = new Date();
                String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                record.setRecordTime(nowtime);
//                操作的出库数量而不是最终数量
                record.setAmount(outAmount);
                record.setGoodId(good.getGoodId());
                Boolean recordflag=recordService.addRecord(record);
                if(recordflag==true)
                {
                    resp.sendRedirect(req.getContextPath()+"/GoodServlet?method=query");
                }else {
                    req.setAttribute("error","出库");
                    req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
                }
            }
            else{
                req.setAttribute("error","出库");
                req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
            }
        }

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        GoodService goodService=new GoodServiceImpl();
        RecordService recordService=new RecordServiceImpl();
        String goodId = req.getParameter("goodId");
//        先删record
        Boolean flagrecord=recordService.deleteByGoodId(goodId);
//        删除完record后再来删除商品才能删
        Boolean flag=goodService.deletegood(goodId);
        if(flag==true)
        {
            resp.sendRedirect(req.getContextPath()+"/GoodServlet?method=query");
        }
        else{
            req.setAttribute("error","删除");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        GoodService goodService=new GoodServiceImpl();
        Good good=new Good();
        good.setGoodId(req.getParameter("goodId"));
        good.setGoodName(req.getParameter("goodName")); //名字
        good.setGoodPrice(Double.parseDouble(req.getParameter("goodPrice")));//进价
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//这里需要将传进来的日期字符串转一下生产时间
        try {
            good.setCreateDate(sdf.parse(req.getParameter("createDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        good.setAmount(Integer.parseInt(req.getParameter("amount")));//数量
        good.setLife(Integer.parseInt(req.getParameter("life")));//保质期
        good.setSupplierId(Integer.parseInt(req.getParameter("supplierId")));//供应商id
//        将得到的good传进去调用service层
        Boolean flag=goodService.updateGoods(good);
        if(flag==true)
        {
            resp.sendRedirect(req.getContextPath()+"/GoodServlet?method=query");
        }
        else{
            req.setAttribute("error","修改");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }

    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodService goodService=new GoodServiceImpl();
        String goodId = req.getParameter("goodId");
        Good good = goodService.getById(goodId);
        req.setAttribute("good",good);
        //        获取供应商
        SupplierService supplierService=new SupplierServiceImpl();
        List<Supplier> supplierList= supplierService.getAllSuppliers();
        System.out.println(supplierList);

        req.setAttribute("supplierlist",supplierList);
        req.getRequestDispatcher("pages/goodupdate.jsp").forward(req, resp);

    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodService goodService=new GoodServiceImpl();
        List<Good> goodList= goodService.getAllGoods();
        req.setAttribute("goodlist",goodList);
        req.getRequestDispatcher("pages/goodlist.jsp").forward(req, resp);
    }
    public void queryByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodService goodService=new GoodServiceImpl();
        List<Good> goodList=null;
        String goodName=req.getParameter("goodName");
        if(goodName.equals(""))
        {
            goodList=goodService.getAllGoods();
        }else{
            goodList=goodService.getByName(goodName);
        }
        req.setAttribute("goodlist",goodList);
        req.getRequestDispatcher("pages/goodlist.jsp").forward(req, resp);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodService goodService=new GoodServiceImpl();
        RecordService recordService=new RecordServiceImpl();
        HttpSession session = req.getSession();//获取一下session记录操作用户id
        User usersession = (User) session.getAttribute("usersession");
        System.out.println(usersession.toString());
        Good good=new Good();
        good.setGoodId(req.getParameter("goodId"));
        good.setGoodName(req.getParameter("goodName")); //名字
        good.setGoodPrice(Double.parseDouble(req.getParameter("goodPrice")));//进价
//        这里需要将传进来的日期字符串转一下
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//生产时间
        try {
            good.setCreateDate(sdf.parse(req.getParameter("createDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        good.setAmount(Integer.parseInt(req.getParameter("amount")));
//        保质期
        good.setLife(Integer.parseInt(req.getParameter("life")));
//        供应商id
        good.setSupplierId(Integer.parseInt(req.getParameter("supplierId")));
//        将得到的good传进去调用service层
        Boolean flag=goodService.addGoods(good);
        if(flag==true)
        {
//            这里要调用一下recordservice记录入库操作
            Record record=new Record();
            record.setRecordType("入库");
            record.setUserId(usersession.getUserId());
            record.setRecordName(usersession.getUserName());
            Date date = new Date();
            String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            record.setRecordTime(nowtime);
            record.setAmount(Integer.parseInt(req.getParameter("amount")));
            record.setGoodId(good.getGoodId());
            Boolean recordflag=recordService.addRecord(record);
            if(recordflag==true)
            {
                resp.sendRedirect(req.getContextPath()+"/GoodServlet?method=query");
            }else {
                req.setAttribute("error","入库");
                req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
            }

        }
        else{
            req.setAttribute("error","入库");
            req.getRequestDispatcher("pages/error.jsp").forward(req,resp);
        }

    }

}
