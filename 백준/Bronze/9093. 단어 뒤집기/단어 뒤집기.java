import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());  // 테스트 케이스 개수 입력
        
        for (int i = 0; i < n; i++) {  // 각 테스트 케이스에 대한 반복문
            String[] words = br.readLine().split(" ");  // 문장을 공백을 기준으로 단어로 나눠 배열에 저장
            
            for (String word : words) {  // 각 단어에 대한 반복문
                StringBuilder sb = new StringBuilder(word);  // 단어를 StringBuilder로 변환
                System.out.print(sb.reverse().toString() + " ");  // 단어를 뒤집어 출력
            }
            
            System.out.println();  // 각 테스트 케이스의 출력이 끝나면 줄바꿈
        }
        
        br.close();  // BufferedReader 닫기
    }
}