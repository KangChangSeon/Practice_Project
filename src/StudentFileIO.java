import java.util.List;

public class StudentFileIO extends StudentDBIO{
    static final StudentIFileIO INSTANCE = null;
    //임시 초기화
    private void StudentFileIO(){}
    @Override
    public List<Student> saveStudntData() {
        return List.of();
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
        return null;
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
