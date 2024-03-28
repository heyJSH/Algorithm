import java.util.*;
import java.io.*;


public class Main {

		// 출력을 빠르게 하기 위해 BufferedWriter를 선언하고 초기화한다.
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main (String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine(); // 첫 번째 입력은 사용하지 않으므로 한 줄을 읽는다.
        String str1[] = br.readLine().split(" "); // 두 번째 입력을 공백을 기준으로 분리하여 문자열 배열에 저장한다.

        int max = 0; // 카드 레벨 중 최대 레벨을 저장할 변수를 선언하고 초기화한다.
        int maxIndex = 0; // 최대 레벨을 가진 카드의 인덱스를 저장할 변수를 선언하고 초기화한다.
        int result = 0; // 결과로 출력할 획득한 골드의 총합을 저장할 변수를 선언하고 초기화한다.

        for (int i = 0; i < str1.length; i++) { // 문자열 배열의 길이만큼 반복하는 반복문
            int level = Integer.parseInt(str1[i]); // 문자열을 정수로 변환하여 카드 레벨을 얻습다.
            if (max < level) { // 현재까지의 최대 레벨보다 큰 레벨을 발견하면
                max = level; // 최대 레벨을 업데이트하고
                maxIndex = i; // 최대 레벨을 가진 카드의 인덱스를 기억한다.
            }
            result += level; // 모든 카드 레벨에 대해 골드를 획득하므로 현재 레벨을 결과에 추가한다.
        }

        result += max * (str1.length - 2); // 최대 레벨을 가진 카드를 제외하고 나머지 카드를 합성할 때 추가로 획득할 골드를 계산하여 결과에 더한다.

        bw.write(String.valueOf(result)); // 결과를 문자열로 변환하여 BufferedWriter를 통해 출력한다.
        bw.flush(); // 출력 버퍼를 비운다.

    } // main 메서드의 끝

} // Main 클래스의 끝
