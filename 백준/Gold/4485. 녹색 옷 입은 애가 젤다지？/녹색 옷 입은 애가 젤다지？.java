import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x, y, w;

		public Node(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

	}

	static int[][] map;
	static ArrayList<Node>[][] nodes;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int size;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while (true) {
			size = Integer.parseInt(br.readLine());
			if (size <= 0) {
				break;
			}

			map = new int[size][size];
			nodes = new ArrayList[size][size];
			for (int i = 0; i < size; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					nodes[i][j] = new ArrayList<>();
				}
			} // 입력 종료

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					for (int k = 0; k < 4; k++) {
						int a = i + dx[k];
						int b = j + dy[k];
						if (a >= 0 && a < size && b >= 0 && b < size) {
							nodes[i][j].add(new Node(a, b, map[a][b]));
						}

					}
				}
			} // nodes 인접리스트완성
			//System.out.println(Arrays.deepToString(map));
			
			System.out.println("Problem "+ tc++ +": "+dijkstra());
			
		} // while문
	}// main문

	static int dijkstra() {
		int[][] distance = new int[size][size];
		boolean[][] flag = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		} // 최대값으로 초기화를 해둠

		distance[0][0] = map[0][0]; // 시작점은 0으로 초기화해줌

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int min = Integer.MAX_VALUE;
				int idxx = -1000;
				int idxy = -1000;
				for (int k = 0; k < size; k++) {
					for (int l = 0; l < size; l++) {
						if(!flag[k][l] && min > distance[k][l]) {
							min = distance[k][l];
							idxx = k;
							idxy = l;
						} // 그 다음으로 가장 작은 값을 찾는 과정.
					}
				}
				
				flag[idxx][idxy] = true;
				
				for(int k = 0; k< nodes[idxx][idxy].size(); k++) {
					Node current = nodes[idxx][idxy].get(k);
					if(!flag[current.x][current.y] && distance[current.x][current.y] > distance[idxx][idxy] + current.w) {
						distance[current.x][current.y] = distance[idxx][idxy] + current.w;
					}
				}
				
				
				

			} // 두번째 for
		} // 첫 for
		
		//System.out.println(Arrays.deepToString(distance));
		return distance[size-1][size-1];
	}
}
