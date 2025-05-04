import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 버블 소트의 스왑 횟수 = 배열의 역순 쌍의 개수
public class Juyeon_1517 {

    private static int[] arr, temp;
    private static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        temp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, N - 1);
        System.out.println(count);
    }

    private static void mergeSort(int s, int e) {
        if (s >= e) return;

        int mid = (s + e) / 2;

        mergeSort(s, mid);
        mergeSort(mid + 1, e);
        merge(s, mid, e);
    }

    private static void merge(int s, int mid, int e) {
        int l = s;
        int r = mid + 1;
        int k = s;

        while (l <= mid && r <= e) {
            if (arr[l] <= arr[r]) {
                temp[k++] = arr[l++];
            } else {
                // arr[l] > arr[j] -> 역순쌍 존재 -> 왼쪽 배열 요소 전부 arr[j]보다 큼
                count += (mid - l + 1);
                temp[k++] = arr[r++];
            }
        }

        // 양쪽 배열에 남은 값들 복사
        while (l <= mid) temp[k++] = arr[l++];
        while (r <= e) temp[k++] = arr[r++];

        for (int i = s; i <= e; i++) {
            arr[i] = temp[i];
        }

//        if (e + 1 - s >= 0) System.arraycopy(temp, s, arr, s, e + 1 - s);
    }
}
