public class Suffix implements Comparable<Suffix>{
    private final String text;
    final int index;

    Suffix(String text, int index) {
        this.text = text;
        this.index = index;
    }

    int length() {
        return text.length() - index;
    }
    char charAt(int i) {
        return text.charAt(index + i);
    }

    @Override
    public int compareTo(Suffix that) {
        if (this == that) return 0;  
        int n = Math.min(this.length(), that.length());
        for (int i = 0; i < n; i++) {
            if (this.charAt(i) < that.charAt(i)) return -1;
            if (this.charAt(i) > that.charAt(i)) return +1;
        }
        return this.length() - that.length();
    }

    public String toString() {
        return text.substring(index);
    }
    
}