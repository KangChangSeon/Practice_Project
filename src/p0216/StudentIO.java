package p0216;

import java.util.List;

public interface StudentIO extends StudentInput,SearchStudent,SortedStudent{

    void save(Student student);
    List<Student> getAllStudents();
    void delete(String sno);
}
