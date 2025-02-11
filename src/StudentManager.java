import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class StudentManager extends StudentDBIO{
    private static StudentManager instance;
    private Student student;
    private static List<Student> studentList = new ArrayList<Student>();
    StudentDBIO studentDBIO;

    private StudentManager(){}

    public static StudentManager getinstance(){
        if(instance == null){
            instance = new StudentManager();
        }
        return instance;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public List<Student> saveStudntData() {
        studentList.add(student);
        return studentList;
    }


    @Override
    public List<String> getStudntData() {
        return List.of();
    }

    @Override
    public List<String> searchStudnt(String keyword) {
        return List.of();
    }

    @Override
    public List<String> sortStuByIdAsce() {
        return List.of();
    }

    @Override
    public List<String> sortStuByIdDesc() {
        return List.of();
    }

    @Override
    public List<String> sortStuByAverage() {
        return List.of();
    }

    @Override
    public List<String> SortStuByGradeDesc() {
        return List.of();
    }

    @Override
    public List<String> SortStuByScoreDesc() {
        return List.of();
    }

    @Override
    public Student inputstuData() {
        student = new Student.StudentBuilder().build();// 학생정보를 빌더로 입력받음
        return student;
    }

    @Override
    public List<String> printStudentDta(Student student) {
        return List.of();
    }

    @Override
    public String printStudentbyName(Student name) {
        return "";
    }

    @Override
    public String printAllStudent(List<Student> students) {
        return "";
    }
}
