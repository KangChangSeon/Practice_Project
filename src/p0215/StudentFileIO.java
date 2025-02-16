package p0215;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentFileIO extends StudentDBIO {
    private String filePath = "StudentDBFile.txt";

    public void saveStudentInfo() {
        try {
            // 파일에 데이터를 추가하기 위해 true 사용 (false이면 덮어쓰기)
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("file save successful");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("file save fail");
            e.printStackTrace();
        }
    }

    /**
     * 파일에 저장된 각 줄(Student.toString() 결과)을 읽어서 Student 객체로 변환 후,
     * StudentManager의 students 리스트에 추가합니다.
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
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("학생 정보 로드 성공");
    }

    /**
     * Student.toString()의 출력 형식(예:
     * "sno='12345', name='John', subjects=[Math:90, English:80], total=170, average=85.0, grade=B")
     * 을 기반으로 문자열을 파싱하여 Student 객체를 생성합니다.
     */
    private Student parseStudent(String data) {
        if (data == null || data.isEmpty()) return null;

        try {
            // 정규표현식을 사용해 sno, name, subjects 부분을 추출합니다.
            // 전체 형식이 변경되면 패턴 수정이 필요합니다.
            Pattern pattern = Pattern.compile("sno='(.*?)', name='(.*?)', subjects=\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(data);
            if (matcher.find()) {
                String sno = matcher.group(1);
                String name = matcher.group(2);
                String subjectsStr = matcher.group(3);

                // StudentBuilder를 이용하여 객체 생성
                Student.StudentBuilder builder = new Student.StudentBuilder()
                        .sno(sno)
                        .name(name);

                // subjects가 비어있지 않다면, 콤마로 분리하여 각 과목을 추가합니다.
                if (subjectsStr != null && !subjectsStr.trim().isEmpty()) {
                    String[] subjectEntries = subjectsStr.split(",\\s*");
                    for (String entry : subjectEntries) {
                        // 각 항목은 "과목명:점수" 형식입니다.
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