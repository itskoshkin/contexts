package StringAlgorithms.BoyerMoore;

class Main {

    private static final int alphabet = 256;

    public static void main(String[] args) {

        char[] txt = "ABAAABCD".toCharArray();
        char[] pat = "ABC".toCharArray();
        new Main().search(txt, pat);
    }

    private void badCharHeuristic(char[] str, int size, int[] badchar) {
        for (int i = 0; i < alphabet; i++) badchar[i] = -1;
        for (int i = 0; i < size; i++) badchar[(int) str[i]] = i;
    }

    private void search(char[] txt, char[] pat) {
        int m = pat.length;
        int n = txt.length;
        int[] badchar = new int[alphabet];
        badCharHeuristic(pat, m, badchar);
        int s = 0;
        while (s <= (n - m)) {
            int j = m - 1;
            while (j >= 0 && pat[j] == txt[s + j])
                j--;
            if (j < 0) {
                System.out.println("Patterns occur at shift = " + s);
                s += (s + m < n) ? m - badchar[txt[s + m]] : 1;
            } else
                s += Math.max(1, j - badchar[txt[s + j]]);
        }
    }
}