package service;

import Information.Student;
import SQl_Utils.ConnAndClo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentServiceImpl implements StudentService{

    @Override
    public TableDTO retrieveStudent(StudentRequest request) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from student ");
        if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
            sql.append(" where student_name like '%"+request.getSearchKey().trim()+"%'");
        }
        sql.append("order by ID desc limit ").append(request.getStart()).append(",")
                .append(request.getPageSize());

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        TableDTO returnDTO=new TableDTO();
        try {
            conn= ConnAndClo.getConnection();
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            returnDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from student where student_name like '%"+request.getSearchKey().trim()+"%'");
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            while(rs.next()){
                int count=rs.getInt(1);
                returnDTO.setTotalCount(count);
            }

            return returnDTO;
           // }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnAndClo.close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public boolean add(Student student) {
        String sql="insert into student(ID,student_name,class,major,course_name,teacher,credit)" +
                "values(?,?,?,?,?,?,?)";
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn= ConnAndClo.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,student.getID());
            ps.setString(2,student.getName());
            ps.setString(3,student.getClass_name());
            ps.setString(4,student.getMajor());
            ps.setString(5,student.getClass_name());
            ps.setString(6,student.getTeacer());
            ps.setInt(7,student.getCredit());
            return ps.executeUpdate()==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnAndClo.close(ps,conn);
        }
        return false;
    }

    @Override
    public Student getByID(int selectedStudentID){
        String sql="select * from student where ID=?";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Student student=new Student();

        try {
            conn=ConnAndClo.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,selectedStudentID);
            rs=ps.executeQuery();
            while(rs.next()){
                int ID= rs.getInt("ID");
                String name= rs.getString("student_name");
                String CLASS= rs.getString("class");
                String major= rs.getString("major");
                String courseName= rs.getString("course_name");
                String teacher= rs.getString("teacher");
                int credit= rs.getInt("credit");

                student.setID(ID);
                student.setName(name);
                student.setClass_name(CLASS);
                student.setMajor(major);
                student.setCourse(courseName);
                student.setTeacer(teacher);
                student.setCredit(credit);
            }
            return student;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnAndClo.close(rs,ps,conn);
        }

        return null;
    }

    @Override
    public boolean update(Student student) {
        String sql="update student set student_name=?,class=?,major=?,course_name=?,teacher=?,credit=?" +
                " where ID=?";
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn= ConnAndClo.getConnection();
            ps=conn.prepareStatement(sql);

            ps.setString(1,student.getName());
            ps.setString(2,student.getClass_name());
            ps.setString(3,student.getMajor());
            ps.setString(4,student.getClass_name());
            ps.setString(5,student.getTeacer());
            ps.setInt(6,student.getCredit());
            ps.setInt(7,student.getID());
            return ps.executeUpdate()==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnAndClo.close(ps,conn);
        }
        return false;
    }

    @Override
    public boolean delete(int[] selectedStudentIDs) {
        StringBuilder sql=new StringBuilder();
        sql.append("delete from student where ID in (");
        int length=selectedStudentIDs.length;
        for (int i = 0; i < length; i++) {
            if(i==(length-1)){
                sql.append(" ? ");
            }else{
                sql.append(" ?, ");
            }
        }
        sql.append(" )");

        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn= ConnAndClo.getConnection();
            ps=conn.prepareStatement(sql.toString());

            for (int i = 0; i < length; i++) {
                //设置参数，从1开始
                ps.setInt(i+1,selectedStudentIDs[i]);
            }

            return ps.executeUpdate()==length;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnAndClo.close(ps,conn);
        }
        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data=new Vector<>();
        while(rs.next()){
            Vector<Object> oneRecord=new Vector<>();
            int ID= rs.getInt("ID");
            String name= rs.getString("student_name");
            String CLASS= rs.getString("class");
            String major= rs.getString("major");
            String courseName= rs.getString("course_name");
            String teacher= rs.getString("teacher");
            int credit= rs.getInt("credit");

            oneRecord.addElement(ID);
            oneRecord.addElement(name);
            oneRecord.addElement(CLASS);
            oneRecord.addElement(major);
            oneRecord.addElement(courseName);
            oneRecord.addElement(teacher);
            oneRecord.addElement(credit);
            data.addElement(oneRecord);
            }
        return data;
    }
}
