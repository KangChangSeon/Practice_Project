package Yes;

import java.util.List;

public interface StudentIO extends SearchStudent,SortedStudent,StudentInput{
    public List<String> saveStuData();
    public List<String> getStudData();
}
