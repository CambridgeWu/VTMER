package StuManageView;

import SQl_Utils.ConnAndClo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterView{
    private static final int width=180;
    private static final int height=240;

    LoginView loginView;
    public RegisterView(LoginView loginView){
        this.loginView=loginView;
    }

    public RegisterView(){
        JDialog jd=new JDialog(loginView,"注册");
        jd.setSize(width,height);
        jd.setLocationRelativeTo(null);
        jd.setLayout(new FlowLayout());

        JLabel registerLable = new JLabel("     注册      ");
        registerLable.setFont(new Font("楷体", Font.PLAIN, 20));
        JLabel usernameLable = new JLabel("账号:");
        JTextField usernameText = new JTextField("", 10);
        JLabel passwordLable = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField("", 10);
        JButton confirmBtn = new JButton("确认");

        jd.add(registerLable);
        jd.add(usernameLable);
        jd.add(usernameText);
        jd.add(passwordLable);
        jd.add(passwordField);
        jd.add(confirmBtn);

        passwordField.setEchoChar('*');
        confirmBtn.setSize(40, 20);

        //确认注册--》将注册信息存进数据库
        confirmBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //将注册信息存进数据库中
                Connection conn1 = null;
                PreparedStatement pst = null;

                try {
                    //如果用户输入为空，则提示用户
                    if (usernameText.getText().equals("") || passwordField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "用户名或密码不能为空", "注册提示框", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        //将注册信息存进数据库
                        conn1 = ConnAndClo.getConnection();
                        String sql = "insert into login values(?,?)";
                        pst = conn1.prepareStatement(sql);
                        pst.setString(1, usernameText.getText());
                        pst.setString(2, passwordField.getText());
                        int count = pst.executeUpdate();
                        if (count != 0) {
                            JOptionPane.showMessageDialog(null, "注册成功", "注册提示框", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "注册失败，请重新注册", "注册提示框", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    ConnAndClo.close(pst, conn1);
                }
            }
        });
        jd.setResizable(false);
        jd.setVisible(true);
        jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
