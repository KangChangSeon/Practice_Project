// JDVC API 이용해 mysql데베 student 테이블 조작하는 DAO 클래스.
import java.sql.Connection; // 데베와 연결.
import java.sql.DriverManager; // JDBC 드라이버 통해 데베 연결 생성하는 클래스.
import java.sql.PreparedStatement; // sql 쿼리 실행하는 객체. sql 인젝션 방지함.
import java.sql.ResultSet; // select쿼리의 결과 데이터를 담는 객체.
import java.util.ArrayList; // 학생 객체를 저장할 리스트를 생성할때 사용.
import java.util.List; // 어레이 리스트를 다룰 때 사용하는 인터페이스.

public class StudentDAO {
    // 데베 연결 정보 설정하기.
    // mysql 데베 주소. 로컬서버, 기본포트 넘버, 사용할할 데베 이름 SMS.
    private static final String URL = "jdbc:mysql://localhost:3306/SMS";
    // mysql login username
    private static final String USERNAME = "root";
    // mysql login password
    private static final String PASSWORD = "wjdaudco";

    // 데베 연결.
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // mysql jdbc 드라이버 로드하기.
        return DriverManager.getConnection(URL, USERNAME, PASSWORD); // 데베 연결 생성, Connection 객체 반환
    }

    // 학생 정보 저장하는 메소드. 학생 객체 받기.
    public void saveToDB(Student student) {
        int total = student.getTotal(); // total 변수 생성해서 아규먼트 학생개체의 getTotal()메소드 호출하여 반환된 값 할당.
        double average = student.getAverage();
        String grade = student.computeGrade();
        int korean = getSubjectScore(student, "korean");
        int english = getSubjectScore(student, "english");
        int math = getSubjectScore(student, "math");
        int science = getSubjectScore(student, "science");


        try {
            Connection conn = getConnection(); // getConnection() 메소드 호출하여 데베와 연결 생성함.
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

    // 학번으로 학생 조회하기.
    public Student findStudentBySno(String sno) {
        Student student = null;
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE sno = ?");
            {
                pstmt.setString(1, sno);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM STUDENT");
            ResultSet rs = pstmt.executeQuery(); {
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
        return student.getSubjects().stream()
                .filter(subject -> subject.getName().equalsIgnoreCase(subjectName))
                .mapToInt(Student.Subject::getScore)
                .findFirst()
                .orElse(0);
    }
}
