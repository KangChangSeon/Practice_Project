import java.util.List;

public interface StudentOutput {
    List<Student>printStudentDta(List<Student> searchFoundStudents);
    List<Student>  printStudentbyName(List<Student> searchFoundStudents);
    List<Student> printAllStudent(List<Student> students);
}
