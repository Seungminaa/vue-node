package backjoon;

import java.io.*;

public class w1526 {
    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String a = br.readLine();
            
            while(true) {
                boolean b = true;
                for(int i = 0; i < a.length(); i++) {
                    if(a.charAt(i) != '4' && a.charAt(i) != '7') {
                        b = false;
                        break;
                    }
                }
                if(b == true) break;
                else a = String.valueOf(Integer.parseInt(a) - 1);
            }
            System.out.println(a);
        }
}