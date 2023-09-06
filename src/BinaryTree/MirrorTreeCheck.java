package BinaryTree;

import java.util.*;

public class MirrorTreeCheck {


    static boolean checkMirrorTree(int[] tree1Root, int[] tree1Child, int[] tree2Root, int[] tree2Child, int size) {
        HashMap<Integer, Stack<Integer>> map = new HashMap<>();
        // using a hashmap adding [key, value] with key=root and value=stack<>() with children pushing to the stack
        // for every root
        for (int i = 0; i < size; i++) {
            if (!map.containsKey(tree1Root[i])) {
                map.put(tree1Root[i], new Stack<Integer>());
            }
            map.get(tree1Root[i]).push(tree1Child[i]);
        }

        // for every root key in the hashmap pop every child node from the stack and compare with the node
        // of tree2 child
        for (int i = 0; i < size; i++) {
            if (map.containsKey(tree2Root[i]) && map.get(tree2Root[i]).size() > 0) {
                if (map.get(tree2Root[i]).peek() != tree2Child[i]) {
                    return false;
                }
                map.get(tree2Root[i]).pop();
            } else {
                // does not contain key
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] tree1Root = { 1, 1, 1, 10, 10, 10 };
        int[] tree1Child = { 10, 7, 3, 4, 5, 6 };
        int[] tree2Root = { 1, 1, 1, 10, 10, 10 };
        int[] tree2Child = { 3, 7, 10, 6, 5, 4 };
        int size = tree1Child.length;

        boolean isMirrorTree = checkMirrorTree(tree1Root, tree1Child, tree2Root, tree2Child, size);
        System.out.println(isMirrorTree);

    }
}
