package StringAlgorithms.RabinKarp;

// Following program is a Java implementation
// of Rabin Karp Algorithm given in the CLRS book

public class Main {
    // d is the number of characters in the input alphabet
    public final static int alphabet = 256;


    public static void main(String[] args) {
        String text = "GEEKS FOR GEEKS";
        String pattern = "GEEK";
        int primeNumber = 101; // A prime number
        new Main().search(pattern, text, primeNumber);
    }

    private void search(String pattern, String text, int primeNumber) {
        int M = pattern.length();
        int N = text.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;
        for (i = 0; i < M - 1; i++) h = (h * alphabet) % primeNumber;
        for (i = 0; i < M; i++) {
            p = (alphabet * p + pattern.charAt(i)) % primeNumber;
            t = (alphabet * t + text.charAt(i)) % primeNumber;
        }
        for (i = 0; i <= N - M; i++) {
            if (p == t) {
                for (j = 0; j < M; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }
            if (i < N - M) {
                t = (alphabet * (t - text.charAt(i) * h) + text.charAt(i + M)) % primeNumber;
                if (t < 0)
                    t = (t + primeNumber);
            }
        }
    }


}