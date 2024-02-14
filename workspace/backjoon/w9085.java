package backjoon;

import java.io.*;
import java.util.*;

public class w9085 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int a = Integer.parseInt(br.readLine());
        
        for(int i=0;i<a;i++){
            int b = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int c = 0;
            for(int j=0;j<b;j++){
                c += Integer.parseInt(st.nextToken());
            }
            sb.append(c+"\n");
        }
        System.out.println(sb);
    }
}