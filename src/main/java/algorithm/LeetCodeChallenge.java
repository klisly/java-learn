package algorithm;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class LeetCodeChallenge {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        LeetCodeChallenge lcc = new LeetCodeChallenge();
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(lcc.findMedianSortedArrays(nums1, nums2));
//        System.out.println(lcc.lengthOfLongestSubstring("ssaccsdaaa"));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mid = 0;
        int l1 = 0, r1 = nums1.length, l2 = 0, r2 = nums2.length;
        boolean isContinue = true;
        while (isContinue){
            int mid1 = (r1 - l1 + 1)/2;
            int mid2 = (r2 - l2 + 1)/2;
            int ls1 = mid1 - 1;
            int rs1 = nums1.length - mid1;
            int ls2 = mid2 - 1;
            int rs2 = nums2.length - mid2;
            int num1 = nums1[mid1];
            int num2 = nums2[mid2];
            if(num1 < num2){

            }
        }
        return mid;
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        int left = 0;
        int right = 0;
        Set<Character> words = new HashSet<>();
        for (int i = 1; i < s.length(); i++) {
            System.out.println(left + "\t" + right);
            words.clear();
            int k = i;
            while (k >= left) {
                if (words.contains(s.charAt(k))) {
                    break;
                }
                words.add(s.charAt(k));
                k--;
            }
            if (words.size() >= (right - left + 1)) {
                right = i;
                left = k + 1;
            }

        }
        System.out.println(right + "\t" + left);
        return right - left + 1;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode lRest = null;
        ListNode retHead = null;
        while (l1 != null || l2 != null || lRest != null) {
            ListNode cl1 = null;
            if (l1 != null) {
                cl1 = l1;
                l1 = l1.next;
            }
            ListNode cl2 = null;
            if (l2 != null) {
                cl2 = l2;
                l2 = l2.next;
            }

            int total = 0;
            if (cl1 != null) {
                total += cl1.val;
            }
            if (cl2 != null) {
                total += cl2.val;
            }
            if (lRest != null) {
                total += lRest.val;
            }
            int val = total % 10;
            int upVal = total / 10;
            if (upVal > 0) {
                lRest = new ListNode(upVal);
            } else {
                lRest = null;
            }
            ListNode valNode = new ListNode(val);
            if (result != null) {
                result.next = valNode;
            }
            result = valNode;
            if (retHead == null) {
                retHead = result;
            }
        }

        return retHead;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[]{-1, -1};
        Hashtable<Integer, Integer> hs = new Hashtable<>();
        // two loop
//        for (int i = 0; i < nums.length; i++) {
//            hs.put(nums[i], i);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int num = nums[i];
//            if (hs.containsKey(target - num)) {
//                ret[0] = i;
//                ret[1] = hs.get(target - num);
//                break;
//            }
//        }
        // one loop
        for (int i = 0; i < nums.length; i++) {
            int rest = target - nums[i];
            if (hs.containsKey(rest)) {
                ret[1] = i;
                ret[0] = hs.get(rest);
                break;
            }
            hs.put(nums[i], i);
        }
        return ret;
    }
}
