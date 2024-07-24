package exploration;

import exploration.helpers.llist.lru.ListNode;
import exploration.helpers.llist.lru.ListNodeCloned;

import java.util.HashMap;
import java.util.Map;

public class LinkedListDSA {

    /*
Key Points to Remember:

ListNode p = head is simply another pointer p that references the head.
When p advances, such as p = p.next.next, it can only see what lies ahead.
 However, the head pointer can still see everything from the start.
Similarly, any other pointer can only see forward from its current position.
If it moves, it can’t see what it has already passed. However, other pointers that haven’t moved
 or have moved to different positions can see different parts of the list.
Point 3 is not applicable when a pointer makes structural changes to the LinkedList data structure.
 Pointers that have already passed the point where changes were made will not see these changes,
 but the rest of the pointers will.
For example, if pointers header, p1, and p2 remain the same and r1
has already moved somewhere in between, and now p1.next = null is executed,
 then header, p1, and p2 will see nothing after p1 as it is null. However, r1 could still see
  the rest of the list as it is not affected by this change.

Note on Sentinel Head (Fake Head Node):

The Sentinel head is a fake head node that is useful for avoiding edge cases.
It is widely used for trees and linked lists, often referred to as pseudo-heads.
These nodes are purely functional and usually don’t hold any data.
Their primary purpose is to standardize the situation to avoid edge case handling.
For instance, when deleting duplicate code and all nodes in the list are duplicates,
 you can add a Sentinel node and then treat other nodes as internal nodes.
 This approach helps to handle the deletion of head and tail nodes.




    * */


    /**
     * This method inserts a new node at the beginning of the linked list.
     *
     * @param  data The data to be inserted in the new node.
     * @param  head The head of the linked list.
     * @return The updated head of the linked list.
     */
    public ListNode InsertNode_Prepend(int data, ListNode head) {
        // Create a new node with the given data
        var prependNode = new ListNode(data);

        // Set the next of the new node to the current head
        prependNode.next = head;

        // Update the head to the new node
        head = prependNode;

        // Return the updated head of the list
        return head;
    }

    /**
     * This method appends a new node at the end of the linked list.
     *
     * @param  data The data to be inserted in the new node.
     * @param  head The head of the linked list.
     * @return The head of the linked list.
     */
    public ListNode InsertNode_Append(int data, ListNode head) {
        // Create a new node with the given data
        var appendNode = new ListNode(data);

        // If the list is empty, make the new node the head
        if (head == null) {
            head = appendNode;
            return head;
        }

        // Traverse to the end of the list
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        // Append the new node at the end of the list
        tail.next = appendNode;

        // Return the head of the list
        return head;
    }

    /**
     * This method deletes the last node of the linked list.
     *
     * @param  head The head of the linked list.
     * @return The updated head of the linked list.
     */
    public ListNode DeleteLastNode(ListNode head) {
        // If the list is empty, return null
        if (head == null) {
            return null;
        }

        // Traverse to the second last node of the list
        ListNode cur = head;
        while (cur.next.next != null) {
            cur = cur.next;
        }

        // Delete the last node
        cur.next = null;

        // Return the updated head of the list
        return head;
    }

    /**
     * This method counts the number of nodes in the linked list using iteration.
     *
     * @param  head The head of the linked list.
     * @return The count of nodes in the linked list.
     */
    public int getCount(ListNode head) {
        // Initialize counter to 0
        var counter = 0;

        // If the list is empty, return counter
        if (head == null) return counter;

        // Traverse the list and increment the counter for each node
        while (head != null) {
            counter++;
            head = head.next;
        }

        // Return the count of nodes
        return counter;
    }

    /**
     * This method counts the number of nodes in the linked list using recursion.
     *
     * @param  head The head of the linked list.
     * @return The count of nodes in the linked list.
     */
    public int getCount_Recursive(ListNode head) {

        // Initialize counter to 0
        var counter = 0;

        // Base case: If the list is empty, return counter
        if (head == null) return counter;

        // Recursive call: Increment the counter and move to the next node
        counter = 1 + getCount_Recursive(head.next);

        // Return the count of nodes
        return counter;
    }

