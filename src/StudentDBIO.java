import lombok.Getter;

import java.io.File;
import java.util.List;

public abstract class StudentDBIO implements SudentIO{

    private static final StudentFileIO INSTANCE = new StudentFileIO("C:\\Users\\java\\Practice_Project\\src\\StudentDBFile");

    private  StudentFileIO StudentFileIO(){
        //학생정보가 저장된 텍스트 파일 반환
        return INSTANCE;
    }
    public StudentFileIO getInstance(){
        return getInstance();
    }

}
