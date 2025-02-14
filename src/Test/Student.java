package Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 학생 정보를 표현하는 클래스입니다.
 * 학번, 이름, 과목 목록을 포함하며, 총점, 평균, 등급을 계산하는 기능을 제공합니다.
 */
public class Student {

    private String sno;
    private String name;
    private List<Subject> subjects;

    /**
     * StudentBuilder를 사용하여 Student 객체를 생성합니다.
     *
     * @param builder 학생 정보를 담은 빌더 객체
     */
    private Student(StudentBuilder builder) {
        this.sno = builder.sno;
        this.name = builder.name;
        this.subjects = builder.subjects;
    }

    /**
     * 학생의 이름을 반환합니다.
     *
     * @return 학생의 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 학생이 수강하는 과목 목록을 반환합니다.
     *
     * @return 과목 목록
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * 학생의 총점을 계산하여 반환합니다.
     *
     * @return 총점
     */
    public int getTotal() {
        int total = 0;
        for (Subject subject : subjects) {
            total += subject.getScore();
        }
        return total;
    }

    /**
     * 학생의 평균 점수를 계산하여 반환합니다.
     *
     * @return 평균 점수
     */
    public double getAverage() {
        return subjects.isEmpty() ? 0 : getTotal() / (double) subjects.size();
    }

    /**
     * 학생의 등급을 계산하여 반환합니다.
     *
     * @return 계산된 등급 (A, B, C, D, F)
     */
    public String computeGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }

    /**
     * 학생 정보를 문자열 형태로 반환합니다.
     *
     * @return 학생 정보를 포함한 문자열
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("sno='").append(sno).append("', ");
        sb.append("name='").append(name).append("', ");
        sb.append("subjects=").append(subjects).append(", ");
        sb.append("total=").append(getTotal()).append(", ");
        sb.append("average=").append(getAverage()).append(", ");
        sb.append("grade=").append(computeGrade());
        return sb.toString();
    }

    /**
     * 학생이 수강하는 과목을 표현하는 내부 클래스입니다.
     */
    public static class Subject {
        private String name;
        private int score;

        /**
         * 과목 이름과 점수를 설정하여 Subject 객체를 생성합니다.
         *
         * @param name  과목 이름
         * @param score 과목 점수
         */
        public Subject(String name, int score) {
            this.name = name;
            this.score = score;
        }

        /**
         * 과목의 이름을 반환합니다.
         *
         * @return 과목 이름
         */
        public String getName() {
            return name;
        }

        /**
         * 과목의 점수를 반환합니다.
         *
         * @return 점수
         */
        public int getScore() {
            return score;
        }

        /**
         * 과목 정보를 "과목이름:점수" 형식의 문자열로 반환합니다.
         *
         * @return 과목 정보 문자열
         */
        @Override
        public String toString() {
            return name + ":" + score;
        }
    }

    /**
     * Student 객체 생성을 위한 빌더 클래스입니다.
     */
    public static class StudentBuilder {
        private String sno;
        private String name;
        private List<Subject> subjects = new ArrayList<>();

        /**
         * 학생 번호를 설정합니다.
         *
         * @param sno 학생 번호
         * @return 현재 StudentBuilder 객체
         */
        public StudentBuilder sno(String sno) {
            this.sno = sno;
            return this;
        }

        /**
         * 학생 이름을 설정합니다.
         *
         * @param name 학생 이름
         * @return 현재 StudentBuilder 객체
         */
        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * 학생의 과목 정보를 추가합니다.
         *
         * @param subjectName 과목 이름
         * @param score       과목 점수
         * @return 현재 StudentBuilder 객체
         */
        public StudentBuilder addSubject(String subjectName, int score) {
            this.subjects.add(new Subject(subjectName, score));
            return this;
        }

        /**
         * Student 객체를 생성하여 반환합니다.
         *
         * @return 생성된 Student 객체
         */
        public Student build() {
            return new Student(this);
        }
    }

    /**
     * 학생의 학번을 반환합니다.
     *
     * @return 학번
     */
    public String getSno() {
        return sno;
    }
}
