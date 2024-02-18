package backjoon;

import java.io.*;
import java.util.*;

public class w1485 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int a = Integer.parseInt(br.readLine());

        for(int i=0;i<a;i++){
            int[][] b = new int[4][2];
            for(int j=0;j<4;j++){
                st = new StringTokenizer(br.readLine());
                b[j][0] = Integer.parseInt(st.nextToken());
                b[j][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(b, new Comparator<int[]>() { 
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1];
                }
            });
            double c = Math.pow(b[3][0] - b[0][0],2) + Math.pow(b[3][1] - b[0][1],2);
            double d = Math.pow(b[1][0] - b[0][0],2) + Math.pow(b[1][1] - b[0][1],2);
            double e = Math.pow(b[2][0] - b[0][0],2) + Math.pow(b[2][1] - b[0][1],2);

            if(d == e && d+e == c){
                sb.append(1 + "\n");
            }else{
                sb.append(0 + "\n");
            }
        }
        System.out.println(sb);
    }
}
