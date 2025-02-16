package yes;

import java.util.List;

public interface SortedStudent {
    List<Student> sortStuById();
    List<Student> sortStuByAverage();
    List<Student> sortStuByGrade();
    List<Student> sortStuByScore();
    List<Student> sortStuByName();

}
