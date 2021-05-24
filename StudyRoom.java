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

    public void show_using_seat_number() {
        int using_number_count = 0;

        System.out.println("以下の座席番号は、予約済みです");
        pauseTime(500);
        for (int i = 0; i < students_list.length; i++) {
            if (students_list[i] == 0) { //0は「予約済み」つまり「使用不可能」
                System.out.println(students_list[i]);
                using_number_count += 1;
            }
        }
        
        System.out.println("予約済みの座席数は" + (students_list.length / 2 - using_number_count) + "席です。");
    }

    public void reserve_seat(int seat_num) {
        if (isReserved(seat_num)) {
            System.out.println(seat_num + "番の座席を予約しました！今日も頑張りましょう！");
        }else if (isReserved(seat_num)) {
            System.out.println(seat_num + "番の座席は既に予約済みか、新型コロナウイルス感染症対策のため使用できない座席です...。別の座席を予約してください。");
        }
    }

    public boolean isReserved(int seat_num) {
        boolean canReserved = true;
        if (students_list[seat_num - 1] == 1) {
            canReserved = true;
        }else if (students_list[seat_num - 1] == -1 || students_list[seat_num - 1] == 0) {
            canReserved = false;
        }
        return canReserved;
    }
    




    
}
