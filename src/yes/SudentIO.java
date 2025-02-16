package yes;

import java.util.List;

public interface SudentIO extends StudentInput, SearchSutent, SortedStudent {
    List<String> saveStudntData(Student student);
    List<Student> getStudntData();
}
