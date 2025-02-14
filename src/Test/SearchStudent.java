package Test;

/**
 * 학생 정보를 이름으로 검색하는 기능을 제공하는 인터페이스입니다.
 * {@link StudentOutput}을 확장하여 출력 기능도 포함합니다.
 */
public interface SearchStudent extends StudentOutput {
    /**
     * 이름을 기준으로 학생을 검색하는 기능을 수행합니다.
     */
    void searchStudentByName();
}
