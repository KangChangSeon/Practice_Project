package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 파일 입출력을 통해 학생 정보를 저장하고 불러오는 기능을 제공하는 클래스입니다.
 * {@link StudentDBIO}를 상속받습니다.
 */
public class StudentFileIO extends StudentDBIO {
    private String filePath = "StudentDBFile.txt";

    /**
     * 학생 정보를 파일에 저장합니다.
     */
    public void saveStudentInfo() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Student student : StudentManager.getStudents()) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("파일 저장 성공");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("파일 저장 실패");
            e.printStackTrace();
        }
    }

    /**
     * 파일에서 학생 정보를 불러옵니다.
     */
    public void loadStudentInfo() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                if (student != null) {
                    StudentManager.getStudents().add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("학생 정보 로드 성공");
    }

    /**
     * 파일에서 읽어들인 문자열 데이터를 파싱하여 {@link Student} 객체를 생성합니다.
     *
     * @param data 학생 정보를 담고 있는 문자열
     * @return 파싱된 Student 객체 (실패 시 null 반환)
     */
    private Student parseStudent(String data) {
        if (data == null || data.isEmpty()) return null;

        try {
            Pattern pattern = Pattern.compile("sno='(.*?)', name='(.*?)', subjects=\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(data);
            if (matcher.find()) {
                String sno = matcher.group(1);
                String name = matcher.group(2);
                String subjectsStr = matcher.group(3);

                Student.StudentBuilder builder = new Student.StudentBuilder()
                        .sno(sno)
                        .name(name);

                if (subjectsStr != null && !subjectsStr.trim().isEmpty()) {
                    String[] subjectEntries = subjectsStr.split(",\\s*");
                    for (String entry : subjectEntries) {
                        String[] parts = entry.split(":");
                        if (parts.length == 2) {
                            String subjectName = parts[0];
                            int score = Integer.parseInt(parts[1]);
                            builder.addSubject(subjectName, score);
                        }
                    }
                }
                return builder.build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
