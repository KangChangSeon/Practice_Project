package Yes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager extends StudentDBIO {
    private static final StudentManager INSTANCE = new StudentManager();
    private List<String> studentDataList = new ArrayList<>();

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

        return student.toString();
    }

    @Override
    public String[] OutputStuData() {
        return new String[0];
    }

    @Override
    public List<String> saveStuData() {
        return studentDataList;
    }

    @Override
    public List<String> getStudData() {
        return List.of();
    }

    @Override
    public String[] SearchStuData() {
        return new String[0];
    }

    @Override
    public String[] SortStuData() {
        return new String[0];
    }
}
