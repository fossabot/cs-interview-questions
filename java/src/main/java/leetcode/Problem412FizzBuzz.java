package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/fizz-buzz
 */
public final class Problem412FizzBuzz {
    // Time - O(N), Space - O(N)
    public List<String> fizzBuzz(int n) {
        List<String> words = new ArrayList<>();
        if (n < 0) {
            return words;
        }

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                words.add("FizzBuzz");
            } else if (i % 3 == 0) {
                words.add("Fizz");
            } else if (i % 5 == 0) {
                words.add("Buzz");
            } else {
                words.add(String.valueOf(i));
            }
        }

        return words;
    }
}
