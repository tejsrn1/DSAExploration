package a.expo.helpers.tree.helper;

import java.util.Stack;

public class BSTIterator {
    Stack<TreeNode> stk = new Stack<TreeNode>();

    public BSTIterator(TreeNode root) {
        //FOLLOW INORDER Traversal.
        while (root != null) {
            stk.push(root);
            root = root.left;
        }
    }

    public int next() {
        var temp = stk.pop();
        var tv = temp.val;
        temp = temp.right;//move to right as IN ORDER.
        while (temp != null) {
            stk.push(temp);
            temp = temp.left;
        }
        return tv;
    }

    public boolean hasNext() {
        return !stk.isEmpty();
    }
}
