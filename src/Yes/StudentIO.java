package Yes;

import java.util.List;

public interface StudentIO extends SearchStudent,SortedStudent,StudentInput{
    public void saveStuData();
    public List<Student> getStuData();
}
