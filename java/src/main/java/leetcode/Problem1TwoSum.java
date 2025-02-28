package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum
 */
public final class Problem1TwoSum {
    // Time - O(N), Space - O(N)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return new int[0];
        }

        Map<Integer, Integer> pairs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            Integer difference = pairs.get(target - value);

            if (difference != null) {
                return new int[] {difference, i};
            }

            pairs.put(value, i);
        }

        return new int[0];
    }
}
