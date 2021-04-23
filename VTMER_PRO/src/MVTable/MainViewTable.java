package MVTable;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {

    public MainViewTable(){
        JTableHeader tableHeader=getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);

        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.black);
        setGridColor(Color.BLACK);
        setRowHeight(30);
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }


    public void renderRule(){
        Vector<String> columns=MainViewTableModel.getColums();
        MainViewCellRender render=new MainViewCellRender();
        for(int i=0;i<columns.size();i++){
            TableColumn column=getColumn(columns.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }
    }
}
