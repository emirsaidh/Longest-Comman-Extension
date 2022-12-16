import java.util.Arrays;

public class SuffixArray {
    private Suffix[] suffixes;

    
    //Initialize a suffix array for the given string.
     
    public SuffixArray(String text) {
        int n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++)
            suffixes[i] = new Suffix(text, i);
        Arrays.sort(suffixes);
    }

    public int length() {
        return suffixes.length;
    }


    
    //Returns the index into the original string of the smallest suffix.   
    public int index(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].index;
    }


    
    //Returns the length of the longest common prefix
    //smallest suffix and the smallest suffix.
    public int lcp(int i) {
        if (i < 1 || i >= suffixes.length) throw new IllegalArgumentException();
        return lcpSuffix(suffixes[i], suffixes[i-1]);
    }

    // longest common prefix of s and t
    private static int lcpSuffix(Suffix s, Suffix t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }

    
    //Returns the smallest suffix as a string.
    public String select(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].toString();
    }

    
    //Returns the number of suffixes strictly less than the string.
    public int rank(String query) {
        int lo = 0, hi = suffixes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, suffixes[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    // compare query string to suffix
    private static int compare(String query, Suffix suffix) {
        int n = Math.min(query.length(), suffix.length());
        for (int i = 0; i < n; i++) {
            if (query.charAt(i) < suffix.charAt(i)) return -1;
            if (query.charAt(i) > suffix.charAt(i)) return +1;
        }
        return query.length() - suffix.length();
    }



}