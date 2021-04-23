package StuManageView;

import Information.Student;
import service.StudentService;
import service.StudentServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentViewHandler implements ActionListener {
    private AddStudentView addStudentView;
    private MainView mainView;
    public AddStudentViewHandler(AddStudentView addStudentView, MainView mainView){
        this.addStudentView=addStudentView;
        this.mainView=mainView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton) e.getSource();//获取对象
        String text=jButton.getText();//获取按钮的名称
        if("添加".equals(text)){
            StudentService studentService=new StudentServiceImpl();
            Student student=addStudentView.buildStudent();//获取添加的内容存进student中
            boolean addResult=studentService.add(student);//执行添加操作
            if(addResult){
                //重新加载数据
                mainView.reloadTable();
                addStudentView.dispose();
            }else{
                JOptionPane.showConfirmDialog(addStudentView,"添加失败");
            }
        }
    }
}
