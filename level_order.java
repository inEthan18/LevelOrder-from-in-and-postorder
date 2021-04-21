import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {
    public static class Tree{
        int value;
        Tree left, right;
        public Tree(int item){
            value = item;
            left = right = null;
        }
    }
    public static ArrayList<Integer> ans = new ArrayList<>();
    public static int[] solve(int[] inorder, int[] postorder){
        if(postorder.length==0) return null;
        if(postorder.length==1) return postorder;
        Tree BT = new Tree(postorder[0]);
        BT = getTree(inorder,postorder,0,postorder.length-1,0,postorder.length-1);
        return getLevelorder(BT);
    }
    public static Tree getTree(int[] inOrder, int[] postOrder, int inOrderS, int inOrderE, int postOrderS, int postOrderE) {
        if (inOrderS > inOrderE) return null; //Start > end == stop
        Tree tree = new Tree(postOrder[postOrderE]);
        if (inOrderS == inOrderE) return tree;
        int ind = find(inOrder, inOrderS, inOrderE, tree.value);
        tree.left = getTree(inOrder, postOrder, inOrderS, ind - 1, postOrderS, postOrderS - inOrderS + ind - 1);
        tree.right = getTree(inOrder, postOrder, ind + 1, inOrderE, postOrderE - inOrderE + ind, postOrderE - 1);
        return tree;
    }
    public static int find(int[] BT, int start, int end, int value){ //Perform a normal search from start to end
        for (int i = start; i <= end; i++) if (BT[i] == value) return i;
        return -1;
    }
    public static int[] getLevelorder(Tree root) {
        List<Integer> list = new ArrayList<>();
        Queue<Tree> q = new LinkedList<>();
        if (root == null)
            return new int[0];
        q.offer(root);
        while (!q.isEmpty()) {
            Tree curr = q.poll();
            if (curr != null)
                list.add(curr.value);
            if (curr.left != null)
                q.offer(curr.left);
            if (curr.right != null)
                q.offer(curr.right);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);
        return result;
    }
    public static void main(String[] args) {
        Main solution = new Solution();
        
        // The output should be [3, 2, 4]
        System.out.println(Arrays.toString(solution.solve(new int[]{2, 3, 4}, new int[]{2, 4, 3})));
    }
 


}