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

    public static void main(String[] args) throws SQLException {
        String dbname = "reservation.db"; // 利用するデータベースファイル
        Connection conn = null; 
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            System.out.println("Connection to " + dbname + " is succeeded.");

            stmt = conn.createStatement();
            // stmt.executeUpdate("CREATE TABLE reservation (seat_id INTEGER NOT NULL, name VARCHAR(20) NOT NULL, grade INTEGER NOT NULL, PRIMARY KEY (seat_id))");
            // System.out.println("Create reservation table.");

            // stmt.executeUpdate("INSERT INTO reservation VALUES(1, 'Sawa', 1)");
            // stmt.executeUpdate("INSERT INTO reservation VALUES(2, 'Ken', 2)");
            // stmt.executeUpdate("INSERT INTO reservation VALUES(3, 'Kazu', 1)");
            // stmt.executeUpdate("INSERT INTO reservation VALUES(4, 'Kei', 2)");
            // System.out.println("Completed data insertation.");

            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM reservation WHERE seat_id = 1");
            // System.out.println("選択");
            System.out.println("Show reservation table.");
            while (rs.next()) {
                int seat_id = rs.getInt("seat_id");
                String name = rs.getString("name");
                int grade = rs.getInt("grade");
                // System.out.println(seat_id + "\t" + name + "\t" + price);
                System.out.println("会員番号" + seat_id + "、" + grade + "年の" + name + "さんですね。");
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
    }
}
