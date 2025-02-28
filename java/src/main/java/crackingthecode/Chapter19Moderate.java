package crackingthecode;

import api.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class Chapter19Moderate {
    /**
     * 19.1 - Write a function to swap a number in place without temporary variables.
     */
    // Time - O(1), Space - O(1)
    public void swapVar(int left, int right) {
        left ^= right;
        right ^= left;
        left ^= right;
    }

    // Time - O(1), Space - O(1)
    public void swapVar2(int left, int right) {
        left = right - left;    // 4 = 9 - 5
        right = right - left;   // 5 = 9 - 4
        left = left + right;    // 9 = 4 + 5
    }

    /**
     * 19.2 - Design an algorithm to figure out if someone has won in a game of tic-tac-toe.
     */
    // Outcomes - X - Player 1, O - Player 2, no one
    // Time - O(1), Space - O(1) (since we know it is 3x3 array)
    @SuppressWarnings("ForLoopReplaceableByForEach") // Leave for readability
    public int checkTicTacToe(int[][] board) {
        if (board == null) {
            return -1;
        }

        // Rows
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] != 0
                && board[0][i] == board[1][i]
                && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // Cols
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != 0
                && board[i][0] == board[i][1]
                && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // Diagnol Cases
        if (board[0][0] != 0
            && board[0][0] == board[1][1]
            && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != 0
            && board[0][2] == board[1][1]
            && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return -1;
    }

    /**
     * 19.3 - Write an algorithm which computes the number of trailing zeros in n factorial.
     */
    // 5 * 2 = 10
    // misinterpreted the question, I thought we were given the factorial
    public int numZeros(int number) {
        int count = 0;
        if (number < 0) {
            return 0;
        }

        for (int i = 5; number / i > 0; i *= 5) {
            count += number / i;
        }

        return count;
    }

    /**
     * 19.4 - Write a method which finds the maximum of two numbers. You should not use if-else or any
     * other comparison operator. EXAMPLE Input: 5, 10 Output: 10
     */
    // >> 31 - for only 32 bit numbers
    // Time - O(1), Space - O(1)
    public long getMax(long left, long right) {
        long diff = (left - right) >> 31;    // >> 31 accounts for negatives
        long diffFirst = diff & right;
        long diffSecond = ~diff & left;

        return diffFirst + diffSecond;
    }

    // Time - O(1), Space - O(1)
    public long getMax2(long left, long right) {
        long[] temp = {left, right};
        return temp[(int) ((left - right) >> 31) & 1];
    }

    // If you have the max, you can find getMinNode, vice-versa
    // Time - O(1), Space - O(1)
    public long getMin(long left, long right) {
        long diff = (left - right) >> 31;    // >> 31 accounts for negatives
        long diffFirst = diff & left;
        long diffSecond = ~diff & right;

        return diffFirst + diffSecond;
    }

    /**
     * 19.5 - The Game of Master Mind is played as follows: The computer has four slots containing
     * balls that are red (R), yellow (Y), green (G) or blue (B). For example, the computer might have
     * RGGB (e.g., Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue). You, the user, are
     * trying to guess the solution. You might, for example, guess YRGB. When you guess the correct
     * color for the correct slot, you get a “hit”. If you guess a color that exists but is in the
     * wrong slot, you get a “pseudo-hit”. For example, the guess YRGB has 2 hits and one pseudo hit.
     * For each guess, you are told the number of hits and pseudo-hits. Write a method that, given a
     * guess and a solution, returns the number of hits and pseudo hits.
     */
    // length should be 4
    // will the slots allow for different lengths, more/less guesses?
    // Time - O(N^2), however we know it is only 4 length, O(4^2) -> O(16) -> O(1), Space - O(1)
    public Pair<Integer, Integer> getPseudoHits(String solution, String guess) {
        if (solution == null || guess == null || solution.length() != guess.length()) {
            return null;
        }

        int hits = 0;
        int pseudoHits = 0;

        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == guess.charAt(i)) {
                hits++;
            } else if (solution.contains(String.valueOf(guess.charAt(i)))) {
                pseudoHits++;
            }
        }

        return new Pair<>(hits, pseudoHits);
    }

    /*
     * 19.6 - Given an integer between 0 and 999,999, print an English phrase that describes the
     * integer (eg, “One Thousand, Two Hundred and Thirty Four”).
     */

    /**
     * 19.7 - You are given an array of integers (both positive and negative). Find the continuous
     * sequence with the largest sum. Return the sum. EXAMPLE Input: {2, -8, 3, -2, 4, -10} Output: 5
     * (i.e., {3, -2, 4} )
     */
    // Time - O(N), Space - O(1)
    // max "sub array"
    public int getMaxSum(int[] array) {
        if (array == null) {
            return 0;
        }

        int sum = 0;
        int max = 0;

        for (int item : array) {
            max = Math.max(max + item, item);
            sum = Math.max(max, sum);
        }

        // i    array   tempSum     sum
        // 0    2       2           2
        // 1    -8      -6          2
        // 2    3       3           3
        // 3    -2      1           3
        // 4    4       5           5
        // 5    -10     5           5

        return sum;
    }

    /**
     * 19.8 - Design a method to find the frequency of occurrences of any given word in a book.
     */
    // What about periods and other non characters? - "word.", maybe use regex? - book did not take
    // this into account
    // For single queries, just count the all the strings in the array
    // For repetitive queries we would create the hashmap one time
    // Time - O(N)
    public int getWordOccurrence(String[] array, String word) {
        if (array == null || word == null) {
            return -1;
        }

        Map<String, Integer> occurrences = new HashMap<>();

        for (String string : array) {
            if (string != null) {
                string = string.toLowerCase().trim();

                boolean isEmpty = string.isEmpty();
                if (!isEmpty) {
                    boolean hasWord = occurrences.containsKey(string);
                    if (hasWord) {
                        int increment = occurrences.get(string) + 1;
                        occurrences.put(string, increment);
                    } else {
                        occurrences.put(string, 1);
                    }
                }
            }
        }

        word = word.toLowerCase().trim();

        boolean hasWord = occurrences.containsKey(word);
        if (hasWord) {
            return occurrences.get(word);
        } else {
            return 0;
        }
    }

    /*
     * 19.9 - Since XML is very verbose, you are given a way of encoding it where each tag gets
     * mapped to a pre-defined integer value. The language/grammar is as follows:
     * Element --> Element Attr* END Element END [aka, encode the element
     * tag, then its attributes, then tack on an END character, then encode its children, then
     * another end tag]
     * Attr --> Tag Value [assume all values are strings]
     * END --> 01
     * Tag --> some predefined mapping to int
     * Value --> string value END
     * Write code to print the encoded version of an xml element (passed in as string).
     * FOLLOW UP
     * Is there anything else you could do to (in many cases) compress this even further?
     */

    /**
     * 19.10 - Write a method to generate a random number between 1 and 7, given a method that
     * generates a random number between 1 and 5 (i.e., implement rand7() using rand5()).
     */
    // book solution
    public int rand7() {
        while (true) {
            int number = 5 * rand5() - 1 + rand5() - 1;
            if (number < 21) {
                return number % 7 + 1;
            }
        }
    }

    // can we use Math.random?
    public int rand72() {
        return new Random().nextInt(rand5()) + 2;
    }

    // Here is our "rand5" - 1..5
    public int rand5() {
        return new Random().nextInt(4) + 1;
    }

    /**
     * 19.11 - Design an algorithm to find all pairs of integers within an array which sum to a
     * specified val.
     */
    // Time - O(N)
    public Map<Integer, Integer> getPairSum(int[] array, int target) {
        Map<Integer, Integer> pairs = new HashMap<>();
        Map<Integer, Integer> uniquePairs = new HashMap<>();

        for (int anArray : array) {
            if (!pairs.containsKey(anArray)) {
                pairs.put(target - anArray, anArray);
            }
        }

        for (int anArray : array) {
            if (pairs.containsKey(anArray)) {
                uniquePairs.put(anArray, pairs.get(anArray));
            }
        }

        return uniquePairs;
    }
}
