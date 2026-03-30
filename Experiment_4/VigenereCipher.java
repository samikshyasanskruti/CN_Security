import java.util.*;

class VigenereCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter key: ");
        String key = sc.nextLine().toUpperCase();

        System.out.print("1.Encrypt  2.Decrypt: ");
        int choice = sc.nextInt();

        String result = "";
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            char t = text.charAt(i);

            if (Character.isLetter(t)) {
                char k = key.charAt(j % key.length());

                if (choice == 1)
                    result += (char) ((t + k - 2 * 'A') % 26 + 'A');
                else
                    result += (char) ((t - k + 26) % 26 + 'A');

                j++;
            } else {
                result += t;
            }
        }

        System.out.println("Result: " + result);
    }
}