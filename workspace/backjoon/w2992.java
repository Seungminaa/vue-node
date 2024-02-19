package backjoon;

import java.io.*;

public class w2992 {
    static int min = Integer.MAX_VALUE;
    static boolean isChanged = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();

        String[] b = a.split("");
        int c = b.length;
        boolean visited[] = new boolean[c];
        int arr[] = new int[c];
        int output[] = new int[c];
        for(int i = 0; i<c; i++){
            arr[i] = Integer.valueOf(b[i]);
        }
        int t = Integer.parseInt(a);

        perm(t, arr, output, visited, 0, c);
        if(isChanged != true){
            System.out.println("0");
        }
        else{
            System.out.println(Integer.toString(min));
        }
    }

    static void perm(int origin, int[] arr, int[] output, boolean[] visited, int depth, int n) {
        if (depth == n) {
                int tmp = arrToInt(output);
                if(tmp>origin){
                    if(tmp<min){
                        min = tmp;
                        isChanged = true;
                    }
                }
                return;
        }

        for (int i=0; i<n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(origin, arr, output, visited, depth + 1, n);
                visited[i] = false;
            }
        }
    }
    static int arrToInt(int[] arr){
        int res = 0;
        int size = arr.length;
        for(int i = 0; i<size; i++){
            res += arr[i]*Math.pow(10,size-i-1);
        }
        return res;
    }
}