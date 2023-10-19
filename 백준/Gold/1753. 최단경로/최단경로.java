import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int v, w;

		public Node(int v, int w) {

			this.v = v;
			this.w = w;
		}

	}
	static int INF = Integer.MAX_VALUE;
	static int V,E;
	static ArrayList<Node>[] arr;
	static int[] distance;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine()) - 1;
		
		arr = new ArrayList[V];
		for(int i = 0; i< V; i++) {
			arr[i] = new ArrayList<Node>();
		}
		
		distance = new int[V];
		Arrays.fill(distance, INF);
		
		for(int i = 0; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			int W = Integer.parseInt(st.nextToken());
			
			arr[A].add(new Node(B, W));
		}
		
		dijkstra(start);
		//System.out.println(Arrays.toString(distance));
		
		for(int i = 0; i<V; i++) {
			if(distance[i] != INF) {
				System.out.println(distance[i]);
			}else {
				System.out.println("INF");
			}
		}
	}
	
	static void dijkstra(int start) {
		boolean[] visited = new boolean[V];
		
		distance[start] = 0;
		
		for(int i = 0; i<V-1; i++) {
			int min = INF;
			int idx  = -1;
			for(int j = 0; j<V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					idx = j;
				}
			}
			if(idx == -1) {
				
				break;
			}
			
			visited[idx] = true;
			
			for(int j = 0; j<arr[idx].size(); j++) {
				Node current = arr[idx].get(j);
				
				if(!visited[current.v] && distance[current.v] > distance[idx] + current.w ) {
					distance[current.v] = distance[idx] + current.w;
				}
			}
		}
	}
}
