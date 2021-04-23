package StuManageView;

import service.StudentService;
import service.StudentServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewHandler implements ActionListener {

    private MainView mainView;
    public MainViewHandler(MainView mainView){
        this.mainView=mainView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton)e.getSource();
        String text=jButton.getText();
        if("添加".equals(text)){
            new AddStudentView(mainView);
        }else if("修改".equals(text)){
            int[] selectedStudentIDs=mainView.getSelectedStudentIDs();
            if(selectedStudentIDs.length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一行");
                return;
            }
            new UpdateStudentView(mainView,selectedStudentIDs[0]);
        }else if("删除".equals(text)){
            int[] selectedStudentIDs=mainView.getSelectedStudentIDs();
            if(selectedStudentIDs.length==0){
                JOptionPane.showMessageDialog(mainView,"请选择要删除的行");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainView, "你确认要删除选择的" +
                            selectedStudentIDs.length + "行吗？", "确认删除",
                    JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_OPTION){
                StudentService studentService=new StudentServiceImpl();
                boolean deleteResult=studentService.delete(selectedStudentIDs);
                if(deleteResult){
                    mainView.reloadTable();
                }else{
                    JOptionPane.showMessageDialog(mainView,"删除失败");
                }
            }
        }else if("查询".equals(text)){
            mainView.setPageNow(1);
            mainView.reloadTable();
        }else if("上一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow()-1);
            mainView.reloadTable();
        }else if("下一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow()+1);
            mainView.reloadTable();
        }

    }
}
