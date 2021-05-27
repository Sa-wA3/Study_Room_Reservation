import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    static String dbname = "reservation.db"; // 利用するデータベースファイル
    static Connection conn = null; 
    static Statement stmt = null;
    
    SQLite() {
        
    }
        
    static void show_using_seat_number() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); 
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            System.out.println(dbname + "への接続完了。");

            stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM reservation");
            System.out.println("座席の使用状況を表示します。");
            System.out.println("seat_id" + "\t" + "name" + "\t" + "grade" + "\t" + "canUse");
            while (rs.next()) {
                int seat_id = rs.getInt("seat_id");
                String name = rs.getString("student_name");
                int grade = rs.getInt("grade");
                String canUse = rs.getString("canUse");
                System.out.println(seat_id + "\t" + name + "\t" + grade + "\t" + canUse);
            }
            rs.close();
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
    }

    static boolean checkReservation(int seat_id) {
        boolean canReserved = false;
        try {
            Class.forName("org.sqlite.JDBC"); 
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            System.out.println(dbname + "への接続完了。");
            stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM reservation WHERE seat_id = " + seat_id);
            if(rs.getString("canUse").equals("Available")) {
                System.out.println(seat_id + "番の座席は予約可能です！");
                canReserved = true;
            }else if (rs.getString("canUse").equals("prohibited")) {
                System.out.println(seat_id + "番の座席は新型コロナウイルス感染症対策のため使用できない座席です...。別の座席を予約してください。");
                canReserved = false;
            }else if (rs.getString("canUse") == "reserved") {
                System.out.println(seat_id + "番の座席はすでに予約されています。別の座席を予約してください。");
            }
      
            rs.close();
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
        return canReserved;
    }

    static void reserveSeat(int seat_num, String Name, int Grade) {
        try {
            Class.forName("org.sqlite.JDBC"); 
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            System.out.println(dbname + "への接続完了。");

            stmt = conn.createStatement();
            stmt.executeQuery("UPDATE reservation SET name = " + Name + " WHERE id = " + seat_num);
            stmt.executeQuery("UPDATE reservation SET grade = " + Grade + " WHERE id = " + seat_num);
            stmt.executeQuery("UPDATE reservation SET canUse = reserved WHERE id = " + seat_num);
            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM reservation WHERE seat_id = " + seat_num);
            System.out.println("座席の使用状況を表示します。");
            System.out.println("seat_id" + "\t" + "name" + "\t" + "grade" + "\t" + "canUse");
            
            rs.close();
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
    }

    public void reserve_seat(int seat_id, String student, int student_grade, int doReserve) {

        if (doReserve == 1) {
            try {
                Class.forName("org.sqlite.JDBC"); 
                conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
                System.out.println(dbname + "への接続完了。");
    
                stmt = conn.createStatement();
                stmt.executeUpdate("UPDATE reservation SET student_name = " + student + " WHERE seat_id = " + seat_id);
                
                System.out.println(seat_id + "番の座席を予約しました！");
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
            
        }else if ( doReserve == 0) {
            System.out.println(seat_id + "番の座席の予約をやめました。");
        }
    }
}
