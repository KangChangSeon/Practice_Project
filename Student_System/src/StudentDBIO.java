

import java.util.ArrayList;
import java.util.List;
// DBIO에서는 StudentIO를 구현 - StudentIO가 상속받는 인터페이스들의 메소드 모두 구현해야 함.
public class StudentDBIO extends ObjectIO implements StudentIO{

    public List<Student> students = new ArrayList<>(); // 학생 객체가 담겨있는 students

    ///  메소드 비게 놔둘거면 왜 오버라이딩 하는거지?
    @Override
    public void inputStudent() {}

    @Override
    public void outputStudent() {}

    @Override
    public void searchBySno() {}

    @Override
    public void sortByTotal(List<Student> students) {}

    @Override
    public void sortBySno(List<Student> students) {}
}