    /**
     * This method searches for a specific value in a linked list.
     *
     * @param  head      The head node of the linked list.
     * @param  searchVal The value to search for.
     * @return true if the value is found, false otherwise.
     * <p>
     * Example:
     * ListNode head = new ListNode(1);
     * head.next = new ListNode(2);
     * head.next.next = new ListNode(3);
     * boolean result = SearchNode(head, 2); // returns true
     */
    public boolean SearchNode(ListNode head, int searchVal) {
        // If the list is empty, the value is not found
        if (head == null) return false;

        // Traverse the list
        while (head != null) {
            // If the current node's value is the search value, return true
            if (head.val == searchVal) {
                return true;
            }
            // Move to the next node
            head = head.next;
        }
        // If the end of the list is reached without finding the value, return false
        return false;
    }

    /**
     * This method recursively searches for a specific value in a linked list.
     *
     * @param  head The head node of the linked list.
     * @param  searchVal The value to search for.
     * @return true if the value is found, false otherwise.
     *
     * Example:
     * ListNode head = new ListNode(1);
     * head.next = new ListNode(2);
     * head.next.next = new ListNode(3);
     * boolean result = SearchNode_Recursive(head, 2); // returns true
     */
    public boolean SearchNode_Recursive(ListNode head, int searchVal) {
        // Base case: if the list is empty, the value is not found
        if (head == null) return false;

        // Recursive call: if the current node's value is the search value, return true
        // Otherwise, search in the rest of the list
        return head.val == searchVal || SearchNode_Recursive(head.next, searchVal);
    }

    /**
     * This method finds the middle element of a linked list.
     *
     * @param  head The head node of the linked list.
     * @return The middle node of the linked list.
     *
     * Example:
     * ListNode head = new ListNode(1);
     * head.next = new ListNode(2);
     * head.next.next = new ListNode(3);
     * ListNode middle = FindMiddleElement(head); // returns the node with value 2
     */
    public ListNode FindMiddleElement(ListNode head) {
        ListNode slow = head, fast = head;

        // Traverse the list with two pointers, one moving twice as fast as the other
        while (fast != null && fast.next != null) {
            slow = slow.next; // Moves one step at a time
            fast = fast.next.next; // Moves two steps at a time
        }

        // When the fast pointer reaches the end of the list, the slow pointer is at the middle
        return slow;
    }

    /**
     * This method reverses a linked list.
     *
     * @param  head The head node of the linked list.
     * @return The head node of the reversed linked list.
     *
     * Example:
     * ListNode head = new ListNode(1);
     * head.next = new ListNode(2);
     * head.next.next = new ListNode(3);
     * ListNode reversed = ReverseList(head); // returns the reversed list 3->2->1
     */
    public ListNode ReverseList(ListNode head) {
        ListNode prev = null, next = null;
        ListNode cur = head;

        // Traverse the list
        while (cur != null) {
            // Keep track of the next node
            next = cur.next;
            // Reverse the link
            cur.next = prev;
            // Move to the next node
            prev = cur;
            cur = next;
        }
        // Return the reversed list
        return prev;
    }

    /**
     * This method recursively reverses a linked list.
     *
     * @param  head The head node of the linked list.
     * @return The head node of the reversed linked list.
     *
     * Example:
     * ListNode head = new ListNode(1);
     * head.next = new ListNode(2);
     * head.next.next = new ListNode(3);
     * ListNode reversed = ReverseList_Recursive(head); // returns the reversed list 3->2->1
     */
    public ListNode ReverseList_Recursive(ListNode head) {
        return Private_ReverseList_Recursive(head, null);
    }

    /**
     * This method reverses a linked list using recursion.
     *
     * @param  curr The current node in the list.
     * @param  prev The previous node in the list.
     * @return The head of the reversed list.
     *
     * Example:
     * Input: 1 -> 2 -> 3 -> null
     * Output: 3 -> 2 -> 1 -> null
     */
    private ListNode Private_ReverseList_Recursive(ListNode curr, ListNode prev) {
        if (curr == null) return prev;

        // Recursively reverse the rest of the list and get the new head
        ListNode head = Private_ReverseList_Recursive(curr.next, curr);

        // Reverse the current node
        curr.next = prev;

        // Return the new head of the reversed list
        return head;
    }

    /**
     * This method detects if a linked list has a cycle using Floyd’s Cycle-Finding Algorithm.
     *
     * @param  head The head of the list.
     * @return True if there is a cycle, false otherwise.
     *
     * Example:
     * Input: 1 -> 2 -> 3 -> 2 (cycle)
     * Output: true
     */
    public boolean Detect_Loop_Cycle(ListNode head) {
        if (head == null) return true;
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list with a slow and a fast pointer
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If the slow and fast pointers meet, there is a cycle
            if (slow.val == fast.val) {
                return true;
            }
        }

