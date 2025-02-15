import java.util.List;

public interface SortStudent {
    void sortByTotal(List<Student> students); // 리스트 타입(스튜던트 객체 담긴)인 students 받기 // ascending.
    void sortBySno(List<Student> students);   // 리스트 타입(스튜던트 객체 담긴)
    void sortByName(List<Student> students); // Ascending order.
}
