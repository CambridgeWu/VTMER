package MVTable;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String> colums=new Vector<>();
    static{
        colums.addElement("ID");
        colums.addElement("姓名");
        colums.addElement("班级");
        colums.addElement("专业");

        colums.addElement("专业课");
        colums.addElement("任课老师");
        colums.addElement("学分");
    }

    private MainViewTableModel(){
        super(null,colums);
    }

    private static MainViewTableModel mainViewTableModel=new MainViewTableModel();

    public static MainViewTableModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,colums);
        return mainViewTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,colums);
    }

    public static Vector<String> getColums(){
        return colums;
    }
    @Override
    public boolean isCellEditable(int row,int column){
        return false;
    }


}
