package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBUtil {
    //数据库URL和账号密码
            private static final String URL="jdbc:mysql://localhost:3306/payiqing?serverTimezone=UTC&characterEncoding=utf-8";
            private static final String UNAME="root";
            private static final String UPWD="root";
            public static PreparedStatement  pstmt=null;
            public static ResultSet  rs = null;
            public static Connection  connection=null;
            
            
     //增删改
     public static boolean executeUpdate(String sql,Object [] params) {
         boolean flag = false;
         try {
             
         //a.导入驱动，加载具体的驱动类
         Class.forName("com.mysql.cj.jdbc.Driver");
         //b.与数据库建立连接
         connection = DriverManager.getConnection(URL,UNAME,UPWD);
         
         pstmt = connection.prepareStatement(sql);
         for(int i=0;i<params.length;i++) {
             pstmt.setObject(i+1, params[i]);
         }
         int count=pstmt.executeUpdate();//返回值表示，增删改几条数据
         //处理结果
         if(count>0)
         {
             System.out.println("操作成功！！！");
             flag=true;
         }
         
         }catch(ClassNotFoundException e) {
             e.printStackTrace();
         }catch(SQLException e) {
             e.printStackTrace();
         }catch(Exception e){
             e.printStackTrace();
         }finally {
             try {
                 //先开的后关，后开的先关
             if(pstmt!=null)pstmt.close();
             if(connection !=null)connection.close();
             }catch(SQLException e) {
                 e.printStackTrace();
             }finally {
                 
             }
         }
        return flag;
     }
    //查
     public static ResultSet executeQuery(String sql,Object [] params) {
         
                 try {
                     
                 //a.导入驱动，加载具体的驱动类
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 //b.与数据库建立连接
                 connection = DriverManager.getConnection(URL,UNAME,UPWD);
                 
                 pstmt = connection.prepareStatement(sql);
                 if(params!=null) {
                 for(int i=0;i<params.length;i++) {
                     pstmt.setObject(i+1, params[i]);
                 }
                 }
                  rs = pstmt.executeQuery();
                  return rs;
                 }catch(ClassNotFoundException e) {
                     e.printStackTrace();
                     return null;
                 }catch(SQLException e) {
                     e.printStackTrace();
                     return null;
                 }catch(Exception e){
                     e.printStackTrace();
                     return null;
                 }
             
        }
}

