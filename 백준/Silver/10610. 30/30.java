import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        char[] digits = br.readLine().toCharArray();

        // 2. 주어진 숫자를 정렬하여 가장 큰 수부터 확인
        Arrays.sort(digits);
        StringBuilder maxNum = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            maxNum.append(digits[i]);
        }

        // 3. 주어진 숫자의 합이 3의 배수이고 0이 존재하는지 확인
        int sum = 0;
        boolean hasZero = false;
        for (char digit : digits) {
            int num = digit - '0';
            if (num == 0) {
                hasZero = true;
            }
            sum += num;
        }

        // 4. 3의 배수이고 0이 존재한다면 가장 큰 수 출력, 아니면 -1 출력
        if (sum % 3 == 0 && hasZero) {
            System.out.println(maxNum);
        } else {
            System.out.println("-1");
        }

        br.close();
    }
}
