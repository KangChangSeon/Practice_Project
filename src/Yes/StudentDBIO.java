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

    protected String getFilePath() {
        return "students.json";
    }

    protected String readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return "파일을 읽어옵니다";
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

    protected void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }


    protected <T> T fromJson(String json, java.lang.reflect.Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }

    protected String toJson(Object src) {
        return getGson().toJson(src);
    }
}
