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
        int seat_num;

        System.out.println("こんにちは！今日も勉強頑張りましょう！");
        pauseTime(1000);
        sr.show_using_seat_number();

        System.out.print("予約したい座席番号を入力してください：");
        seat_num = sc.nextInt();

        System.out.println("予約可能かどうか調べるので、少々お待ちください。");
        pauseTime(2000);
        sr.isReserved(seat_num);
        
    }    
}
