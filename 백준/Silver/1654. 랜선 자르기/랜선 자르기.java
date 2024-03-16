import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 자를 수 있는 선의 길이의 최대 길이를 찾는 메소드
	private static long binary_search(long arr[], int N, long max) {
		long half = 0;
		long min = 1;
		
		while(min <= max) {
			half = (min + max) / 2;
			long count = 0;
			
			for(long num: arr) {
				count += num / half;
			}
			
			// 원하는 랜선 개수보다 잘라진 랜선 수가 적을 경우
			// 하나의 랜선 길이가 길어서 길이를 더 짧게 만들어야 함
			// half보다 아래의 수.
			if(count < N) {
				max = half - 1;
			}
			// 원하는 랜선 개수보다 잘라진 랜선 수가 많을 경우
			// 하나의 랜선 길이가 짧아서 더 길게 만들어야 함
			// half보다 위의 수에 있음
			else {
				min = half + 1;
			}
		}
		return (max+min)/2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long result = 0;
		long max = 0;
		
		long[] arr = new long[K];
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		result = binary_search(arr, N, max);
		
		System.out.println(result);
		br.close();
	}

}
