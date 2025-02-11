import java.util.List;

public interface StudentOutput {
    List<String> printStudentDta(Student student);
    String printStudentbyName(Student name);
    String printAllStudent(List<Student> students);
}
