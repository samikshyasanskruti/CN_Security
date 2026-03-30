import java.util.*;

class CeasorCypher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter key: ");
        int key = sc.nextInt();

        System.out.print("1.Encrypt  2.Decrypt: ");
        int choice = sc.nextInt();

        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                if (choice == 1)
                    result += (char) ((c - 'A' + key) % 26 + 'A');
                else if (choice == 2)
                    result += (char) ((c - 'A' - key + 26) % 26 + 'A');
            } else {
                result += c;
            }
        }

        System.out.println("Result: " + result);
    }
}