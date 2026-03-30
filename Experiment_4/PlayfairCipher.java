
import java.util.*;

class PlayfairCipher {

    static char[][] matrix = new char[5][5];

    static void generateMatrix(String key) {
        boolean[] used = new boolean[26];
        key = key.toUpperCase().replace("J", "I");

        String result = "";

        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                result += c;
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                result += c;
            }
        }

        int k = 0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = result.charAt(k++);
    }

    static String formatText(String text) {
        text = text.toUpperCase().replace("J", "I");
        String res = "";

        for (int i = 0; i < text.length(); i++) {
            res += text.charAt(i);
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1))
                res += 'X';
        }

        if (res.length() % 2 != 0)
            res += 'X';

        return res;
    }

    static int[] find(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

    static String encrypt(String text) {
        String res = "";
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] p1 = find(a), p2 = find(b);

            if (p1[0] == p2[0]) {
                res += matrix[p1[0]][(p1[1] + 1) % 5];
                res += matrix[p2[0]][(p2[1] + 1) % 5];
            } else if (p1[1] == p2[1]) {
                res += matrix[(p1[0] + 1) % 5][p1[1]];
                res += matrix[(p2[0] + 1) % 5][p2[1]];
            } else {
                res += matrix[p1[0]][p2[1]];
                res += matrix[p2[0]][p1[1]];
            }
        }
        return res;
    }

    static String decrypt(String text) {
        String res = "";
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] p1 = find(a), p2 = find(b);

            if (p1[0] == p2[0]) {
                res += matrix[p1[0]][(p1[1] + 4) % 5];
                res += matrix[p2[0]][(p2[1] + 4) % 5];
            } else if (p1[1] == p2[1]) {
                res += matrix[(p1[0] + 4) % 5][p1[1]];
                res += matrix[(p2[0] + 4) % 5][p2[1]];
            } else {
                res += matrix[p1[0]][p2[1]];
                res += matrix[p2[0]][p1[1]];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        generateMatrix(key);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("1.Encrypt  2.Decrypt: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            String formatted = formatText(text);
            System.out.println("Encrypted: " + encrypt(formatted));
        } else {
            System.out.println("Decrypted: " + decrypt(text));
        }
    }
}