import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int f;
		int w;

		public Node(int f, int w) {
			super();
			this.f = f;
			this.w = w;
		}

	}

	static int V, E;
	static List<Node>[] adj;
	static boolean[] flag;
	static int INF = 200000000;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V];
		//System.out.println(adj.length);
		//System.out.println(Arrays.toString(adj));
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			int W = Integer.parseInt(st.nextToken());
			adj[A].add(new Node(B, W));
            adj[B].add(new Node(A, W));
		} // 입력완료

		st = new StringTokenizer(br.readLine());
		int must1 = Integer.parseInt(st.nextToken()) - 1;
		int must2 = Integer.parseInt(st.nextToken()) - 1;

		flag = new boolean[V];
		distance = new int[V];
		

		int res1 = 0;
		res1 += dijkstra(0, must1);
		res1 += dijkstra(must1, must2);
		res1 += dijkstra(must2, V - 1);

		int res2 = 0;
		res2 += dijkstra(0, must2);
		res2 += dijkstra(must2, must1);
		res2 += dijkstra(must1, V - 1);

		int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);

		System.out.println(ans);
	}

	static int dijkstra(int start, int end) {
		for (int i = 0; i < V; i++) {
			distance[i] = INF;
		}
		for (int i = 0; i < V; i++) {
			flag[i] = false;
		}
		distance[start] = 0;

		for (int i = 0; i < V - 1; i++) {
			int min = INF;
			int idx = -1;
			for (int j = 0; j < V; j++) {
				if (!flag[j] && min > distance[j]) {
					min = distance[j];
					idx = j;
				}
			}

			if (idx == -1) {
				break;
			}

			flag[idx] = true;

			for (int j = 0; j < adj[idx].size(); j++) {
				//System.out.println(adj[j].size());
				Node current = adj[idx].get(j);
				if (!flag[current.f] && distance[current.f] > distance[idx] + current.w) {
					distance[current.f] = distance[idx] + current.w;
				}
			}

		}
		return distance[end];
	}
}