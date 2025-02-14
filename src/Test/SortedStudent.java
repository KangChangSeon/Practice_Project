package Test;

import java.util.List;

/**
 * 학생 목록을 정렬하는 기능을 제공하는 인터페이스입니다.
 */
public interface SortedStudent {
    /**
     * 총점을 기준으로 학생 목록을 정렬합니다.
     *
     * @param students 정렬할 학생 목록
     */
    void sortByTotal(List<Student> students);

    /**
     * 학번을 기준으로 학생 목록을 정렬합니다.
     *
     * @param students 정렬할 학생 목록
     */
    void sortBySno(List<Student> students);
}
