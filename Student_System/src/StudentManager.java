

import java.util.*; // Scanner, List, Map, Comparator 등 유틸리티 클래스.
import java.util.regex.Pattern;

// DBIO 는 학생 데이터 입출력 관련 기능 포함함.
public class StudentManager extends StudentDBIO {

    private final Scanner scanner = new Scanner(System.in);
    /// 왜 final, private으로 생성하는지?
    private final StudentDAO studentDAO = new StudentDAO(); // 데베와 소통할 할 StudentDAO
    // 10자리 숫자인지 검증.
    private static final Pattern SNO_PATTERN = Pattern.compile("^\\d{10}$");
    // 한글 또는 영어 문자만 있는지 검증.
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");
    // 메뉴 선택과 실행할 동작을 매핑하는 해쉬맵 생성. 키는 메뉴 번호, 값(Runnable)은 실행할 메소드 (람다식).
    private final Map<Integer, Runnable> menuChoice = new HashMap<>();

    // 매니저 객체가 생성되는 순간 자동으로 mainMenu() 메소드가 호출하여 메뉴 선택 맵을 초기화함.
    /// '초기화'할필요가 있나?
    public StudentManager() {
        initializeMenu();
    }

    // menuChoice 맵에 메뉴번호와 해당 기능을 실행하는 람다식을 매핑함.
    ///
    private void initializeMenu() {
        menuChoice.put(1, () -> this.inputStudent()); // 학생 정보 입력
        menuChoice.put(2, this::deleteStudentInfo); // 학생 정보 삭제
        menuChoice.put(3, this::searchBySno); // 학번으로 학생 검색
        menuChoice.put(4, this::sortStudents); // 학생 정렬하기
        menuChoice.put(5, this::exitApp); // 프로그램 종료.
    }

    // 프로그램 종료.
    private void exitApp() {
        System.exit(0);
    }

