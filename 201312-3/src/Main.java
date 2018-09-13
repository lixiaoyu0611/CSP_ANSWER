import java.util.Scanner;

public class Main {

	private static Scanner scanner;
	private static int arrays[];

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		arrays = new int[n];
		for(int i = 0;i < arrays.length; i++)
		{
			arrays[i] = scanner.nextInt();
		}
		int k = 1;
		int max = 0;
		int min;
		for(int i = 0;i < arrays.length-1; i++)
		{
			min = arrays[i];
			max = Math.max(max, min*k);
			//System.out.println(max);
			for( int j = i+1;j<arrays.length;j++)
			{
				min = Math.min(min, arrays[j]);
				k ++;
				max = Math.max(max, min*k);
			}
			k = 1;
		}
		System.out.println(max);
	}
	
}
