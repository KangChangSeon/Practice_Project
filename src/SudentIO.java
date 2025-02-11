import java.util.List;

public interface SudentIO extends StudentInput,SearchSutent,SortedStudent{
    List<String> saveStudntData();
    List<Student> getStudntData();
}
