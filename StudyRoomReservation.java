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
        String dbname = "reservation.db"; // 利用するデータベースファイル
        Connection conn = null; 
        Statement stmt = null;
        // Student A = new Student("平澤", 123456, 1);
        // Student B = new Student("吉井", 234567, 2);
        // Student C = new Student("西川", 345678, 2);
        // Student D = new Student("中山", 456789, 1);
        int seat_num = 0;
        int doReserve;
        String student_name;
        int student_grade;

        try {
            Class.forName("org.sqlite.JDBC"); 
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            System.out.println("Connection to " + dbname + " is succeeded.");
            stmt = conn.createStatement();
            // stmt.executeUpdate("CREATE TABLE reservation (student_id INTEGER NOT NULL, name VARCHAR(20) NOT NULL, grade INTEGER NOT NULL, PRIMARY KEY (student_id))");
            // System.out.println("Create reservation table.");

            // stmt.executeUpdate("INSERT INTO reservation VALUES(1, 'Sawa', 1)");
            // stmt.executeUpdate("INSERT INTO reservation VALUES(2, 'Ken', 2)");
            // stmt.executeUpdate("INSERT INTO reservation VALUES(3, 'Kazu', 1)");
            // stmt.executeUpdate("INSERT INTO reservation VALUES(4, 'Kei', 2)");
            // System.out.println("Completed data insertation.");

            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM reservation WHERE student_id = 1");
            // System.out.println("選択");
            System.out.println("Show reservation table.");
            while (rs.next()) {
                int student_id = rs.getInt("student_id");
                String name = rs.getString("name");
                int grade = rs.getInt("grade");
                // System.out.println(student_id + "\t" + name + "\t" + price);
                System.out.println("会員番号" + student_id + "、" + grade + "年の" + name + "さんですね。");
                System.out.println("今日も勉強頑張っていきましょう！");
            }
            rs.close();

            // stmt.executeUpdate("DROP TABLE products");
            // System.out.println("テーブル削除");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        System.out.println("氏名と学年を入力してください");
        student_name = sc.next();
        student_grade = sc.nextInt();
        
        Student student = new Student(student_name, 123456, student_grade); //認証処理みたいなのもあったらいいな（未）

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

        // System.out.println("予約可能か確認中です。少々お待ちください。");
        pauseTime(2000);

        if(sr.checkReservation(seat_num) == true) {
            System.out.print(seat_num + "番の座席を予約しますか？（1：はい / 0：いいえ）：");
            doReserve = sc.nextInt();
            sr.reserve_seat(seat_num, doReserve);
        }
        
    }    
}
