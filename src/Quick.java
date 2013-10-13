/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 10/12/13
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Quick {
    private Quick() {}

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    //quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);

        assert isSorted(a, lo, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            //find item on lo to swap
            while(less(a[++i], v))
                if(i == hi)
                    break;

            //find item on hi to swap
            while(less(v, a[--j]))
                if(j == lo)
                    break;

            //check if pointer cross
            if(i >= j)
                break;

            exch(a, i, j);
        }

        //put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k > a.length) {
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length -1;
        while(hi > lo) {
            int i = partition(a, lo, hi);
            if(i > k)
                hi = i - 1;
            else if (i < k)
                lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }

    //helper sorting function
    private static boolean less (Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    //exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    //check if array is sorted
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }

    //check if part of array is sorted
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }

    //print array to standard output
    private static void show (Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        Quick.sort(a);
        show(a);

        StdRandom.shuffle(a);

        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String) Quick.select(a, i);
            StdOut.println(ith);
        }
    }
}
