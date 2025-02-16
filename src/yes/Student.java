package yes;

import lombok.Data;

import java.util.Scanner;
@Data
public class Student {
    //필수멤버
    private String sno;
    private String name;
    //선택멤버
    private int korean;
    private int english;
    private int math;
    private int science;

    //입력받지 않고 직접 계산할 멤버
    private int total;
    private double average;
    private char grade;

//    private char koreanGrade;
//    private char englidhGrade;
//    private char methgrade;
//    private char scienceGrade;

    private int cnt;
    static Scanner sc = new Scanner(System.in);

    public Student(){}
    private Student(StudentBuilder studentBuilder) {
        this.sno = studentBuilder.sno;
        this.name = studentBuilder.name;
        this.korean = studentBuilder.korean;
        this.english = studentBuilder.english;
        this.math = studentBuilder.math;
        this.science = studentBuilder.science;
        this.cnt = studentBuilder.cnt;
        this.total = korean + english + math + science;
        this.average = (double) total / cnt;
        switch ((int)total/10*cnt){
            case 10:
                    grade = 'A';
                    break;
                case 9:
                    grade = 'B';
                    break;
                case 8:
                    grade = 'C';
                    break;
                case 7:
                    grade = 'D';
                    break;
                default:
                    grade = 'F';
                    break;
            }
        System.out.println("입력된 정보 : "+ toString());
    }

    public static class StudentBuilder {
        private String sno;
        private String name;
        //선택멤버
        private int korean;
        private int english;
        private int math;
        private int science;

        private char grade;


//        private char koreanGrade;
//        private char englidhGrade;
//        private char methgrade;
//        private char scienceGrade;

        private int cnt;
        boolean builderin = true;

        public StudentBuilder() {

                System.out.println("학번을 입력해주세요.");
                this.sno = sc.nextLine();
            System.out.println("이름을 입력해주세요.");
            this.name = sc.nextLine();

            while (builderin){
                System.out.println("입력을 원하시는 과목을 선택하세요.");
                System.out.println("1. 국어 2.영어 3.수학 4.과학 5. 종료");
                int switchNum = sc.nextInt();
                sc.nextLine();


                switch(switchNum) {
                    case 1:
                        koreanScore();
                        break;
                    case 2:
                        englishScore();
                        break;
                    case 3:
                        mathScore();
                        break;
                    case 4:
                        scienceScore();
                        break;
                    case 5:
                        System.out.println("입력을 종료합니다.");
                        builderin =false;
                        break;
                    default:
                        System.out.println("잘못된 번호입니다. 다시 입력하세요.");
                        break;
                 }
            }
        }

        public StudentBuilder koreanScore() {
            System.out.println("국어 점수를 입력해주세요");
            this.korean = sc.nextInt(); // 점수를 사용자에게 입력받음
            cnt++; //입력한 과목 카운팅
            return this;
        }

        public StudentBuilder englishScore() {

            System.out.println("영어 점수를  입력해주세요");
            this.english = sc.nextInt();
            cnt++;
            return this;
        }
        public StudentBuilder mathScore(){
            System.out.println("수학 점수를  입력해주세요");
            this.math = sc.nextInt();
            cnt++;
            return this;
            }

            public StudentBuilder scienceScore(){
                System.out.println("과학 점수를 입력해주세요");
                this.science = sc.nextInt();
                cnt++;
                return this;
            }
        public Student build(){
            return new Student(this);
            }
        }


    @Override
    public String toString() {
        String result = "학번: " + sno +", 이름: "+name ;
        if(korean != 0) result += ", 국어점수: "+korean;
        if(english != 0) result += ", 영어점수: " + english;
        if (math != 0) result += ", 수학점수: " + math;
        if (science != 0) result += ", 과학점수: " + science;
        result += ", 총합점수 :" + total + ", 평균점수" + average+", 등급 : "+grade;
        return result;
    }


}
