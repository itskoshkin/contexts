package StringAlgorithms.NaiveSearch;

public class Main {
    public static void main(String[] args) {
        String text = "AAAAAAAAAAAAAAAAAA";
        String pattern = "AA";
        new Main().search(text, pattern);
    }

    private void search(String text, String pattern) {
        int pl = pattern.length(), nl = text.length();
        for (int i = 0; i <= nl - pl; i++) {
            int j;
            for (j = 0; j < pl; j++)
                if (text.charAt(i + j) != pattern.charAt(j))
                    break;
            if (j == pl)
                System.out.println("Pattern found at index " + i);
        }
    }


}
