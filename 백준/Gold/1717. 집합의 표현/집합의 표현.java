import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] num;
public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int size = Integer.parseInt(st.nextToken());
	int math = Integer.parseInt(st.nextToken());
	
	num = new int[size+1];
	for(int i = 0 ; i<=size; i++) {
		num[i] = i;
	}// 초기화 , 처음에는 다들 자신이 부모임
	// num은 부모배열
	
	for(int i = 0; i<math; i++) {
		st= new StringTokenizer(br.readLine());
		int play = Integer.parseInt(st.nextToken());
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		
		
		
		if(play == 0) {
			union(find(num1),find(num2));
		}else {
			System.out.println(check(find(num1),find(num2)));
		}
		
	}
	
}

public static String check(int num1, int num2) {
	num1 = find(num1);
	num2 = find(num2);
	
	if(num1 == num2)//부모가 같다면
		return "YES";
	
	return "NO";
}

public static void union(int num1, int num2) {
	num1 = find(num1);
	num2 = find(num2);
	
	if(num1 == num2)//부모가 같다면
		return;
	
	if(num1<=num2) {
		num[num2] = num1;
	}else {
		num[num1] = num2;
	}
}

public static int find(int x) {
	if(num[x] == x) {
		
		return x;
	}else {
		
		return num[x] = find(num[x]);
	}
}

}
