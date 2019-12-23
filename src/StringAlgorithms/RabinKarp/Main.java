package StringAlgorithms.RabinKarp;

// Following program is a Java implementation
// of Rabin Karp Algorithm given in the CLRS book

public class Main {
    // d is the number of characters in the input alphabet
    public final static int alphabet = 256;


    public static void main(String[] args) {
        String text = "AAABAABAABAAAAAAA";
        String pattern = "AAB";
        int primeNumber = 1488; // A prime number o/
        new Main().search(pattern, text, primeNumber);
    }

    private void search(String pattern, String text, int hashcode) {
        int M = pattern.length();
        int N = text.length();
        int i, j;

        int h = 1;
        for (i = 0; i < M - 1; i++) h = (h * alphabet) % hashcode;

        int p = 0, t = 0;
        for (i = 0; i < M; i++) {
            p = (alphabet * p + pattern.charAt(i)) % hashcode;
            t = (alphabet * t + text.charAt(i)) % hashcode;
        }

        for (i = 0; i <= N - M; i++) {

            if (p == t) {
                for (j = 0; j < M; j++)
                    if (text.charAt(i + j) != pattern.charAt(j)) break;
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }

            if (i < N - M) {
                t = (alphabet * (t - text.charAt(i) * h) + text.charAt(i + M)) % hashcode;
                if (t < 0)
                    t = (t + hashcode);
            }

        }
    }


}