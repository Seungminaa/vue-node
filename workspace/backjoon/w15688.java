package backjoon;

import java.io.*;
import java.util.*;

public class w15688 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int a = Integer.parseInt(br.readLine());
		int b[] = new int[a];

		for (int i = 0; i < a; i++) {
			b[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(b);

		for (int i = 0; i < a; i++) {
			sb.append(b[i]).append("\n");
		}
		System.out.println(sb);
	}
}
