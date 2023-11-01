import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	PriorityQueue<Integer> queue = new PriorityQueue<>();
	int size = sc.nextInt();
	for(int i=0; i < size; i++) {
		queue.add(sc.nextInt());
	} // 입력 종료
	
	int result =0;
	if(size == 1) {
		System.out.println(0);
	}else {
		
		while(true) {
			if(queue.size()<=2) {
				break;
			}
			int a = queue.poll();
			int b = queue.poll();
			result += a+b;
			queue.add(a+b);
			
		}
		
		int q = queue.size();
		for(int i=0; i < q; i++) {
			result += queue.poll();
		}
		System.out.println(result);
	}
}
}
