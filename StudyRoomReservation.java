import java.util.InputMismatchException;
import java.util.Scanner;

public class StudyRoomReservation {

    static void pauseTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudyRoom sr = new StudyRoom();
        // Student A = new Student("平澤", 123456, 1);
        // Student B = new Student("吉井", 234567, 2);
        // Student C = new Student("西川", 345678, 2);
        // Student D = new Student("中山", 456789, 1);
        int seat_num = 0;;
        int doReserve;
        String student_name;
        int student_grade;

        System.out.println("氏名と学年を入力してください");
        student_name = sc.next();
        student_grade = sc.nextInt();
        
        Student student = new Student(student_name, 123456, student_grade);

        sr.scan_student(student);
        pauseTime(1000);
        sr.show_using_seat_number();
        do {
            try {
                System.out.print("予約したい座席番号を入力してください（1番~32番）：");
                seat_num = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("座席番号は1~32番から選択してください。");
                sc.next();
            }
        } while (seat_num <= 0 || seat_num >= 33);

        System.out.println("予約可能かどうか調べるので、少々お待ちください。");
        pauseTime(2000);

        if(sr.checkReservation(seat_num) == true) {
            System.out.print(seat_num + "番の座席を予約しますか？（1：はい / 0：いいえ）：");
            doReserve = sc.nextInt();
            sr.reserve_seat(seat_num, sc.nextInt());
        }
        
    }    
}
