package Yes;

import lombok.Data;

@Data
public class Student {
    private final String sno;
    private final String name;
    private final int korean;
    private final int english;
    private final int math;
    private final int science;
    private final int total;
    private final double average;
    private final char grade;

    private Student(StudentBuilder builder) {
        this.sno = builder.sno;
        this.name = builder.name;
        this.korean = builder.korean;
        this.english = builder.english;
        this.math = builder.math;
        this.science = builder.science;
        this.total = korean + english + math + science;
        this.average = total / 4.0;
        this.grade = calcGrade(average);
    }

    private char calcGrade(double avg) {
        if (avg >= 90) return 'A';
        else if (avg >= 80) return 'B';
        else if (avg >= 70) return 'C';
        else if (avg >= 60) return 'D';
        else return 'F';
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private String sno;
        private String name;
        private int korean = 0;
        private int english = 0;
        private int math = 0;
        private int science = 0;

        public StudentBuilder sno(String sno) {
            this.sno = sno;
            return this;
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder korean(int korean) {
            this.korean = korean;
            return this;
        }

        public StudentBuilder english(int english) {
            this.english = english;
            return this;
        }

        public StudentBuilder math(int math) {
            this.math = math;
            return this;
        }

        public StudentBuilder science(int science) {
            this.science = science;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
