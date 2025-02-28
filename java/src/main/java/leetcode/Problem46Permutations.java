package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/permutations
 */
public final class Problem46Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return permutations;
        }

        permute(permutations, new ArrayList<>(), nums);
        return permutations;
    }

    // backtrack
    private void permute(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num)) {
                    continue; // element already exists, skip
                }
                tempList.add(num);
                permute(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // recursion + swap
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return permutations;
        }

        permute(nums, 0, permutations);
        return permutations;
    }

    private void permute(int[] nums, int start, List<List<Integer>> permutations) {
        if (start >= nums.length) {
            permutations.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            permute(nums, start + 1, permutations);
            swap(nums, start, i);
        }
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
