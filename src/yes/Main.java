package yes;//import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("*".repeat(40));
        System.out.println("학생 정보 프로그램 시작");
        System.out.println("*".repeat(40));
        while (run) {
            System.out.println("*".repeat(40));
            System.out.println("원하시는 메뉴를 선택해주세요.");
            System.out.println("1.입력 2.출력 3.검색 4.정렬 5.종료");
            System.out.println("*".repeat(40));
            int num1 = sc.nextInt();
            switch (num1) {
                case 1: //입력
                    StudentManager.getinstance().inputstuData();
                    break;
                case 2: //출력
                    StudentManager.getinstance().printAllStudent();
                    break;
                case 3: //검색
                    StudentManager.getinstance().searchStudnt();
                    break;
                case 4: //정렬
                    boolean searchEnd = true;
                    while (searchEnd) {
                        System.out.println("정렬할 기준을 선택해주세요. ");
                        System.out.println("1.학번 2. 이름 3.과목별점수 4.평균점수 5.등급");
                        int num2 = sc.nextInt();
                        sc.nextLine();

                        switch (num2) {
                            case 1:
                                StudentManager.getinstance().sortStuById();
                                searchEnd = false;
                                break;
                            case 2:
                                StudentManager.getinstance().sortStuByName();
                                searchEnd = false;
                                break;
                            case 3:
                                StudentManager.getinstance().sortStuByScore();
                                searchEnd = false;
                                break;
                            case 4:
                                StudentManager.getinstance().sortStuByAverage();
                                searchEnd = false;
                                break;
                            case 5:
                                StudentManager.getinstance().sortStuByGrade();
                                searchEnd = false;
                                break;
                            default:
                                System.out.println("잘못 누르셨습니다. 다시 선택해주세요.");
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("프로그램 종료합니다.");
                    run = false;
                    break;

                default:
                    System.out.println("잘못누르셨습니다. 다시 선택해주세요.");


            }
        }
    }
}
