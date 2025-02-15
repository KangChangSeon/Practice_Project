import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// 매니저 객체에서 학생 개체에 대한 정보 업데이트.
// DBIO 으로부터 상속 받은 메소드 재정의
public class StudentManager extends StudentDBIO {

    private final Scanner scanner = new Scanner(System.in);
    private final Student student = new Student();

    public void inputStudent() {
        System.out.println("adding student");


    }
}
