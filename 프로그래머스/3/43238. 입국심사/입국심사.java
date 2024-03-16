import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times); // 심사 시간을 오름차순으로 정렬

        long minTime = 1; // 최소 시간
        long maxTime = (long) times[times.length - 1] * n; // 최대 시간
        long answer = maxTime; // 답으로 반환할 값, 일단 최대 시간으로 초기화

        // 이진 탐색을 통해 최소 시간과 최대 시간을 조절하여 모든 사람이 심사를 받는데 걸리는 최소 시간을 구함
        while (minTime <= maxTime) {
            long midTime = (minTime + maxTime) / 2; // 중간값 계산

            long totalPeople = 0; // 중간 시간에 처리 가능한 총 사람 수
            for (int time : times) {
                totalPeople += midTime / time; // 각 심사대에서 처리 가능한 사람 수 누적
            }

            if (totalPeople >= n) { // 처리 가능한 사람 수가 주어진 사람 수 이상인 경우
                answer = midTime; // 최소 시간을 중간값으로 업데이트하고
                maxTime = midTime - 1; // 최대 시간을 중간값 - 1로 업데이트하여 범위를 줄임
            } else { // 처리 가능한 사람 수가 주어진 사람 수보다 적은 경우
                minTime = midTime + 1; // 최소 시간을 중간값 + 1로 업데이트하여 범위를 줄임
            }
        }

        return answer; // 모든 사람이 심사를 받는데 걸리는 최소 시간 반환
    }
}
