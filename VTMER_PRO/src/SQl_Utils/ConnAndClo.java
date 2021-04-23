package SQl_Utils;

import Information.Contans;

import java.sql.*;

public class ConnAndClo {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;


    //获取连接
    public static Connection getConnection() {
        try {
            Class.forName(Contans.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn= DriverManager.getConnection(Contans.ur1,Contans.user,Contans.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }


    //释放资源1(输入对应参数)，根据参数不同选择不同的方法
    public static void close(Statement sta,Connection conn){
        if(sta!=null) {
            try {
                sta.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    //释放资源2
    public static void close(ResultSet rs,Statement sta, Connection conn){
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(sta!=null) {
            try {
                sta.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
