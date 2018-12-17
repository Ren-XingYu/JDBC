package x.y.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class JdbcTest {
    public static void main(String[] args){

        try {
            //1、注册驱动
            //DriverManager.registerDriver(new Driver());//会导致注册两次驱动，Driver里面有静态代码块
            //Class.forName("com.mysql.jdbc.Driver");//这样使用不会加载两次
            //2、建立连接
            //Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/store","root","root");
            Connection connection=JdbcUtil.getConnection();
            //3、创建Statement对象,操作sql
            Statement stat=connection.createStatement();
            //4、执行sql,得到结果集
            //String sql="";
            //ResultSet rs=stat.executeQuery(sql);
            System.out.println(connection);
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
