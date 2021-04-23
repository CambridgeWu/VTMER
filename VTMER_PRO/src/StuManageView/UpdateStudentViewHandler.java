package StuManageView;

import Information.Student;
import service.StudentService;
import service.StudentServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentViewHandler implements ActionListener {
    private UpdateStudentView updateStudentView;
    private MainView mainView;
    public UpdateStudentViewHandler(UpdateStudentView updateStudentView, MainView mainView){
        this.updateStudentView=updateStudentView;
        this.mainView=mainView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton) e.getSource();
        String text=jButton.getText();
        if("修改".equals(text)){
            StudentService studentService=new StudentServiceImpl();
            Student student=updateStudentView.buildUpdateStudent();
            boolean updateResult=studentService.update(student);
            if(updateResult){
                mainView.reloadTable();
                updateStudentView.dispose();
            }else{
                JOptionPane.showConfirmDialog(updateStudentView,"修改失败");
            }
        }
    }
}
