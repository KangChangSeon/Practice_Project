package yes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class StudentFileIO extends StudentDBIO {

    private String filePath ;


    public StudentFileIO() {
        this.filePath = StudentDBIO.getInstance().getFilePath();
    }
    public StudentFileIO(String filePath){
        this.filePath = filePath;
    }
    public String getFilePath() {
        return filePath;
    }
    //리스트를 파일에 저장
    public void saveStudentInfo(){
        List<String> studentList = StudentManager.getinstance().getStudentList(); // 매니저 통해 리스트 불러옴
        try { //파일이 존재하지 않거나 용량이 다 차서 더이상 데이터를 저장할 수 없을 경의 예외 처리
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true)); //파라미터의 파일명의 파일에 데이터를 추가(fales 시 덮어쓰기 됨.)
            for (String student : studentList) writer.write(student);
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
                loadinfoList.add(line);
            }// 한 줄 그대로 리스트에 추가
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadinfoList;
    }





    @Override
    public List<String> saveStudntData(Student student) {
        return List.of();
    }

    @Override
    public List<Student> getStudntData() {
        return List.of();
    }

    @Override
    public List<Student> searchStudnt() {
        return List.of();
    }


    @Override
    public List<Student> sortStuById() {
        return List.of();
    }

    @Override
    public List<Student> sortStuByAverage() {
        return List.of();
    }

    @Override
    public List<Student> sortStuByGrade() {
        return List.of();
    }
    @Override
    public List<Student> sortStuByName() {
        return List.of();
    }

    @Override
    public List<Student> sortStuByScore() {
        return List.of();
    }

    @Override
    public void inputstuData() {
    }

    @Override
    public List<Student> printStudentDta(List<Student> searchFoundStudents) {
        return List.of();
    }

    @Override
    public List<Student> printStudentbyName(List<Student> searchFoundStudents) {
        return  List.of();
    }

    @Override
    public List<Student> printAllStudent() {
        return   List.of();
    }
}
