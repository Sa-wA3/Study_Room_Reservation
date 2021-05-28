import java.util.HashMap;

public class StudyRoom {
    private int students_list[] = new int[32];

    StudyRoom() {
        for (int i = 0; i < students_list.length; i++) {
            if (i % 2 == 0) {
                students_list[i] = -1; //-1は「使用不可」（コロナの関係で距離を取ることを想定）
            }else {
                students_list[i] = 1; //1は「予約されていない」つまり「使用可能」
            }
        }
    }

    static void pauseTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void scan_student(Student student) {
        System.out.println("会員番号" + student.getMember_id() + "、" + student.getGrade() + "年の" + student.getName() + "さんですね。こんにちは！");
        // System.out.println("こんにちは！" + student.getName() + "さん！");
        System.out.println("今日も勉強頑張っていきましょう！");
    }

    // public void show_using_seat_number() {
    //     int using_number_count = 1;
    //     if (using_number_count == 0) {
    //         System.out.println("現在、座席は全て予約可能です。（新型コロナウイルス感染症対策による使用禁止席を除く）");
    //     } else {
    //         System.out.println("以下の座席番号は、予約済みです"); //表示処理を追加（未完成）
    //         for (int i = 0; i < students_list.length; i++) {
    //             if (i % 2 == 0) {
    //                 System.out.println((i + 1) + "番：使用禁止席");
    //             }else {
    //                 System.out.println((i + 1) + "番：空席");
    //             }
    //         }
    //     }
        
    //     pauseTime(500);
    //     for (int i = 0; i < students_list.length; i++) {
    //         if (students_list[i] == 0) { //0は「予約済み」つまり「使用不可能」
    //             System.out.println(students_list[i]);
    //             using_number_count += 1;
    //         }
    //     }
        
    //     System.out.println("予約可能な座席数は" + (students_list.length / 2 - using_number_count) + "席です。");
    // }

    // public boolean checkReservation(int seat_num) {
    //     boolean canReserved = true;
    //     if (students_list[seat_num - 1] == 1) {
    //         System.out.println(seat_num + "番の座席は予約可能です！");
    //         canReserved = true;
    //     }else if (students_list[seat_num - 1] == -1 || students_list[seat_num - 1] == 0) {
    //         System.out.println(seat_num + "番の座席は既に予約済みか、新型コロナウイルス感染症対策のため使用できない座席です...。別の座席を予約してください。");
    //         canReserved = false;
    //     }
    //     return canReserved;
    // }

    // public void reserve_seat(int seat_num, int doReserve) {
    //     do {
    //         if (doReserve == 1) {
    //             System.out.println(seat_num + "番の座席を予約しました！");
    //             students_list[seat_num - 1] = 0;
    //         }else if ( doReserve == 0) {
    //             System.out.println(seat_num + "番の座席の予約をやめました。");
    //         }
    //     }while (doReserve < 0 || doReserve > 1);
        
    // }

    




    
}
