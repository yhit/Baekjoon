import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] arr;
	public static int[] dp;
	public static int size;
	public static int max = Integer.MIN_VALUE;
public static void main(String[] args) throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	size = Integer.parseInt(br.readLine());
	arr = new int[size];
	dp = new int[size];
	StringTokenizer st = new StringTokenizer(br.readLine());
	for(int i = 0; i < size; i++) {
		arr[i] = Integer.parseInt(st.nextToken());
	}
	
	int max = 1;
	
	for(int i = 0; i < size; i++) {
		dp[i] = 1;
		for(int j = 0 ; j < i; j++) {
			if(arr[i]>arr[j]) {
				dp[i] = Math.max(dp[i], dp[j]+1);
			}
			
		}
		max = Math.max(max, dp[i]);
	}
	System.out.println(max);
	
}

}
