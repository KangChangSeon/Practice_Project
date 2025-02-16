package yes;

public abstract class StudentDBIO implements SudentIO {

    private static final StudentFileIO INSTANCE = new StudentFileIO("C:\\Users\\java\\Practice_Project\\src\\StudentDBFile.txt");

    public static StudentFileIO getInstance() {
        return INSTANCE;
    }
}
