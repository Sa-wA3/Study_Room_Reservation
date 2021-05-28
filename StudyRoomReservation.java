import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        ReserveOperation ro = new ReserveOperation();

        int initialize = 0;
        do {
            System.out.print("現在の予約状況を初期化しますか？（1：はい / 0：いいえ）：");
            initialize = sc.nextInt();
        } while (initialize < 0 || initialize > 1);
        
        if (initialize == 1) {
            try {
                ro.reservation_initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        System.out.print("氏名を入力してください：");
        String student_name = sc.next();
        System.out.print("学年を入力してください：");
        int student_grade = sc.nextInt();
        System.out.print("生徒番号をを入力してください：");
        int student_id = sc.nextInt();
        Student student = new Student(student_name, student_id, student_grade); //認証処理みたいなのもあったらいいな：studentテーブル作成（予定）

        ro.scan_student(student);
        
        pauseTime(1000);

        try {
            ro.show_using_seat_number();    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        

        int seat_id = 0;;
        do {
            try {
                System.out.print("予約したい座席番号を入力してください（1番~32番）：");
                seat_id = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("座席番号は1~32番から選択してください。");
                sc.next();
            }
        } while (seat_id <= 0 || seat_id >= 33);

        pauseTime(1000);

        int doReserve;
        try {
            if(ro.checkReservation(seat_id) == true) {
                do {
                    System.out.print(seat_id + "番の座席を予約しますか？（1：はい / 0：いいえ）：");
                doReserve = sc.nextInt();
                }while (doReserve < 0 || doReserve > 1);
                
                ro.reserve_seat(seat_id, student.getName(), student.getGrade(), doReserve);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }    
}
