package PhilosophyAlgorithms.KBasedNumbers;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();


        BigInteger[][] bigIntegers = new BigInteger[N][2];
        BigInteger integer = new BigInteger(String.valueOf(K - 1));

        bigIntegers[1][0] = integer.add(BigInteger.ONE);
        bigIntegers[1][1] = integer;

        for (int i = 2; i < N; ++i) {
            bigIntegers[i][0] = (bigIntegers[i - 1][0].multiply(integer)).add(bigIntegers[i - 1][1]);
            bigIntegers[i][1] = bigIntegers[i - 1][0].multiply(integer);
        }

        BigInteger ans = bigIntegers[N - 1][0].multiply(integer);
        System.out.println(ans);
    }
}