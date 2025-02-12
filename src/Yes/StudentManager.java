package Yes;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 3:
                    List<Student> foundStudents = SearchStuData();
                    if (!foundStudents.isEmpty()) {
                        System.out.println("검색 결과:");
                        for (Student s : foundStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 4:
                    System.out.println("정렬 기준을 입력하세요:");
                    System.out.println("1번 학생번호 내림차순 | 2번 학생번호 오름차순 | 3번 총점 내림차순 | 4번 학점 오름차순");
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
                    break;
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
                // 변경: 학생번호를 정수로 변환하여 내림차순 정렬 (원래 문자열 비교가 아니라)
                sortingStudents.sort(Comparator.comparingInt((Student s) -> Integer.parseInt(s.getSno())).reversed());
                break;
            case 2:
                // 변경: 학생번호를 정수로 변환하여 오름차순 정렬
                sortingStudents.sort(Comparator.comparingInt((Student s) -> Integer.parseInt(s.getSno())));
                break;
            case 3:
                // 총점은 이미 int 타입이므로 바로 내림차순 정렬
                sortingStudents.sort(Comparator.comparingInt(Student::getTotal).reversed());
                break;
            case 4:
                // 학점 오름차순 정렬 (문자열 또는 char 비교)
                sortingStudents.sort(Comparator.comparing(Student::getGrade));
                break;
            default:
                System.out.println("잘못된 선택입니다. 기본 학생번호 내림차순 정렬을 적용합니다.");
                sortingStudents.sort(Comparator.comparingInt((Student s) -> Integer.parseInt(s.getSno())).reversed());
                break;
        }
        return sortingStudents;
    }

    @Override
    public void saveStuData() {
        // StudentManager에서는 파일 저장은 StudentFileIO가 담당하므로 빈 구현
    }

    @Override
    public List<Student> getStuData() {
        // StudentManager의 getStuData()는 메모리상의 studentDataList를 반환
        return studentDataList;
    }
}
