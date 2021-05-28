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
        StudyRoom sr = new StudyRoom();
        SQLite sqlite = new SQLite();
        int seat_id = 0;
        int initialize = 0;
        int doReserve;
        String student_name;
        int student_grade;

        do {
            System.out.print("現在の予約状況を初期化しますか？（1：はい / 0：いいえ）：");
            initialize = sc.nextInt();
        } while (initialize < 0 || initialize > 1);
        
        if (initialize == 1) {
            try {
                sqlite.reservation_initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        System.out.println("氏名と学年を入力してください");
        student_name = sc.next();
        student_grade = sc.nextInt();
        
        Student student = new Student(student_name, 123456, student_grade); //認証処理みたいなのもあったらいいな（未）

        sr.scan_student(student);
        pauseTime(1000);
        try {
            sqlite.show_using_seat_number();    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // sr.show_using_seat_number();


        do {
            try {
                System.out.print("予約したい座席番号を入力してください（1番~32番）：");
                seat_id = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("座席番号は1~32番から選択してください。");
                sc.next();
            }
        } while (seat_id <= 0 || seat_id >= 33);

        // System.out.println("予約可能か確認中です。少々お待ちください。");
        pauseTime(1000);
        try {
            if(sqlite.checkReservation(seat_id) == true) {
                do {
                    System.out.print(seat_id + "番の座席を予約しますか？（1：はい / 0：いいえ）：");
                doReserve = sc.nextInt();
                }while (doReserve < 0 || doReserve > 1);
                
                sqlite.reserve_seat(seat_id, student.getName(), student.getGrade(), doReserve);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // if(sr.checkReservation(seat_num) == true) {
        //     System.out.print(seat_num + "番の座席を予約しますか？（1：はい / 0：いいえ）：");
        //     doReserve = sc.nextInt();
        //     sr.reserve_seat(seat_num, doReserve);
        // }
        
    }    
}
