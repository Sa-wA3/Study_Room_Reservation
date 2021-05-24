import java.io.*;

public class FileWrite { //DBの代わりとしてテキストファイルを使用する気でいた
    public static void main(String[] args) {
        try {
            File file = new File("/Users/atware_hirasawa/Documents/Apps/Study_room_reservation/test.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < 5; i++) {
                bw.write("[" + i + "]");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}