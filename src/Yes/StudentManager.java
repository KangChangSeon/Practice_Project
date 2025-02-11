package Yes;

import lombok.Getter;

import java.util.*;

public class StudentManager extends StudentDBIO {
    private static final StudentManager INSTANCE = new StudentManager();
    String studentStr;

    @Getter
    private List<Student> studentDataList = new ArrayList<>();


    private StudentManager() {
    }

    public static StudentManager getInstance() {
        return INSTANCE;
    }


    @Override
    public String InputStuData() {
        Scanner sc = new Scanner(System.in);

        System.out.print("학번을 입력하세요: ");
        String sno = sc.nextLine();

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();

        System.out.print("국어 점수를 입력하세요: ");
        int korean = Integer.parseInt(sc.nextLine());

        System.out.print("영어 점수를 입력하세요: ");
        int english = Integer.parseInt(sc.nextLine());

        System.out.print("수학 점수를 입력하세요: ");
        int math = Integer.parseInt(sc.nextLine());

        System.out.print("과학 점수를 입력하세요: ");
        int science = Integer.parseInt(sc.nextLine());

        Student student = Student.builder()
                .sno(sno)
                .name(name)
                .korean(korean)
                .english(english)
                .math(math)
                .science(science)
                .build();

        studentDataList.add(student);

        return "학생 정보가 저장되었습니다";
    }

    @Override
    public void OutputStuData() {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = StudentManager.getInstance();
        StudentFileIO fileIO = new StudentFileIO();
        int choice = 0;

        System.out.println("프로그램을 시작합니다");
        while (true) {
            System.out.println();
            System.out.println("1번 입력 | 2번 출력 | 3번 검색 | 4번 정렬 | 5번 종료");
            System.out.print("메뉴를 선택하세요: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(manager.InputStuData());
                    fileIO.saveStuData();
                    break;
                case 2:
                    List<Student> students = fileIO.getStuData();
                    if (students.isEmpty()) {
                        System.out.println("저장된 학생 데이터가 없습니다.");
                    } else {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 3:
                    List<Student> foundStudents = manager.SearchStuData();
                    if (!foundStudents.isEmpty()) {
                        System.out.println("검색 결과:");
                        for (Student student : foundStudents) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 4:
                    System.out.println("정렬 기준을 입력하세요:");
                    System.out.println("1번 내림차순 | 2번 오름차순 | 3번 총점 | 4번 학점");
                    int sortchoice = scanner.nextInt();
                    scanner.nextLine();

                    List<Student> sortedStudents = SortStuData(sortchoice);

                    if (sortedStudents.isEmpty()) {
                        System.out.println("정렬할 학생 데이터가 없습니다.");
                    } else {
                        System.out.println("정렬 결과:");
                        for (Student s : sortedStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("올바른 번호를 입력하세요.");
                    break;
            }
        }
    }


    @Override
    public List<Student> SearchStuData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색할 학번을 입력하세요: ");
        String searchSno = scanner.nextLine();


        StudentFileIO fileIO = new StudentFileIO();
        List<Student> allStudents = fileIO.getStuData();


        List<Student> foundStudents = new ArrayList<>();


        for (Student s : allStudents) {
            if (s.getSno().equals(searchSno)) {
                foundStudents.add(s);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("해당 학번의 학생 데이터가 없습니다.");
        }
        return foundStudents;
    }


    @Override
    public List<Student> SortStuData(int choice) {
        StudentFileIO fileIO = new StudentFileIO();
        List<Student> sortingStudents = fileIO.getStuData();

        switch (choice) {
            case 1:
                sortingStudents.sort(Comparator.comparingInt((Student s) -> Integer.parseInt(s.getSno())).reversed());
                break;
            case 2:
                sortingStudents.sort(Comparator.comparingInt((Student s) -> Integer.parseInt(s.getSno())));
                break;
            case 3:
                sortingStudents.sort(Comparator.comparingInt(Student::getTotal).reversed());
                break;
            case 4:
                sortingStudents.sort(Comparator.comparing(Student::getGrade));
                break;
        }
        return sortingStudents;
    }


    @Override
    public void saveStuData() {
    }

    @Override
    public List<Student> getStuData() {
        return List.<Student>of();
    }

}
