package exploration;

import exploration.helpers.tree.helper.BSTIterator;
import exploration.helpers.tree.helper.TreeNode;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class BinarySearchTree {

    public TreeNode SearchBST(TreeNode root, int nodeToFind) {

        if (root == null || root.val == nodeToFind) {
            return root;
        }
        if (nodeToFind < root.val) {
            return SearchBST(root.left, nodeToFind);
        } else {
            return SearchBST(root.right, nodeToFind);
        }
    }

    public void Find_Min_Max_BST(TreeNode root, int[] res, boolean findMin) {

       /*  ask a question on your interview.
         Is it ok to return a -1 if BST is empty?
         BST can store not only positive but negative values as well.*/

        if (root == null) {
            res[0] = -1;
        } else {
            res[0] = root.val;
        }
        if (findMin && root.left != null) {
            Find_Min_Max_BST(root.left, res, findMin);
        } else if (!findMin && root.right != null) {
            Find_Min_Max_BST(root.right, res, findMin);
        }
    }

    public int Find_Ceil_BST(TreeNode root, int givenNode) {

      /*   Idea:
       Ceiling =>  any number immediately greater than given No  or number it self
       e.g.  Key: 11   Ceil: 12 (when 11 not present if does then 11 is ceiling)

       Go to right when given number is bigger than root
       and then keep going left as to find immediate bigger number then given.

       */

        int celi = -1;

        while (root != null) {
            if (givenNode == root.val) {
                return givenNode;
            } else if (givenNode > root.val) {
                root = root.right;
            } else {
                //this is where possible immediate bigger number so keep record ceiling.
                celi = root.val;
                root = root.left;
            }
        }
        return celi;
    }

    public int Find_Floor_BST(TreeNode root, int givenNode) {

         /*   Idea:
       Floor =>  any number immediately smaller than given No  or number itself
       e.g.  Key: 15   Ceil: 12 (when 15 not present if does then 15 is floor)

        when given number is bigger than root and record root as floor and go right
        why? as left is already smaller than root and we need immediate smaller which is root already
        so go right and look for number smaller than given numben on left on right subtree.

       */

        int floor = -1;

        while (root != null) {

            if (givenNode == root.val) {
                return givenNode;
            } else if (givenNode > root.val) {
                //track potential floor
                floor = root.val;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }

    public TreeNode InsertIntoBST(TreeNode root, int val) {

        /*
        Idea : find position of new node by traversing BST and before it hits null add new node at last.
        * */
        if (root == null) {
            return new TreeNode(val);
        }

        var orgRoot = root;

        while (root != null) {

            if (val > root.val) {
                if (root.right == null) {
                    var newNode = new TreeNode(val);
                    root.right = newNode;
                    break;
                }
                root = root.right;
            } else {

                if (root.left == null) {
                    var newNode = new TreeNode(val);
                    root.left = newNode;
                    break;
                }
                root = root.left;
            }
        }
        return orgRoot;
    }

    public TreeNode Delete_Node_BST(TreeNode root, int key) {

        /*
        Idea: There are 3 things.
        1. Traversing e..g pre post in..
        2. Successor i.e. next node i.e next bigger node then given node. e.g. 32 then 34 is next node
        3. Predecessor i.e. prev node i.e prev smaller node then given node. e.g 24 then 22 is prev node

        In this problem when deleting node there could be 3  situtation.
        1. given node IS leaf node then simply delete it.
        2. given node has right side PRESENT, so we can not just delete it then
            find its successor
            replace given node value with successor info.
            now successor becomes node to be deleted.

        3. given node is NOT leaf and NO right side present BUT left PRESENT, then
             find its predecessor node
             replace given node value with predecessor info
             now predecessor becomes node to be deleted.

        * */

        if (root == null) {
            return null;
        }

        //search node
        if (key < root.val) {
            // delete left side of tree attach to left on current node
            root.left = Delete_Node_BST(root.left, key);
            return root;
        } else if (key > root.val) {
            // delete right side of tree and attach to right on current node
            root.right = Delete_Node_BST(root.right, key);
            return root;
        } else {

            //node found and

            // it is LEAF
            if (root.left == null && root.right == null) {
                root = null;// deleted.
                return root;
            }
            //it has RIGHT subtree
            else if (root.right != null) {

                // go for successor.
                var successorValue = findSuccessor(root);
                root.val = successorValue;

                //go on right side and delete successor
                root.right = Delete_Node_BST(root.right, successorValue);
                return root;
            }
            //it is NOT a leaf and has NO RIGHT subtree
            else {

                // go for Predecessor.
                var predecessorValue = findPredecessor(root);
                root.val = predecessorValue;

                //go on right side and delete successor
                root.left = Delete_Node_BST(root.left, predecessorValue);
                return root;
            }

        }
    }

    private int findSuccessor(TreeNode node) {

        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;

    }

    private int findPredecessor(TreeNode node) {

        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;

    }

    public TreeNode Kth_Small(TreeNode root, int[] K) {

        /* Idea : Go all the way to left and start from there till reach count
          IN order traversal works here.

        * */

        if (root == null) {
            return null;
        }

        var left = Kth_Small(root.left, K);

        if (left != null) {
            // i.e. previously found kth value just need to pass it back.
            return left;
        }

        // reduce count stored in array
        K[0]--;
        // if 0 i.e. reached at K small value so return it.
        if (K[0] == 0) {
            return root;
        }

        return Kth_Small(root.right, K);
    }

    public TreeNode Kth_Large(TreeNode root, int[] K) {

         /* Idea : Go all the way to right and start from there till reach count
          Reverse IN order i.e. right , root , left  traversal works here.

        * */

        if (root == null) {
            return null;
        }

        var right = Kth_Large(root.right, K);

        if (right != null) {
            // i.e previously found largest value just need to pass it to root.
            return right;
        }

        // reduce count stored in array
        K[0]--;
        if (K[0] == 0) {
            // if 0 i.e. reached at K large value so return it.
            return root;
        }
        return Kth_Large(root.left, K);
    }

    public Boolean Is_Valid_BST(TreeNode root, long min, long max) {

        /*
        Idea: Traverse Pre , root, L , R and keep checking BST rule for left and right.

        * */

        if (root == null) {
            return true;
        }

        // validate for false as we need to keep traversing on left and right
        // so no need to return or stop when tru.
        if (root.val <= min || root.val >= max) {
            return false;
        }

        // send max as root value to make sure left is atleast smaller than root value.
        var left = Is_Valid_BST(root.left, min, root.val);
        // send min as root value to make sure right is atleast larger than root value.
        var right = Is_Valid_BST(root.right, root.val, max);

        if (left && right) {
            return true;
        } else {
            return false;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {

        if (root == null) {
            return null; // hit end without matching
        }

        //traversing left
        if (root.val > node2.val && root.val > node1.val) {
            return lowestCommonAncestor(root.left, node1, node2);
        }
        //traversing right
        if (root.val < node2.val && root.val < node1.val) {
            return lowestCommonAncestor(root.right, node1, node2);
        }

        //returning as it can not go further left or right so this is the LCA.
        // OR root can be either or node so matched and return
        return root;

    }

    public TreeNode Inorder_Predecessor_BST(TreeNode root, int givenNode) {

        /*
        Idea :  here we do not have node for which prececessor needs to find ,
        we have to find node in tree from root and pon path we also need to capture predecessor.
        * */
        TreeNode predecessor = null;

        while (root != null) {
            //if given node is big then current node is potential predecessor node.
            if (givenNode > root.val) {
                predecessor = root;
                root = root.right;//go right as per BST
            } else {
                root = root.left; //go left as per BST
            }
        }
        return predecessor;
    }

    public TreeNode Inorder_Successor_BST(TreeNode root, int givenNode) {

        /*
        Idea :  here we do not have node for which successor needs to find ,
        we have to find node in tree from root and pon path we also need to capture  successor.
        * */

        TreeNode successor = null;
        while (root != null) {
            //if given node is smaller than current node is potential successor node.
            if (givenNode < root.val) {
                successor = root;
                root = root.left;//as per BST rule
            } else {
                root = root.right;//as per BST rules
            }
        }
        return successor;
    }

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

    public boolean findTarget_aka_find2Sum(TreeNode root, int k) {
        /*
        Idea :
        Traverse Tree say IN order and keep adding visited node in set so that diff (target - cur Node)
        can be checked and if found then True

        * */
        return findTarget_aka_find2Sum(root, k, new HashSet<Integer>());
    }

    private boolean findTarget_aka_find2Sum(TreeNode root, int k, HashSet<Integer> set) {

        if (root == null) {
            return false;
        }

        var lt = findTarget_aka_find2Sum(root.left, k, set);
        if (lt) {
            return true; // found no need to go futher.
        }
        var leftSum = k - root.val;
        if (set.contains(leftSum)) { // found another sum value.
            return true;
        } else {
            set.add(root.val); // register itself
        }

        var rt = findTarget_aka_find2Sum(root.right, k, set);
        if (rt) {
            return true;
        }

        return false;

    }


    // Recover Tree Start ---------------------------------------------
    // Class leve var
    TreeNode firstViolation = null;
    TreeNode lastViolation = null;
    TreeNode middleViolation = null;

    TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {


        Correct_BST(root);

        if (lastViolation != null) {
            var t = lastViolation.val;
            lastViolation.val = firstViolation.val;
            firstViolation.val = t;
        } else {
            var t = middleViolation.val;
            middleViolation.val = firstViolation.val;
            firstViolation.val = t;
        }
    }


    private void Correct_BST(TreeNode root) {

      /*   Idea  : Do IN order, L, Nd R as it goes in sorted order and keep tracking numbers which violates sorting rule
         3 tracker to satisfy 2 use cases
         First :  3,5,8,7,20=> 7 and 8 both neighbour violates so swap -> 3 5 7 8 20
         Second : not a neighbour nodes it can be any bst tree.
*/

        //validating
        if (root == null) {
            return;
        }

        // Go Left
        Correct_BST(root.left);

        //Root i.e. do operation

        if (prevNode != null && prevNode.val > root.val)// prev node must be Less then Current Root node.
        {
            // violation occurred.

            if (firstViolation == null) {
                firstViolation = prevNode; // found first bad node.
                middleViolation = root; // to support adjacent use case.
            } else {
                lastViolation = root; // found second bad node
            }
        }
        // make cur root/node is prev node
        prevNode = root;

        // Go Right
        Correct_BST(root.right);

    }

    //------Recover Tree   end-------------------------------------


    public TreeNode sortedArrayToBST(int[] sorted) {

        /*
        Idea :  pick mid element as root and first half as left and second half as right
        keep doing till runs out array
        * */
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


    public int Valid_BST_Length(TreeNode root) {

        /*
        Idea: Start with root check if valid BST if yes get size and done.
        if not do same for its left and right child and pick max of size between them.
        * */

        if (root == null) {
            return 0;
        }

        //check if tree is valid or not.
        var isValid = Is_Valid_BST(root, Integer.MIN_VALUE * 10L,
                Integer.MAX_VALUE * 10L);

        //if valid then get size
        if (isValid) {
            return findSizeOfBST(root);
        } else {
            // go left and right subtree and pick max len.
            var ltSize = Valid_BST_Length(root.left);
            var rtSiz = Valid_BST_Length(root.right);
            return Math.max(ltSize, rtSiz);
        }

    }

    private int findSizeOfBST(TreeNode root) {

        if (root == null) {
            return 0;
        }

        var leftSize = findSizeOfBST(root.left);
        var rightSize = findSizeOfBST(root.right);
        return 1 + leftSize + rightSize; // parent + child sizes
    }


    int idxRunner = 0;

    public TreeNode Build_BST_Pre_Efficient(int[] preorder, int upperbound) {

    /*    Idea :

            First element is Root in Pre Traversal.
            keep index count running on pre order A[] and keep
            creating left and right nodes of root.

            Now when send left , Max can go is the parent root recall BST root > left
            but for Right node since root < right , Max can go is way higher Integer.MAX_VALUE

         more info= https://www.youtube.com/watch?v=UmJT3j26t1I&t=731s*/

        //reached to end of array
        if (idxRunner == preorder.length) {
            return null;
        }
        //this will help to set leaf node with null left and right
        if (preorder[idxRunner] > upperbound) {
            return null;
        }

        var root = new TreeNode(preorder[idxRunner]);
        idxRunner++;

        var lft = Build_BST_Pre_Efficient(preorder, root.val);
        var rgh = Build_BST_Pre_Efficient(preorder, upperbound);

        root.left = lft;
        root.right = rgh;

        return root;
    }


    //----- NOT needed anymore

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

                    if (inordscrParent == root) { // i.e. there is only one right node just after root i.e. node to delete and parent still at roo.
                        inordscrParent.right = inordscrParent.right.right;// to make sure if more child preseent down we dont set delete them all.
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
        // Note : if IN was not given then sort given pre array and it will become IN i.e. for BST it will be also sorted.


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



