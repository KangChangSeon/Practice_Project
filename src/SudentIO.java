import java.util.List;

public interface SudentIO extends StudentInput,SearchSutent,SortedStudent{
    List<Student> saveStudntData();
    List<String> getStudntData();
}
