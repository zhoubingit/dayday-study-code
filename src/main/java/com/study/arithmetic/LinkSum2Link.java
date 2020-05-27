package com.study.arithmetic;

import java.util.Objects;

/**
 * 链表求和
 */
public class LinkSum2Link {

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode l1 = ListNodeUtil.createListNode(2, 4, 3);
        ListNode l2 = ListNodeUtil.createListNode(5, 6, 6);
        ListNode listNode = sumLink(l1, l2);
        ListNodeUtil.print(l1);
        ListNodeUtil.print(l2);
        ListNodeUtil.print(listNode);
        System.out.println("-----------------------------------------------");
        l1 = ListNodeUtil.createListNode(2, 4, 3);
        l2 = ListNodeUtil.createListNode(5, 6, 6, 5, 4);
        listNode = sumLink(l1, l2);
        ListNodeUtil.print(l1);
        ListNodeUtil.print(l2);
        ListNodeUtil.print(listNode);
    }

    /**
     * 链表求和
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode sumLink(ListNode l1, ListNode l2) {
        // 创建链表
        ListNode listNode = new ListNode(0);
        // 链表指针
        ListNode m = l1, n = l2, curr = listNode;
        // 阈值 满10进1
        int thresholdVal = 10;
        // 链表相同指针位置求和 若大于阈值取余carry为1
        int nval = 0, mval = 0, carry = 0;
        while (Objects.nonNull(m) || Objects.nonNull(n)) {
            nval = n == null ? 0 : n.val;
            mval = m == null ? 0 : m.val;

            n = n == null ? null : n.next;
            m = m == null ? null : m.next;

            int sum = nval + mval + carry;
            carry = sum / thresholdVal;
            curr.val = sum % thresholdVal;
            if (Objects.nonNull(m) || Objects.nonNull(n)) {
                curr.next = new ListNode(0);
                curr = curr.next;
            }
        }
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        return listNode;
    }
}

/**
 * 链表类
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

/**
 * 链表工具类
 */
class ListNodeUtil {

    /**
     * 创建链表
     *
     * @param args
     * @return
     */
    public static ListNode createListNode(int... args) {
        if (args.length == 0) return null;
        ListNode listNode = new ListNode(args[0]);
        ListNode curr = listNode;
        for (int i = 1; i < args.length; i++) {
            curr.next = new ListNode(args[i]);
            curr = curr.next;
        }
        return listNode;
    }

    /**
     * 打印
     *
     * @param listNode
     */
    public static void print(ListNode listNode) {
        ListNode curr = listNode;

        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : "\n"));
            curr = curr.next;
        }
    }
}