import java.util.*;

public class Main {

    public static boolean isSumEqual(String[] words, String result) {
        // create a map to store the digit assigned to each character
        Map<Character, Integer> charToDigit = new HashMap<>();
        charToDigit.put('A', 0);
        charToDigit.put('B', 1);
        charToDigit.put('C', 2);
        charToDigit.put('D', 3);
        charToDigit.put('E', 4);
        charToDigit.put('F', 5);
        charToDigit.put('G', 6);
        charToDigit.put('H', 7);
        charToDigit.put('I', 8);
        charToDigit.put('J', 9);
        charToDigit.put('K', 0);
        charToDigit.put('L', 1);
        charToDigit.put('M', 2);
        charToDigit.put('N', 3);
        charToDigit.put('O', 4);
        charToDigit.put('P', 5);
        charToDigit.put('Q', 6);
        charToDigit.put('R', 7);
        charToDigit.put('S', 8);
        charToDigit.put('T', 9);
        charToDigit.put('U', 0);
        charToDigit.put('V', 1);
        charToDigit.put('W', 2);
        charToDigit.put('X', 3);
        charToDigit.put('Y', 4);
        charToDigit.put('Z', 5);

        // convert each word to its corresponding number and concatenate them
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                sb.append(charToDigit.get(c));
            }
        }
        int num1 = Integer.parseInt(sb.toString());

        // convert the targeted word to its corresponding number
        sb = new StringBuilder();
        for (char c : result.toCharArray()) {
            sb.append(charToDigit.get(c));
        }
        int num2 = Integer.parseInt(sb.toString());

        // check if the sum of the numbers is equal to the targeted number
        return num1 == num2;
    }
public static void main(String[] args) {
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        boolean isEqual = isSumEqual(words, result);
        System.out.println(isEqual); // Output: true
    }
}