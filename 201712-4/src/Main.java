import java.util.Scanner;

public class Main {
	static Scanner sc;
	static int[][]edge;
	static int n,m;
	static boolean flag[][];
	static boolean visited[];
	static int INF = Integer.MAX_VALUE;
	static int dist[];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		edge = new int[n + 1][n + 1];
		dist = new int[n + 1];
		flag = new boolean[n + 1][n + 1];
		for(int i = 1; i <= n; i++)
			for (int j = i; j <= n; j++) 
				if(i == j)
					edge[i][j] = 0;
				else
					edge[i][j] = INF;
		sc.nextLine();
		for(int i = 0; i < m; i++){
			String temp = sc.nextLine() + " ";
			String[] cmd = temp.split(" ");
			if (Integer.parseInt(cmd[0]) == 1)
				flag[Integer.parseInt(cmd[1])][Integer.parseInt(cmd[2])] = true;
			edge[Integer.parseInt(cmd[1])][Integer.parseInt(cmd[2])] = Integer.parseInt(cmd[3]);
		}
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++) {
				edge[j][i] = edge[i][j];
				flag[j][i] = flag[i][j];
			}
		dis(1,dist);
		System.out.println(dist[n]);
	}
	
	public static void dis(int start,int[] dist) {
		visited = new boolean[n + 1];
		for(int i = 0;i <= n; i++){
			visited[i] = false;
			dist[i] = edge[start][i];
		}
		visited[start] = true;
		dist[start] = 0;
		
		int k = 0;
		int result = 0;
		int pre = start;
		boolean check = false;
		int premin = 0;
		for(int i = 0; i < n - 1; i++){
			int min = INF;
			for(int j = 1; j <= n; j++){
				if(visited[j] == false && dist[j] < min){
					if(flag[pre][j] == true){
						result += edge[pre][j];
						if(check == true){
							min = result * result;
						}else {
							min = premin + result * result;
						}
						check = true;
					}else {
						result = 0;
						check = false;
						min = dist[j];
					}
					k = j;
				}
			}
			premin = min;
			visited[k] = true;
			for (int j = 1; j <= n; j++) {
				int temp = INF;
				if(visited[j] == false){
					if(edge[k][j] != INF){
						if(flag[k][j] == true){
							if(check == true){
								temp = (result + edge[k][j]) * (result + edge[k][j]);
							} else {
								temp = min + edge[k][j] * edge[k][j];
							}
 
						} else { // 表示小道
							temp = min + edge[k][j];
						}
					}
				}
				if (visited[j] == false && (temp < dist[j])) {
					dist[j] = temp;
					pre = k;
			}
		}
	}
}
			
	
	
	
	
	
	
	
	
	
	
	
}
