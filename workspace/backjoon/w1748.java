package backjoon;

import java.io.*;

public class w1748 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        int c = Integer.parseInt(a);
        long b = 0L;

        switch (a.length()) {
            case 1:
                b = c;
                break;
            case 2:
                b = 9 + ((c-9) * 2);
                break;
            case 3:
                b = 9 + (90 * 2) + ((c-99) * 3);
                break;
            case 4:
                b = 9 + (90 * 2) + (900 * 3) + ((c-999) * 4);
                break;
            case 5:
                b = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + ((c-9999) * 5);    
                break;
            case 6:
                b = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + ((c-99999) * 6);   
                break;
            case 7:
                b = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + (900000 * 6) + ((c-999999) * 7);   
                break;
            case 8:
                b = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + (900000 * 6) + (9000000 * 7) + ((c-9999999) * 8);    
                break;
            case 9:
                b = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + (900000 * 6) + (9000000 * 7) + (90000000 * 8) + ((c-99999999) * 9);    
                break;
        }
        System.out.println(b);
    }
}