        // If the end of the list is reached, there is no cycle
        return false;
    }

    /**
     * This method finds the starting point of a cycle in a linked list using Floyd’s Cycle-Finding Algorithm.
     *
     * @param  head The head of the list.
     * @return The starting node of the cycle, or null if there is no cycle.
     *
     * Example:
     * Input: 1 -> 2 -> 3 -> 4 -> 2 (cycle)
     * Output: Node with value 2
     */
    public ListNode Find_StartingPoint_Loop_Cycle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If a cycle is detected
            if (slow == fast) {
                // Move the slow and entry pointers one step at a time until they meet
                while (slow != entry) {
                    slow = slow.next;
                    entry = entry.next;
                }

                // Return the starting point of the cycle
                return slow;
            }
        }

        // If no cycle is detected, return null
        return null;
    }

    /**
     * This method checks if a linked list is a palindrome.
     *
     * @param  head The head of the list.
     * @return True if the list is a palindrome, false otherwise.
     *
     * Example:
     * Input: 1 -> 2 -> 2 -> 1
     * Output: true
     */
    public boolean Is_Palindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode entry = head;

        // Find the middle node
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half of the list
        ListNode prev = null;
        ListNode next = null;
        ListNode cur = slow.next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // Connect the reversed second half to the first half
        slow.next = prev;

        // Compare the first half and the reversed second half
        while (prev != null) {
            if (prev.val != entry.val) {
                return false;
            }
            prev = prev.next;
            entry = entry.next;
        }

        return true;
    }

    /**
     * This method removes the nth node from the end of a linked list.
     *
     * @param  head The head of the list.
     * @param  n The position from the end of the list.
     * @return The head of the list after the removal.
     *
     * Example:
     * Input: 1 -> 2 -> 3 -> 4 -> 5, n = 2
     * Output: 1 -> 2 -> 3 -> 5
     */
    public ListNode Remove_Nth_Node_From_Back(ListNode head, int n) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Move the fast pointer n steps ahead
        for (int i = 1; i <= n; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }

        // If the end of the list is reached, remove the head
        if (fast == null) {
            head = head.next;
            return head;
        }

        // Move the slow and fast pointers until the end of the list is reached
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return head;
    }


    /**
     * This method finds the intersection of two linked lists.
     *
     * @param  head  The head of the first linked list.
     * @param  head2 The head of the second linked list.
     * @return The value of the intersecting node. If no intersection, returns 0.
     * <p>
     * Example:
     * List1: 1 -> 2 -> 3 -> 4
     * List2: 5 -> 2 -> 3 -> 4
     * The intersection is at node with value 2.
     */
    public int findIntersectionOfTwoLists(ListNode head, ListNode head2) {
        // Base case: if either list is null, there is no intersection.
        if (head == null || head2 == null) return 0;

        // Initialize pointers for each list.
        ListNode first = head;
        ListNode second = head2;

        // Find the length of each list.
        int countFirst = 0;
        int countSecond = 0;
        while (first != null) {
            countFirst++;
            first = first.next;
        }
        while (second != null) {
            countSecond++;
            second = second.next;
        }

        // Determine which list is longer.
        ListNode longest;
        ListNode shortest;
        int diffBetweenTwo = countFirst - countSecond;
        if (diffBetweenTwo < 0) {
            longest = head2;
            shortest = head;
        } else {
            longest = head;
            shortest = head2;
        }

        // Move the pointer of the longer list to the same starting point as the shorter list.
        for (int a = 1; a <= Math.abs(diffBetweenTwo); a++) {
            longest = longest.next;
        }

        // Traverse both lists and compare nodes.
        while (longest != null && shortest != null) {
            if (longest.val == shortest.val) {
                return longest.val;
            }
            longest = longest.next;
            shortest = shortest.next;
        }

        // If no intersection, return 0.
        return 0;
    }

    /**
     * This method adds 1 to a number represented as a linked list.
     *
     * @param  head The head of the linked list.
     * @return The head of the linked list after adding 1.
     * <p>
     * Example:
     * List: 1 -> 9 -> 9
     * After adding 1, the list becomes: 2 -> 0 -> 0
     */
    public ListNode addOneToList(ListNode head) {
        // Reverse the list so that we can add 1 to it.
        ListNode prev = null, next = null;
        ListNode cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        ListNode curAfterRev1 = prev;

        // Add 1 to the reversed list.
        int cf = 1; // For adding 1, cf starts with 1.
        while (curAfterRev1 != null) {
            int addOne = curAfterRev1.val + cf;
            int rem = addOne % 10;
            curAfterRev1.val = rem;
            cf = addOne / 10;
            curAfterRev1 = curAfterRev1.next;
        }

        // Reverse the list again.
        ListNode prev2 = null, next2 = null;
        ListNode cur2 = prev; // Because curAfterRev1 now points to null after iteration so we pick prev.
        while (cur2 != null) {
            next2 = cur2.next;
            cur2.next = prev2;
            prev2 = cur2;
            cur2 = next2;
        }

        // Add new node at first if there was extra number left.
        ListNode curAfterRev2 = prev2;
        if (cf > 0) {
            ListNode n1 = new ListNode(cf);
            n1.next = curAfterRev2;
            return n1;
        }

        return curAfterRev2;
    }

    /**
     * This method sums two numbers represented as linked lists.
     *
     * @param  head  The head of the first linked list.
     * @param  head2 The head of the second linked list.
     * @return The head of the linked list representing the sum.
     * <p>
     * Example:
     * List1: 2 -> 1 -> 0
     * List2: 9 -> 3 -> 1
     * The sum is: 1 -> 1 -> 5 -> 2
     */
    public ListNode sumTwoLists(ListNode head, ListNode head2) {
        // Reverse both lists.
        ListNode curAfterRev1 = reverseIt(head);
        ListNode curAfterRev2 = reverseIt(head2);

        ListNode totalNode = null;
        ListNode dummyHead = totalNode;

        // Add both lists.
        int cf = 0;
        while (curAfterRev1 != null || curAfterRev2 != null || cf == 1) {
            int totalVal = 0;
            if (curAfterRev1 != null) {
                totalVal += curAfterRev1.val;
                curAfterRev1 = curAfterRev1.next;
            }
            if (curAfterRev2 != null) {
                totalVal += curAfterRev2.val;
                curAfterRev2 = curAfterRev2.next;
            }
            totalVal += cf;
            cf = totalVal / 10;
            int rem = totalVal % 10;

            totalNode = new ListNode(rem);
            totalNode.next = dummyHead;
            dummyHead = totalNode;
        }

        return dummyHead;
    }

    /**
     * This method reverses a linked list in groups of k.
     *
     * @param  head The head of the linked list.
     * @param  k    The size of the groups.
     * @return The head of the linked list after reversing in groups of k.
     * <p>
     * Example:
     * List: 1 -> 2 -> 3 -> 4 -> 5
     * k: 2
     * After reversing in groups of 2, the list becomes: 2 -> 1 -> 4 -> 3 -> 5
     */
    public ListNode reverseListInGroupK(ListNode head, int k) {
        // Base case: if the list is null or has only one node, return the list as is.
        if (head == null || head.next == null) return head;

        // Find the length of the list.
        int len = 0;
        ListNode lenCur = head;
        while (lenCur != null) {
            ++len;
            lenCur = lenCur.next;
        }
        // If k is greater than the length of the list, return null.
        if (k > len) return null;

        int count = 0;
        ListNode cur = head;
        ListNode dummy = cur;
        ListNode prev = null;
        ListNode next = null;
        ListNode finalNodes = prev;
        int loop = len / k; // Find loop as multiplier of k.

        // Do till nodes are there in list.
        while (cur != null || count <= len) {
            // When reached to k.
            if (count != 0 && count % k == 0) {
                // This makes sure 1 -> 4 after second round of reverse happens and then so forth.
                if (dummy.next != null && dummy.next.next != null) {
                    dummy.next.next = prev;
                }
                // Below steps to make sure 1 -> 3 and 3->5 happens.
                dummy.next = cur;
                finalNodes = finalNodes == null ? prev : finalNodes;
                prev = dummy;
                dummy = cur;
                // Break when loop reached i.e. rest node are not multiplier of k.
                if ((count + 1) / k >= loop) break;
            }
            // Do routine reverse but keep counter as we can't cross k.
            count++;
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return finalNodes;
    }

    /**
     * This method rotates a linked list by k positions.
     *
     * @param  head The head of the linked list.
     * @param  k    The number of positions to rotate.
     * @return The head of the linked list after rotating.
     * <p>
     * Example:
     * List: 1 -> 2 -> 3 -> 4 -> 5
     * k: 2
     * After rotating by 2 positions, the list becomes: 4 -> 5 -> 1 -> 2 -> 3
     */
    public ListNode rotateList(ListNode head, int k) {
        // Base case: if the list is null, return null.
        if (head == null) return null;

        // Find the length of the list.
        ListNode lenH = head;
        int len = 0;
        while (lenH != null) {
            len++;
            lenH = lenH.next;
        }

        // Edge case: if the length of the list is equal to k, no rotation is required.
        if (len - k == 0) return head;
        // When k is larger than len.
        if (k > len) {
            k = k - len;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Make fast reach till n.
        for (int i = 1; i <= k; i++) {
            fast = fast.next;
        }

        // Reached last node.
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head; // 5->1
        head = slow.next; // 4 is head
        slow.next = null; // 3 is last node

        return head;
    }


    /**
     * This method removes duplicates from a linked list.
     *
     * @param  head The head node of the linked list.
     * @return The head node of the linked list with duplicates removed.
     *
     * Example:
     * ListNode head = new ListNode(1);
     * head.next = new ListNode(2);
     * head.next.next = new ListNode(2);
     * ListNode result = Delete_Duplicate(head); // returns the list 1->2
     */
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

    /**
     * This method partitions a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.
     *
     * @param  head The head node of the linked list.
     * @param  x The value to partition around.
     * @return The head node of the partitioned linked list.
     *
     * Example:
     * ListNode head = new ListNode(3);
     * head.next = new ListNode(5);
     * head.next.next = new ListNode(8);
     * head.next.next.next = new ListNode(5);
     * head.next.next.next.next = new ListNode(10);
     * head.next.next.next.next.next = new ListNode(2);
     * head.next.next.next.next.next.next = new ListNode(1);
     * ListNode result = Partition_CleanWay(head, 5); // returns the list 3->2->1->5->8->5->10
     */
    public ListNode Partition_CleanWay(ListNode head, int x) {
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
        afterRunner.next = null; // remove lingering anything

        beforeRunner.next = after.next; // combine both
        return before.next; // return
    }

    /**
     * This method merges two sorted linked lists and returns it as a new sorted list.
     *
     * @param  head1 The head node of the first sorted linked list.
     * @param  head2 The head node of the second sorted linked list.
     * @return The head node of the merged sorted linked list.
     *
     * Example:
     * ListNode head1 = new ListNode(1);
     * head1.next = new ListNode(2);
     * head1.next.next = new ListNode(4);
     * ListNode head2 = new ListNode(1);
     * head2.next = new ListNode(3);
     * head2.next.next = new ListNode(4);
     * ListNode result = mergeTwoLists_Sorted_CleanWay(head1, head2); // returns the list 1->1->2->3->4->4
     */
    public ListNode mergeTwoLists_Sorted_CleanWay(ListNode head1, ListNode head2) {
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

        // At least one of head1 and head2 can still have nodes at this point, so connect
        // the non-null list to the end of the merged list.
        mergedRunner.next = head1 != null ? head1 : head2;

        return merged.next;
    }

    /**
     * This method merges k sorted linked lists and returns it as a new sorted list.
     *
     * @param  lists An array of head nodes of the k sorted linked lists.
     * @return The head node of the merged sorted linked list.
     *
     * Example:
     * ListNode[] lists = new ListNode[2];
     * lists[0] = new ListNode(1);
     * lists[0].next = new ListNode(4);
     * lists[0].next.next = new ListNode(5);
     * lists[1] = new ListNode(1);
     * lists[1].next = new ListNode(3);
     * lists[1].next.next = new ListNode(4);
     * ListNode result = merge_K_Lists_Sorted(lists); // returns the list 1->1->3->4->4->5
     */
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


    /**
     * This method deletes the middle node of the linked list.
     *
     * @param  head The head of the linked list.
     * @return The updated head of the linked list.
     */
    public ListNode deleteMiddle(ListNode head) {
        // Initialize two pointers, 'slow' and 'fast'.
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list until 'fast' and 'fast.next' is not null
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node
        slow.val = slow.next.val;
        slow.next = slow.next.next;

        // Return the updated head of the list
        return head;
    }

    /**
     * This method rearranges the linked list such that all odd nodes come before even nodes.
     *
     * @param  head The head of the linked list.
     * @return The head of the rearranged linked list.
     */
    public ListNode oddEvenList(ListNode head) {
        // If the list is empty, return head
        if (head == null) return head;

        // Initialize pointers for odd and even nodes
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenRunner = even;

        // Traverse the list until 'evenRunner' and 'evenRunner.next' is not null
        while (evenRunner != null && evenRunner.next != null) {
            odd.next = evenRunner.next; // Link odd nodes
            odd = odd.next;

            evenRunner.next = odd.next; // Link even nodes
            evenRunner = evenRunner.next;
        }

        // Append even nodes after odd nodes
        odd.next = even;

        // Return the head of the rearranged list
        return head;
    }

    /**
     * This method sorts the linked list using Merge Sort algorithm.
     *
     * @param  head The head of the linked list.
     * @return The head of the sorted linked list.
     */
    public ListNode sortList(ListNode head) {
        // Base case: If the list is empty or has only one node, return head
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        ListNode secondHalf = getSecondHalf(head);

        // Recursively sort the first half
        ListNode left = sortList(head);
        // Recursively sort the second half
        ListNode right = sortList(secondHalf);

        // Merge the two sorted halves
        return merge(left, right);
    }

    /**
     * This method splits the linked list into two halves.
     *
     * @param  head The head of the linked list.
     * @return The head of the second half of the linked list.
     */
    private ListNode getSecondHalf(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // Traverse the list until 'fast' and 'fast.next' is not null
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        // Split the list into two halves
        prev.next = null;

        // Return the head of the second half
        return slow;
    }

    /**
     * This method merges two sorted linked lists into one sorted linked list.
     *
     * @param  left The head of the first sorted linked list.
     * @param  right The head of the second sorted linked list.
     * @return The head of the merged sorted linked list.
     */
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;

        // Traverse the two lists
        while (left != null && right != null) {
            // Append the smaller node to the merged list
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        // Append the remaining nodes of the longer list
        tail.next = left != null ? left : right;

        // Return the head of the merged list
        return dummy.next;
    }

    /**
     * This method clones a linked list with random pointers.
     *
     * @param  head The head of the original linked list.
     * @return The head of the cloned linked list.
     */
    public ListNodeCloned copyRandomList_aka_CloneLinkedList(ListNodeCloned head) {
        // If the list is empty, return null
        if (head == null) {
            return null;
        }

        // Create a map to store the visited nodes
        Map<ListNodeCloned, ListNodeCloned> visitedMap = new HashMap<>();

        // Create the first node of the cloned list and mark it as visited
        var newCloneNode = new ListNodeCloned(head.val);
        visitedMap.put(head, newCloneNode);

        var newCloneNodeP = newCloneNode;
        // Traverse the original list and create the cloned list
        while (head != null) {
            // Clone the 'next' and 'random' pointers
            newCloneNode.next = getMe(head.next, visitedMap);
            newCloneNode.random = getMe(head.random, visitedMap);

            // Move to the next node
            head = head.next;
            newCloneNode = newCloneNode.next;
        }

        // Return the head of the cloned list
        return newCloneNodeP;
    }


    /**
     * This method returns a cloned node from a map if it exists, otherwise creates a new node, adds it to the map, and returns it.
     *
     * @param  head The head of the list.
     * @param  visitedMap The map containing the visited nodes.
     * @return The cloned node.
     *
     * Example:
     * Input: head = 1 -> 2 -> 3, visitedMap = {1 -> 1', 2 -> 2'}
     * Output: 3'
     */
    private ListNodeCloned getMe(ListNodeCloned head, Map<ListNodeCloned, ListNodeCloned> visitedMap) {
        if (head == null) return null;

        if (visitedMap.containsKey(head)) {
            return visitedMap.get(head);
        }

        ListNodeCloned newCloneNode = new ListNodeCloned(head.val);
        visitedMap.put(head, newCloneNode);
        return newCloneNode;
    }

    /**
     * This method reverses a linked list.
     *
     * @param  head The head of the list.
     * @return The head of the reversed list.
     *
     * Example:
     * Input: 1 -> 2 -> 3 -> null
     * Output: 3 -> 2 -> 1 -> null
     */
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

    /**
     * This method appends a node to the end of a linked list and returns the head of the list.
     *
     * @param  data The data for the new node.
     * @param  head The head of the list.
     * @return The head of the list after the node is appended.
     *
     * Example:
     * Input: data = 4, head = 1 -> 2 -> 3
     * Output: 1 -> 2 -> 3 -> 4
     */
    public ListNodeCloned BuildLinkedList(int data, ListNodeCloned head) {
        ListNodeCloned appendNode = new ListNodeCloned(data);

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

    /**
     * This method searches for a node in a linked list and returns it.
     *
     * @param  head The head of the list.
     * @param  searchVal The value to search for.
     * @return The node if found, null otherwise.
     *
     * Example:
     * Input: head = 1 -> 2 -> 3, searchVal = 2
     * Output: Node with value 2
     */
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


    /**
     * This method sums two numbers represented as linked lists.
     *
     * @param  head  The head of the first linked list.
     * @param  head2 The head of the second linked list.
     * @return The head of the linked list representing the sum.
     * <p>
     * Example:
     * List1: 2 -> 1 -> 0
     * List2: 9 -> 3 -> 1
     * The sum is: 1 -> 1 -> 5 -> 2
     */
    private ListNode sumTwoListsOld(ListNode head, ListNode head2) {
        // Reverse both lists.
        ListNode rev1 = reverseIt(head);
        ListNode rev2 = reverseIt(head2);

        ListNode curAfterRev1 = rev1;
        ListNode curAfterRev2 = rev2;
        ListNode totalNode = null;
        ListNode dummyHead = totalNode;

        // Add both lists.
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
            int rem = totalVal % 10;

            totalNode = new ListNode(rem);
            totalNode.next = dummyHead;
            dummyHead = totalNode;

            cf = totalVal / 10;
        }

        // Add new node at first if there was extra number left.
        if (cf > 0) {
            ListNode n1 = new ListNode(cf);
            n1.next = dummyHead;
            dummyHead = n1;
            return dummyHead;
        }

        return dummyHead;
    }

    /**
     * This method partitions a linked list around a value x.
     *
     * @param  head The head of the linked list.
     * @param  x    The value to partition around.
     * @return The head of the linked list after partitioning.
     * <p>
     * Example:
     * List: 1 -> 4 -> 3 -> 2 -> 5 -> 2
     * x: 3
     * After partitioning, the list becomes: 1 -> 2 -> 2 -> 4 -> 3 -> 5
     */
    public ListNode partitionOld(ListNode head, int x) {
        // Base case: if the list is null, return null.
        if (head == null) return null;
        // If x is out of range, return null.
        if (x < -200 || x > 200) return null;

        ListNode smaller = new ListNode(Integer.MIN_VALUE);
        ListNode larger = new ListNode(Integer.MAX_VALUE);
        ListNode sRunner = smaller;
        ListNode lRunner = larger;
        ListNode curr = head;

        while (curr != null) {
            ListNode tempNext = curr.next; // Save the next node.
            curr.next = null; // Disconnect the current node from the list.
            if (curr.val < x) {
                sRunner.next = curr;
                sRunner = curr;
            } else {
                lRunner.next = curr;
                lRunner = curr;
            }
            curr = tempNext;
        }

        // Connect the smaller and larger lists.
        sRunner.next = larger.next;
        return smaller.next;
    }

    /**
     * This method merges two sorted linked lists.
     *
     * @param  head  The head of the first linked list.
     * @param  head2 The head of the second linked list.
     * @return The head of the merged linked list.
     * <p>
     * Example:
     * List1: 1 -> 2 -> 4
     * List2: 1 -> 3 -> 4
     * The merged list is: 1 -> 1 -> 2 -> 3 -> 4 -> 4
     */
    public ListNode mergeTwoListsSortedOld(ListNode head, ListNode head2) {
        // Base cases: if either list is null, return the other list.
        if (head == null && head2 == null) return null;
        if (head == null && head2 != null) return head2;
        if (head != null && head2 == null) return head;

        ListNode merged = new ListNode(Integer.MIN_VALUE);
        ListNode runner = merged;
        ListNode curr1 = head;
        ListNode curr2 = head2;

        // Merge the lists by comparing the values of each node.
        while (curr1 != null && curr2 != null) {
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
                runner.next = curr2;
                runner = curr2;
                curr2 = curr2.next;
            }
        }
        // If there are remaining nodes in either list, append them to the merged list.
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




