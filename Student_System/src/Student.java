import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

// Student = DTO ?
public class Student {

    private String sno;
    private String name;
    private List<Subject> subjects; // Subject 객체를 담는 List 타입의 subejcts.

    // Student 생성자는 빌더 객체를 받기.
    private Student(StudentBuilder builder) {
        this.sno = builder.sno;  // 빌더 객체의 sno 로 세팅.
        this.name  = builder.name;
        this.subjects = builder.subjects;
    }

    public String getName() {
        return name;
    }

    // 어떤 객체를 담는 리스트인지 명시하기.
    public List<Subject> getSubjects() {
        return subjects;
    }

    /// 스태틱 과목 클래스 이용해서 스튜던트 객체에 과목 추가.
    /// why is it better to make it static? are there other ways ?
    public static class Subject {
        private String name;
        private int score;

        public Subject(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Subject{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
   ///  빌더 클래스
    public static class StudentBuilder {
        private String sno;
        private String name;
        private List<Subject> subjects = new ArrayList<>();

        // getters and setters
       public StudentBuilder sno(String sno) {
           this.sno = sno;
           return this; // 빌더 객체를 리턴하기.
       }

       public StudentBuilder name(String name) {
           this.name = name;
           return this;
       }

       // 빌더의 메소드 선언하기 - StudentBuilder 리턴함
       public StudentBuilder addSubject(String subjectName, int score) {
           // 빌더의 subjects 리스트에 add하기.
           this.subjects.add(new Subject(subjectName, score)); // 과목 자체를 추가하기.
           return this; // 빌더 객체를 리턴하기.
       }

       /// 빌더의 메소드 deleteSubject도 있어야 하지 않을까?

       // 학생 객체 리턴하는 빌더 객체의 build() 메소드.
       // 이너 클래스는 바깥 클래스의 타입 리턴 가능.
       public Student build() {
           return new Student(this); // 학생 객체에 빌더 객체 던져주기.
       }
   }
}
