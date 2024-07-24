package exploration;

import exploration.helpers.tree.helper.BSTIterator;
import exploration.helpers.tree.helper.TreeNode;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class BinarySearchTreeDSA {

    // The first violated node in the BST.
    TreeNode firstViolation = null;
    // The last violated node in the BST.
    TreeNode lastViolation = null;
    // The middle violated node in the BST.
    TreeNode middleViolation = null;
    // The previous node in the BST.
    TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);

    /**
     * This method searches for a node in a Binary Search Tree (BST).
     *
     * @param root       The root node of the BST.
     * @param nodeToFind The value of the node to find.
     * @return The node if found, otherwise null.
     * <p>
     * Example:
     * TreeNode root = new TreeNode(4);
     * root.left = new TreeNode(2);
     * root.right = new TreeNode(5);
     * root.left.left = new TreeNode(1);
     * root.left.right = new TreeNode(3);
     * TreeNode node = SearchBST(root, 3); // Returns the node with value 3
     */
    public TreeNode SearchBST(TreeNode root, int nodeToFind) {
        // If the tree is empty or the root is the node we are searching for
        if (root == null || root.val == nodeToFind) {
            return root;
        }
        // If the node to find is less than the root, search in the left subtree
        if (nodeToFind < root.val) {
            return SearchBST(root.left, nodeToFind);
        } else {
            // If the node to find is greater than the root, search in the right subtree
            return SearchBST(root.right, nodeToFind);
        }
    }

    /**
     * This method finds the minimum or maximum value in a BST.
     *
     * @param root    The root node of the BST.
     * @param res     An array where the result will be stored.
     * @param findMin A boolean that determines whether to find the minimum (if true) or maximum (if false).
     *                <p>
     *                Example:
     *                TreeNode root = new TreeNode(4);
     *                root.left = new TreeNode(2);
     *                root.right = new TreeNode(5);
     *                root.left.left = new TreeNode(1);
     *                root.left.right = new TreeNode(3);
     *                int[] res = new int[1];
     *                Find_Min_Max_BST(root, res, true); // Finds the minimum value in the BST
     */
    public void Find_Min_Max_BST(TreeNode root, int[] res, boolean findMin) {
        // If the tree is empty, return -1
        if (root == null) {
            res[0] = -1;
        } else {
            // Otherwise, store the root's value
            res[0] = root.val;
        }
        // If we are finding the minimum and the left child is not null
        if (findMin && root.left != null) {
            Find_Min_Max_BST(root.left, res, findMin);
        } else if (!findMin && root.right != null) {
            // If we are finding the maximum and the right child is not null
            Find_Min_Max_BST(root.right, res, findMin);
        }
    }

    /**
     * This method finds the ceiling of a given node in a BST.
     * The ceiling is the smallest element in the BST that is greater than or equal to the given node.
     *
     * @param root      The root node of the BST.
     * @param givenNode The node for which to find the ceiling.
     * @return The value of the ceiling node.
     * <p>
     * Example:
     * TreeNode root = new TreeNode(4);
     * root.left = new TreeNode(2);
     * root.right = new TreeNode(5);
     * root.left.left = new TreeNode(1);
     * root.left.right = new TreeNode(3);
     * int ceil = Find_Ceil_BST(root, 3); // Returns 4
     */
    public int Find_Ceil_BST(TreeNode root, int givenNode) {
        int celi = -1;

        while (root != null) {
            if (givenNode == root.val) {
                return givenNode;
            } else if (givenNode > root.val) {
                root = root.right;
            } else {
                celi = root.val;
                root = root.left;
            }
        }
        return celi;
    }

    /**
     * This method finds the floor of a given node in a BST.
     * The floor is the largest element in the BST that is less than or equal to the given node.
     *
     * @param root      The root node of the BST.
     * @param givenNode The node for which to find the floor.
     * @return The value of the floor node.
     * <p>
     * Example:
     * TreeNode root = new TreeNode(4);
     * root.left = new TreeNode(2);
     * root.right = new TreeNode(5);
     * root.left.left = new TreeNode(1);
     * root.left.right = new TreeNode(3);
     * int floor = Find_Floor_BST(root, 3); // Returns 3
     */
    public int Find_Floor_BST(TreeNode root, int givenNode) {
        int floor = -1;

        while (root != null) {
            if (givenNode == root.val) {
                return givenNode;
            } else if (givenNode > root.val) {
                floor = root.val;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }

    /**
     * This method inserts a new node into a Binary Search Tree (BST).
     *
     * @param root The root of the BST.
     * @param val  The value to be inserted into the BST.
     * @return The root of the modified BST.
     * <p>
     * Example:
     * If we have a BST [4,2,7,1,3] and val = 5, the BST after insertion will be [4,2,7,1,3,5].
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If the BST is empty, create a new node with the given value and return it.
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode orgRoot = root;

        // Traverse the BST to find the correct position for the new node.
        while (root != null) {
            if (val > root.val) {
                // If the new node's value is greater than the current node's value, go right.
                if (root.right == null) {
                    TreeNode newNode = new TreeNode(val);
                    root.right = newNode;
                    break;
                }
                root = root.right;
            } else {
                // If the new node's value is less than or equal to the current node's value, go left.
                if (root.left == null) {
                    TreeNode newNode = new TreeNode(val);
                    root.left = newNode;
                    break;
                }
                root = root.left;
            }
        }
        return orgRoot;
    }

    /**
     * This method deletes a node from a Binary Search Tree (BST).
     *
     * @param root The root of the BST.
     * @param key  The value to be deleted from the BST.
     * @return The root of the modified BST.
     * <p>
     * Example:
     * If we have a BST [5,3,6,2,4,null,7] and key = 3, the BST after deletion of the key will be [5,4,6,2,null,null,7].
     */
    public TreeNode deleteNodeBST(TreeNode root, int key) {
        // If the BST is empty, return null.
        if (root == null) {
            return null;
        }

        // Search for the node to be deleted.
        if (key < root.val) {
            // If the key is less than the current node's value, go left.
            root.left = deleteNodeBST(root.left, key);
            return root;
        } else if (key > root.val) {
            // If the key is greater than the current node's value, go right.
            root.right = deleteNodeBST(root.right, key);
            return root;
        } else {
            // Node found.

            // If the node is a leaf node, delete it.
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            }
            // If the node has a right subtree, find the successor.
            else if (root.right != null) {
                int successorValue = findSuccessor(root);
                root.val = successorValue;
                // Delete the successor.
                root.right = deleteNodeBST(root.right, successorValue);
                return root;
            }
            // If the node is not a leaf and has no right subtree but has a left subtree, find the predecessor.
            else {
                int predecessorValue = findPredecessor(root);
                root.val = predecessorValue;
                // Delete the predecessor.
                root.left = deleteNodeBST(root.left, predecessorValue);
                return root;
            }
        }
    }

    /**
     * This method finds the successor of a node in a BST.
     *
     * @param node The node whose successor is to be found.
     * @return The value of the successor node.
     * <p>
     * Example:
     * If we have a BST [5,3,6,2,4,null,7] and node = 5, the successor of the node will be 6.
     */
    private int findSuccessor(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    /**
     * This method finds the predecessor of a node in a BST.
     *
     * @param node The node whose predecessor is to be found.
     * @return The value of the predecessor node.
     * <p>
     * Example:
     * If we have a BST [5,3,6,2,4,null,7] and node = 5, the predecessor of the node will be 4.
     */
    private int findPredecessor(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    /**
     * This method finds the Kth smallest element in a binary search tree.
     * It uses an in-order traversal (left, root, right) to find the Kth smallest element.
     *
     * @param root The root of the binary search tree.
     * @param K    An array of size 1 where K[0] is the Kth smallest element to find.
     * @return The Kth smallest TreeNode if it exists, null otherwise.
     * <p>
     * Example:
     * Input: root = [5,3,6,2,4,null,null,1], k = 3
     * Output: 3
     */
    public TreeNode Kth_Small(TreeNode root, int[] K) {
        if (root == null) {
            return null;
        }

        TreeNode left = Kth_Small(root.left, K);

        if (left != null) {
            // If the Kth smallest value was found in the left subtree, return it.
            return left;
        }

        K[0]--;
        if (K[0] == 0) {
            // If K is 0, we've found the Kth smallest value.
            return root;
        }

        return Kth_Small(root.right, K);
    }

    /**
     * This method finds the Kth largest element in a binary search tree.
     * It uses a reverse in-order traversal (right, root, left) to find the Kth largest element.
     *
     * @param root The root of the binary search tree.
     * @param K    An array of size 1 where K[0] is the Kth largest element to find.
     * @return The Kth largest TreeNode if it exists, null otherwise.
     * <p>
     * Example:
     * Input: root = [5,3,6,2,4,null,null,1], k = 2
     * Output: 5
     */
    public TreeNode Kth_Large(TreeNode root, int[] K) {
        if (root == null) {
            return null;
        }

        TreeNode right = Kth_Large(root.right, K);

        if (right != null) {
            // If the Kth largest value was found in the right subtree, return it.
            return right;
        }

        K[0]--;
        if (K[0] == 0) {
            // If K is 0, we've found the Kth largest value.
            return root;
        }

        return Kth_Large(root.left, K);
    }

    /**
     * This method checks if a binary tree is a valid binary search tree (BST).
     * It uses a pre-order traversal (root, left, right) and checks the BST property at each node.
     *
     * @param root The root of the binary tree.
     * @param min  The minimum value that the current node's value should be greater than.
     * @param max  The maximum value that the current node's value should be less than.
     * @return true if the binary tree is a valid BST, false otherwise.
     * <p>
     * Example:
     * Input: root = [2,1,3]
     * Output: true
     */
    public Boolean Is_Valid_BST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        boolean left = Is_Valid_BST(root.left, min, root.val);
        boolean right = Is_Valid_BST(root.right, root.val, max);

        return left && right;
    }

    /**
     * This method finds the lowest common ancestor (LCA) of two nodes in a binary search tree (BST).
     * The LCA is defined between two nodes node1 and node2 as the lowest node in the tree that has both node1 and
     * node2 as descendants.
     *
     * @param root  The root node of the BST.
     * @param node1 The first node.
     * @param node2 The second node.
     * @return The lowest common ancestor of node1 and node2.
     * <p>
     * Example:
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], node1 = 2, node2 = 8
     * Output: 6
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) {
            return null; // If the tree is empty, return null
        }

        // If both nodes are less than the root, traverse the left subtree
        if (root.val > node2.val && root.val > node1.val) {
            return lowestCommonAncestor(root.left, node1, node2);
        }

        // If both nodes are greater than the root, traverse the right subtree
        if (root.val < node2.val && root.val < node1.val) {
            return lowestCommonAncestor(root.right, node1, node2);
        }

        // If neither of the above conditions are met, this node is the LCA
        return root;
    }

    /**
     * This method finds the inorder predecessor of a given node in a BST.
     * The inorder predecessor of a node is the node with the highest value that is less than the given node.
     *
     * @param root      The root node of the BST.
     * @param givenNode The node for which the inorder predecessor is to be found.
     * @return The inorder predecessor of the given node.
     * <p>
     * Example:
     * Input: root = [5,3,6,2,4,null,null,1], givenNode = 4
     * Output: 3
     */
    public TreeNode Inorder_Predecessor_BST(TreeNode root, int givenNode) {
        TreeNode predecessor = null;

        while (root != null) {
            // If the given node is greater than the current node, the current node is a potential predecessor
            if (givenNode > root.val) {
                predecessor = root;
                root = root.right; // Traverse the right subtree as per BST rules
            } else {
                root = root.left; // Traverse the left subtree as per BST rules
            }
        }
        return predecessor;
    }

    /**
     * This method finds the inorder successor of a given node in a BST.
     * The inorder successor of a node is the node with the lowest value that is greater than the given node.
     *
     * @param root      The root node of the BST.
     * @param givenNode The node for which the inorder successor is to be found.
     * @return The inorder successor of the given node.
     * <p>
     * Example:
     * Input: root = [5,3,6,2,4,null,null,1], givenNode = 4
     * Output: 5
     */
    public TreeNode Inorder_Successor_BST(TreeNode root, int givenNode) {
        TreeNode successor = null;

        while (root != null) {
            // If the given node is less than the current node, the current node is a potential successor
            if (givenNode < root.val) {
                successor = root;
                root = root.left; // Traverse the left subtree as per BST rules
            } else {
                root = root.right; // Traverse the right subtree as per BST rules
            }
        }
        return successor;
    }

    /**
     * This method demonstrates the usage of a BST iterator.
     *
     * @param root The root node of the BST.
     *             <p>
     *             Example:
     *             TreeNode root = new TreeNode(4);
     *             root.left = new TreeNode(2);
     *             root.right = new TreeNode(5);
     *             root.left.left = new TreeNode(1);
     *             root.left.right = new TreeNode(3);
     *             BSTIterator(root); // Demonstrates the usage of the BST iterator
     */
    public void BSTIterator(TreeNode root) {
        BSTIterator obj = new BSTIterator(root);

        int param_1 = obj.next();
        System.out.println(param_1);
        boolean param_2 = obj.hasNext();
        System.out.println(param_2);

        int param_11 = obj.next();
        System.out.println(param_11);
        boolean param_22 = obj.hasNext();
        System.out.println(param_22);

        int param_111 = obj.next();
        System.out.println(param_111);
        boolean param_222 = obj.hasNext();
        System.out.println(param_222);
    }

    /**
     * This method finds if there are two nodes in the BST that add up to a given target.
     *
     * @param root The root node of the BST.
     * @param k    The target sum.
     * @return True if there are two nodes that add up to the target, otherwise false.
     * <p>
     * Example:
     * TreeNode root = new TreeNode(4);
     * root.left = new TreeNode(2);
     * root.right = new TreeNode(5);
     * root.left.left = new TreeNode(1);
     * root.left.right = new TreeNode(3);
     * boolean found = findTarget_aka_find2Sum(root, 7); // Returns true as 2 + 5 = 7
     */
    public boolean findTarget_aka_find2Sum(TreeNode root, int k) {
        return findTarget_aka_find2Sum(root, k, new HashSet<Integer>());
    }

    /**
     * This is a helper method for the findTarget_aka_find2Sum method.
     *
     * @param root The root node of the BST.
     * @param k    The target sum.
     * @param set  A set to keep track of the visited nodes.
     * @return True if there are two nodes that add up to the target, otherwise false.
     */
    private boolean findTarget_aka_find2Sum(TreeNode root, int k, HashSet<Integer> set) {
        if (root == null) {
            return false;
        }

        boolean lt = findTarget_aka_find2Sum(root.left, k, set);
        if (lt) {
            return true;
        }
        int leftSum = k - root.val;
        if (set.contains(leftSum)) {
            return true;
        } else {
            set.add(root.val);
        }

        boolean rt = findTarget_aka_find2Sum(root.right, k, set);
        if (rt) {
            return true;
        }

        return false;
    }

    /**
     * This method recovers a Binary Search Tree (BST) where two elements are swapped.
     *
     * @param root The root of the BST.
     *             <p>
     *             Example:
     *             If we have a BST [1,3,null,null,2], the BST after recovery will be [3,1,null,null,2].
     */
    public void recoverTree(TreeNode root) {
        // Correct the BST.
        correctBST(root);

        // If there is a last violation, swap the values of the first and last violations.
        if (lastViolation != null) {
            int temp = lastViolation.val;
            lastViolation.val = firstViolation.val;
            firstViolation.val = temp;
        }
        // If there is no last violation, swap the values of the first and middle violations.
        else {
            int temp = middleViolation.val;
            middleViolation.val = firstViolation.val;
            firstViolation.val = temp;
        }
    }


    /**
     * This method corrects a binary search tree (BST) where two nodes are swapped.
     * It uses an in-order traversal (left, root, right) to find the nodes that violate the BST property.
     *
     * @param root The root of the BST.
     *             <p>
     *             Example:
     *             Input: root = [3,5,8,7,20]
     *             Output: root = [3,5,7,8,20]
     */
    private void correctBST(TreeNode root) {
        if (root == null) {
            return;
        }

        correctBST(root.left);

        if (prevNode != null && prevNode.val > root.val) {
            if (firstViolation == null) {
                firstViolation = prevNode;
                middleViolation = root;
            } else {
                lastViolation = root;
            }
        }

        prevNode = root;

        correctBST(root.right);
    }

    /**
     * This method converts a sorted array to a balanced binary search tree (BST).
     * It picks the middle element as the root and recursively does the same for the left and right halves of the array.
     *
     * @param sorted The sorted array to convert.
     * @return The root of the balanced BST.
     * <p>
     * Example:
     * Input: sorted = [-10,-3,0,5,9]
     * Output: root = [0,-3,9,-10,null,5]
     */
    public TreeNode sortedArrayToBST(int[] sorted) {
        if (sorted.length == 0) return null;

        return sortedArrayToBST(sorted, 0, sorted.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] sorted, int start, int end) {
        if (end < start) {
            return null;
        } else {
            int mid = (end + start) / 2;
            int midVal = sorted[mid];

            TreeNode resTree = new TreeNode(midVal);

            resTree.left = sortedArrayToBST(sorted, start, mid - 1);
            resTree.right = sortedArrayToBST(sorted, mid + 1, end);

            return resTree;
        }
    }

    /**
     * This method finds the length of the largest valid binary search tree (BST) within a binary tree.
     * It starts with the root and checks if it's a valid BST. If it is, it returns its size.
     * If it's not, it does the same for its left and right children and returns the maximum size.
     *
     * @param root The root of the binary tree.
     * @return The length of the largest valid BST within the binary tree.
     * <p>
     * Example:
     * Input: root = [10,5,15,1,8,null,7]
     * Output: 3
     */
    public int Valid_BST_Length(TreeNode root) {
        if (root == null) {
            return 0;
        }

        boolean isValid = Is_Valid_BST(root, Integer.MIN_VALUE * 10L, Integer.MAX_VALUE * 10L);

        if (isValid) {
            return findSizeOfBST(root);
        } else {
            int ltSize = Valid_BST_Length(root.left);
            int rtSiz = Valid_BST_Length(root.right);
            return Math.max(ltSize, rtSiz);
        }
    }


    /**
     * This method calculates the size of a binary search tree (BST).
     * The size of a BST is defined as the total number of nodes in the tree.
     *
     * @param root The root node of the BST.
     * @return The size of the BST.
     * <p>
     * Example:
     * Input: root = [5,3,6,2,4,null,null,1]
     * Output: 7
     */
    private int findSizeOfBST(TreeNode root) {
        if (root == null) {
            return 0; // If the tree is empty, return 0
        }

        int leftSize = findSizeOfBST(root.left); // Calculate the size of the left subtree
        int rightSize = findSizeOfBST(root.right); // Calculate the size of the right subtree

        return 1 + leftSize + rightSize; // The size of the tree is the size of the root node (1) plus the sizes of
        // its child subtrees
    }

    int idxRunner = 0;

    /**
     * This method builds a BST from a given preorder traversal array.
     * The first element of the preorder array is the root of the tree.
     * The method keeps a running index count on the preorder array and creates left and right nodes of the root.
     *
     * @param preorder   The preorder traversal array of the BST.
     * @param upperbound The maximum value that a node in the BST can have.
     * @return The root node of the BST.
     * <p>
     * Example:
     * Input: preorder = [8,5,1,7,10,12], upperbound = Integer.MAX_VALUE
     * Output: [8,5,10,1,7,null,12]
     */
    public TreeNode Build_BST_Pre_Efficient(int[] preorder, int upperbound) {
        // If the end of the array is reached, return null
        if (idxRunner == preorder.length) {
            return null;
        }

        // If the current element is greater than the upper bound, return null
        if (preorder[idxRunner] > upperbound) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[idxRunner]);
        idxRunner++;

        TreeNode lft = Build_BST_Pre_Efficient(preorder, root.val); // Build the left subtree
        TreeNode rgh = Build_BST_Pre_Efficient(preorder, upperbound); // Build the right subtree

        root.left = lft;
        root.right = rgh;

        return root;
    }


    public TreeNode Delete_Node_NOTUSED(TreeNode root, int nodeToDelete) {
        if (root == null) {
            return root;

        }
        var parentNode = root;
        var orgRoot = root;

        while (root != null) {

            if (root.val == nodeToDelete) {
                // when node to delete is leaf node
                if (root.left == null && root.right == null) {
                    //If root is alone and deleting
                    if (parentNode == root) {
                        return null;
                    }
                    //for leaf node
                    if (parentNode.val > root.val) {
                        root = null;
                        parentNode.left = root;

                    } else {
                        root = null;
                        parentNode.right = root;
                    }

                    // when node has two child
                } else if (root.left != null && root.right != null) {

                    TreeNode inordscrParent = root;
                    var inordscr = root.right;
                    // get in order successor i.e. if x = 10 then in order can be
                    // 11, 12 and it can only be found on last left once
                    // takes first right.
                    while (inordscr.left != null) {
                        inordscrParent = inordscr;
                        inordscr = inordscr.left;
                    }

                    if (inordscrParent == root) { // i.e. there is only one right node just after root i.e. node to
                        // delete and parent still at roo.
                        inordscrParent.right = inordscrParent.right.right;// to make sure if more child preseent down
                        // we dont set delete them all.
                    } else {
                        inordscrParent.left = null; // node deleted.
                    }
                    root.val = inordscr.val; // swap val of node to be deleted

                    root = null; // just to stop loop.
                }
                // when node has one child
                else if (root.left != null) {

                    if (parentNode == root) {
                        parentNode.val = root.left.val;
                        parentNode.left = root.left.left;
                        parentNode.right = root.left != null ? root.left.right : null;
                    } else {

                        if (parentNode.val > root.left.val) {
                            parentNode.left = root.left;
                        } else {
                            parentNode.right = root.left;
                        }


                    }
                    root = null;
                }

                // when node has one child
                else if (root.right != null) {

                    if (parentNode == root) {
                        parentNode.val = root.right.val;
                        parentNode.right = root.right.right;
                        parentNode.left = root.right != null ? root.right.left : null;
                    } else {

                        if (parentNode.val > root.right.val) {
                            parentNode.left = root.right;
                        } else {
                            parentNode.right = root.right;
                        }


                    }
                    root = null;


                }
            } else if (nodeToDelete > root.val) {
                parentNode = root;
                root = root.right;
            } else {
                parentNode = root;
                root = root.left;
            }
        }
        return orgRoot;
    }

    public TreeNode Build_BST_Pre_In_NOTUSED(int[] inorder, int is, int ie, int[] preorder, int preS, int preE,
                                             LinkedHashMap<Integer, Integer> map) {

        // Idea : Same as IN - POST but  for PRE first element is root unlike POST.
        // Note : if IN was not given then sort given pre array and it will become IN i.e. for BST it will be also
        // sorted.


        if (is > ie || preS > preE) {
            return null; // array end must be gt then start.
        }

        var rtVAlFromPre = preorder[preS];
        var ri = map.get(rtVAlFromPre);
        var tre = new TreeNode(rtVAlFromPre);

        var rtLeft = Build_BST_Pre_In_NOTUSED(inorder, is, ri - 1, preorder, preS + 1, preS + ri - is, map);
        // ri-is bcs need len from start idx of IN till RI
        //preS+1 bcs first ele is root identified already

        var rtRight = Build_BST_Pre_In_NOTUSED(inorder, ri + 1, ie, preorder, preS + ri - is + 1, preE, map);
        // ri-is bcs need len from start idx of IN till RI

        tre.left = rtLeft;
        tre.right = rtRight;

        return tre;

    }


}



