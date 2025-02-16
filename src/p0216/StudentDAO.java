package p0216;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO { //연결할 데이터베이스 드라이버
    private static final String URL = "jdbc:mysql://localhost:3306/SMS";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "wjdaudco";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void save(Student student) {
        int total = student.getTotal();
        double average = student.getAverage();
        String grade = student.computeGrade();
        int korean = getSubjectScore(student, "korean");
        int english = getSubjectScore(student, "english");
        int math = getSubjectScore(student, "math");
        int science = getSubjectScore(student, "science");

        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO STUDENT (sno, name, korean, english, math, science, total, average, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            {
                pstmt.setString(1, student.getSno());
                pstmt.setString(2, student.getName());
                pstmt.setInt(3, korean);
                pstmt.setInt(4, english);
                pstmt.setInt(5, math);
                pstmt.setInt(6, science);
                pstmt.setInt(7, total);
                pstmt.setDouble(8, average);
                pstmt.setString(9, grade);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student findStudentBySno(String sno) {
        Student student = null;
        try {
            Connection conn = getConnection();//데이터베이스와 연결된 Connection객체 conn
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE sno = ?");
            {
                pstmt.setString(1, sno); //첫번째?에 sno값을 쿼리에 바인딩
                try (ResultSet rs = pstmt.executeQuery()) { //쿼리를 실행한 결과를 rs에 저장
                    if (rs.next()) { //Resultset 에서 해당하는 레코드가 있으면 true반환하고 그 값을 가져옴
                        String name = rs.getString("name");
                        int korean = rs.getInt("korean");
                        int english = rs.getInt("english");
                        int math = rs.getInt("math");
                        int science = rs.getInt("science");

                        Student.StudentBuilder builder = new Student.StudentBuilder()
                                .sno(sno)
                                .name(name);
                        builder.addSubject("korean", korean);
                        builder.addSubject("english", english);
                        builder.addSubject("math", math);
                        builder.addSubject("science", science);
                        student = builder.build();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
    // DB에서 학번으로 학생정보 삭제
    public void delete(String sno) {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM STUDENT WHERE sno = ?"); {
                pstmt.setString(1, sno);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // DB에서 모든 학생정보 조회
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM STUDENT");//STUDENT 테이블에서 모든 데이터를 조회하는 SQL 쿼리
            ResultSet rs = pstmt.executeQuery(); { // 모든 학생 정보를 조회한 쿼리를 저장
                while (rs.next()) {
                    String sno = rs.getString("sno");
                    String name = rs.getString("name");
                    int korean = rs.getInt("korean");
                    int english = rs.getInt("english");
                    int math = rs.getInt("math");
                    int science = rs.getInt("science");

                    Student student = new Student.StudentBuilder()
                            .sno(sno)
                            .name(name)
                            .addSubject("korean", korean)
                            .addSubject("english", english)
                            .addSubject("math", math)
                            .addSubject("science", science)
                            .build();
                    studentList.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
    // DB의 전체 정보를 업데이트 (이름과 성적 모두)
    public void updateStudent(Student student) {
        int total = student.getTotal();
        double average = student.getAverage();
        String grade = student.computeGrade();
        int korean = getSubjectScore(student, "korean");
        int english = getSubjectScore(student, "english");
        int math = getSubjectScore(student, "math");
        int science = getSubjectScore(student, "science");

        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE STUDENT SET name=?, korean=?, english=?, math=?, science=?, total=?, average=?, grade=? WHERE sno = ?"); {
                        pstmt.setString(1, student.getName());
                        pstmt.setInt(2, korean);
                        pstmt.setInt(3, english);
                        pstmt.setInt(4, math);
                        pstmt.setInt(5, science);
                        pstmt.setInt(6, total);
                        pstmt.setDouble(7, average);
                        pstmt.setString(8, grade);
                        pstmt.setString(9, student.getSno());
                        pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // DB의 성적 정보만 업데이트 (이름은 기존 값 유지)
    public void updateStudentScores(Student student) {
        int total = student.getTotal();
        double average = student.getAverage();
        String grade = student.computeGrade();
        int korean = getSubjectScore(student, "korean");
        int english = getSubjectScore(student, "english");
        int math = getSubjectScore(student, "math");
        int science = getSubjectScore(student, "science");

        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE STUDENT SET korean=?, english=?, math=?, science=?, total=?, average=?, grade=? WHERE sno = ?"); {
                        pstmt.setInt(1, korean);
                        pstmt.setInt(2, english);
                        pstmt.setInt(3, math);
                        pstmt.setInt(4, science);
                        pstmt.setInt(5, total);
                        pstmt.setDouble(6, average);
                        pstmt.setString(7, grade);
                        pstmt.setString(8, student.getSno());
                        pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getSubjectScore(Student student, String subjectName) {
        return student.getSubjects().stream() //과목배열을 스트림으로 변환
                .filter(subject -> subject.getName().equalsIgnoreCase(subjectName))//매개변수로 받은 과목과 같은 값의 과목(대소문자 구분 없음 )을 찾아 리턴
                .mapToInt(Student.Subject::getScore)//해당 과목의 점수를 정수형으로 변환하여 리턴
                .findFirst()// 처음 일치하는 값만 찾음
                .orElse(0); // 해당하는 과목이 없으면 0을 반환
    }
}