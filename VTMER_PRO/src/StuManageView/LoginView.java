package StuManageView;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    JLabel nameLable=new JLabel("学生成绩管理系统",JLabel.CENTER);

    //面板采用弹簧布置
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout);

    JLabel userNameLable=new JLabel("用户名:");
    JTextField userText=new JTextField();
    JLabel pwdLable=new JLabel("密码:");
    JTextField pwdField=new JPasswordField();
    JButton loginBtn=new JButton("登录");
    JButton registerBtn=new JButton("注册");

    LoginHandler loginHandler;

    //记得导入托盘
    public LoginView(){
        super("学生成绩管理系统");

        loginHandler=new LoginHandler(this);

        Container contenPane=getContentPane();

        nameLable.setFont(new Font("华文行楷",Font.PLAIN,40));

        Font centerFont=new Font("楷体",Font.PLAIN,20);
        userNameLable.setFont(centerFont);
        userText.setPreferredSize(new Dimension(200,30));
        pwdLable.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        registerBtn.setFont(centerFont);
        //把组件加入面板
        centerPanel.add(userNameLable);
        centerPanel.add(userText);
        centerPanel.add(pwdLable);
        centerPanel.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        centerPanel.add(loginBtn);
        registerBtn.addActionListener(loginHandler);
        centerPanel.add(registerBtn);

        //弹簧布局
        //布局userNameLable
        Spring childWidth=Spring.sum(Spring.sum(Spring.width(userNameLable),Spring.width(userText)),Spring.constant(20));
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLable,-offsetX, SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLable,20,SpringLayout.NORTH,centerPanel);
        //布局userText
        springLayout.putConstraint(SpringLayout.WEST,userText,20,SpringLayout.EAST,userNameLable);
        springLayout.putConstraint(SpringLayout.NORTH,userText,0,SpringLayout.NORTH,userNameLable);
        //布局pwdLable
        springLayout.putConstraint(SpringLayout.EAST,pwdLable,0,SpringLayout.EAST,userNameLable);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLable,20,SpringLayout.SOUTH,userNameLable);
        //布局pwdField
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLable);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLable);
        //布局LoginBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,50,SpringLayout.WEST,pwdLable);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdLable);
        //布局resetBtn
        springLayout.putConstraint(SpringLayout.WEST,registerBtn,50,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,registerBtn,0,SpringLayout.NORTH,loginBtn);


        contenPane.add(nameLable,BorderLayout.NORTH);
        contenPane.add(centerPanel,BorderLayout.CENTER);


        setSize(400,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
