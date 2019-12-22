package StringAlgorithms.LongestCommonSubsequence;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        String string2 = scanner.nextLine();
        System.out.println("The length of common subsequence: " + new Main().getLongestCommonSubsequence(string1, string2));
        System.out.print("The subsequence: " + new Main().lcs(string1, string2));
    }

    private int getLongestCommonSubsequence(String a, String b) {
        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++)
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 || j == 0) matrix[i][j] = 0;
                else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    matrix[i][j] = 1 + matrix[i - 1][j - 1];
                } else matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
            }
        return matrix[a.length()][b.length()];
    }

    private String lcs(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        if (aLen == 0 || bLen == 0) {
            return "";
        } else if (a.charAt(aLen - 1) == b.charAt(bLen - 1)) {
            return lcs(a.substring(0, aLen - 1), b.substring(0, bLen - 1))
                    + a.charAt(aLen - 1);
        } else {
            String x = lcs(a, b.substring(0, bLen - 1));
            String y = lcs(a.substring(0, aLen - 1), b);
            return (x.length() > y.length()) ? x : y;
        }
    }
}
