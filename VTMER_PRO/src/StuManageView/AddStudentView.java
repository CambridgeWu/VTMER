package StuManageView;

import Information.Student;

import javax.swing.*;
import java.awt.*;

public class AddStudentView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel IDLable=new JLabel("ID:",JLabel.RIGHT);
    JTextField IDText=new JTextField();
    JLabel nameLable=new JLabel("姓名:",JLabel.RIGHT);
    JTextField nameText=new JTextField();
    JLabel classLable=new JLabel("班级:",JLabel.RIGHT);
    JTextField classText=new JTextField();
    JLabel majorLable=new JLabel("专业:",JLabel.RIGHT);
    JTextField majorText=new JTextField();
    JLabel courseLable=new JLabel("专业课:",JLabel.RIGHT);
    JTextField courseText=new JTextField();
    JLabel teacherLable=new JLabel("任课老师:",JLabel.RIGHT);
    JTextField teacherText=new JTextField();
    JLabel creditLable=new JLabel("学分:",JLabel.RIGHT);
    JTextField creditText=new JTextField();
    JButton addBtn=new JButton("添加");

    AddStudentViewHandler addStudentViewHandler;
    public AddStudentView(MainView mainView){
        super(mainView,"添加学生信息",true);

        addStudentViewHandler=new AddStudentViewHandler(this,mainView);
        IDLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(IDLable);
        IDText.setPreferredSize(new Dimension(200,30));
        jPanel.add(IDText);

        nameLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLable);
        nameText.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameText);

        classLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(classLable);
        classText.setPreferredSize(new Dimension(200,30));
        jPanel.add(classText);

        majorLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(majorLable);
        majorText.setPreferredSize(new Dimension(200,30));
        jPanel.add(majorText);

        courseLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(courseLable);
        courseText.setPreferredSize(new Dimension(200,30));
        jPanel.add(courseText);

        teacherLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(teacherLable);
        teacherText.setPreferredSize(new Dimension(200,30));
        jPanel.add(teacherText);

        creditLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(creditLable);
        creditText.setPreferredSize(new Dimension(200,30));
        jPanel.add(creditText);

        addBtn.addActionListener(addStudentViewHandler);
        jPanel.add(addBtn);

        Container contenPane=getContentPane();
        contenPane.add(jPanel);

        setSize(320,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public Student buildStudent(){
        Student student=new Student();
        student.setID(Integer.valueOf(IDText.getText()));
        student.setName(nameText.getText());
        student.setClass_name(classText.getText());
        student.setMajor(majorText.getText());
        student.setCourse(courseText.getText());
        student.setTeacer(teacherText.getText());
        student.setCredit(Integer.valueOf(creditText.getText()));
        return student;
    }
}
