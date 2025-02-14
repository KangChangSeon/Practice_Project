package Test;

import java.util.List;

/**
 * 학생 데이터의 입출력 기능을 제공하는 클래스입니다.
 * {@link ObjectIO}를 확장하며, {@link StudentIO} 인터페이스를 구현합니다.
 */
public class StudentDBIO extends ObjectIO implements StudentIO {

    /**
     * 학생 정보를 입력받는 기능을 수행합니다.
     */
    @Override
    public void inputStudent() {
        // 구현 내용
    }

    /**
     * 학생 정보를 출력하는 기능을 수행합니다.
     */
    @Override
    public void outputStudent() {
        // 구현 내용
    }

    /**
     * 학생 정보를 이름으로 검색하는 기능을 수행합니다.
     */
    @Override
    public void searchStudentByName() {
        // 구현 내용
    }

    /**
     * 학생 목록을 총점 기준으로 정렬하는 기능을 수행합니다.
     *
     * @param students 정렬할 학생 목록
     */
    @Override
    public void sortByTotal(List<Student> students) {
        // 구현 내용
    }

    /**
     * 학생 목록을 학번 기준으로 정렬하는 기능을 수행합니다.
     *
     * @param students 정렬할 학생 목록
     */
    @Override
    public void sortBySno(List<Student> students) {
        // 구현 내용
    }
}
