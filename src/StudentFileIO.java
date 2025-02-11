import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFileIO extends StudentDBIO{

    private String filePath ;//파일 제목

    public StudentFileIO(){}

    public StudentFileIO(String filePath) {
        this.filePath = filePath;
        saveStudentInfo();
    }

    //리스트를 파일에 저장
    public void saveStudentInfo(){
        List<String> studentList = StudentManager.getinstance().studentList; // 매니저 통해 리스트 불러옴
        try { //파일이 존재하지 않거나 용량이 다 차서 더이상 데이터를 저장할 수 없을 경의 예외 처리
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true)); //파라미터의 파일명의 파일에 데이터를 추가(fales 시 덮어쓰기 됨.)
           for(String studentIO :studentList){ //for each
               writer.write(studentIO);
           }
            writer.newLine(); // 줄바꿈
            System.out.println("학생 정보를 파일에 저장하였습니다.");
            writer.flush();//버퍼비우기
            writer.close();//버퍼 종료
        } catch (IOException e) {
            System.out.println("파일 저장에 실패 했습니다. ");
            e.printStackTrace();
        }
    }

    public List<String> loadStudentInfo() {
        List<String> loadinfoList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                loadinfoList.add(line); // 한 줄 그대로 리스트에 추가
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadinfoList;
    }





    @Override
    public List<String> saveStudntData() {
        return List.of();
    }

    @Override
    public List<Student> getStudntData() {
        return List.of();
    }

    @Override
    public List<String> searchStudnt(String keyword) {
        return List.of();
    }

    @Override
    public List<String> sortStuByIdAsce() {
        return List.of();
    }

    @Override
    public List<String> sortStuByIdDesc() {
        return List.of();
    }

    @Override
    public List<String> sortStuByAverage() {
        return List.of();
    }

    @Override
    public List<String> SortStuByGradeDesc() {
        return List.of();
    }

    @Override
    public List<String> SortStuByScoreDesc() {
        return List.of();
    }

    @Override
    public Student inputstuData() {
        return null;
    }

    @Override
    public List<String> printStudentDta(Student student) {
        return List.of();
    }

    @Override
    public String printStudentbyName(Student name) {
        return "";
    }

    @Override
    public String printAllStudent(List<Student> students) {
        return "";
    }
}
