package Yes;

import java.util.List;

public class StudentFileIO extends StudentDBIO{

    @Override
    public List<String> saveStuData() {
        return List.of();
    }

    @Override
    public List<String> getStudData() {
        return List.of();
    }

    @Override
    public String[] SearchStuData() {
        return new String[0];
    }

    @Override
    public String[] SortStuData() {
        return new String[0];
    }

    @Override
    public String InputStuData() {
        return "";
    }

    @Override
    public String[] OutputStuData() {
        return new String[0];
    }
}
