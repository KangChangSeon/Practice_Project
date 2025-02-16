package yes;

import lombok.Getter;

import java.util.*;

@Getter
public class StudentManager extends StudentDBIO {
    private static StudentManager instance;
    private Student student;
    private List<String> studentList;
    private final List<Student> studentDBList = getStudntData();

    Scanner sc = new Scanner(System.in);

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

        studentList.add(student.getSno() + ",");
        studentList.add(student.getName() + ",");
        studentList.add(String.valueOf(student.getKorean()) + ",");
        studentList.add(String.valueOf(student.getEnglish()) + ",");
        studentList.add(String.valueOf(student.getMath()) + ",");
        studentList.add(String.valueOf(student.getScience()) + ",");
        studentList.add(String.valueOf(student.getTotal()) + ",");
        studentList.add(String.valueOf(student.getAverage()) + ",");
        studentList.add(String.valueOf(student.getGrade()));

        return studentList;
    }


    @Override
    public List<Student> getStudntData() {

        List<Student> loadStudentList = new ArrayList<>();
        StudentFileIO studentFileIO = new StudentFileIO();

        for (String info : studentFileIO.loadStudentInfo()) { //for each믄 : 로드한 데이터를 쉼표기준으로 분리
            List<String> lineStudent = Arrays.asList(info.split(","));

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
            student= createStudentFromData(sno, name, korean, english, math, science, total, average, grade);
            loadStudentList.add(student);
           }
        return loadStudentList;
    }

    @Override
    public List<Student> searchStudnt() {//Keyword 멤버변수로 변경 //리스트 Studnet타입으로 변경
        String keyword;

        boolean search = false;

        List<Student> searchFoundStudents = new ArrayList<>();

        System.out.println("검색할 학번 또는 이름을 입력해주세요.");
        keyword = sc.nextLine();
        System.out.println(keyword + "를 검색합니다.");

        for (Student searchStudent : studentDBList) {
            if (searchStudent.getSno().equals(keyword) || searchStudent.getName().equals(keyword)) {
                searchFoundStudents.add(searchStudent);
                search = true;
            }
        }
        if (!search) {
            System.out.println(keyword + "에 해당하는 학생이 존재하지 않습니다.");
        }else if(search) {
            System.out.println("1.해당하는 학생의 이름만 조회 2.해당하는 학생의 모든 정보 조회");
            int num = sc.nextInt();
            sc.nextLine();
            if (num == 1) printStudentbyName(searchFoundStudents);
            else if (num == 2) printStudentDta(searchFoundStudents);
            else System.out.println("잘못입력하셨습니다.");
        }

        return searchFoundStudents;
    }

    @Override
    public List<Student> sortStuByName() {


        System.out.println("정렬 방식을 선택해주세요.");
        System.out.println("1. 오름차순 2.내림차순");
        int num = sc.nextInt();

        sc.nextLine();

        switch (num){
            case 1://Comparator 학생 아이디 오름차순
                Collections.sort(studentDBList,Comparator.comparing(Student::getName));//람다 studnet->student.getSno
                for(Student sortStudent : studentDBList)
                    if(sortStudent.getName()==null)
                    System.out.println(sortStudent);
                break;
            case 2 :  //Collections.sort 오버로딩 내림차순
                Collections.sort(studentDBList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o2.getName().compareTo(o1.getName()); // 문자열 정렬 방식
                    }
                });
                for(Student sortStudent : studentDBList)
                    if(sortStudent.getName()==null)
                System.out.println(sortStudent);
                break;

            default:
                System.out.println("잘못입력하셨습니다.");
                break;

        }
        return studentDBList;

    }

    @Override
    public List<Student> sortStuById() {


        System.out.println("정렬 방식을 선택해주세요.");
        System.out.println("1. 오름차순 2.내림차순");
        int num = sc.nextInt();

        sc.nextLine();

        switch (num){
            case 1://Comparator 학생 아이디 오름차순
                Collections.sort(studentDBList,Comparator.comparing(Student::getSno));//람다 studnet->student.getSno
                for(Student sortStudent : studentDBList)
                    if(sortStudent.getSno()==null)
                    System.out.println(sortStudent);
                break;

            case 2 :  //Collections.sort 오버로딩 내림차순
                Collections.sort(studentDBList, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o2.getSno().compareTo(o1.getSno()); // 문자열 정렬 방식
                }
                });
                for(Student sortStudent :studentDBList)
                    if(sortStudent.getSno()==null)
                    System.out.println(sortStudent);
                break;
            default:
                System.out.println("잘못입력하셨습니다.");
                break;
        }
        return studentDBList;
    }


    @Override
    public List<Student> sortStuByAverage() {


        System.out.println("정렬 방식을 선택해주세요.");
        System.out.println("1. 오름차순 2.내림차순");
        int num2 = sc.nextInt();

        sc.nextLine();;

        if(num2 == 1) {
            Collections.sort(studentDBList, Comparator.comparing(Student::getAverage));
            for(Student sortStudent : studentDBList)
                System.out.println(sortStudent);
        } else if (num2 ==2){
            Collections.sort(studentDBList, Comparator.comparing(Student::getAverage).reversed());
            for(Student sortStudent : studentDBList)
                System.out.println(sortStudent);
        }else System.out.println("잘못입력하셨습니다.");

        return studentDBList;
    }

    @Override
    public List<Student> sortStuByGrade() {//Collection.sort() 오버라이딩 등급 내림차순
        Scanner sc = new Scanner(System.in);


        System.out.println("정렬 방식을 선택해주세요.");
        System.out.println("1. 오름차순 2.내림차순");
        int num2 = sc.nextInt();

        sc.nextLine();;

        if (num2==1) {
            Collections.sort(studentDBList, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Character.compare(o1.getGrade(), o2.getGrade());
                }
            });
            for(Student sortStudent : studentDBList)
                if(sortStudent.getGrade()!= 0)
                System.out.println(sortStudent);
        } else if (num2==2) {
            Collections.sort(studentDBList, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Character.compare(o2.getGrade(), o1.getGrade());
                }
            });
            for(Student sortStudent : studentDBList)
                if(sortStudent.getGrade()!= 0)
                System.out.println(sortStudent);
        }else System.out.println("잘못입력하셨습니다.");
        return studentDBList;
    }

    @Override
    public List<Student> sortStuByScore() {//과목별점수 오름차순/ 내림차순


        Scanner sc = new Scanner(System.in);


        System.out.println("정렬할 과목을 선택해 주세요");
        System.out.println("1.국어 2.영어 3. 수학 4.과학 ");
        int num1 = sc.nextInt();
        sc.nextLine();

        System.out.println("정렬 방식을 선택해주세요.");
        System.out.println("1. 오름차순 2.내림차순");
        int num2 = sc.nextInt();
        sc.nextLine();

        switch (num1) {
            case 1: // 국어 정렬
                switch (num2) {
                    case 1:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getKorean));
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getKorean()!=0) // 해당 과목 점수를 입력하지 않은 경우 출력하지 않음
                            System.out.println(sortStudent);
                        break;
                    case 2:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getKorean).reversed());
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getKorean()!=0)
                            System.out.println(sortStudent);
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        break;
                }
                break;

            case 2: // 영어 정렬
                switch (num2) {
                    case 1:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getEnglish));
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getEnglish()!=0)
                            System.out.println(sortStudent);
                        break;
                    case 2:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getEnglish).reversed());
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getEnglish()!=0)
                            System.out.println(sortStudent);
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        for(Student sortStudent : studentDBList)
                        System.out.println(sortStudent);
                    break;
                }
                break;

            case 3: // 수학 정렬
                switch (num2) {
                    case 1:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getMath));
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getMath()!=0)
                            System.out.println(sortStudent);
                        break;
                    case 2:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getMath).reversed());
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getMath()!=0)
                            System.out.println(sortStudent);
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                }
                break;

            case 4: // 과학 정렬
                switch (num2) {
                    case 1:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getScience));
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getScience()!=0)
                            System.out.println(sortStudent);
                        break;
                    case 2:
                        Collections.sort(studentDBList, Comparator.comparing(Student::getScience).reversed());
                        for(Student sortStudent : studentDBList)
                            if(sortStudent.getScience()!=0)
                            System.out.println(sortStudent);
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        break;
                 }
                break;

            default:
                System.out.println("잘못 입력하셨습니다.");
        }


        return studentDBList;
    }

    @Override
    public void inputstuData() {
        student = new Student.StudentBuilder().build();// 학생정보를 빌더로 입력받음
        saveStudntData(student);
        StudentFileIO studentFileIO = new StudentFileIO();
        studentFileIO.saveStudentInfo();
    }

    @Override
    public List<Student> printStudentDta(List<Student> searchFoundStudents) {
        for (Student searFoundstudent : searchFoundStudents)
            System.out.println(searFoundstudent);
        return searchFoundStudents;
    }

    @Override
    public List<Student>  printStudentbyName(List<Student> searchFoundStudents) {
        for (Student searFoundstudent : searchFoundStudents)
            System.out.println(searFoundstudent.getName());
        return searchFoundStudents;
    }

    @Override
    public List<Student> printAllStudent() {
        for(Student printstudent : studentDBList)
            System.out.println(printstudent);
        return studentDBList;
    }

    //데이터 로드시 생성할 생성자로 사용
    public Student createStudentFromData(String sno, String name, int korean, int english, int math, int science, int total, double average, char grade) {
        Student student = new Student();
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
