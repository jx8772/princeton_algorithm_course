import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 9/22/13
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
/*brute-force*/
//public class ThreeSum {
//    public static void main(String[] args) {
//        In in = new In(args[0]);
//        int[] a = in.readAllInts();
//
//        Stopwatch timer = new Stopwatch();
//        int cnt = count(a);
//        StdOut.println("elapsed time = " + timer.elapsedTime());
//        StdOut.println(cnt);
//    }
//
//    public static int count(int[] a) {
//        int N = a.length;
//        int cnt = 0;
//
//        for(int i = 0; i < N; i++)
//            for(int j = i + 1; j < N; j++)
//                for(int k = j + 1; k < N; k++) {
//                    if(a[i] + a[j] + a[k] == 0)
//                        cnt++;
//                }
//
//        return cnt;
//    }
//}

/*based on binary search, complexity:N*N*LogN  */
public class ThreeSum {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        int cnt = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(cnt);
    }

    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        Arrays.sort(a);

        for(int i = 0; i < N; i++)
            for(int j = i + 1; j < N; j++) {
                int k = binarySearch(a, -(a[i] + a[j]));
                if(k > j) {
                    cnt++;
                }
            }

        return cnt;
    }

    public static int binarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length -1;
        while(lo <= hi) {
            int mid = (lo + hi)/2;
            if(key > a[mid]) {
                lo = mid + 1;
            } else if (key < a[mid]) {
                hi = mid -1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

