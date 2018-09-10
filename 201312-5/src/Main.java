import java.util.Scanner;

public class Main {

	
	static int MAX = 50;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int L = sc.nextInt();
//		System.out.println(L);
		char map[][] = new char[MAX][MAX];
		boolean sCanReach[][] = new boolean[MAX][MAX];//����ܵ���ľ��� 
		boolean tCanReach[][] = new boolean[MAX][MAX];//�յ��ܵ��ľ���  
//		for(int i = 0;i < MAX;i++){
//			for(int j = 0;j<MAX;j++){
//				sCanReach[i][j] = true;
//				tCanReach[i][j] = false;
//			}
//		}
		for(int i = 0;i < R;i++){
			String s = sc.next();
			for(int j = 0;j<L;j++){
				map[i][j] = s.charAt(j);
			}
		}
		/*for(int i = 0;i < R;i++){
			for(int j = 0;j<L;j++){
				System.out.print(map[i][j]);
			}
		}*/
		for (int i=0; i<R; i++)
		{
			for (int j=0; j<L; j++)
			{
				if (map[i][j] == 'S')
				{
					ForwardDFS(map,sCanReach,R,L,i,j);
				}
			}
		}
		for (int i=0; i<R; i++)
		{
			for (int j=0; j<L; j++)
			{
				if (map[i][j] == 'T')
				{
					if (sCanReach[i][j] == false)
					{
						System.out.println("I'm stuck!");
					}
					ReserveDFS(map,tCanReach,R,L,i,j,i,j);
				}
			}
		}
		int num = 0;
		for (int i=0; i<R; i++)
		{
			for (int j=0; j<L; j++)
			{
				if (map[i][j] != 'S' && map[i][j] != 'T' &&
					sCanReach[i][j]  && !tCanReach[i][j])
				{
					num++; 
				}
			}
		}
		System.out.println(num);		
}
	
	public static void ForwardDFS(char map[][],boolean[][] sign,
			int Row,int Col,int curR,int curC) {
		//�� ��ǰ�����߹� �� ��ǰ�����ϰ��ֹͣ�ݹ� 
		if (sign[curR][curC] || map[curR][curC] == '#')
		{
			return;
		}
		//��Ǵ˵�ɴ� 
		sign[curR][curC] = true;
		//��ǿ������ĸ������ƶ�
		boolean up,down,left,right;
		up = down = left = right = false; 
		if (map[curR][curC] == '.')//�����ƶ� 
		{
			down = true;
		}
		else if (map[curR][curC] == '-')//�����ƶ� 
		{
			left = right = true;
		}
		else if (map[curR][curC] == '|')//�����ƶ� 
		{
			up = down = true;
		}
		else if (map[curR][curC] == '+' || map[curR][curC] == 'S' || map[curR][curC] == 'T')//���������ƶ� 
		{
			up = down = left = right = true;
		}
		//��
		if (up && curR-1 >= 0)
		{
			ForwardDFS(map,sign,Row,Col,curR-1,curC);
		}
		//�� 
		if (down && curR+1 < Row)
		{
			ForwardDFS(map,sign,Row,Col,curR+1,curC);
		}
		//��
		if (left && curC-1 >= 0)
		{
			ForwardDFS(map,sign,Row,Col,curR,curC-1);
		}
		//��
		if (right && curC+1 < Col)
		{
			ForwardDFS(map,sign,Row,Col,curR,curC+1);
		}
	}

	
	public static void ReserveDFS(char map[][],//������� 
			boolean[][] tCanReach,//���ر�ǽ��ͼ��true��ʾ���Ե�������㣬false��ʾ���ܵ��� 
			int Row,int Col,//�����ģ 
			int curR, int curC,//��ǰ�� 
			int preR, int preC) {
		if (tCanReach[curR][curC] || map[curR][curC] == '#')
		{
			return ;
		}
		if (map[curR][curC] == '.' && preR == curR+1 && preC == curC)
		{	
			tCanReach[curR][curC] = true;
		}
		else if (map[curR][curC] == '-' && preR == curR)
		{	
			tCanReach[curR][curC] = true;
		}
		else if (map[curR][curC] == '|' && preC == curC)
		{
			tCanReach[curR][curC] = true;
		}
		else if (map[curR][curC] == 'S' || map[curR][curC] == '+' || map[curR][curC] == 'T')
		{
			tCanReach[curR][curC] = true;
		}
		if (tCanReach[curR][curC] == false)
		{
			return ;
		}
		if (curR-1 >= 0)
		{
			ReserveDFS(map,tCanReach,Row,Col,curR-1,curC,curR,curC);
		}
		if (curR+1 < Row)
		{
			ReserveDFS(map,tCanReach,Row,Col,curR+1,curC,curR,curC);
		}
		if (curC-1 >= 0)
		{
			ReserveDFS(map,tCanReach,Row,Col,curR,curC-1,curR,curC);
		}
		if (curC+1 < Col)
		{
			ReserveDFS(map,tCanReach,Row,Col,curR,curC+1,curR,curC);
		}	
	}
}
