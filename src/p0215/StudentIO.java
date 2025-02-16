package p0215;

import java.util.List;

public interface StudentIO extends StudentInput, SearchStudent, SortedStudent {
    List<Student> getStudntData();
}
