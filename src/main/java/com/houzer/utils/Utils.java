package com.houzer.utils;

import java.sql.*;

public class Utils {
    public static Connection con = null;//定义连接
    public static Statement st = null;//定义statement
    public ResultSet result = null;//定义结果集
    public static String driver;//定义驱动
    public static String url;//定义URL
    public static String user;//定义用户名
    public static String password;//定义密码

    //建立与数据库连接的函数
    public  Connection getConn(){
        try {

            driver="com.mysql.jdbc.Driver";
            url="jdbc:mysql://localhost:3306/jsp_inventory?useSSL=false&serverTimezone=Asia/Shanghai";
            user="root";
            password="root";

            Class.forName(driver);

            con = DriverManager.getConnection(url,user,password);//获取数据库连接
            System.out.println("-------数据库连接成功------");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return this.con;
    }



    // 执行数据库查询并返回查询结果
    public  ResultSet query(String sql)
    {
        this.con=this.getConn();
        try 	{
            st = con.createStatement();//获取Statement
            result = st.executeQuery(sql);//执行查询，返回结果集
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }


    //执行数据库更新，包含增删改
    public int update(String sql)
    {
        int i=0;
        this.con=this.getConn();
        try 	{
            st = con.createStatement();//获取Statement
            i=st.executeUpdate(sql);//执行查询，返回结果集
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }



    //关闭数据库连接
    public void close(){
        try{
            if (result != null)result.close();
            if (st != null)st.close();
            if (con != null)con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
