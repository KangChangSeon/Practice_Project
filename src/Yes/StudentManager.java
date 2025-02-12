package Yes;

import Yes.Student;
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

        String sno = checkSno(sc);

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();

        int korean = checkScore(sc, "국어");
        int english = checkScore(sc, "영어");
        int math = checkScore(sc, "수학");
        int science = checkScore(sc, "과학");

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

    private String checkSno(Scanner sc) {
        while (true) {
            String sno;
            System.out.print("학번을 입력하세요: ");
            sno = sc.nextLine();

            StudentFileIO fileIO = new StudentFileIO();
            List<Student> fileStudents = fileIO.getStuData();

            boolean check = fileStudents.stream().anyMatch(s -> s.getSno().equals(sno))
                    || studentDataList.stream().anyMatch(s -> s.getSno().equals(sno));
            if (check) {
                System.out.println("이미 존재하는 학번입니다. 다시 입력해주세요.");
            } else {
                return sno;
            }
        }
    }

    private int checkScore(Scanner sc, String subject) {
        int score;
        while (true) {
            System.out.print(subject + " 점수를 입력하세요. : ");
            try {
                score = Integer.parseInt(sc.nextLine());
                if (score < 0 || score > 100) {
                    System.out.println("점수는 0 ~ 100 사이의 정수여야 합니다.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("유효한 정수를 입력하세요.");
            }
        }
        return score;
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
                    int sortchoice = 0;
                    while (true) {
                        System.out.println("정렬 기준을 입력하세요:");
                        System.out.println("1번 학번 | 2번 이름 | 3번 총점 | 4번 학점");
                        try {
                            sortchoice = Integer.parseInt(scanner.nextLine());
                            if (sortchoice >= 1 && sortchoice <= 4) {
                                break;
                            } else {
                                System.out.println("잘못된 번호입니다. 1부터 4 사이의 숫자를 입력해주세요.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("유효한 숫자를 입력해주세요.");
                        }
                    }

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
        String ck;
        StudentFileIO fileIO = new StudentFileIO();
        List<Student> sortingStudents = fileIO.getStuData();
        Scanner scanner = new Scanner(System.in);

        switch (choice) {
            case 1:
                while (true) {
                    System.out.println("1번 오름차순 | 2번 내림차순");
                    ck = scanner.nextLine();
                    if (ck.equals("1")) {
                        sortingStudents.sort(Comparator.comparingInt((Student s) -> Integer.parseInt(s.getSno())).reversed());
                        break;
                    } else if (ck.equals("2")) {
                        sortingStudents.sort(Comparator.comparingInt((Student s) -> Integer.parseInt(s.getSno())));
                        break;
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                }
                break;
            case 2:
                while (true) {
                    System.out.println("1번 오름차순 | 2번 내림차순");
                    ck = scanner.nextLine();
                    if (ck.equals("1")) {
                        sortingStudents.sort(Comparator.comparing(Student::getName).reversed());
                        break;
                    } else if (ck.equals("2")) {
                        sortingStudents.sort(Comparator.comparing(Student::getName));
                        break;
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                }
                break;
            case 3:
                while (true) {
                    System.out.println("1번 오름차순 | 2번 내림차순");
                    ck = scanner.nextLine();
                    if (ck.equals("1")) {
                        sortingStudents.sort(Comparator.comparingInt(Student::getTotal).reversed());
                        break;
                    } else if (ck.equals("2")) {
                        sortingStudents.sort(Comparator.comparingInt(Student::getTotal));
                        break;
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                }
                break;
            case 4:
                while (true) {
                    System.out.println("1번 오름차순 | 2번 내림차순");
                    ck = scanner.nextLine();
                    if (ck.equals("1")) {
                        sortingStudents.sort(Comparator.comparing(Student::getGrade).reversed());
                        break;
                    } else if (ck.equals("2")) {
                        sortingStudents.sort(Comparator.comparing(Student::getGrade));
                        break;
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                }
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