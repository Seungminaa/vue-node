package backjoon;

import java.io.*;
import java.util.*;

public class w1680 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(br.readLine());

        for(int i=0;i<a;i++){
            st = new StringTokenizer(br.readLine());
            int b1 = Integer.parseInt(st.nextToken()); // 쓰레기차의 용량
            int b2 = Integer.parseInt(st.nextToken()); // 지점의 개수
            int c =0; //현재 차안에 용량
            int d =0; // 누적거리
            int e =0; // 이전지점거리
            boolean w = true;
            for(int j=1;j<=b2;j++){
                st = new StringTokenizer(br.readLine());
                int f1 = Integer.parseInt(st.nextToken()); // 거리
                int f2 = Integer.parseInt(st.nextToken()); // 쓰레기양

                if(f2+c < b1){
                    d += (f1-e);
                    c += f2;
                    e = f1;
                }else if(f2+c == b1){
                    d += (f1-e) + f1*2;
                    c = 0;
                    if(j == b2){
                        d -= f1 *2;
                    }
                }else if(b1 == f2){
                    d += (f1-e) + (f1*4);
                    c = f2;
                    e = 0;
                    if(j == b2){
                        d -= f1*2;
                    }
                }else if(f2+c > b1){
                    c = f2;
                    d += (f1-e) + (f1*2);
                    e = f1;
                }

                if(j == b2){
                    d += f1;
                }
            }
            sb.append(d+"\n");
        }
        System.out.println(sb);
    }
}
