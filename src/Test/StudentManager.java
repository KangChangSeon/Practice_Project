package Test;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 학생 정보를 관리하는 클래스입니다.
 * 학생 추가, 조회, 검색, 정렬 기능을 제공하며, {@link StudentDBIO}를 확장합니다.
 */
public class StudentManager extends StudentDBIO {

    @Getter
    private static List<Student> students = new ArrayList<>();

    private StudentFileIO studentFileIO = new StudentFileIO();
    private Scanner scanner = new Scanner(System.in);

    private static final Pattern SNO_PATTERN = Pattern.compile("^\\d{6}$");  // 학번은 6자리 숫자
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$"); // 한글 및 영문 허용

    /**
     * StudentManager 객체를 생성하며, 파일에서 학생 정보를 미리 로드합니다.
     */
    public StudentManager() {
        studentFileIO.loadStudentInfo();
    }

    /**
     * 메인 메뉴를 출력하고 사용자 입력을 처리합니다.
     */
    public void run() {
        System.out.println("1. 학생 정보 추가");
        System.out.println("2. 학생 정보 조회");
        System.out.println("3. 학생 정보 검색");
        System.out.println("4. 학생 정보 정렬");
        System.out.println("5. 종료");
        System.out.println("메뉴 선택:");

        int menu = 0;
        try {
            menu = scanner.nextInt();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
        }
        switch (menu) {
            case 1:
                inputStudent();
                studentFileIO.saveStudentInfo();
                break;
            case 2:
                outputStudent();
                break;
            case 3:
                searchStudentByName();
                break;
            case 4:
                sortStudents();
                break;
            case 5:
                return;
            default:
                System.out.println("재입력 해주세요.");
        }
    }

    /**
     * 사용자로부터 학생 정보를 입력받습니다.
     */
    @Override
    public void inputStudent() {
        System.out.println("학생 추가");

        String sno;
        do {
            System.out.print("학번 (6자리): ");
            sno = scanner.nextLine();
            if (!SNO_PATTERN.matcher(sno).matches()) {
                System.out.println("6자리 숫자를 정확히 입력하세요.");
            }
        } while (!SNO_PATTERN.matcher(sno).matches());

        String name;
        do {
            System.out.print("이름 (한글/영문): ");
            name = scanner.next();
            if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.println("한글 또는 영문으로 입력하세요.");
            }
        } while (!NAME_PATTERN.matcher(name).matches());

        Student.StudentBuilder builder = new Student.StudentBuilder()
                .sno(sno)
                .name(name);

        builder.addSubject("korean", getValidatedScore("korean"));
        builder.addSubject("english", getValidatedScore("english"));
        builder.addSubject("math", getValidatedScore("math"));
        builder.addSubject("science", getValidatedScore("science"));

        students.add(builder.build());

        System.out.println("학생 정보 추가 성공");
        run();
    }

    /**
     * 과목 점수를 검증하여 반환합니다.
     *
     * @param subjectName 과목 이름
     * @return 0부터 100 사이의 유효한 점수
     */
    private int getValidatedScore(String subjectName) {
        int score;
        do {
            System.out.print(subjectName + ": ");
            score = scanner.nextInt();
            if (score < 0 || score > 100) {
                System.out.println("0에서 100 사이의 값을 입력하세요.");
            }
        } while (score < 0 || score > 100);
        return score;
    }

    /**
     * 저장된 학생 정보를 출력합니다.
     */
    @Override
    public void outputStudent() {
        for (Student student : students) {
            System.out.println(student);
        }
        run();
    }

    /**
     * 사용자로부터 입력받은 이름을 기준으로 학생 정보를 검색합니다.
     */
    @Override
    public void searchStudentByName() {
        System.out.print("검색할 이름 입력: ");
        String searchName = scanner.nextLine().trim();

        List<Student> foundStudents = students.stream()
                .filter(s -> containsIgnoreCase(s.getName(), searchName))
                .collect(Collectors.toList());

        if (foundStudents.isEmpty()) {
            System.out.println("검색 결과가 없습니다: " + searchName);
        } else {
            System.out.println("검색 결과:");
            for (Student student : foundStudents) {
                System.out.println(student);
            }
        }
        System.out.println();
        run();
    }

    /**
     * 이름에 검색 문자열이 포함되어 있는지(대소문자 구분 없이) 확인합니다.
     *
     * @param name       비교할 이름
     * @param searchName 검색할 문자열
     * @return 포함되어 있으면 true, 아니면 false
     */
    private boolean containsIgnoreCase(String name, String searchName) {
        return name.toLowerCase().contains(searchName.toLowerCase());
    }

    /**
     * 사용자가 선택한 기준에 따라 학생 목록을 정렬합니다.
     */
    private void sortStudents() {
        System.out.println("정렬 기준 선택:");
        System.out.println("1. 총점 기준 정렬");
        System.out.println("2. 학번 기준 정렬");

        int sortChoice = scanner.nextInt();
        scanner.nextLine();

        switch (sortChoice) {
            case 1:
                sortByTotal(students);
                break;
            case 2:
                sortBySno(students);
                break;
            default:
                System.out.println("잘못된 선택입니다. 메인 메뉴로 돌아갑니다.");
                run();
                return;
        }

        System.out.println("정렬된 학생 목록:");
        for (Student student : students) {
            System.out.println(student);
        }
        run();
    }

    /**
     * 학생 목록을 학번 기준으로 오름차순 정렬합니다.
     *
     * @param students 정렬할 학생 목록
     */
    @Override
    public void sortBySno(List<Student> students) {
        students.sort(Comparator.comparing(Student::getSno));
        System.out.println("학번 기준 정렬:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * 학생 목록을 총점 기준으로 내림차순 정렬합니다.
     *
     * @param students 정렬할 학생 목록
     */
    @Override
    public void sortByTotal(List<Student> students) {
        students.sort(Comparator.comparingInt(Student::getTotal).reversed());
        System.out.println("총점 기준 내림차순 정렬:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
