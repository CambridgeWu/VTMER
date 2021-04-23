package service;

import Information.Student;

public interface StudentService {
    TableDTO retrieveStudent(StudentRequest request);

    boolean add(Student student);

    Student getByID(int selectedStudentID);

    boolean update(Student student);

    boolean delete(int[] selectedStudentIDs);
}
