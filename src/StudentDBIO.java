import java.util.List;

public abstract class StudentDBIO implements SudentIO{

    public StudentFileIO getInstance(){
        return getInstance();
    }

    public abstract List<Student> saveStudntData();

    static class StudentIFileIO {
    }
}
