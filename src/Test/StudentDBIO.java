package Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDBIO extends ObjectIO implements StudentIO{

    @Override
    public void inputStudent() {}

    @Override
    public void outputStudent() {}

    @Override
    public void searchStudentByName() {}

    @Override
    public void sortByTotal(List<Student> students) {}

    @Override
    public void sortBySno(List<Student> students) {}
}
