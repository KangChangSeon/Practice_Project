package Yes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class StudentDBIO implements StudentIO {

    // 변경: 고정된 파일 경로 대신 공통 메서드를 통해 파일 경로를 반환합니다.
    protected String getFilePath() {
        return "students.json";
    }

    // 변경: 파일을 읽어오는 공통 메서드 추가
    protected String readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return "";
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // 변경: 파일에 쓰는 공통 메서드 추가
    protected void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 변경: Gson 인스턴스를 생성하는 공통 메서드 (Pretty Printing 포함)
    protected Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    // 변경: JSON 문자열을 객체로 변환하는 공통 메서드
    protected <T> T fromJson(String json, java.lang.reflect.Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }

    // 변경: 객체를 JSON 문자열로 변환하는 공통 메서드
    protected String toJson(Object src) {
        return getGson().toJson(src);
    }
}
