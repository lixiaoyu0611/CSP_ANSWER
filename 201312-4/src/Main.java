import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long mod = 1000000007;
		long dp[][] = new long[1000][6];
		dp[1][0] = 1;
		dp[1][1]=dp[1][2]=dp[1][3]=dp[1][4]=dp[1][5]=0;
		for(int i = 2; i <= n;i++){
			dp[i][0] = 1;
			dp[i][1] = (dp[i-1][1]*2 + dp[i-1][0]) % mod;
			dp[i][2] = (dp[i-1][2]*2 + dp[i-1][1]) % mod;
			dp[i][3] = (dp[i-1][3] + dp[i-1][0]) % mod;
			dp[i][4] = (dp[i-1][4]*2 + dp[i-1][1] + dp[i-1][3]) % mod;
			dp[i][5] = (dp[i-1][5]*2 + dp[i-1][2] + dp[i-1][4]) % mod;
		}
		System.out.println(dp[n][5]);
	} 
}
