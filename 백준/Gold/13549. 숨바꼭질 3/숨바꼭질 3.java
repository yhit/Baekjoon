import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main {
	
	static int young;
	static int old;
	static int min = Integer.MAX_VALUE;
	public static class Node {
	    int x;
	    int time;
	    
	    public Node(int x, int time) {
	        this.x = x;
	        this.time = time;
	    }
	}

	static boolean[] flag; // 갈 수 있는 위치들
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		old = Integer.parseInt(st.nextToken());
		young = Integer.parseInt(st.nextToken());
		flag = new boolean[100001];
		bfs();
		System.out.println(min);
		
	}
	
	 public static void bfs() {
	        Queue<Node> q = new LinkedList<>();
	        q.offer(new Node(old, 0));
	        
	        while(!q.isEmpty()) {
	            Node node = q.poll();
	            flag[node.x] = true;
	            if(node.x == young) min = Math.min(min, node.time);
	            
	            if(node.x * 2 <= 100000 && flag[node.x * 2] == false) q.offer(new Node(node.x * 2, node.time));
	            if(node.x + 1 <= 100000 && flag[node.x + 1] == false) q.offer(new Node(node.x + 1, node.time + 1));
	            if(node.x - 1 >= 0 && flag[node.x - 1] == false) q.offer(new Node(node.x - 1, node.time + 1));
	        }
	    }
	    
	
}