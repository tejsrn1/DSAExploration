package exploration;

import exploration.helpers.llist.lru.ListNode;
import exploration.helpers.llist.lru.ListNodeCloned;

import java.util.HashMap;
import java.util.Map;

//https://takeuforward.org/strivers-a2z-dsa-course/strivers-a2z-dsa-course-sheet-2/
// 1D ,Doubly , Medium and Hard.
public class LinkedListDSA_wpi {

    /*
    Notes :
    while reference in play e.g in LinkedList DS
    remember below points.
    1. ListNode p= head is just another pointer p looking at head.
    2. when p moves on like p = p.next.next. It can only see what is ahead
    but at that same time head pointer can see everything from start.
    3. like wise any other pointer if it moves it moved and only see fwd but not necessarily other pointer if
    they have not moved anywehre or thye moved at diff places they see diff things.
    4. #3 is not true when some pointer making structural changes to linkedlist ds and
    then pointers already passed that point where changes made will not see it but rest pointers will see changes.

    e.g. header p1 and p2 stays same and r1 already moved to somewhere in between
    and now p1.next = null then all header, p1 and p2 sees nothing after p1 as it is null but r1 could still see other
    list as it is not affected to it.

    Note :
    Sentinel head i.e. fake head node.
     - this is helpful to avid edge cases.
    -  Widely used for trees and linked lists such as pseudo-heads
    -  they are purely functional and usually don't hold any data
    - primary purpose is to standardize the situation to avoid edge case handling

    e..g Delete duplicate code and all nodes in list are duplicate then add Sentinel node and then treat other nodes are
    interneal nodes else we have to take care head - tail deletion.





    * */

    public ListNode InsertNode_Prepend(int data, ListNode head) {
        var prependNode = new ListNode(data);
        prependNode.next = head;
        head = prependNode;
        return head;
    }

    public ListNode InsertNode_Append(int data, ListNode head) {
        var appendNode = new ListNode(data);

        if (head == null) {
            head = appendNode;
            return head;
        }

        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = appendNode;

        return head;
    }

