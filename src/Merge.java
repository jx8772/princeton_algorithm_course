/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 10/12/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Merge {
    //mergesort is stable in this implementation
    private Merge() {}

    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        //precondition
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        //copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        //merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    //mergesort a[lo .. hi] using aux[lo .. hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    //
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }

    //helper sorting function
    private static boolean less (Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
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
        Merge.sort(a);
        show(a);
    }
}
