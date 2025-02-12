
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentManager extends StudentDBIO {
    private static StudentManager instance;
    private Student student;
    List<String> studentList;
    StudentDBIO studentDBIO;


    public static StudentManager getinstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    @Override
    public List<String> saveStudntData(Student student) {
        this.student = student;
        studentList = new ArrayList<>();
        studentList.add(student.getSno()+",");
        studentList.add(student.getName()+",");
        studentList.add(String.valueOf(student.getKorean())+",");
        studentList.add(String.valueOf(student.getEnglish())+",");
        studentList.add(String.valueOf(student.getMath())+",");
        studentList.add(String.valueOf(student.getScience())+",");
        studentList.add(String.valueOf(student.getTotal())+",");
        studentList.add(String.valueOf(student.getAverage())+",");
        studentList.add(String.valueOf(student.getGrade()));
        return studentList;
    }


    @Override
    public List<Student> getStudntData() {
        StudentFileIO studentFileIO = new StudentFileIO();
        List<Student> loadStudentList = new ArrayList<>(); //학생객체를 담을 리스트

        for (String info : studentFileIO.loadStudentInfo()) { //for each믄 : 로드한 데이터를 쉼표기준으로 분리
            List<String> lineStudent = Arrays.asList(info.split(","));

            for (int i = 0; i < lineStudent.size(); i++) {
                if(lineStudent.get(i) == null || lineStudent.isEmpty())
                    lineStudent.set(i, "0");
            }
        //lineSudent의 요소를 Student 순서에 맞게 대입
        String sno = lineStudent.get(0);// 학번
        String name = lineStudent.get(1); // 이름

        int korean = Integer.parseInt(lineStudent.get(2)); // 국어
        int english = Integer.parseInt(lineStudent.get(3)); // 영어
        int math = Integer.parseInt(lineStudent.get(4)); // 수학
        int science = Integer.parseInt(lineStudent.get(5)); // 과학
        int total = Integer.parseInt(lineStudent.get(6));
        double average = Double.parseDouble(lineStudent.get(7));
        char grade = lineStudent.get(8).charAt(0);
        //lineStudentList.get(8)에서 가져온 문자열의 첫 번째 문자를 char로 변환
        // StudentManager의 createStudentFromData 메서드를 통해 학생 객체 생성하여 대입
        student = StudentManager.getinstance().createStudentFromData(sno, name, korean, english, math, science, total, average, grade);
        loadStudentList.add(student);
        }
        return loadStudentList;
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
    public void inputstuData() {
        student = new Student.StudentBuilder().build();// 학생정보를 빌더로 입력받음
        saveStudntData(student);
        StudentFileIO studentFileIO = new StudentFileIO();
        studentFileIO.saveStudentInfo();
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

    //데이터 로드시 생성할 생성자로 사용
    public Student createStudentFromData(String sno, String name, int korean, int english, int math, int science,int total,double average,char grade) {
        student.setSno(sno);
        student.setName(name);
        student.setKorean(korean);
        student.setEnglish(english);
        student.setMath(math);
        student.setScience(science);
        student.setTotal(total);
        student.setAverage(average);
        student.setGrade(grade);
        return student;
    }
}
