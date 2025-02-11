package Yes;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = StudentManager.getInstance();
        StudentFileIO fileIO = new StudentFileIO();

        manager.OutputStuData();


    }
}
