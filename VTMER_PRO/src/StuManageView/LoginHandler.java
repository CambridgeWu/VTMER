package StuManageView;

import SQl_Utils.ConnAndClo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHandler implements ActionListener {

    private LoginView loginView;
    public LoginHandler(LoginView loginView){
        this.loginView=loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton =(JButton) e.getSource();
        String text=jButton.getText();
        if("注册".equals(text)){
            RegisterView registerView=new RegisterView();
        }else if("登录".equals(text)){
            String Username = null;
            String Password = null;

            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                //获取数据库连接并且将用户名和密码传递过来
                conn = ConnAndClo.getConnection();
                String sql = "select * from login where username=? and passwords=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,loginView.userText.getText());
                ps.setString(2,loginView.pwdField.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    Username = rs.getString("username");
                    Password = rs.getString("passwords");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                ConnAndClo.close(rs, ps, conn);
            }

            //如果登录成功
            if (loginView.userText.getText().equals(Username) && loginView.pwdField.getText().equals(Password)) {
                JOptionPane.showMessageDialog(null, "密码正确", "登录提示框", JOptionPane.INFORMATION_MESSAGE);
                new MainView();
                loginView.dispose();
            } else {//登录失败
                JOptionPane.showMessageDialog(null, "密码错误，请重新登录", "登录提示框", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
