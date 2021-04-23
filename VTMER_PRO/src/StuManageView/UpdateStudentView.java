package StuManageView;

import Information.Student;
import service.StudentService;
import service.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentView extends JDialog{
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
    JButton updateBtn=new JButton("修改");

    UpdateStudentViewHandler updateStudentViewHandler;
    public UpdateStudentView(MainView mainView, int selectedStudentID){
        super(mainView,"修改学生信息",true);

        updateStudentViewHandler=new UpdateStudentViewHandler(this,mainView);
        StudentService studentService=new StudentServiceImpl();
        Student selectedStudent=studentService.getByID(selectedStudentID);

        /*idLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLable);
        idText.setPreferredSize(new Dimension(200,30));
        idText.setText(selectedStudent.getID()+"");
        //设置id不可编辑
        idText.setEnabled(false);
        jPanel.add(idText);*/

        IDLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(IDLable);
        IDText.setPreferredSize(new Dimension(200,30));
        IDText.setText(selectedStudent.getID()+"");
        IDText.setEnabled(false);
        jPanel.add(IDText);

        nameLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLable);
        nameText.setPreferredSize(new Dimension(200,30));
        nameText.setText(selectedStudent.getName());
        jPanel.add(nameText);

        classLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(classLable);
        classText.setPreferredSize(new Dimension(200,30));
        classText.setText(selectedStudent.getClass_name());
        jPanel.add(classText);

        majorLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(majorLable);
        majorText.setPreferredSize(new Dimension(200,30));
        majorText.setText(selectedStudent.getMajor());
        jPanel.add(majorText);

        courseLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(courseLable);
        courseText.setPreferredSize(new Dimension(200,30));
        courseText.setText(selectedStudent.getCourse());
        jPanel.add(courseText);

        teacherLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(teacherLable);
        teacherText.setPreferredSize(new Dimension(200,30));
        teacherText.setText(selectedStudent.getTeacer());
        jPanel.add(teacherText);

        creditLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(creditLable);
        creditText.setPreferredSize(new Dimension(200,30));
        creditText.setText(selectedStudent.getCredit()+"");
        jPanel.add(creditText);

        updateBtn.addActionListener(updateStudentViewHandler);
        jPanel.add(updateBtn);

        Container contenPane=getContentPane();
        contenPane.add(jPanel);

        setSize(320,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //获取修改后的学生对象
    public Student buildUpdateStudent() {
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
