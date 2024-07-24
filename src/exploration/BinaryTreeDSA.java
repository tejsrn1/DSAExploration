package exploration;


import exploration.helpers.tree.helper.binarytree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeDSA {


    /**
     * This method performs an in-order traversal of a Binary Search Tree (BST).
     * In an in-order traversal, the left subtree is visited first, then the root, and then the right subtree.
     *
     * @param  root The root node of the BST.
     *             <p>
     *             Example:
     *             TreeNode root = new TreeNode(4);
     *             root.left = new TreeNode(2);
     *             root.right = new TreeNode(5);
     *             root.left.left = new TreeNode(1);
     *             root.left.right = new TreeNode(3);
     *             Inorder_Traversal(root); // Prints "1 2 3 4 5 "
     */
    public void Inorder_Traversal(TreeNode root) {
        if (root == null) return;
        Inorder_Traversal(root.left);
        System.out.print(root.val + " ");
        Inorder_Traversal(root.right);
    }

    /**
     * This method performs a pre-order traversal of a BST.
     * In a pre-order traversal, the root is visited first, then the left subtree, and then the right subtree.
     *
     * @param  root The root node of the BST.
     *             <p>
     *             Example:
     *             TreeNode root = new TreeNode(4);
     *             root.left = new TreeNode(2);
     *             root.right = new TreeNode(5);
     *             root.left.left = new TreeNode(1);
     *             root.left.right = new TreeNode(3);
     *             Preorder_Traversal(root); // Prints "4 2 1 3 5 "
     */
    public void Preorder_Traversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        Preorder_Traversal(root.left);
        Preorder_Traversal(root.right);
    }

    /**
     * This method performs a post-order traversal of a BST.
     * In a post-order traversal, the left subtree is visited first, then the right subtree, and then the root.
     *
     * @param  root The root node of the BST.
     *             <p>
     *             Example:
     *             TreeNode root = new TreeNode(4);
     *             root.left = new TreeNode(2);
     *             root.right = new TreeNode(5);
     *             root.left.left = new TreeNode(1);
     *             root.left.right = new TreeNode(3);
     *             Postorder_Traversal(root); // Prints "1 3 2 5 4 "
     */
    public void Postorder_Traversal(TreeNode root) {
        if (root == null) return;
        Postorder_Traversal(root.left);
        Postorder_Traversal(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * This method performs a pre-order traversal of a BST iteratively.
     * It uses a stack to keep track of the nodes to visit.
     *
     * @param  root The root node of the BST.
     * @return A list of the nodes in the order they were visited.
     * <p>
     * Example:
     * TreeNode root = new TreeNode(4);
     * root.left = new TreeNode(2);
     * root.right = new TreeNode(5);
     * root.left.left = new TreeNode(1);
     * root.left.right = new TreeNode(3);
     * List<Integer> result = Preorder_Traversal_Iterative(root); // Returns [4, 2, 1, 3, 5]
     */
    public List<Integer> Preorder_Traversal_Iterative(TreeNode root) {
        var list = new ArrayList<Integer>();
        if (root == null) return list;

        var stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            var nd = stack.pop();
            list.add(nd.val);
            if (nd.right != null) {
                stack.push(nd.right);
            }
            if (nd.left != null) {
                stack.push(nd.left);
            }
        }
        return list;
    }


    /**
     * This method performs an in-order traversal of a Binary Search Tree (BST) iteratively.
     *
     * @param  root The root of the BST.
     * @return A list of integers representing the in-order traversal of the BST.
     * <p>
     * Example:
     * If we have a BST [1,null,2,3], the in-order traversal will be [1,3,2].
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root;

        // Add all left nodes to the stack.
        while (currNode != null) {
            stack.push(currNode);
            currNode = currNode.left;
        }

        // Pop the stack and collect the result.
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            // If the node has a right child, move to the right child and add it and all its left children.
            if (node.right != null) {
                TreeNode rightNode = node.right;
                while (rightNode != null) {
                    stack.push(rightNode);
                    rightNode = rightNode.left;
                }
            }
        }
        return list;
    }

    /**
     * This method performs a post-order traversal of a Binary Search Tree (BST) iteratively.
     *
     * @param  root The root of the BST.
     * @return A list of integers representing the post-order traversal of the BST.
     * <p>
     * Example:
     * If we have a BST [1,null,2,3], the post-order traversal will be [3,2,1].
     */
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> holdingStack = new Stack<>();
        Stack<TreeNode> finalStack = new Stack<>();

        // Add the root to the holding stack.
        holdingStack.push(root);

        while (!holdingStack.isEmpty()) {
            // Pop the holding stack and add the node to the final stack.
            TreeNode node = holdingStack.pop();
            finalStack.push(node);

            // If the node has left and right children, add them to the holding stack.
            if (node.left != null) {
                holdingStack.push(node.left);
            }
            if (node.right != null) {
                holdingStack.push(node.right);
            }
        }

        // Pop the final stack and collect the result.
        while (!finalStack.isEmpty()) {
            TreeNode node = finalStack.pop();
            list.add(node.val);
        }
        return list;
    }

    /**
     * This method performs a level-order traversal of a Binary Search Tree (BST).
     *
     * @param  root The root of the BST.
     * @return A list of integers representing the level-order traversal of the BST.
     * <p>
     * Example:
     * If we have a BST [3,9,20,null,null,15,7], the level-order traversal will be [3,9,20,15,7].
     */
    public List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        // Add the root to the queue.
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);

            // Add the left and right children to the queue.
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return list;
    }

    /**
     * This method performs a level-order traversal of a Binary Search Tree (BST) and collects the nodes at each level.
     *
     * @param  root The root of the BST.
     * @return A list of lists of integers representing the nodes at each level in the level-order traversal of the BST.
     * <p>
     * Example:
     * If we have a BST [3,9,20,null,null,15,7], the level-order traversal will be [[3],[9,20],[15,7]].
     */
    public List<List<Integer>> levelOrderTraversalCollectAtEachLevel(TreeNode root) {
        List<List<Integer>> listOfList = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        if (root == null) return listOfList;

        Queue<TreeNode> queue = new LinkedList<>();
        // Add the root to the queue.
        queue.offer(root);
        // Add a null flag to indicate the completion of a level.
        queue.offer(null);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                list.add(node.val);

                // Add the left and right children to the queue.
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            } else {
                // Add the list of all nodes at the same level to the final list.
                listOfList.add(list);
                list = new LinkedList<>();

                if (!queue.isEmpty()) {
                    // Add a null flag to indicate the completion of a level.
                    queue.offer(null);
                }
            }
        }

        return listOfList;
    }


    /**
     * This method finds the maximum depth of a binary tree using level order traversal (Breadth-First Search).
     *
     * @param  root The root of the binary tree.
     * @return The maximum depth of the binary tree.
     * <p>
     * Example:
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 3
     */
    public int MaxDepthOfTree_LevelOrder(TreeNode root) {
        int count = 0;
        if (root == null) return count;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        que.offer(null);

        while (!que.isEmpty()) {
            TreeNode nd = que.poll();

            if (nd != null) {
                if (nd.left != null) {
                    que.offer(nd.left);
                }
                if (nd.right != null) {
                    que.offer(nd.right);
                }
            } else {
                count++;
                if (!que.isEmpty()) {
                    que.offer(null);
                }
            }
        }

        return count;
    }

    /**
     * This method finds the maximum depth of a binary tree using post-order traversal (Depth-First Search).
     *
     * @param  root The root of the binary tree.
     * @return The maximum depth of the binary tree.
     * <p>
     * Example:
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 3
     */
    public int MaxDepthOfTree_Recursive_Via_PostOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lf = MaxDepthOfTree_Recursive_Via_PostOrder(root.left);
        int rt = MaxDepthOfTree_Recursive_Via_PostOrder(root.right);
        int max = Math.max(lf, rt);

        return 1 + max;
    }

    /**
     * This method checks if a binary tree is height-balanced.
     * A binary tree is height-balanced if the depth of the two subtrees of every node never differs by more than one.
     *
     * @param  root The root of the binary tree.
     * @return 0 if the binary tree is height-balanced, -1 otherwise.
     * <p>
     * Example:
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 0
     */
    public int Is_Tree_Balanced(TreeNode root) {
        if (root == null) return 0;

        int lt = Is_Tree_Balanced(root.left);
        if (lt == -1) return lt;

        int rt = Is_Tree_Balanced(root.right);
        if (rt == -1) return rt;

        int diff = Math.abs(lt - rt);
        if (diff > 1) {
            return -1;
        } else {
            return 1 + Math.max(lt, rt);
        }
    }

    /**
     * This method finds the diameter of a binary tree.
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
     *
     * @param  root   The root of the binary tree.
     * @param  maxDia An array of size 1 where maxDia[0] is the maximum diameter found so far.
     * @return The height of the binary tree.
     * <p>
     * Example:
     * Input: root = [1,2,3,4,5]
     * Output: 3
     */
    public int Find_Diameter(TreeNode root, int[] maxDia) {
        if (root == null) return 0;

        int lt = Find_Diameter(root.left, maxDia);
        int rt = Find_Diameter(root.right, maxDia);

        int calDia = lt + rt;
        maxDia[0] = Math.max(maxDia[0], calDia);

        int height = 1 + Math.max(lt, rt);
        return height;
    }

    /**
     * This method finds the maximum path sum in a binary tree.
     * The path is a line between any two nodes in the tree where each node can only be visited once.
     *
     * @param  root   The root of the binary tree.
     * @param  maxSum An array of size 1 where maxSum[0] is the maximum path sum found so far.
     * @return The maximum path sum that can be obtained by considering the current node and either of its left or
     * right child.
     * <p>
     * Example:
     * Input: root = [-10,9,20,null,null,15,7]
     * Output: 42
     */
    public int Find_Max_Path_Sum(TreeNode root, int[] maxSum) {
        if (root == null) return 0;

        int lt = Find_Max_Path_Sum(root.left, maxSum);
        int rt = Find_Max_Path_Sum(root.right, maxSum);

        int caseRegular = Math.max(lt, rt) + root.val;
        int caseNodeAlone = root.val;

        int caseNoDivergentInPath = Math.max(caseRegular, caseNodeAlone);

        int caseAllIncluded = lt + rt + root.val;

        int maxValue = Math.max(caseNoDivergentInPath, caseAllIncluded);

        maxSum[0] = Math.max(maxValue, maxSum[0]);

        return caseNoDivergentInPath;
    }


    /**
     * This method checks if two binary trees are identical.
     * Two trees are identical if they have the same structure and the same node values.
     *
     * @param  root1 The root node of the first tree.
     * @param  root2 The root node of the second tree.
     * @return true if the trees are identical, false otherwise.
     * <p>
     * Example:
     * Input: root1 = [1,2,3], root2 = [1,2,3]
     * Output: true
     */
    public boolean IS_Same_Tree(TreeNode root1, TreeNode root2) {
        // If both trees are empty, they are identical
        if (root1 == null && root2 == null) return true;

        // If one tree is empty and the other is not, they are not identical
        if (root1 == null && root2 != null) return false;
        if (root1 != null && root2 == null) return false;

        // Recursively check the left and right subtrees
        boolean lt = IS_Same_Tree(root1.left, root2.left);
        if (!lt) return false;
        boolean rt = IS_Same_Tree(root1.right, root2.right);
        if (!rt) return false;

        // The trees are identical if the root values are equal and the left and right subtrees are identical
        return root1.val == root2.val && lt && rt;
    }

    /**
     * This method returns the zigzag level order traversal of a binary tree.
     * The nodes' values of the tree are returned as a list of lists. Each list represents one level of the tree.
     * The nodes are listed from left to right, then right to left for the next level and alternate between.
     *
     * @param  root The root node of the tree.
     * @return The zigzag level order traversal of the tree.
     * <p>
     * Example:
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[20,9],[15,7]]
     */
    public List<List<Integer>> Zig_zag_LevelOrder_NEW(TreeNode root) {
        List<List<Integer>> listOfList = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return listOfList;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // Add the root node to the queue
        que.offer(null); // Add a null marker to denote the end of a level
        int curDir = 1; // Initialize the direction to left to right

        while (!que.isEmpty()) {
            TreeNode quNode = que.poll();
            if (quNode != null) {
                list.add(quNode.val);
                if (quNode.left != null) {
                    que.offer(quNode.left);
                }
                if (quNode.right != null) {
                    que.offer(quNode.right);
                }
            } else {
                if (curDir == 1) { // If the direction is left to right
                    listOfList.add(list);
                    curDir = -1; // Flip the direction
                    list = new LinkedList<>();
                } else { // If the direction is right to left
                    Collections.reverse(list); // Reverse the list
                    listOfList.add(list);
                    list = new LinkedList<>();
                    curDir = 1; // Flip the direction
                }
                if (!que.isEmpty()) {
                    que.offer(null); // Add a null marker for the next level
                }
            }
        }

        return listOfList;
    }

    /**
     * This method returns the boundary of a binary tree.
     * The boundary includes the left boundary, leaves, and right boundary in anti-clockwise direction.
     *
     * @param  root The root node of the tree.
     * @return The boundary of the tree.
     * <p>
     * Example:
     * Input: root = [1,null,2,3,4]
     * Output: [1,3,4,2]
     */
    public List<Integer> BoundaryOfBinaryTree(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (!isLeafNode(root)) {
            result.add(root.val);
        }

        BoundaryOfBinaryTree_Left(root.left, result); // Add the left boundary
        BoundaryOfBinaryTree_Leaves(root, result); // Add the leaves
        BoundaryOfBinaryTree_Right(root.right, result); // Add the right boundary

        return result;
    }

    private void BoundaryOfBinaryTree_Left(TreeNode node, List<Integer> list) {
        while (node != null) {
            if (!isLeafNode(node)) {
                list.add(node.val);
            }
            if (node.left != null) { // First check left because it's the left boundary
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    private void BoundaryOfBinaryTree_Leaves(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (isLeafNode(root)) { // Only add leaf nodes
            list.add(root.val);
            return; // Stop as we got a leaf
        }
        // Go find leaves on the left and right side
        BoundaryOfBinaryTree_Leaves(root.left, list);
        BoundaryOfBinaryTree_Leaves(root.right, list);
    }


    /**
     * This method adds the right boundary nodes of a Binary Tree to a list.
     *
     * @param  node The root node of the Binary Tree.
     * @param  list A list to store the right boundary nodes.
     *             <p>
     *             Example:
     *             TreeNode root = new TreeNode(1);
     *             root.left = new TreeNode(2);
     *             root.right = new TreeNode(3);
     *             List<Integer> list = new ArrayList<>();
     *             BoundaryOfBinaryTree_Right(root, list); // Adds 3 to the list
     */
    private void BoundaryOfBinaryTree_Right(TreeNode node, List<Integer> list) {
        var tempList = new LinkedList<Integer>();

        while (node != null) {
            if (!isLeafNode(node)) {
                tempList.add(node.val);
            }
            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        for (int i = tempList.size() - 1; i >= 0; i--) {
            list.add(tempList.get(i));
        }
    }

    /**
     * This method checks if a node is a leaf node.
     *
     * @param  node The node to check.
     * @return True if the node is a leaf node, otherwise false.
     */
    private boolean isLeafNode(TreeNode node) {
        return node.right == null && node.left == null;
    }

    /**
     * This method performs a vertical traversal of a Binary Tree.
     * It returns a list of lists where each list contains the nodes at a specific column from top to bottom.
     *
     * @param  root The root node of the Binary Tree.
     * @return A list of lists representing the vertical order traversal.
     * <p>
     * Example:
     * TreeNode root = new TreeNode(1);
     * root.left = new TreeNode(2);
     * root.right = new TreeNode(3);
     * List<List<Integer>> result = Vertical_Traversal_L(root); // Returns [[2], [1], [3]]
     */
    public List<List<Integer>> Vertical_Traversal_L(TreeNode root) {
        var resMap = new TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>();
        List<List<Integer>> res = new ArrayList<>();

        Vertical_Traversal_L(root, 0, 0, resMap);

        for (var colLevelRes : resMap.entrySet()) {
            var lt = new ArrayList<Integer>();
            for (var rowLevelRes : colLevelRes.getValue().entrySet()) {
                var srtVal = rowLevelRes.getValue().stream().sorted().collect(Collectors.toList());
                for (var val : srtVal) {
                    lt.add(val);
                }
            }
            res.add(lt);
        }

        return res;
    }

    /**
     * This is a helper method for the Vertical_Traversal_L method.
     * It performs a depth-first search on the Binary Tree and stores the nodes in a map.
     *
     * @param  root        The root node of the Binary Tree.
     * @param  curColDepth The current column depth.
     * @param  curRowDepth The current row depth.
     * @param  resMap      A map to store the nodes.
     */
    void Vertical_Traversal_L(TreeNode root, int curColDepth, int curRowDepth,
                              TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> resMap) {
        if (root == null) {
            return;
        }

        if (!resMap.containsKey(curColDepth)) {
            var pq = new PriorityQueue<Integer>();
            pq.add(root.val);
            var rwMap = new TreeMap<Integer, PriorityQueue<Integer>>();
            rwMap.put(curRowDepth, pq);
            resMap.put(curColDepth, rwMap);
        } else {
            var rwMap = resMap.get(curColDepth);
            if (!rwMap.containsKey(curRowDepth)) {
                var pq = new PriorityQueue<Integer>();
                pq.add(root.val);
                rwMap.put(curRowDepth, pq);
                resMap.put(curColDepth, rwMap);
            } else {
                var pq = rwMap.get(curRowDepth);
                pq.add(root.val);
                rwMap.put(curRowDepth, pq);
                resMap.put(curColDepth, rwMap);
            }
        }

        Vertical_Traversal_L(root.left, curColDepth - 1, curRowDepth + 1, resMap);
        Vertical_Traversal_L(root.right, curColDepth + 1, curRowDepth + 1, resMap);
    }

    /**
     * This method performs a vertical traversal of a Binary Search Tree (BST).
     *
     * @param  root The root of the BST.
     * @return A list of lists of integers representing the nodes at each column in the vertical traversal of the BST.
     * <p>
     * Example:
     * If we have a BST [3,9,20,15,7], the vertical traversal will be [[9],[3,15],[20],[7]].
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> resMap = new TreeMap<>();
        List<List<Integer>> res = new ArrayList<>();

        verticalTraversal(root, 0, 0, resMap);

        // For each column sorted in ascending order.
        for (var colLevelRes : resMap.entrySet()) {
            var lt = new ArrayList<Integer>();

            // For each row sorted in ascending order.
            for (var rowLevelRes : colLevelRes.getValue().entrySet()) {
                // Collect in list.
                for (var val : rowLevelRes.getValue()) {
                    lt.add(val);
                }
            }

            // Add into main result.
            res.add(lt);
        }

        return res;
    }

    private void verticalTraversal(TreeNode root, int curColDepth, int curRowDepth,
                                   TreeMap<Integer, TreeMap<Integer, List<Integer>>> resMap) {
        if (root == null) {
            return;
        }

        // Prep all first time.
        if (!resMap.containsKey(curColDepth)) {
            // Collect root value in list.
            var list = new ArrayList<Integer>();
            list.add(root.val);

            // Capture Row #.
            var rowMap = new TreeMap<Integer, List<Integer>>();
            rowMap.put(curRowDepth, list);

            // Add back result map at column level.
            resMap.put(curColDepth, rowMap);
        }
        // Update column but make sure row map exists if not create one.
        else {
            var rowMap = resMap.get(curColDepth);

            if (!rowMap.containsKey(curRowDepth)) {
                // Add value in list.
                var list = new ArrayList<Integer>();
                list.add(root.val);

                // Capture Row #.
                rowMap.put(curRowDepth, list);

                // Update back result map at column level.
                resMap.put(curColDepth, rowMap);
            }
            // Row already present.
            else {
                // Update in list.
                var list = rowMap.get(curRowDepth);
                list.add(root.val);

                // Update back to row map.
                rowMap.put(curRowDepth, list);

                // Update back result map at column level.
                resMap.put(curColDepth, rowMap);
            }
        }

        verticalTraversal(root.left, curColDepth - 1, curRowDepth + 1, resMap);
        verticalTraversal(root.right, curColDepth + 1, curRowDepth + 1, resMap);
    }

    /**
     * This method performs a top view traversal of a Binary Search Tree (BST).
     *
     * @param  root The root of the BST.
     * @return A TreeMap of integers representing the nodes visible from the top view of the BST.
     * <p>
     * Example:
     * If we have a BST [1,2,3,4,5,6,7], the top view will be {0=1, -1=2, -2=4, 1=3, 2=7}.
     */
    public TreeMap<Integer, Integer> topViewTraversal(TreeNode root) {
        TreeMap<Integer, Integer> sortedKeyMap = new TreeMap<>();
        topViewTraversal(root, 0, sortedKeyMap);
        return sortedKeyMap;
    }

    private void topViewTraversal(TreeNode root, int col, TreeMap<Integer, Integer> map) {
        if (root == null) {
            return;
        }

        // Will only put one value for given key.
        map.putIfAbsent(col, root.val);

        topViewTraversal(root.left, col - 1, map);
        topViewTraversal(root.right, col + 1, map);
    }

    /**
     * This method performs a bottom view traversal of a Binary Search Tree (BST).
     *
     * @param  root The root of the BST.
     * @return A TreeMap of integers representing the nodes visible from the bottom view of the BST.
     * <p>
     * Example:
     * If we have a BST [1,2,3,4,5,6,7], the bottom view will be {0=1, -1=2, -2=4, 1=3, 2=7}.
     */
    public TreeMap<Integer, Integer> bottomViewTraversal(TreeNode root) {
        TreeMap<Integer, Integer> sortedKeyMap = new TreeMap<>();
        bottomViewTraversal(root, 0, sortedKeyMap);
        return sortedKeyMap;
    }

    private void bottomViewTraversal(TreeNode root, int col, TreeMap<Integer, Integer> map) {
        if (root == null) {
            return;
        }

        // Will only put the last value for given key.
        map.put(col, root.val);

        bottomViewTraversal(root.left, col - 1, map);
        bottomViewTraversal(root.right, col + 1, map);
    }


    /**
     * This method performs a bottom view traversal of a binary tree.
     * It uses a TreeMap to store the last node at each horizontal distance from the root.
     *
     * @param  root The root of the binary tree.
     * @param  col  The horizontal distance from the root.
     * @param  map  A TreeMap where the key is the horizontal distance and the value is the node's value.
     *             <p>
     *             Example:
     *             Input: root = [1,2,3,4,5,6,7]
     *             Output: [4,2,1,3,7]
     */
    private void BottomView_Traversal(TreeNode root, int col, TreeMap<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        map.put(col, root.val);

        BottomView_Traversal(root.left, col - 1, map);
        BottomView_Traversal(root.right, col + 1, map);
    }

    /**
     * This method finds the leftmost node at the bottom level of a binary tree.
     *
     * @param  root The root of the binary tree.
     * @return The value of the leftmost node at the bottom level.
     * <p>
     * Example:
     * Input: root = [1,2,3,4,5]
     * Output: 4
     */
    public int Bottom_LEFT_View_Traversal(TreeNode root) {
        int[] store = new int[2];
        store[0] = 0;
        store[1] = root.val;

        Bottom_LEFT_View_Traversal(root, 0, store);
        return store[1];
    }

    private void Bottom_LEFT_View_Traversal(TreeNode root, int curDepth, int[] store) {
        if (root == null) {
            return;
        }
        if (curDepth > store[0]) {
            store[0] = curDepth;
            store[1] = root.val;
        }

        Bottom_LEFT_View_Traversal(root.left, curDepth + 1, store);
        Bottom_LEFT_View_Traversal(root.right, curDepth + 1, store);
    }

    /**
     * This method performs a left view traversal of a binary tree.
     * It captures the first node at each level of the binary tree.
     *
     * @param  root The root of the binary tree.
     * @return A list of nodes representing the left view of the binary tree.
     * <p>
     * Example:
     * Input: root = [1,2,3,4,5]
     * Output: [1,2,4]
     */
    public List<Integer> LeftView_Traversal(TreeNode root) {
        var res = new LinkedList<Integer>();
        LeftView_Traversal(root, 0, res);
        return res;
    }


    /**
     * This method performs a left view traversal of a binary tree.
     * The left view of a binary tree contains the first node of each level as seen from the left side.
     *
     * @param  root       The root node of the tree.
     * @param  rowOrDepth The current depth or row in the tree.
     * @param  res        The result list to store the left view nodes.
     *                   <p>
     *                   Example:
     *                   Input: root = [1,2,3,4,5]
     *                   Output: [1,2,4]
     */
    private void LeftView_Traversal(TreeNode root, int rowOrDepth, LinkedList<Integer> res) {
        if (root == null) {
            return;
        }

        // If the size of the result list is equal to the current depth, add the node to the list
        if (res.size() == rowOrDepth) {
            res.add(root.val);
        }

        // Recursively traverse the left and right subtrees, increasing the depth at each level
        LeftView_Traversal(root.left, rowOrDepth + 1, res);
        LeftView_Traversal(root.right, rowOrDepth + 1, res);
    }

    /**
     * This method returns the right view of a binary tree.
     * The right view of a binary tree contains the first node of each level as seen from the right side.
     *
     * @param  root The root node of the tree.
     * @return The right view of the tree.
     * <p>
     * Example:
     * Input: root = [1,2,3,4,5]
     * Output: [1,3,5]
     */
    public List<Integer> RightView_Traversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        RightView_Traversal(root, 0, res); // Start the traversal at depth 0
        return res;
    }

    private void RightView_Traversal(TreeNode root, int rowOrDepth, LinkedList<Integer> res) {
        if (root == null) {
            return;
        }

        // If the size of the result list is equal to the current depth, add the node to the list
        if (res.size() == rowOrDepth) {
            res.add(root.val);
        }

        // Recursively traverse the right and left subtrees, increasing the depth at each level
        RightView_Traversal(root.right, rowOrDepth + 1, res);
        RightView_Traversal(root.left, rowOrDepth + 1, res);
    }

    /**
     * This method checks if a binary tree is symmetric.
     * A binary tree is symmetric if the left subtree is a mirror reflection of the right subtree.
     *
     * @param  root The root node of the tree.
     * @return true if the tree is symmetric, false otherwise.
     * <p>
     * Example:
     * Input: root = [1,2,2,3,4,4,3]
     * Output: true
     */
    public boolean Symmetric_Tree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return IsSymmetricTree(root.left, root.right);
    }

    private boolean IsSymmetricTree(TreeNode root1, TreeNode root2) {
        // If both subtrees are empty, they are symmetric
        if (root1 == null && root2 == null)
            return true;

        // If one subtree is empty and the other is not, they are not symmetric
        if (root1 == null && root2 != null)
            return false;
        if (root1 != null && root2 == null)
            return false;

        // Recursively check the left subtree of root1 and the right subtree of root2, and vice versa
        boolean lt = IS_Same_Tree(root1.left, root2.right);
        if (!lt)
            return false;
        boolean rt = IS_Same_Tree(root1.right, root2.left);
        if (!rt)
            return false;

        // The subtrees are symmetric if the root values are equal and the left and right subtrees are symmetric
        return root1.val == root2.val && lt && rt;
    }


    /**
     * This method prints the path from the root to a given node in a binary tree.
     * The path is returned as a list of node values.
     *
     * @param  root       The root node of the tree.
     * @param  nodeToFind The node to find in the tree.
     * @param  res        The result list to store the path.
     * @return true if the node is found, false otherwise.
     * <p>
     * Example:
     * Input: root = [1,2,3], nodeToFind = 3
     * Output: [1,3]
     */
    public boolean Print_Path_From_RootTONode(TreeNode root, int nodeToFind, LinkedList<Integer> res) {
        if (root == null) {
            return false;
        }

        res.add(root.val);

        if (root.val == nodeToFind) {
            return true;
        }

        // Recursively search the left and right subtrees
        boolean lt = Print_Path_From_RootTONode(root.left, nodeToFind, res);
        boolean rt = Print_Path_From_RootTONode(root.right, nodeToFind, res);

        if (lt || rt) { // If the node is found in either subtree, return true
            return true;
        }

        // If the node is not found, remove the last node from the path and return false
        res.remove(res.size() - 1);
        return false;
    }

    /**
     * This method returns all root-to-leaf paths in a binary tree.
     * Each path is represented as a string of node values separated by "->".
     *
     * @param  root The root node of the tree.
     * @return The root-to-leaf paths in the tree.
     * <p>
     * Example:
     * Input: root = [1,2,3,null,5]
     * Output: ["1->2->5","1->3"]
     */
    public List<String> BinaryTreePaths(TreeNode root) {
        ArrayList<Integer> lt = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        BinaryTreePaths(root, lt, res);

        return res;
    }

    void BinaryTreePaths(TreeNode root, List<Integer> list, List<String> res) {
        if (root == null) {
            return;
        }

        list.add(root.val);

        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int l : list) {
                sb.append(l).append("->");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            list.remove(list.size() - 1);
            return;
        }

        // Recursively find paths in the left and right subtrees
        BinaryTreePaths(root.left, list, res);
        BinaryTreePaths(root.right, list, res);

        list.remove(list.size() - 1);
    }

    /**
     * This method finds the lowest common ancestor (LCA) of two nodes in a binary tree.
     * The LCA is defined between two nodes nodeA and nodeB as the lowest node in the tree that has both nodeA and
     * nodeB as descendants.
     *
     * @param  root  The root node of the tree.
     * @param  nodeA The first node.
     * @param  nodeB The second node.
     * @return The lowest common ancestor of nodeA and nodeB.
     * <p>
     * Example:
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodeA = 5, nodeB = 1
     * Output: 3
     */
    public TreeNode Lowest_Common_Ancestor(TreeNode root, int nodeA, int nodeB) {
        if (root == null) {
            return root;
        }

        // If the root is either nodeA or nodeB, it is the LCA
        if (root.val == nodeA || root.val == nodeB) {
            return root;
        }

        // Recursively find the LCA in the left and right subtrees
        TreeNode lt = Lowest_Common_Ancestor(root.left, nodeA, nodeB);
        TreeNode rt = Lowest_Common_Ancestor(root.right, nodeA, nodeB);

        // If the LCA is not found in either subtree, return the other subtree
        if (lt == null) {
            return rt;
        }
        if (rt == null) {
            return lt;
        }

        // If the LCA is found in both subtrees, the current node is the LCA
        return root;
    }

    /**
     * This method counts the number of nodes in a binary tree.
     *
     * @param  root The root node of the tree.
     * @return The number of nodes in the tree.
     * <p>
     * Example:
     * Input: root = [1,2,3,4,5]
     * Output: 5
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Recursively count the nodes in the left and right subtrees
        int lt = countNodes(root.left);
        int rt = countNodes(root.right);

        return 1 + lt + rt; // The total number of nodes is the sum of the root node and its child nodes
    }


    /**
     * This method calculates the width of a Binary Search Tree (BST).
     *
     * @param  root The root of the BST.
     * @return The maximum width of the BST.
     * <p>
     * Example:
     * If we have a BST [1,3,2,5,3,null,9], the maximum width of the BST is 4.
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int max = 0;
        LinkedList<HashMap<TreeNode, Integer>> queue = new LinkedList<>();

        HashMap<TreeNode, Integer> treeMap = new HashMap<>();
        treeMap.put(root, 0); // Node and its column index.
        queue.offer(treeMap); // Add root first.

        while (!queue.isEmpty()) {
            TreeNode firstNode = queue.getFirst().keySet().stream().findFirst().get();
            int firstIdx = queue.getFirst().get(firstNode);

            TreeNode lastNode = queue.getLast().keySet().stream().findFirst().get();
            int lastIdx = queue.getLast().get(lastNode);

            // Calculate max and capture so far max.
            max = Math.max(max, lastIdx - firstIdx + 1);

            int sizeAtEachLevel = queue.size();

            for (int i = 0; i < sizeAtEachLevel; i++) {
                treeMap = queue.removeFirst();
                TreeNode node = treeMap.keySet().stream().findFirst().get();
                int nodeIdx = treeMap.get(node);

                if (node.left != null) {
                    HashMap<TreeNode, Integer> mapLeft = new HashMap<>();
                    mapLeft.put(node.left, 2 * nodeIdx); // Tree node parent -> child gets double.
                    queue.addLast(mapLeft);
                }
                if (node.right != null) {
                    HashMap<TreeNode, Integer> mapRight = new HashMap<>();
                    mapRight.put(node.right, 2 * nodeIdx + 1); // Tree node parent -> child gets double.
                    queue.addLast(mapRight);
                }
            }
        }

        return max;
    }

    /**
     * This method serializes a Binary Search Tree (BST) into a string.
     *
     * @param  root The root of the BST.
     * @return A string representing the serialized BST.
     * <p>
     * Example:
     * If we have a BST [1,2,3], the serialized BST will be "1,2,null,null,3,null,null".
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }

        sb.append(root.val).append(',');
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    /**
     * This method deserializes a string into a Binary Search Tree (BST).
     *
     * @param  input The string to be deserialized.
     * @return The root of the deserialized BST.
     * <p>
     * Example:
     * If we have a string "1,2,null,null,3,null,null", the deserialized BST will be [1,2,3].
     */
    public TreeNode deserialize(String input) {
        String[] inputList = input.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(inputList));
        return deserialize(list);
    }

    private TreeNode deserialize(List<String> list) {
        // When null comes up.
        if (list.get(0).equals("null")) {
            // Remove from list and return null.
            list.remove(0);
            return null;
        }

        // Build tree node.
        TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
        // Remove from list.
        list.remove(0);
        node.left = deserialize(list);
        node.right = deserialize(list);
        return node;
    }

    /**
     * This method modifies a binary tree in such a way that the sum of the children nodes is equal to or greater
     * than the parent node.
     * It uses a post-order traversal (left, right, root) to ensure that the children nodes are processed before the
     * parent node.
     *
     * @param  root The root of the binary tree.
     *             <p>
     *             Example:
     *             Input: root = [1,2,3]
     *             Output: root = [5,2,3]
     */
    public void Child_sum_Prop(TreeNode root) {
        if (root == null) {
            return;
        }

        Child_sum_Prop(root.left);
        Child_sum_Prop(root.right);

        int childTotal = 0;
        if (root.left != null) {
            childTotal += root.left.val;
        }
        if (root.right != null) {
            childTotal += root.right.val;
        }
        if (root.val < childTotal) {
            root.val = childTotal;
        }
    }

    /**
     * This method builds a binary tree from its in-order and post-order traversals.
     * It uses a LinkedHashMap to store the indices of the in-order traversal elements for efficient lookups.
     *
     * @param  inorder   The in-order traversal of the binary tree.
     * @param  is        The start index of the in-order traversal.
     * @param  ie        The end index of the in-order traversal.
     * @param  postorder The post-order traversal of the binary tree.
     * @param  ps        The start index of the post-order traversal.
     * @param  pe        The end index of the post-order traversal.
     * @param  map       A LinkedHashMap where the key is the node's value and the value is the node's index in the
     *                  in-order traversal.
     * @return The root of the binary tree.
     * <p>
     * Example:
     * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * Output: root = [3,9,20,null,null,15,7]
     */
    public TreeNode Build_Tree_Post_In(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
                                       LinkedHashMap<Integer, Integer> map) {
        if (is > ie || ps > pe) {
            return null;
        }

        int rtVAlFromPost = postorder[pe];
        int ri = map.get(rtVAlFromPost);
        TreeNode tre = new TreeNode(rtVAlFromPost);

        TreeNode rtLeft = Build_Tree_Post_In(inorder, is, ri - 1, postorder, ps, ps + ri - is - 1, map);
        TreeNode rtRight = Build_Tree_Post_In(inorder, ri + 1, ie, postorder, ps + ri - is, pe - 1, map);

        tre.left = rtLeft;
        tre.right = rtRight;

        return tre;
    }

    /**
     * This method builds a binary tree from its in-order and pre-order traversals.
     * It uses a LinkedHashMap to store the indices of the in-order traversal elements for efficient lookups.
     *
     * @param  inorder  The in-order traversal of the binary tree.
     * @param  is       The start index of the in-order traversal.
     * @param  ie       The end index of the in-order traversal.
     * @param  preorder The pre-order traversal of the binary tree.
     * @param  preS     The start index of the pre-order traversal.
     * @param  preE     The end index of the pre-order traversal.
     * @param  map      A LinkedHashMap where the key is the node's value and the value is the node's index in the
     *                 in-order traversal.
     * @return The root of the binary tree.
     * <p>
     * Example:
     * Input: inorder = [9,3,15,20,7], preorder = [3,9,20,15,7]
     * Output: root = [3,9,20,null,null,15,7]
     */
    public TreeNode Build_Tree_Pre_In(int[] inorder, int is, int ie, int[] preorder, int preS, int preE,
                                      LinkedHashMap<Integer, Integer> map) {
        if (is > ie || preS > preE) {
            return null;
        }

        int rtVAlFromPre = preorder[preS];
        int ri = map.get(rtVAlFromPre);
        TreeNode tre = new TreeNode(rtVAlFromPre);

        TreeNode rtLeft = Build_Tree_Pre_In(inorder, is, ri - 1, preorder, preS + 1, preS + ri - is, map);
        TreeNode rtRight = Build_Tree_Pre_In(inorder, ri + 1, ie, preorder, preS + ri - is + 1, preE, map);

        tre.left = rtLeft;
        tre.right = rtRight;

        return tre;
    }

    /**
     * This method flattens a binary tree into a linked list in-place.
     * It uses a post-order traversal (right, left, root) to ensure that the right child is processed before the left
     * child.
     *
     * @param  root The root of the binary tree.
     *             <p>
     *             Example:
     *             Input: root = [1,2,5,3,4,null,6]
     *             Output: root = [1,null,2,null,3,null,4,null,5,null,6]
     */
    public void Flatten_Tree_LinkedList(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        Flatten_Tree_LinkedList_PreFashion_D(root, prev);
    }

    void Flatten_Tree_LinkedList_PreFashion_D(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }

        Flatten_Tree_LinkedList_PreFashion_D(root.right, prev);
        Flatten_Tree_LinkedList_PreFashion_D(root.left, prev);

        root.right = prev[0];
        root.left = null;
        prev[0] = root;
    }


    /**
     * This method returns the zigzag level order traversal of a binary tree.
     * The nodes' values of the tree are returned as a list of lists. Each list represents one level of the tree.
     * The nodes are listed from left to right, then right to left for the next level and alternate between.
     *
     * @param  root The root node of the tree.
     * @return The zigzag level order traversal of the tree.
     * <p>
     * Example:
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[20,9],[15,7]]
     */
    public List<List<Integer>> Zig_zag_LevelOrder_NOTUSED(TreeNode root) {
        List<List<Integer>> listOfList = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return listOfList;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // Add the root node to the queue
        que.offer(null); // Add a null marker to denote the end of a level

        boolean IsNextLevelflip = true;
        Stack<TreeNode> stack = new Stack<>();

        while (!que.isEmpty()) {
            TreeNode quNode = que.poll();
            if (quNode != null) {
                if (IsNextLevelflip) {
                    list.add(quNode.val); // Add the node to the list
                    stack.push(quNode); // Add the node to the stack
                    while (que.peek() != null) { // Add all nodes on the same level to the stack
                        TreeNode tempQuNode = que.poll();
                        list.add(tempQuNode.val);
                        stack.push(tempQuNode);
                    }
                    while (!stack.isEmpty()) {
                        TreeNode stkNode = stack.pop();
                        if (stkNode.right != null) {
                            que.offer(stkNode.right);
                        }
                        if (stkNode.left != null) {
                            que.offer(stkNode.left);
                        }
                    }
                } else {
                    list.add(quNode.val); // Add the node to the list
                    stack.push(quNode); // Add the node to the stack
                    while (que.peek() != null) {
                        TreeNode tempQuNode = que.poll();
                        list.add(tempQuNode.val);
                        stack.push(tempQuNode);
                    }
                    while (!stack.isEmpty()) {
                        TreeNode stkNode = stack.pop();
                        if (stkNode.left != null) {
                            que.offer(stkNode.left);
                        }
                        if (stkNode.right != null) {
                            que.offer(stkNode.right);
                        }
                    }
                }
            } else {
                listOfList.add(list);
                list = new LinkedList<>();
                IsNextLevelflip = !IsNextLevelflip; // Flip the direction for the next level
                if (!que.isEmpty()) {
                    que.offer(null); // Add a null marker for the next level
                }
            }
        }

        return listOfList;
    }

    /**
     * This method returns the boundary of a binary tree.
     * The boundary includes the left boundary, leaves, and right boundary in anti-clockwise direction.
     *
     * @param  root The root node of the tree.
     * @return The boundary of the tree.
     * <p>
     * Example:
     * Input: root = [1,null,2,3,4]
     * Output: [1,3,4,2]
     */
    public List<Integer> BoundaryOfBinaryTree_NOTUSED(TreeNode root) {
        List<Integer> boundaryList = new LinkedList<>();
        LinkedList<Integer> listLT = new LinkedList<>();
        LinkedList<Integer> listRT = new LinkedList<>();
        LinkedList<Integer> runningList = new LinkedList<>();
        if (root == null) return boundaryList;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // Add the root node to the queue
        que.offer(null); // Add a null marker to denote the end of a level

        while (!que.isEmpty()) {
            TreeNode nd = que.poll();

            if (nd != null) {
                runningList.add(nd.val); // Add the node to the running list

                if (nd.left != null) { // Add the left child to the queue
                    que.offer(nd.left);
                }
                if (nd.right != null) { // Add the right child to the queue
                    que.offer(nd.right);
                }
            } else {
                if (!que.isEmpty()) {
                    que.offer(null); // Add a null marker for the next level
                } else {
                    boundaryList.addAll(listLT);
                    boundaryList.addAll(runningList);

                    for (int p = listRT.size() - 1; p >= 0; p--) {
                        boundaryList.add(listRT.get(p));
                    }
                    return boundaryList;
                }

                int size = runningList.size();
                if (size <= 1) {
                    listLT.add(runningList.get(0));
                } else {
                    listLT.add(runningList.get(0));
                    listRT.add(runningList.get(size - 1));
                }
                runningList = new LinkedList<>();
            }
        }
        return boundaryList;
    }


}