    /// startMenuloop
    public void startMenuLoop () {
        // 무한 루프 , 5 입력될 때까지 프로그램 종료하지 않음.
        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input); // 사용자 입력 정수로 변환
            } catch (NumberFormatException e) { // 숫자가 아니면 예외 발생.
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                /// while과 컨티뉴가 어떻게 해석되는지 확인하기??
                continue; // 다시 입력 받음.
            }
            // 사용자가 입력한 메뉴번호(choice)에 해당하는 Runnable 가져와 실행함.
            Runnable action = menuChoice.get(choice); // menuChoice에서 'choice' key를 가져온다
            if (action != null) {
                action.run(); // 널값이 아니면 Runnable인 액션을 run(). run()은 Runnable의 메소드.
            } else {
                // 유효한 메뉴 번호가 아닐 경우 메시지 출력.
                System.out.println("재입력");
            }
        }
    }

    ///  왜 private 일까?
    private void printMenu() {
        System.out.println("1. add student info");
        System.out.println("2. view student info");
        System.out.println("3. search student info");
        System.out.println("4. sort student info");
        System.out.println("5. exit");
        System.out.println("choice menu");
    }

    // 문자열검증
    private String validateString(String prompt, Pattern pattern, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            // 사용자가 입력한 pattern이 정규표현식 패턴과 일치하는지 검사하기.
            if (pattern.matcher(input).matches()) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }
    // 숫자 검증
    private int validateInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input); // 입력을 정수로 바꿈.
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println(min + "~" + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
        }
    }

    ///  메소드가 너무 큰것같아서 쪼개야 할 것 같음.
    @Override
    public void inputStudent() {
        System.out.println("학생정보를 저장합니다. ");
        // 문자열 검증하는 메소드 readValidatedString, 반환값은 검증된 스트링.
        /// 입력은 어디서 받는거지?
        String sno = validateString("sno (10자리수): ", SNO_PATTERN, "정확히 10자리 수 재입력");

        // 데베와 연결된 studentDAO 객체에서 sno에 맞는 학생 찾기.
        Student existingStudent = studentDAO.findStudentBySno(sno);
        if (existingStudent == null) {
            // sno로 학생을 못찾았으면
            // 신규 학생 정보를 모두 입력한다.
            // 그 전에, 입력된 데이터를 validate 하기. 검증기준 통과하면 입력값 반환.
            String name = validateString("name (한, 영): ", NAME_PATTERN, "한, 영문으로 재입력");
            int korean = validateInt("korean: ", 0, 100);
            int english = validateInt("english: ", 0, 100);
            int math = validateInt("math: ", 0, 100);
            int science = validateInt("science: ", 0, 100);

            // 검증된 데이터로 학생 객체 만들기. (객체 만들때 이름은 생각해보니 따로 정하지 않아도 됨)
            Student student = new Student.StudentBuilder()
                    .sno(sno)
                    .name(name)
                    .addSubject("korean", korean)
                    .addSubject("english", english)
                    .addSubject("math", math)
                    .addSubject("science", science)
                    .build();
            // 데베와 연결된 studentDAO에 , studentDAO에 선언된 save메소드를 이용해, student 객체 저장하기.
            studentDAO.saveToDB(student);

            // 학생 개체가 담겨있는 students arraylist에 학생 개체 저장하기.
            students.add(student);
        } else { // sno로 학생 개체를 찾았으면
            // 호출되는 값으로 데이터 업데이트.
            System.out.println("already registered student: " + existingStudent.getName()); // getName인데 sno?
            System.out.println("1.edit all info");
            System.out.println("2.edit subject score");
            System.out.println("3.exit add");

            String option = scanner.nextLine().trim();
            if (("1").equals(option)) {
                String name = validateString("name (한, 영): ", NAME_PATTERN, "한, 영문으로 재입력");
                int korean = validateInt("korean: ", 0, 100);
                int english = validateInt("english: ", 0, 100);
                int math = validateInt("math: ", 0, 100);
                int science = validateInt("science: ", 0, 100);

                Student student = new Student.StudentBuilder()
                        .sno(sno)
                        .name(name)
                        .addSubject("korean", korean)
                        .addSubject("english", english)
                        .addSubject("math", math)
                        .addSubject("science", science)
                        .build();
                studentDAO.updateStudent(student);
                updateInMemoryStudent(student);
            } else if ("2".equals(option)) {
                int korean = validateInt("korean: ", 0, 100);
                int english = validateInt("english: ", 0, 100);
                int math = validateInt("math: ", 0, 100);
                int science = validateInt("science: ", 0, 100);

                Student student = new Student.StudentBuilder()
                        .sno(sno)
                        .name(existingStudent.getName())
                        .addSubject("korean", korean)
                        .addSubject("english", english)
                        .addSubject("math", math)
                        .addSubject("science", science)
                        .build();
                studentDAO.updateStudentScores(student);
                updateInMemoryStudent(student);
            } else if ("3".equals(option)){
                System.out.println("exit");
                return;
            } else {
                System.out.println("잘못된 입력");
            }
        }
        System.out.println("success");
    }

    // 메모리 내 리스트 업데이트 (동일 학번 학생 제거 후 새 객체 추가)
    private void updateInMemoryStudent(Student student) {
        students.removeIf(s -> s.getSno().equals(student.getSno()));
        students.add(student);
    }

    @Override
    public void outputStudent() {
        studentDAO.getAllStudents().forEach(System.out::println);
    }

    public void deleteStudentInfo() {
        outputStudent();

        System.out.print("삭제할 학생의 학번을 입력 (삭제하지 않으려면 그냥 엔터): ");
        String deleteSno = scanner.nextLine().trim();

        if (!deleteSno.isEmpty()) {
            deleteStudent(deleteSno);
        } else {
            System.out.println("삭제 없이 종료합니다");
        }
    }

    private void deleteStudent(String sno) {
        studentDAO.delete(sno);
        students.removeIf(s -> s.getSno().equals(sno));
        System.out.println("삭제 완료");
    }

    @Override
    public void searchBySno() {
        System.out.print("enter (sno 기준검색) :");
        String searchSno = scanner.nextLine().trim();

        Student foundStudent = studentDAO.findStudentBySno(searchSno);

        if (foundStudent == null) {
            System.out.println("no " + searchSno);
        } else {
            System.out.println("result");
            System.out.println(foundStudent);
        }
    }

    public void sortStudents() {
        System.out.println("Select sorting criteria:");
        System.out.println("1. Sort by total score ");
        System.out.println("2. Sort by sno ");
        String option = scanner.nextLine().trim();

        int sortChoice;
        try {
            sortChoice = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력");
            return;
        }

        List<Student> studentList = studentDAO.getAllStudents();
        if (studentList.isEmpty()) {
            System.out.println("DB에 저장된 학생 데이터가 없습니다.");
            return;
        }

        Map<Integer, Runnable> sortActions = new HashMap<>();
        sortActions.put(1, () -> sortByTotal(studentList));
        sortActions.put(2, () -> sortBySno(studentList));

        Runnable sortAction = sortActions.get(sortChoice);
        if (sortAction == null) {
            System.out.println("잘못된 입력");
            return;
        }
        sortAction.run();

        System.out.println("Sorted Students:");
        studentList.forEach(System.out::println);
    }

    @Override
    public void sortByTotal(List<Student> studentList) {
        studentList.sort(Comparator.comparingInt(Student::getTotal).reversed());
        System.out.println("Sorted by total score (descending):");
    }
    @Override
    public void sortBySno(List<Student> studentList) {
        studentList.sort(Comparator.comparing(Student::getSno));
        System.out.println("Sorted by sno:");
    }
}
