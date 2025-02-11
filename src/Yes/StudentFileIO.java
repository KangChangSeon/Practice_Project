package Yes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentFileIO extends StudentDBIO{

    private static final String FILE_PATH = "students.json";
    @Getter List<Student> allStudentsData = new ArrayList<>();


    @Override
    public void saveStuData() {

        List<Student> beforeStudentData = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }

                Gson gson = new Gson();
                Type studentListType = new TypeToken<List<Student>>(){}.getType();
                List<Student> tempList = gson.fromJson(jsonContent.toString(), studentListType);
                if (tempList != null) {
                    beforeStudentData = tempList;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        List<Student> newStudentData = StudentManager.getInstance().getStudentDataList();


        beforeStudentData.addAll(newStudentData);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(beforeStudentData);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Student> getStuData() {
        List<Student> formattedStudents = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다.");
            return formattedStudents;
        }

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type studentListType = new TypeToken<List<Student>>() {}.getType();
        allStudentsData = gson.fromJson(jsonContent.toString(), studentListType);

        return allStudentsData;
    }

    @Override
    public List<Student> SearchStuData() {
        return List.of();
    }

    @Override
    public List<Student> SortStuData(int sort) {
        return List.of();
    }

    @Override
    public String InputStuData() {
        return "";
    }


    @Override
    public void OutputStuData() {
    }
}
