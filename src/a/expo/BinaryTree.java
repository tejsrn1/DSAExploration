package a.expo;


import a.expo.helpers.tree.helper.binarytree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {


/*
 Note :
    1. just to get context ,start with  https://www.interviewcake.com/concept/java/binary-tree?course=fc1&section=trees-graphs
    2. Formulas
     height of Tree -> h = log N
     Total nodes of tree -> n= (2^h) - 1 i.e 2 rest to h and then - 1
     Total Nodes of Tree at particular Level -> n =  2^h-1 i.e. 2 rest to h-1
*/


    public void Inorder_Traversal(TreeNode root) {
        // left subtree is visited first
        // then visit the root and
        // then the right subtree is visited

        //base
        if (root == null) return;
        Inorder_Traversal(root.left);
        System.out.print(root.val + " ");//print
        Inorder_Traversal(root.right);

    }

    public void Preorder_Traversal(TreeNode root) {
        // root is visited first
        // then left subtree and then right subtree is visited

        //base
        if (root == null) return;
        System.out.print(root.val + " ");//print
        Preorder_Traversal(root.left);
        Preorder_Traversal(root.right);
    }

    public void Postorder_Traversal(TreeNode root) {
        // left subtree is visited first
        // then right subtree and then root.

        //base
        if (root == null) return;
        Postorder_Traversal(root.left);
        Postorder_Traversal(root.right);
        System.out.print(root.val + " ");//print

    }

    public List<Integer> Preorder_Traversal_Iterative(TreeNode root) {

        /*
            root Left Right

        Idea : Take one Stack and add root and its right and left child and
        a .keep adding till it has child
        b. also keep pop and collect result and till stack is not empty


        * */
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

    public List<Integer> Inorder_Traversal_Iterative(TreeNode root) {

        /*
         Left root Right

        Idea: Take one Stack and just add all left then pop stack and collect result
        if any child has right then go to its right child and add it and all its left child
        and then continue pop. while pop collect result.

        * */
        var list = new ArrayList<Integer>();
        if (root == null) return list;

        var stack = new Stack<TreeNode>();
        TreeNode currNode = root;

        while (currNode != null) {
            stack.push(currNode); // add root first
            currNode = currNode.left;// keep going on left and add all.
        }

        while (!stack.isEmpty()) {
            var nd = stack.pop();
            list.add(nd.val);

            if (nd.right != null) {// If it has right node then
                var rightNd = nd.right;// move to right side
                while (rightNd != null) {
                    stack.push(rightNd);
                    rightNd = rightNd.left;// add all its left.
                }
            }
        }
        return list;
    }

    public List<Integer> Postorder_Traversal_Iterative(TreeNode root) {

        /*
        Idea: Simple way there are 2 stacks holding and final. keep adding left and child
        in to holding and while pop this nodes add them in to another stack called final or reuslt stack
        just pop and print from result statck..

        * */
        var list = new ArrayList<Integer>();
        if (root == null) return list;

        var holdingStack = new Stack<TreeNode>();
        var finalStack = new Stack<TreeNode>();

        holdingStack.push(root); // add first root.

        while (!holdingStack.isEmpty()) {
            // add in to final Stack
            var nd = holdingStack.pop();
            finalStack.push(nd);

            // if nod has lef and right then add it holding stack
            if (nd.left != null) {
                holdingStack.push(nd.left);
            }
            if (nd.right != null) {
                holdingStack.push(nd.right);
            }
        }
        while (!finalStack.isEmpty()) {
            var r = finalStack.pop();
            list.add(r.val);
        }
        return list;
    }

    public List<Integer> Level_Order_Traversal(TreeNode root) {

        /*
         Level by level i.e all immediate childs and then their childs.

         Idea: Take one Queue and keep adding left and right child and while pop
         collect result.

         * */
        var list = new ArrayList<Integer>();
        if (root == null) return list;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // add root first.

        while (!que.isEmpty()) {
            var nd = que.poll();
            list.add(nd.val);// collect

            if (nd.left != null) { // add left
                que.offer(nd.left);
            }
            if (nd.right != null) { // add right
                que.offer(nd.right);
            }
        }

        return list;
    }

    public List<List<Integer>> Level_Order_Traversal_CollectAtEachLevel(TreeNode root) {

        List<List<Integer>> listOfList = new LinkedList<>();
        var list = new LinkedList<Integer>();
        if (root == null) return listOfList;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // add root first.
        que.offer(null); // add flag for level complete

        while (!que.isEmpty()) {
            var nd = que.poll();

            if (nd != null) {
                list.add(nd.val);// just add in list

                if (nd.left != null) { // add left to Q
                    que.offer(nd.left);
                }
                if (nd.right != null) { // add right to Q
                    que.offer(nd.right);
                }
            } else {
                listOfList.add(list); // add list of all nodes at same level to final list.
                list = new LinkedList<>();

                if (!que.isEmpty()) {
                    que.offer(null);// add flag for level complete.
                }
            }
        }

        return listOfList;
    }

    public int MaxDepthOfTree_LevelOrder(TreeNode root) {

        int count = 0;
        if (root == null) return count;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // add root first.
        que.offer(null); // add flag for level complete

        while (!que.isEmpty()) {
            var nd = que.poll();

            if (nd != null) {
                if (nd.left != null) { // add left to Q
                    que.offer(nd.left);
                }
                if (nd.right != null) { // add right to Q
                    que.offer(nd.right);
                }
            } else {
                count++;
                if (!que.isEmpty()) {
                    que.offer(null);// add flag for level complete.
                }
            }
        }

        return count;
    }

    public int MaxDepthOfTree_Recursive_Via_PostOrder(TreeNode root) {

        //base
        if (root == null) {
            return 0;
        }

        var lf = MaxDepthOfTree_Recursive_Via_PostOrder(root.left);
        var rt = MaxDepthOfTree_Recursive_Via_PostOrder(root.right);
        var max = Math.max(lf, rt);

        return 1 + max; // add 1 for itself in to max count got from left or right child.
    }

    public int Is_Tree_Balanced(TreeNode root) {
        /*
        Idea:
        A height-balanced binary tree is a binary tree in
         which the depth of the two subtrees of every node never differs by more than one.
        * */

        if (root == null) return 0;

        var lt = Is_Tree_Balanced(root.left);
        if (lt == -1) return lt; // skip rest if already found imbalanced

        var rt = Is_Tree_Balanced(root.right);
        if (rt == -1) return rt; // skip rest if already found imbalanced

        // make height is -1 if Diff > 1
        var diff = Math.abs(lt - rt);
        if (diff > 1) {
            return -1; // when diff shows im balance send back height -1
        } else {
            // send max +1 as height
            return 1 + Math.max(lt, rt);
        }
    }

    public int Find_Diameter(TreeNode root, int[] maxDia) {

        /*

        Idea :
         Diameter i.e. longest path between 2 nodes and that does not have to go via root node.
         length of a path between two nodes is represented by the number of edges between them

         to Find it total edges between nodes is nothing but Sum =  Left subtree Height + Right Subtree Height

         The Max Diameter is the answer.
         DFS : Post order bcs we need height and for this Post order required.
         int [] bcs we need ref in recursion call alone int will lose value once out of stack*/

        if (root == null) return 0;

        var lt = Find_Diameter(root.left, maxDia);
        var rt = Find_Diameter(root.right, maxDia);

        var calDia = lt + rt;
        maxDia[0] = Math.max(maxDia[0], calDia);// capture max dia as we traverse tree.

        var height = 1 + Math.max(lt, rt);
        return height;

    }

    public int Find_Max_Path_Sum(TreeNode root, int[] maxSum) {
        /*
        Idea :
        1. Path is line between 2 nodes and it can not have divergent. e.g. below

                1
               /  \
              2    3

                 1
               /  \
              2    3
             /     /
            4     5


                 1
               /  \
               2   3
                    \
                     4
                     /
                    5


                 100
               /  \
             -20    -30

        2. Max sum can be any of below
            a. Max of Lt or Rt  + Node value Regular flow
            b. Lt + Rt + Node value see e.g. 1
            c. Node value it self if child are negative but node it self is positive then pick it.



         * */

        if (root == null) return 0;

        var lt = Find_Max_Path_Sum(root.left, maxSum);
        var rt = Find_Max_Path_Sum(root.right, maxSum);


        // Regular e.g. when sum is growing and we are deciding which left or right node in path makes it
        var caseRegular = Math.max(lt, rt) + root.val;
        //when Node it self is +ve childs are -ve
        var caseNodeAlone = root.val;

        // as per rule no divergent so need to pick best answer to take it upwards
        var caseNoDivergentInPath = Math.max(caseRegular, caseNodeAlone);

        var caseAllIncluded = lt + rt + root.val; // e.g. 1 diagram shows at given node at subtree what could be the total sum.

        var maxValue = Math.max(caseNoDivergentInPath, caseAllIncluded); // find if all 3 big or running Max

        maxSum[0] = Math.max(maxValue, maxSum[0]); //capture max so far and update value.

        return caseNoDivergentInPath; // always return regular or node it self e.g. c

    }

    public boolean IS_Same_Tree(TreeNode root1, TreeNode root2) {

        /*
        Idea : Traverse both tree same time and their value should match
        and if null then both tree nodes must be null.
        * */
        //base
        if (root1 == null && root2 == null) return true;
        if (root1 == null && root2 != null) return false;
        if (root1 != null && root2 == null) return false;


        var lt = IS_Same_Tree(root1.left, root2.left);
        if (!lt) return false;
        var rt = IS_Same_Tree(root1.right, root2.right);
        if (!rt) return false;

        //root1 and root2 both left child should be True
        //root1 and root2 both right child should be True
        // and root1 and root2 both value should be same.
        return root1.val == root2.val && lt && rt;
    }


    public List<List<Integer>> Zig_zag_LevelOrder_NEW(TreeNode root) {

        /*
        Idea : Very simple pick direction Left or Right and it only flips when null hit which tells
        that level over and say when leve over with Left sign ON then just add to list
        but when Right sign in ON then reverse list and then add in to  main result list.
        * */
        List<List<Integer>> listOfList = new LinkedList<>();
        var list = new LinkedList<Integer>();
        if (root == null) return listOfList;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // add root first.
        que.offer(null); // add flag for level complete
        int curDir = 1;

        while (!que.isEmpty()) {
            var quNode = que.poll();
            if (quNode != null) {
                list.add(quNode.val);
                if (quNode.left != null) {
                    que.offer(quNode.left);
                }
                if (quNode.right != null) {
                    que.offer(quNode.right);
                }
            } else {
                if (curDir == 1) {//i.e. L->R i.e org sequence
                    listOfList.add(list);
                    curDir = -1;//flip direction
                    list = new LinkedList<>();
                } else {
                    //i.e. R->L i.e Reverse the sequence
                    var st = new Stack<Integer>();
                    list.forEach(c -> st.push(c));

                    list = new LinkedList<>();
                    while (!st.isEmpty()) {
                        list.add(st.pop());
                    }

                    listOfList.add(list);
                    list = new LinkedList<>();
                    curDir = 1;//flip direction
                }
                if (!que.isEmpty()) {
                    que.offer(null);
                }
            }
        }

        return listOfList;
    }


    public List<Integer> BoundaryOfBinaryTree(TreeNode root) {

        /*
        Idea :
        1. Go to left and collect all nodes except leaf
        2. Go to leaf and only collect those.
        3 Go to right and collect all nodes except leaf ( just reverse as per ask)

        * */

        var result = new LinkedList<Integer>();
        if (!isLeafNode(root)) {
            result.add(root.val);
        }

        BoundaryOfBinaryTree_Left(root.left, result);
        BoundaryOfBinaryTree_Leaves(root, result);
        BoundaryOfBinaryTree_Right(root.right, result);

        return result;
    }

    private void BoundaryOfBinaryTree_Left(TreeNode node, List<Integer> list) {

        while (node != null) {
            if (!isLeafNode(node)) {
                list.add(node.val);
            }
            if (node.left != null) { // first check left bcs its left boundary
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

        if (isLeafNode(root)) { // only leaf nodes.
            list.add(root.val);
            return; // stop as we got leaf
        }
        // go find leaf on left and right side
        BoundaryOfBinaryTree_Leaves(root.left, list);
        BoundaryOfBinaryTree_Leaves(root.right, list);
    }

    private void BoundaryOfBinaryTree_Right(TreeNode node, List<Integer> list) {
        var tempList = new LinkedList<Integer>();

        while (node != null) {
            if (!isLeafNode(node)) {
                tempList.add(node.val);
            }
            if (node.right != null) {
                node = node.right; // first check right bcs its right boundary
            } else {
                node = node.left;
            }
        }

        for (int i = tempList.size() - 1; i >= 0; i--) {
            list.add(tempList.get(i));
        }
    }

    private boolean isLeafNode(TreeNode node) {
        if (node.right == null && node.left == null) {
            return true;
        } else {
            return false;
        }
    }

    public List<List<Integer>> Vertical_Traversal_L(TreeNode root) {
        /*
        Idea :

        Ka -> K1 ,Sorted List
             K2 ,Sorted List
        Kb -> K3 ,Sorted List


        * */

        var resMap = new TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>();
        List<List<Integer>> res = new ArrayList<>();

        Vertical_Traversal_L(root, 0, 0, resMap);

        //@ each col sorted ascending
        for (var colLevelRes : resMap.entrySet()) {

            var lt = new ArrayList<Integer>();

            //@ each row sorted ascending
            for (var rowLevelRes : colLevelRes.getValue().entrySet()) {

                //this is just to make sure at row level we have sorted value
                //Note : PQ should already have it sorted and it works for local but letcode giant tree need this sort
                var srtVal = rowLevelRes.getValue().stream().sorted().collect(Collectors.toList());
                for (var val : srtVal) {
                    lt.add(val); //collect in list
                }
            }

            //add into main result
            res.add(lt);

        }

        return res;
    }

    void Vertical_Traversal_L(TreeNode root, int curColDepth, int curRowDepth,
                              TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> resMap) {

        if (root == null) {
            return;
        }

        // prep all first time.
        if (!resMap.containsKey(curColDepth)) {

            //collect root value in pq.
            var pq = new PriorityQueue<Integer>();
            pq.add(root.val);

            //capture Row #
            var rwMap = new TreeMap<Integer, PriorityQueue<Integer>>();
            rwMap.put(curRowDepth, pq);

            //add back result map at col level
            resMap.put(curColDepth, rwMap);
        }
        // update column but make sure row map exists if not create one.
        else {

            var rwMap = resMap.get(curColDepth);

            if (!rwMap.containsKey(curRowDepth)) {

                //add value in pq
                var pq = new PriorityQueue<Integer>();
                pq.add(root.val);

                //capture Row #
//                var firstRwMap = new TreeMap<Integer, PriorityQueue<Integer>>();
                rwMap.put(curRowDepth, pq);

                //update back result map at col level
                resMap.put(curColDepth, rwMap);


            }
            //row already present.
            else {
                //update in PQ
                var pq = rwMap.get(curRowDepth);
                pq.add(root.val);

                //update back to row map.
                rwMap.put(curRowDepth, pq);

                //update back result map at col level
                resMap.put(curColDepth, rwMap);
            }

        }


        Vertical_Traversal_L(root.left, curColDepth - 1, curRowDepth + 1, resMap);
        Vertical_Traversal_L(root.right, curColDepth + 1, curRowDepth + 1, resMap);

    }


    public List<List<Integer>> Vertical_Traversal_L314(TreeNode root) {
        /*
        Idea :

        Ka -> K1 ,Sorted List
             K2 ,Sorted List
        Kb -> K3 ,Sorted List


        * */

        var resMap = new TreeMap<Integer, TreeMap<Integer, List<Integer>>>();
        List<List<Integer>> res = new ArrayList<>();

        Vertical_Traversal_L314(root, 0, 0, resMap);

        //@ each col sorted ascending
        for (var colLevelRes : resMap.entrySet()) {

            var lt = new ArrayList<Integer>();

            //@ each row sorted ascending
            for (var rowLevelRes : colLevelRes.getValue().entrySet()) {

                //this is just to make sure at row level we have sorted value
                //Note : PQ should already have it sorted and it works for local but letcode giant tree need this sort
                var srtVal = rowLevelRes.getValue();
                for (var val : srtVal) {
                    lt.add(val); //collect in list
                }
            }

            //add into main result
            res.add(lt);

        }

        return res;
    }

    void Vertical_Traversal_L314(TreeNode root, int curColDepth, int curRowDepth,
                                 TreeMap<Integer, TreeMap<Integer, List<Integer>>> resMap) {

        if (root == null) {
            return;
        }

        // prep all first time.
        if (!resMap.containsKey(curColDepth)) {

            //collect root value in pq.
            var pq = new ArrayList<Integer>();
            pq.add(root.val);

            //capture Row #
            var rwMap = new TreeMap<Integer, List<Integer>>();
            rwMap.put(curRowDepth, pq);

            //add back result map at col level
            resMap.put(curColDepth, rwMap);
        }
        // update column but make sure row map exists if not create one.
        else {

            var rwMap = resMap.get(curColDepth);

            if (!rwMap.containsKey(curRowDepth)) {

                //add value in pq
                var pq = new ArrayList<Integer>();
                pq.add(root.val);

                //capture Row #
//                var firstRwMap = new TreeMap<Integer, PriorityQueue<Integer>>();
                rwMap.put(curRowDepth, pq);

                //update back result map at col level
                resMap.put(curColDepth, rwMap);


            }
            //row already present.
            else {
                //update in PQ
                var pq = rwMap.get(curRowDepth);
                pq.add(root.val);

                //update back to row map.
                rwMap.put(curRowDepth, pq);

                //update back result map at col level
                resMap.put(curColDepth, rwMap);
            }

        }


        Vertical_Traversal_L314(root.left, curColDepth - 1, curRowDepth + 1, resMap);
        Vertical_Traversal_L314(root.right, curColDepth + 1, curRowDepth + 1, resMap);

    }


    public TreeMap<Integer, Integer> TOPView_Traversal(TreeNode root) {

        /*
        Idea :  Basically only first tem in each col is visible or needs to record
        Prep Map and only collect 1 item for each column.

        Use Tree map to keep Key sorted i.e. Coloumn sorted.
        * */
        TreeMap<Integer, Integer> sortedKeyMap = new TreeMap<>();
        TOPView_Traversal(root, 0, sortedKeyMap);
        return sortedKeyMap;
    }

    private void TOPView_Traversal(TreeNode root, int col, TreeMap<Integer, Integer> map) {

        if (root == null) {
            return;
        }

        map.putIfAbsent(col, root.val);//will only put one val for given key

        TOPView_Traversal(root.left, col - 1, map);
        TOPView_Traversal(root.right, col + 1, map);
    }

    public TreeMap<Integer, Integer> BottomView_Traversal(TreeNode root) {

        /*
        Idea :  Basically only last tem in each col is visible or needs to record
        Prep Map and only collect last item for each column.

        Use Tree map to keep Key sorted i.e. Column sorted.
        * */
        TreeMap<Integer, Integer> sortedKeyMap = new TreeMap<>();
        BottomView_Traversal(root, 0, sortedKeyMap);
        return sortedKeyMap;
    }

    private void BottomView_Traversal(TreeNode root, int col, TreeMap<Integer, Integer> map) {

        if (root == null) {
            return;
        }
        map.put(col, root.val);//will keep overwriting till hit last val for given key

        BottomView_Traversal(root.left, col - 1, map);
        BottomView_Traversal(root.right, col + 1, map);
    }

    public int Bottom_LEFT_View_Traversal(TreeNode root) {

        /*
        Idea :  Basically left most or first item in last level of tree.
        last leve is nothing but max depth of tree.
        * */

        int[] store = new int[2];
        //store root info.
        //**VIMP edge case where only Root is present in Tree then our solution never capture it
        // to fix that edge case we store root value like below to get start with it.
        store[0] = 0;
        store[1] = root.val;

        Bottom_LEFT_View_Traversal(root, 0, store);
        return store[1];
    }

    private void Bottom_LEFT_View_Traversal(TreeNode root, int curDepth, int[] store) {

        if (root == null) {
            return;
        }
        //capture value if it is max depth so far
        if (curDepth > store[0]) {
            store[0] = curDepth; //update max depth
            store[1] = root.val;//record result
        }

        //go deeper on left and right
        Bottom_LEFT_View_Traversal(root.left, curDepth + 1, store);
        Bottom_LEFT_View_Traversal(root.right, curDepth + 1, store);
    }

    public List<Integer> LeftView_Traversal(TreeNode root) {

        /*
         Idea : at each row level or depth capture first element.

         Note : Level order and then keep first element at each level would do but
         we are using Pre order where at each depth i.e. row level we pick element
         while picking element we make sure if previously capture at same level then
         we do not capture it again.*/

        var res = new LinkedList<Integer>();
        LeftView_Traversal(root, 0, res);// depth goes increasing at each level
        return res;

    }

    private void LeftView_Traversal(TreeNode root, int rowOrDepth, LinkedList<Integer> res) {

        if (root == null) {
            return;
        }

        //at given row depth size of collection should be matching
        //i.e. previously if there are 2 record exists in result collection i.e. size=2
        // at row #2 it matches and ready to accept new result.
        //basically to make sure only 1 record exists from left side at each row level.
        if (res.size() == rowOrDepth) {
            res.add(root.val);
        }

        LeftView_Traversal(root.left, rowOrDepth + 1, res); // depth goes increasing at each level
        LeftView_Traversal(root.right, rowOrDepth + 1, res);// depth goes increasing at each level
    }


    public List<Integer> RightView_Traversal(TreeNode root) {

        /*
         Idea : at each row level or depth capture first element.
         Since it needs right go pre in opposite way
         ie root right and left.

         Note : Level order and then keep first element at each level would do but
         we are using Pre order where at each depth i.e. row level we pick element
         while picking element we make sure if previously capture at same level then
         we do not capture it again.*/

        var res = new LinkedList<Integer>();
        RightView_Traversal(root, 0, res);// depth goes increasing at each level
        return res;

    }

    private void RightView_Traversal(TreeNode root, int rowOrDepth, LinkedList<Integer> res) {

        if (root == null) {
            return;
        }

        if (res.size() == rowOrDepth) { // this make sure we capture only once.
            res.add(root.val);
        }

        RightView_Traversal(root.right, rowOrDepth + 1, res); // depth goes increasing at each level
        RightView_Traversal(root.left, rowOrDepth + 1, res);// depth goes increasing at each level
    }

    public boolean Symmetric_Tree(TreeNode root) {

        /*
        Idea : Traverse both tree same time and their value should match BUT
         BUT It is symmetric NOT NOT same so it is like mirron and that needs
         to check left side of tree with right side of  other tree and vice versa.

        * */
        if (root == null) {
            return true;
        }
        return IsSymmetricTree(root.left, root.right);
    }

    private boolean IsSymmetricTree(TreeNode root1, TreeNode root2) {

        /*
         Idea : Traverse both tree same time and their value should match BUT
         BUT It is symmetric NOT NOT same so it is like mirron and that needs
         to check left side of tree with right side of  other tree and vice versa.

         */
        // base
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null && root2 != null)
            return false;
        if (root1 != null && root2 == null)
            return false;

        var lt = IS_Same_Tree(root1.left, root2.right);
        if (!lt)
            return false;
        var rt = IS_Same_Tree(root1.right, root2.left);
        if (!rt)
            return false;

        // root1 and root2 both left child should be True
        // root1 and root2 both right child should be True
        // and root1 and root2 both value should be same.
        return root1.val == root2.val && lt && rt;
    }

    public boolean Print_Path_From_RootTONode(TreeNode root, int nodeToFind, LinkedList<Integer> res) {


        if (root == null) {
            return false;
        }

        res.add(root.val);

        if (root.val == nodeToFind) {
            return true;
        }
        // we can skip going right by checking if lt is true but here we are not doing it.
        var lt = Print_Path_From_RootTONode(root.left, nodeToFind, res);
        var rt = Print_Path_From_RootTONode(root.right, nodeToFind, res);

        if (lt || rt) { // if one of child tree returns true then parent goes back as true.
            return true; // also we keep path in stack.
        }

        res.remove(res.size() - 1);// remove one element as its going to retract back to caller
        return false; // when nothing found so return true and removed from path as above.
    }

    public List<String> BinaryTreePaths(TreeNode root) {

        /*
        Idea: Just traverse and when hit leaf collect result and on way back up
        remove node from list.
        * */

        var lt = new ArrayList<Integer>();
        var res = new ArrayList<String>();
        BinaryTreePaths(root, lt, res);

        return res;

    }

    void BinaryTreePaths(TreeNode root, List<Integer> list, List<String> res) {

        if (root == null) {
            return;
        }

        list.add(root.val);

        if (root.left == null && root.right == null) {
            var sb = new StringBuilder();
            for (var l : list) {
                sb.append(l).append("->");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            list.remove(list.size() - 1);
            return;
        }

        BinaryTreePaths(root.left, list, res);
        BinaryTreePaths(root.right, list, res);

        list.remove(list.size() - 1);
    }


    public TreeNode Lowest_Common_Ancestor(TreeNode root, int nodeA, int nodeB) {

        /*
        Idea : Traverse and if found both node then its parent is LCA
        if found either or node it is LCA
        when hit null it goes as not found

        * */

        // when hit end
        if (root == null) {
            return root;
        }
        // when one of value match
        if (root.val == nodeA || root.val == nodeB) {
            return root; // it is found and may be LCA it self
        }

        var lt = Lowest_Common_Ancestor(root.left, nodeA, nodeB);
        var rt = Lowest_Common_Ancestor(root.right, nodeA, nodeB);

        // when left null then right may be match
        if (lt == null) {
            return rt;
        }

        // when right null then left may be match
        if (rt == null) {
            return lt;
        }

        //when both not null then current node is LCA
        return root;

    }

    public int countNodes(TreeNode root) {

        /*
        Idea : Traverse and keep counting for itself and its left and righ child

        * */

        // when hit end
        if (root == null) {
            return 0;
        }

        var lt = countNodes(root.left);
        var rt = countNodes(root.right);

        return 1 + lt + rt;

    }

    public int Width_Of_BinaryTree(TreeNode root) {

        /*
        Idea : Use index start from 0 to n at each level and get len last-first +1 for that level.
        keep getting it and keep recording max at each level.
        remember first parent to child nodes is dobule so assingment of index to child is
        in multiplication of 2 * idx of parent for left
        and 2* idx of parent +1 on right why? see below where parent A has 2 child 0 idx and 1 index

                 A
               /   \
             W     G
            0th   1st index

         * */

        if (root == null) return 0;

        int max = 0;

        //instead of Pair class , KV map used.
        LinkedList<HashMap<TreeNode, Integer>> que = new LinkedList<>();

        HashMap<TreeNode, Integer> treeMap = new HashMap<>();
        treeMap.put(root, 0); // node and its column index
        que.offer(treeMap); // add root first.

        while (!que.isEmpty()) {

            //first get index of first and last element from Q which are at same level

            TreeNode firstNode = que.getFirst().keySet().stream().findFirst().get();
            int firstIdx = que.getFirst().get(firstNode);

            TreeNode lastNode = que.getLast().keySet().stream().findFirst().get();
            int lastIdx = que.getLast().get(lastNode);

            //calculate max and capture so far max
            max = Math.max(max, lastIdx - firstIdx + 1);

            // Do business like we do in level where add childs but remember to add their index too
            //Instead of  null or dummy node to see level over
            // iterate que based on its size to get all element at same level.
            // Note = LinkedList is the class used as Queue to use some method of its.

            // VIMP as adding items in Q while iterating so do not delare inside for loop
            int sizeAtEachLevel = que.size();

            for (int i = 0; i < sizeAtEachLevel; i++) {
                //poll Q
                var treMap = que.removeFirst();
                TreeNode node = treMap.keySet().stream().findFirst().get();
                int nodeIdx = treMap.get(node);

                if (node.left != null) {
                    HashMap<TreeNode, Integer> mpL = new HashMap<>();
                    mpL.put(node.left, 2 * nodeIdx);// Tree node parent -> child gets double
                    que.addLast(mpL);
                }
                if (node.right != null) {
                    HashMap<TreeNode, Integer> mpR = new HashMap<>();
                    mpR.put(node.right, 2 * nodeIdx + 1);// Tree node parent -> child gets double
                    que.addLast(mpR);
                }
            }

        }

        return max;
    }

    public String Serialized(TreeNode root) {

        /*
        Idea: Just traverse and build string
        * */
        var sb = new StringBuilder();
        Serialized(root, sb);
        return sb.deleteCharAt(sb.length() - 1).toString();

    }

    private void Serialized(TreeNode root, StringBuilder sb) {

        if (root == null) {
            sb.append("null,");
            return;
        }

        sb.append(root.val).append(',');
        Serialized(root.left, sb);
        Serialized(root.right, sb);

    }

    public TreeNode DeSerialized(String input) {
    /*
     Idea: Just traverse and build Tree from list.
     pick first element from list and once used remove it.
    * */
        String[] inputList = input.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(inputList));
        return DeSerialized(list);
    }


    private TreeNode DeSerialized(List<String> list) {

        //when null comes up
        if (list.get(0).equals("null")) {
            //remove from list and return null
            list.remove(0);
            return null;
        }

        //build tree node
        TreeNode nd = new TreeNode(Integer.valueOf(list.get(0)));
        //remove from list
        list.remove(0);
        nd.left = DeSerialized(list);
        nd.right = DeSerialized(list);
        return nd;

    }

    public void Child_sum_Prop(TreeNode root) {

        /*
        Idea: just add child nodes and compare against parent.
        POST order is used here.
        * */

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

    public TreeNode Build_Tree_Post_In(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, LinkedHashMap<Integer, Integer> map) {

        // Idea :

        /*
        IN = 40, 20, 50, 10, 60, 30, 90
        POST = 40, 50, 20, 60, 90, 30, 10

        Start with post last ele as its a Root
        then look for idx in Map for given root.
        that index called RI root index.

        IN has left elements from RI <--
        IN has right elements from RI -->

        Travers left and right and keep getting Root and its left and right and build.

        */

        if (is > ie || ps > pe) {
            return null; // array end must be gt then start.
        }

        var rtVAlFromPost = postorder[pe];
        var ri = map.get(rtVAlFromPost);
        var tre = new TreeNode(rtVAlFromPost);

        var rtLeft = Build_Tree_Post_In(inorder, is, ri - 1, postorder, ps, ps + ri - is - 1, map);
        // ri-is bcs need len from start idx of IN till RI

        var rtRight = Build_Tree_Post_In(inorder, ri + 1, ie, postorder, ps + ri - is, pe - 1, map);
        // ri-is bcs need len from start idx of IN till RI

        tre.left = rtLeft;
        tre.right = rtRight;

        return tre;

    }

    public TreeNode Build_Tree_Pre_In(int[] inorder, int is, int ie, int[] preorder, int preS, int preE, LinkedHashMap<Integer, Integer> map) {

        // Idea : Same as IN - POST but  for PRE first element is root unlike POST.


        if (is > ie || preS > preE) {
            return null; // array end must be gt then start.
        }

        var rtVAlFromPre = preorder[preS];
        var ri = map.get(rtVAlFromPre);
        var tre = new TreeNode(rtVAlFromPre);

        var rtLeft = Build_Tree_Pre_In(inorder, is, ri - 1, preorder, preS + 1, preS + ri - is, map);
        // ri-is bcs need len from start idx of IN till RI
        //preS+1 bcs first ele is root identified already

        var rtRight = Build_Tree_Pre_In(inorder, ri + 1, ie, preorder, preS + ri - is + 1, preE, map);
        // ri-is bcs need len from start idx of IN till RI

        tre.left = rtLeft;
        tre.right = rtRight;

        return tre;

    }

    public void Flatten_Tree_LinkedList(TreeNode root) {

        /*
         Idea :
         Go Post but opposite R - L - Rt
         Why ? think like we always have to connect left -> right
         so lets get right first and make it connect to its left
         then this left will connect to right will make it flatten at end.
         Prev Point keep ref of right node where our left node connects L ->> R

        Note : Prev MUST at  Class level variable as to keep ref , method level will be lost each time recursion calls
        OR send as ref array.
         */

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


    //---- Not used anymore -------------

    public List<List<Integer>> Zig_zag_LevelOrder_NOTUSED(TreeNode root) {

        List<List<Integer>> listOfList = new LinkedList<>();
        var list = new LinkedList<Integer>();
        if (root == null) return listOfList;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // add root first.
        que.offer(null); // add flag for level complete

        boolean IsNextLevelflip = true;
        var stack = new Stack<TreeNode>();

        while (!que.isEmpty()) {
            var quNode = que.poll();
            if (quNode != null) {
                if (IsNextLevelflip) {
                    list.add(quNode.val); //first add to list so we have it collected before we operate on it
                    stack.push(quNode); // //Just add to stack
                    while (que.peek() != null) { // add all nodes on same level in stack
                        var tempQuNode = que.poll();
                        list.add(tempQuNode.val);
                        stack.push(tempQuNode);
                    }
                    while (!stack.isEmpty()) {
                        var stkNode = stack.pop();
                        if (stkNode.right != null) {
                            que.offer(stkNode.right);
                        }
                        if (stkNode.left != null) {
                            que.offer(stkNode.left);
                        }
                    }
                } else {
                    list.add(quNode.val);// just add in list
                    stack.push(quNode); // it was already pulled from Q
                    while (que.peek() != null) {
                        var tempQuNode = que.poll();
                        list.add(tempQuNode.val);
                        stack.push(tempQuNode);
                    }
                    while (!stack.isEmpty()) {
                        var stkNode = stack.pop();
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
                IsNextLevelflip = IsNextLevelflip == true ? false : true;
                if (!que.isEmpty()) {
                    que.offer(null);
                }
            }
        }

        return listOfList;
    }

    public List<Integer> BoundaryOfBinaryTree_NOTUSED(TreeNode root) {

        List<Integer> boundaryList = new LinkedList<>();
        var listLT = new LinkedList<Integer>();
        var listRT = new LinkedList<Integer>();
        var runningList = new LinkedList<Integer>();
        if (root == null) return boundaryList;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root); // add root first.
        que.offer(null); // add flag for level complete

        while (!que.isEmpty()) {
            var nd = que.poll();

            if (nd != null) {
                runningList.add(nd.val);// just add in list

                if (nd.left != null) { // add left to Q
                    que.offer(nd.left);
                }
                if (nd.right != null) { // add right to Q
                    que.offer(nd.right);
                }
            } else {

                if (!que.isEmpty()) {
                    que.offer(null);// add flag for level complete.
                } else {

                    boundaryList.addAll(listLT);
                    boundaryList.addAll(runningList);

                    for (int p = listRT.size() - 1; p >= 0; p--) {
                        boundaryList.add(listRT.get(p));
                    }
                    return boundaryList;
                }

                var size = runningList.size();
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
