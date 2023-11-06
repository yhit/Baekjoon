import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static Integer[][] dp;
	static int[] W; // weight
	static int[] V; // value
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
 
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
 
		W = new int[N];
		V = new int[N];
 
		dp = new Integer[N][K + 1];
 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
 
		System.out.println(DP(N - 1, K));
 
	}
	
	static int DP(int i, int k) {
		
		if(i<0) {
			return 0;
		}
		
		// 탐색하지 않은 위치라혐
		if(dp[i][k] == null) {
			
			// 현재 물곤 (i)를 추가로 못담는 경우
			if(W[i] > k) {
				dp[i][k] = DP(i - 1 , k);
			}
			// 현재 물건(i)를 담을 수 있는 경우
			else {
				dp[i][k] = Math.max(DP(i-1,k), DP(i-1,k-W[i]) + V[i]);
			}
			
		}
		
		return dp[i][k];
		
	}
}
