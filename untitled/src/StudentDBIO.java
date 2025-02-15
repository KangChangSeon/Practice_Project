import java.util.ArrayList;
import java.util.List;

// DBIO 가 ObjectIO
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
