import java.util.ArrayList;
import java.util.List;

// DBIO 가 ObjectIO 상속 받기.
// 데이터베이스에서 수행할 기능들 표준화 하기.
public class StudentDBIO extends ObjectIO implements StudentIO{
    public List<Student> students = new ArrayList<>();


    @Override // from searchStu<I>
    public void searchByName() {

    }

    @Override
    public void inputStudent() {

    }

    @Override
    public void outputStudent() {

    }

    @Override
    public void sortByTotal(List<Student> students) {

    }

    @Override
    public void sortBySno(List<Student> students) {

    }

    @Override
    public void sortByName(List<Student> students) {

    }
}
