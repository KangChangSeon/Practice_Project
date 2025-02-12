package Yes;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentFileIO extends StudentDBIO {

    @Override
    public void saveStuData() {
        // 기존 데이터를 파일에서 읽어오기 위해 DBIO의 공통 메서드 사용
        List<Student> existingData = new ArrayList<>();
        String filePath = getFilePath(); // 변경: DBIO의 getFilePath() 사용
        String jsonContent = readFile(filePath); // 변경: DBIO의 readFile() 사용
        if (!jsonContent.isEmpty()) {
            Type listType = new TypeToken<List<Student>>() {}.getType();
            List<Student> temp = fromJson(jsonContent, listType); // 변경: DBIO의 fromJson() 사용
            if (temp != null) {
                existingData = temp;
            }
        }
        // 새로운 데이터는 StudentManager의 메모리 데이터를 사용
        List<Student> newData = StudentManager.getInstance().getStudentDataList();
        existingData.addAll(newData);
        String jsonArray = toJson(existingData); // 변경: DBIO의 toJson() 사용
        writeFile(filePath, jsonArray); // 변경: DBIO의 writeFile() 사용
    }

    @Override
    public List<Student> getStuData() {
        List<Student> data = new ArrayList<>();
        String filePath = getFilePath(); // 변경: DBIO의 getFilePath() 사용
        String jsonContent = readFile(filePath); // 변경: DBIO의 readFile() 사용
        if (jsonContent.isEmpty()) {
            System.out.println("파일이 존재하지 않거나 데이터가 없습니다.");
            return data;
        }
        Type listType = new TypeToken<List<Student>>() {}.getType();
        data = fromJson(jsonContent, listType); // 변경: DBIO의 fromJson() 사용
        return data;
    }

    @Override
    public List<Student> SearchStuData() {
        // 아직 구현되지 않음
        return new ArrayList<>();
    }

    @Override
    public List<Student> SortStuData(int sort) {
        // 아직 구현되지 않음
        return new ArrayList<>();
    }

    @Override
    public String InputStuData() {
        return "";
    }

    @Override
    public void OutputStuData() {
        // 파일 I/O 클래스에서는 출력 기능 필요 없음
    }
}
