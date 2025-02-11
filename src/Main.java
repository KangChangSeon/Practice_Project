public class Main {
    public static void main(String[] args) {
        StudentManager.getinstance().inputstuData();
        StudentManager.getinstance().saveStudntData();
        StudentManager.getinstance().inputstuData();
        StudentManager.getinstance().saveStudntData();
        System.out.println(StudentManager.getinstance().studentList.toString());



    }
}
