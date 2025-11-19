import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        TreeMap<String, Node> children = new TreeMap<>();
    }

    static Node root = new Node();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            Node curr = root;
            for (int j = 0; j < k; j++) {
                String food = st.nextToken();

                curr.children.putIfAbsent(food, new Node());
                curr = curr.children.get(food);
            }
        }

        printTree(root, 0);
    }

    public static void printTree(Node node, int depth) {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) System.out.print("--");
            System.out.println(key);
            printTree(node.children.get(key), depth + 1);
        }
    }
}