    public ListNode DeleteLastNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }

    public int getCount(ListNode head) {
        var counter = 0;
        if (head == null) return counter;

        while (head != null) {
            counter++;
            head = head.next;
        }

        return counter;
    }

    public int getCount_Recursive(ListNode head) {

        var counter = 0;

        //base
        if (head == null) return counter;
        //call
        counter = 1 + getCount_Recursive(head.next);
        //return
        return counter;
    }

    public boolean SearchNode(ListNode head, int searchVal) {

        if (head == null) return false;

        while (head != null) {
            if (head.val == searchVal) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public boolean SearchNode_Recursive(ListNode head, int searchVal) {

        //base
        if (head == null) return false;

        //call

        return head.val == searchVal || SearchNode_Recursive(head.next, searchVal);

//        var resFromOther = SearchNode_Recursive(head.next, searchVal);
//        var resFromHere = false;
//        if (head.val == searchVal) {
//            resFromHere = true;
//        }
//        return resFromHere || resFromOther;
    }

    public ListNode FindMiddleElement(ListNode head) {

        /*
         * Only run 1 loop as some Q asked diff position of fast
         * */

        ListNode slow = head, fast = head;

        // Below makes return 4 as middle point 1-2-3-4-5-6
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Below makes return 3 as middle point 1-2-3-4-5-6 AND same for 1-2-3-4-5
//        while (fast.next != null && fast.next.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }


        return slow;
    }

    public ListNode ReverseList(ListNode head) {

        ListNode prev = null, next = null;
        ListNode cur = head;

        while (cur != null) {

            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode ReverseList_Recursive(ListNode head) {

        var newhead = Private_ReverseList_Recursive(head, null);
        return newhead;
    }

    private ListNode Private_ReverseList_Recursive(ListNode curr, ListNode prev) {

        if (curr == null) return prev;

        //for current method for say N1 and P nothin will be done
        //before that next node say N2 -> N1 and return its head as N2
        var head = Private_ReverseList_Recursive(curr.next, curr);
        //now for current method N1 -> Prev but since we already have head
        curr.next = prev;
        return head;//return head.

    }

    public boolean Detect_Loop_Cycle(ListNode head) {
        /*
         Idea : Floyd’s Cycle-Finding Algorithm:
         * */

        if (head == null) return true;
        ListNode slow = head;
        ListNode fast = head;

        //just check its double jump not goes to null
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.val == fast.val) {
                return true;
            }
        }

        return false;
    }

    public ListNode Find_StartingPoint_Loop_Cycle(ListNode head) {

         /*
         Idea : Floyd’s Cycle-Finding Algorithm:
         * */

        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { //we could also compare slow.val ==  fast.val

                //loop found now take 1 step at a time
                // and make slow and entry node move
                //logic explains at URL but in short its intuition that it will meet.
                while (slow != entry) { // loop until both meet
                    slow = slow.next;
                    entry = entry.next;
                }

                return slow;//when meet just return.
            }
        }

        return null;
    }

    public boolean Is_Palindrome(ListNode head) {
        //base
        if (head == null || head.next == null) return true;
        ListNode entry = head;

        //first find middle node
        ListNode slow = head;
        ListNode fast = head;

        //we could have used .next !=null and node !=null like find dup method
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //found slow =middle node

        // reverse second half and join new head to first half
        ListNode prev = null;
        ListNode next = null;
        ListNode cur = slow.next;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        slow.next = prev;

        // keep dummy at start and start comparing from new head.
        while (prev != null) {
            if (prev.val != entry.val) {
                return false;
            }
            prev = prev.next;
            entry = entry.next;
        }
        return true;
    }

    public ListNode Remove_Nth_Node_From_Back(ListNode head, int n) {
        //base
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        //EDGE CASE: when K is large then total node
        // Make fast reach till n
        for (int i = 1; i <= n; i++) {
            if (fast == null) {
                return null;// i.e single element in list or it reached to end before kth node
            }
            fast = fast.next;
        }

        //EDGE CASE: when K is large then total node
        //after traversal it reached to null
        //  K = 2 for A -> B
        // it will be on A -B - null and to remove k-2 which is A below works.
        if (fast == null) {
            head = head.next;
            return head;
        }

        //go till slow reached to node just before node = n from last.
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // remove nth  node.
        //5 -> 7 instead 5 ->6
        slow.next = slow.next.next;

        return head;
    }

    public int Find_Intersection_Of_2List(ListNode head, ListNode head2) {
        //base
        if (head == null || head2 == null) return 0;

        //find len of each list
        ListNode first = head;
        ListNode second = head2;
        int counfirst = 0;
        int counsecond = 0;

        while (first != null) {
            counfirst++;
            first = first.next;
        }

        while (second != null) {
            counsecond++;
            second = second.next;
        }
        //find long and short
        ListNode longest;
        ListNode shortest;

        var diffbetwenTwo = counfirst - counsecond;

        if (diffbetwenTwo < 0) {
            longest = head2;
            shortest = head;
        } else {
            longest = head;
            shortest = head2;
        }

        // make fast go and cover extra len
        for (int a = 1; a <= Math.abs(diffbetwenTwo); a++) {
            longest = longest.next;
        }

        // now starts both and compare
        while (longest != null && shortest != null) {
            if (longest.val == shortest.val) {
                return longest.val;
            }
            longest = longest.next;
            shortest = shortest.next;
        }
        return 0;

    }

    public ListNode Add_1_To_List(ListNode head) {
        //e.g. 1-9-9

        //reverse it so that we can add 1 to it 9-9-1
        ListNode prev = null, next = null;
        ListNode cur = head;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        ListNode curAfterRev1 = prev;

        // Add 1 to it.
        int cf = 1;// for add 1 cf starts with 1 and it will help when we have cf while adding
        while (curAfterRev1 != null) {
            int ad1 = curAfterRev1.val + cf;
            int rem = ad1 % 10; // 0
            curAfterRev1.val = rem;
            cf = ad1 / 10; //1
            curAfterRev1 = curAfterRev1.next;
        }

        // reverse again
        ListNode prev2 = null, next2 = null;
        ListNode cur2 = prev; //bcs curAfterRev1 now points to null after iteration so we pick prev

        while (cur2 != null) {
            next2 = cur2.next;
            cur2.next = prev2;
            prev2 = cur2;
            cur2 = next2;
        }
        // add new node at fist if there was extra no left.
        ListNode curAfterRev2 = prev2;

        if (cf > 0) {
            ListNode n1 = new ListNode(cf);
            n1.next = curAfterRev2;
            return n1;
        }

        return curAfterRev2;
    }

    public ListNode Sum_Two_List(ListNode head, ListNode head2) {

    /*
        Reverse it since we assume we get 2-1-0 + 9-3-1 and we need 0-1-2 + 1-3-0 to do addition.
        if list came as already reversed then no need to do it agian.

        LeetCode : it was given reverse only.
        */

        ListNode curAfterRev1 = reverseIt(head);
        ListNode curAfterRev2 = reverseIt(head2);

        ListNode totalNode = null;
        ListNode dummyHead = totalNode;

        // Add both list
        int cf = 0;
        while (curAfterRev1 != null || curAfterRev2 != null || cf == 1) {

            int totalVal = 0;

            if (curAfterRev1 != null) {
                totalVal += curAfterRev1.val;// tovl = tovl + cur.val
                curAfterRev1 = curAfterRev1.next;
            }
            if (curAfterRev2 != null) {
                totalVal += curAfterRev2.val;
                curAfterRev2 = curAfterRev2.next;

            }

            totalVal += cf;
            cf = totalVal / 10; //1
            int rem = totalVal % 10; // 0

            totalNode = new ListNode(rem);
            // bcs at end for result say 29 , 9->2 so to avoid rev 9->2->DummyHed.
            //basically prepend to avoid rev after answer ready e.g. 1-5-8 return 1 instead 8.
            totalNode.next = dummyHead;
            dummyHead = totalNode;
        }

        return dummyHead;
    }

    public ListNode Reverse_List_In_Group_K(ListNode head, int k) {

        if (head == null) return null;
        if (head.next == null) return head;

        //find len of list
        int len = 0;
        ListNode lenCur = head;
        while (lenCur != null) {
            ++len;
            lenCur = lenCur.next;
        }
        if (k > len) return null;

        int count = 0;
        ListNode cur = head;
        ListNode dummy = cur;
        ListNode prev = null;
        ListNode next = null;
        ListNode finalnodes = prev;
        int loop = len / k;//find loop as multiplier of k 5 / 2 = 2 loop.

        // do till nodes are there in list
        while (cur != null || count <= len) {

            //2. when reached to K
            if (count != 0 && count % k == 0) {

                //4 this  make sure 1 -> 4 after second round of reverse happens and then so forth.
                if (dummy.next != null && dummy.next.next != null) {
                    dummy.next.next = prev;
                }
                //3. below steps to make sure
                // # 1 -> 3 and 3->5 happens
                // # make result node head
                // # set for next curr so that routing reverse contine.
                dummy.next = cur;
                finalnodes = finalnodes == null ? prev : finalnodes;
                prev = dummy;
                dummy = cur;
                // break when loop reached i.e. rest node are not multiplier of K.
                // 4 +1 = 5/2 =2 = loop 2. so we did 4 node and last 5th one left as it is.
                if ((count + 1) / k >= loop) break;
            }
            //1.  do routine reverse but keep counter as we cant cross K
            count++;
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return finalnodes;
    }

    public ListNode Rotate_List(ListNode head, int k) {
        //base
        if (head == null) return null;

        ListNode lenH = head;
        int len = 0;
        while (lenH != null) {
            len++;
            lenH = lenH.next;
        }

        //edge case :
        if (len - k == 0) return head;// no rotation required.
        //when k is larger then Len
        if (k > len) {
            k = k - len;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Make fast reach till n
        for (int i = 1; i <= k; i++) {
            fast = fast.next;
        }

        //reached last node
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head; // 5->1
        head = slow.next; // 4 is head
        slow.next = null; // 3 is last node

        return head;
    }

    public ListNode Delete_Duplicate(ListNode head) {

        ListNode org = head;
        while (org != null) {
            ListNode runner = org;
            while (runner.next != null) {
                if (runner.next.val == org.val) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            org = org.next;
        }

        return head;
    }

    public ListNode Partition_CleanWay(ListNode head, int x) {

        /*
        Idea : Do not worry about head just traverse and keep building smaller and larger.
        * */
        if (head == null) return null;

        ListNode before = new ListNode(0);
        ListNode after = new ListNode(0);

        ListNode beforeRunner = before;
        ListNode afterRunner = after;


        while (head != null) {
            if (head.val < x) {
                beforeRunner.next = head;
                beforeRunner = beforeRunner.next;
            } else {
                afterRunner.next = head;
                afterRunner = afterRunner.next;
            }
            head = head.next;
        }
        afterRunner.next = null;// remove lingering anything

        beforeRunner.next = after.next; //combine both
        return before.next;// return

    }

    public ListNode mergeTwoLists_Sorted_CleanWay(ListNode head1, ListNode head2) {

        /*
        Idea: same as partition keep traversing both list and prep sorted
        * */
        ListNode merged = new ListNode(-1);
        ListNode mergedRunner = merged;

        while (head1 != null && head2 != null) {

            if (head1.val <= head2.val) {
                mergedRunner.next = head1;
                head1 = head1.next;

            } else {
                mergedRunner.next = head2;
                head2 = head2.next;
            }
            mergedRunner = mergedRunner.next;
        }

        // At least one of l1 and l2 can still have nodes at this point, so connect
        // the non-null list to the end of the merged list.
        mergedRunner.next = head1 != null ? head1 : head2;

        return merged.next;
    }

    public ListNode merge_K_Lists_Sorted(ListNode[] lists) {

        if (lists.length <= 0) return null;
        if (lists.length == 1) return lists[0];

        ListNode merged = null;
        int counter = 0;
        while (counter < lists.length) {
            merged = mergeTwoLists_Sorted_CleanWay(merged, lists[counter]);
            counter++;
        }
        return merged;
    }


    public ListNode deleteMiddle(ListNode head) {

        /*
        Idea: Just find middle by checking fast and fast.next not null

        we could also start by  // Initialize two pointers, 'slow' and 'fast'.
        ListNode slow = head, fast = head.next.next; and look for middle point.

        we could also do f.next and f.n.n not null and check where it lands.


        * */
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.val = slow.next.val;
        slow.next = slow.next.next;

        return head;
    }

    public ListNode oddEvenList(ListNode head) {

        /*
        Even and EvenRunner is imp.
        even not null to rule out any single node

        * */
        if (head == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenRunner = even;

        while (evenRunner != null && evenRunner.next != null) {
            odd.next = evenRunner.next; // 1->3
            odd = odd.next;// 3 is odd now

            evenRunner.next = odd.next;//2->4 as 3 still has 3->4
            evenRunner = evenRunner.next;//4 is now even.

        }
        odd.next = even;// append even

        return head;
    }


    public ListNode sortList(ListNode head) {

        /*
        Similar to Merge Sort algorithm
        * */

        //address single node and base condition.
        if (head == null || head.next == null) {
            return head;
        }

        //this breaks list in half
        // (clean half i.e. from head it only can see till half of list)
        ListNode secondHalf = getSecondHalf(head);

        //send first half
        ListNode left = sortList(head);
        // send second half
        ListNode right = sortList(secondHalf);

        //merge
        return merge(left, right);
    }

    private ListNode getSecondHalf(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        //break in half
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;//ensure slow list is now broken from main head list.

        return slow; //second half.
    }

    private ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;

        // compare and prep new merge list.
        while (left != null && right != null) {

            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
                tail = tail.next;
            } else {
                tail.next = right;
                right = right.next;
                tail = tail.next;
            }
        }
        tail.next = left != null ? left : right;// append whatever remains.
        return dummy.next;
    }

    public ListNodeCloned copyRandomList_aka_CloneLinkedList(ListNodeCloned head) {

        /*
        Idea : Traverse like Linked list and link its next and random like org one.
        Relies on Visited Map to see if cloned node already exists and if not crate one and return.

        * */
        if (head == null) {
            return null;
        }

        Map<ListNodeCloned, ListNodeCloned> visitedMap = new HashMap<>();

        //start with creating first node and mark visited.
        var newCloneNode = new ListNodeCloned(head.val);
        visitedMap.put(head, newCloneNode);

        var newCloneNodeP = newCloneNode;
        // Iterate org list and make new node list
        while (head != null) {
            //expect its next and random will get assigned, they next and random node will not have their childs or ref to their next/random but it will
            // get assigned as it will move on..
            newCloneNode.next = getMe(head.next, visitedMap);
            newCloneNode.random = getMe(head.random, visitedMap);
            head = head.next;
            newCloneNode = newCloneNode.next;
        }
        return newCloneNodeP;
    }


    private ListNodeCloned getMe(ListNodeCloned head, Map<ListNodeCloned, ListNodeCloned> visitedMap) {

        if (head == null) return null;

        if (visitedMap.containsKey(head)) {
            return visitedMap.get(head);
        }

        var newCloneNode = new ListNodeCloned(head.val);
        visitedMap.put(head, newCloneNode);
        return newCloneNode;
    }


    //-----------------------helpers----------------------------


    private ListNode reverseIt(ListNode head) {
        ListNode prev = null, next = null;
        ListNode cur = head;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNodeCloned BuildLinkedList(int data, ListNodeCloned head) {
        var appendNode = new ListNodeCloned(data);

        if (head == null) {
            head = appendNode;
            return head;
        }

        ListNodeCloned tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = appendNode;

        return head;
    }

    public ListNodeCloned SearchClonedNode(ListNodeCloned head, int searchVal) {

        if (head == null) return null;

        while (head != null) {
            if (head.val == searchVal) {
                return head;
            }
            head = head.next;
        }
        return null;
    }


    //-----------------------Alternates----------------------------
    private ListNode Sum_Two_List_OLD(ListNode head, ListNode head2) {

        //reverse it so that we can add 1 to it 9-9-1
        ListNode rev1 = reverseIt(head);
        ListNode rev2 = reverseIt(head2);

        ListNode curAfterRev1 = rev1;
        ListNode curAfterRev2 = rev2;
        ListNode totalNode = null;
        ListNode dummyHead = totalNode;

        // Add both list
        int cf = 0;
        while (curAfterRev1 != null || curAfterRev2 != null) {

            int val1 = 0, val2 = 0;

            if (curAfterRev1 != null) {
                val1 = curAfterRev1.val;
                curAfterRev1 = curAfterRev1.next;
            }
            if (curAfterRev2 != null) {
                val2 = curAfterRev2.val;
                curAfterRev2 = curAfterRev2.next;

            }

            int totalVal = val1 + val2 + cf;
            int rem = totalVal % 10; // 0

            totalNode = new ListNode(rem);
            totalNode.next = dummyHead;
            dummyHead = totalNode;

            cf = totalVal / 10; //1
        }

        // add new node at fist if there was extra no left.
        //  ListNode curAfterRev2 = prev2;

        if (cf > 0) {
            ListNode n1 = new ListNode(cf);
            n1.next = dummyHead;
            dummyHead = n1;
            return dummyHead;
        }

        return dummyHead;
    }

    public ListNode Partition_OLD(ListNode head, int x) {

        if (head == null) return null;
        if (x < -200 || x > 200) return null;

        ListNode smaller = new ListNode(Integer.MIN_VALUE);
        ListNode larger = new ListNode(Integer.MAX_VALUE);
        ;
        ListNode sRunner = smaller;
        ListNode lRunner = larger;
        ListNode curr = head;

        while (curr != null) {
            ListNode tempnxt = curr.next; //MUST so that we still have list
            curr.next = null; //MUST so that no chain when attach to smaller or larger
            if (curr.val < x) {
                sRunner.next = curr;
                sRunner = curr;
            } else {
                lRunner.next = curr;
                lRunner = curr;
            }
            curr = tempnxt;
        }

        sRunner.next = larger.next;
        return smaller.next;

    }

    public ListNode mergeTwoLists_Sorted_OLD(ListNode head, ListNode head2) {

        if (head == null && head2 == null) return null;
        if (head == null && head2 != null) return head2;
        if (head != null && head2 == null) return head;

        ListNode merged = new ListNode(Integer.MIN_VALUE);
        ListNode runner = merged;
        ListNode curr1 = head;
        ListNode curr2 = head2;

        while (curr1 != null && curr2 != null) {
            //Since two list gets reduced as we go .next and merged list intact
            // we do not need to worry about chaining like Partition.
            if (curr1.val < curr2.val) {
                runner.next = curr1;
                runner = curr1;
                curr1 = curr1.next;
            } else if (curr1.val > curr2.val) {
                runner.next = curr2;
                runner = curr2;
                curr2 = curr2.next;
            } else {
                runner.next = curr1;
                runner = curr1;
                curr1 = curr1.next;
                // we need both values when its same
                runner.next = curr2;
                runner = curr2;
                curr2 = curr2.next;
            }
        }
        while (curr1 != null) {
            runner.next = curr1;
            runner = curr1;
            curr1 = curr1.next;
        }
        while (curr2 != null) {
            runner.next = curr2;
            runner = curr2;
            curr2 = curr2.next;
        }
        return merged.next;
    }

}




