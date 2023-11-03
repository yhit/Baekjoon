import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int connected;

    static class Node implements Comparable<Node> {
        int N, V;
        double w;

        public Node(int x, int y, double w) {
            super();
            this.N = x;
            this.V = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return Double.compare(this.w, o.w);
            // 앞의 값이 더 작으면 -1 크면 1 같으면 0 반환
        }

    }

    static class Temp {
        int x, y;

        public Temp(int x, int y) {
            super();
            this.x = x;
            this.y = y;

        }

    }

    static int size1;
    static Temp[] temps;
    static PriorityQueue<Node> nodes;
    static int[] p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        size1 = sc.nextInt(); // 받아야하는 값들
        int size2 = sc.nextInt(); // 이미 연결된 통로 갯수

        temps = new Temp[size1];
        nodes = new PriorityQueue<>();
        p = new int[size1];

        for (int i = 0; i < size1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            temps[i] = new Temp(a, b);
            p[i] = i; // 초기값 ; 자기 자신이 부모
        } // 각 간선의 정보를 저장합니다.

        for (int i = 0; i < size1; i++) {
            for (int j = i + 1; j < size1; j++) {
                double distance = Math
                        .sqrt(Math.pow(temps[i].x - temps[j].x, 2) + Math.pow(temps[i].y - temps[j].y, 2));
                nodes.add(new Node(i, j, distance));
            }
        } // 각 정점에서 뻗어나가는 가장 가까운 경로들을 거리순으로 저장함

        for (int i = 0; i < size2; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            union(a, b);
        }

        System.out.printf("%.2f", kruscal(connected));

    }// main 종료

    public static void union(int a, int b) {
        a = findparent(a);
        b = findparent(b);
        if (a == b)
            return;
        ++connected;
        if (a > b) {
            p[a] = b;
        } else {
            p[b] = a;
        }
    }

    public static int findparent(int num) {
        if (p[num] == num) {
            return p[num];
        } else {
            return p[num] = findparent(p[num]);
        }
    }

    public static double kruscal(int cnt) {
        int Cnt = cnt + 1;
        double result = 0.0;
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (findparent(node.N) != findparent(node.V)) { // 부모가 다르면 함수 실행

                result += node.w;
                Cnt++;
                if (Cnt == size1) {
                    return result;
                }
                union(node.N, node.V);

            }
        }

        return result;
    }

}