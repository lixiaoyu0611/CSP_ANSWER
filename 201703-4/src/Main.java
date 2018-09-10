import java.awt.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

    class Node implements Comparable<Node>{
		int x;
		int y;
		int z;
		@Override
		public int compareTo(Node o) {
			if(this.z > o.z)
				return 1;
			else if(this.z < o.z)
				return -1;
			else
				return 0;
		}
	}
public class Main {
	
	
    private static int[] flag ;
    static Node node;
    private static int x,y,z,a,b;
	private static int unionFind(int e) {
        while (flag[e] != e) {
            e = flag[e];
        }
        return e;
    }
	private static void union(int x,int y) {
		if(x == y)
			return;
		flag[x] = y;
	}
	private static int find(int x) {
		if(flag[x] == x){
			return x;
		}else {
			int t = find(flag[x]);
			flag[x] = t;
			return t;
		}
	}
	static LinkedList<Node> list = new LinkedList<Node>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		flag = new int[n+1];
		for(int i = 0; i <= n; i++){
		flag[i] = i;
		}
		for(int i = 0;i < m; i++){
			node = new Node();
			node.x = scanner.nextInt();
			node.y = scanner.nextInt();
			node.z = scanner.nextInt();
			list.add(node);
		}
		Collections.sort(list);
		
		for(Node n1:list){
			x = n1.x;
			y = n1.y;
			z = n1.z;
			//System.out.println(z);
			a = unionFind(x);
			b = unionFind(y);
			union(a, b);
			if(find(1) == find(n))
				break;
		}
		System.out.println(z);
	}
}
