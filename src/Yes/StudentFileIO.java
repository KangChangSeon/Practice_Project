package Yes;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentFileIO extends StudentDBIO {

    @Override
    public void saveStuData() {
        List<Student> existingData = new ArrayList<>();
        String filePath = getFilePath();
        String jsonContent = readFile(filePath);
        if (!jsonContent.isEmpty()) {
            Type listType = new TypeToken<List<Student>>() {}.getType();
            List<Student> temp = fromJson(jsonContent, listType);
            if (temp != null) {
                existingData = temp;
            }
        }

        List<Student> newData = StudentManager.getInstance().getStudentDataList();
        existingData.addAll(newData);
        String jsonArray = toJson(existingData);
        writeFile(filePath, jsonArray);
    }

    @Override
    public List<Student> getStuData() {
        List<Student> data = new ArrayList<>();
        String filePath = getFilePath();
        String jsonContent = readFile(filePath);
        if (jsonContent.isEmpty()) {
            System.out.println("파일이 존재하지 않거나 데이터가 없습니다.");
            return data;
        }
        Type listType = new TypeToken<List<Student>>() {}.getType();
        data = fromJson(jsonContent, listType);
        return data;
    }

    @Override
    public List<Student> SearchStuData() {
        return new ArrayList<>();
    }

    @Override
    public List<Student> SortStuData(int sort) {
        return new ArrayList<>();
    }

    @Override
    public String InputStuData() {
        return "";
    }

    @Override
    public void OutputStuData() {
    }
}
