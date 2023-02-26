import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

//Encoding
class HuffmanNode {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

public class HuffmanEncoding {
    public static void printCode(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }

        if (root.c != '\0') {
            System.out.println(root.c + ": " + code);
        }

        printCode(root.left, code + "0");
        printCode(root.right, code + "1");
    }

    public static void encode(String text) {
        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new MyComparator());

        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                HuffmanNode node = new HuffmanNode();
                node.c = (char) i;
                node.data = freq[i];
                node.left = null;
                node.right = null;
                pq.add(node);
            }
        }

        while (pq.size() > 1) {
            HuffmanNode x = pq.peek();
            pq.poll();
            HuffmanNode y = pq.peek();
            pq.poll();

            HuffmanNode node = new HuffmanNode();
            node.data = x.data + y.data;
            node.c = '\0';
            node.left = x;
            node.right = y;

            pq.add(node);
        }

        HuffmanNode root = pq.peek();
        printCode(root, "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text to encode: ");
        String text = sc.nextLine();
        System.out.println("Huffman Codes are: ");
        encode(text);
    }
}

//Decoding
public class HuffmanDecoding {
    public static String decode(HuffmanNode root, String s) {
        HuffmanNode curr = root;
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                curr = curr.left;
            } else {
                curr = curr.right;
            }

            if (curr.left == null && curr.right == null) {
                result += curr.c;
                curr = root;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Construct Huffman tree from frequency table
        HuffmanNode root = new HuffmanNode();
        root.data = 5;
        root.c = '\0';

        root.left = new HuffmanNode();
        root.left.data = 2;
        root.left.c = '\0';

        root.left.left = new HuffmanNode();
        root.left.left.data = 1;
        root.left.left.c = 'A';

        root.left.right = new HuffmanNode();
        root.left.right.data = 1;
        root.left.right.c = 'B';

        root.right = new HuffmanNode();
        root.right.data = 3;
        root.right.c = '\0';
