package StuManageView;

import MVTable.MainViewTable;
import MVTable.MainViewTableModel;
import SQl_Utils.DimensionUtil;
import service.StudentRequest;
import service.StudentService;
import service.StudentServiceImpl;
import service.TableDTO;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MainView extends JFrame {
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn=new JButton("添加");
    JButton updataBtn=new JButton("修改");
    JButton deleteBtn=new JButton("删除");
    JTextField searchText=new JTextField(15);
    JButton searchBtn=new JButton("查询");

    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn=new JButton("上一页");
    JButton nextBtn=new JButton("下一页");

    MainViewTable mainViewTable=new MainViewTable();
    private int pageNow=1;//当前第几页
    private int pageSize=10;//每一页多少条数据库记录

    MainViewHandler mainViewHandler;
    public MainView(){
        super("主界面-学生管理系统");
        Container contentPane=getContentPane();
        Rectangle bounds=DimensionUtil.getBounds();
        pageSize=Math.floorDiv(bounds.height,35);
        mainViewHandler=new MainViewHandler(this);
        //北边组件
        addBtn.addActionListener(mainViewHandler);
        updataBtn.addActionListener(mainViewHandler);
        deleteBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updataBtn);
        northPanel.add(deleteBtn);
        northPanel.add(searchText);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
        //中间的table
        layoutCenter(contentPane);
        
        //南边组件
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);


        //填充整个屏幕
        setBounds(bounds);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    /*
    设置上一页下一页是否可见
     */
    private void showPreNext(int totalCount){
        if(pageNow==1){
            preBtn.setVisible(false);
        }else{
            preBtn.setVisible(true);
        }

        int pageCount=0;//总共多少页
        if(totalCount%pageSize==0){
            pageCount=totalCount/pageSize;
        }else{
            pageCount=totalCount/pageSize+1;
        }

        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else{
            nextBtn.setVisible(true);
        }
    }



    private void layoutCenter(Container contentPane) {
        TableDTO dto = getTableDTO();
        MainViewTableModel mainViewTableModel=MainViewTableModel.assembleModel(dto.getData());
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane=new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchText.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudent(request);
        return tableDTO;
    }

    public static void main(String[] args) {
        new MainView();
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void reloadTable() {
        TableDTO dto = getTableDTO();
        MainViewTableModel.updateModel(dto.getData());
        mainViewTable.renderRule();
        showPreNext(dto.getTotalCount());
    }

    //获取学生id
    public int[] getSelectedStudentIDs(){
        int[] selectedRows=mainViewTable.getSelectedRows();
        int[] ids=new int[selectedRows.length];
        for (int i=0;i<selectedRows.length;i++){
            int rowIndex=selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIndex, 0);
            ids[i]=Integer.valueOf(idObj.toString());
        }
        return ids;
    }

}
