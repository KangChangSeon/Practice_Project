import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileIO extends StudentDBIO {
    private String filePath = "C:\\Users\\java\\Practice_Project\\src\\StudentDBFile.txt";


    public void saveStudentInfo() {
        try { //파일이 존재하지 않거나 용량이 다 차서 더이상 데이터를 저장할 수 없을 경의 예외 처리
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true)); //파라미터의 파일명의 파일에 데이터를 추가(fales 시 덮어쓰기 됨.)
            for (Student student : students) writer.write(student.toString());
            writer.newLine(); // 줄바꿈
            System.out.println("file save successful");
            writer.flush();//버퍼비우기
            writer.close();//버퍼 종료
        } catch (IOException e) {
            System.out.println("file save fail");
            e.printStackTrace();
        }
    }

    public List<String> loadStudentInfo() {
        List<String> loadinfoList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                loadinfoList.add(line);
            }// 한 줄 그대로 리스트에 추가
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadinfoList;
    }
}