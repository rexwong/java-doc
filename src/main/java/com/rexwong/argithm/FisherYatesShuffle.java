package com.rexwong.argithm;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Fisher–Yates shuffle
 * <code>
 * -- To shuffle an array a of n elements (indices 0..n-1):
 * for i from n−1 downto 1 do
 * j ← random integer such that 0 ≤ j ≤ i
 * exchange a[j] and a[i]
 * </code>
 * <p>
 * https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
 * <p>
 * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 *
 * @author rexwong
 */
public class FisherYatesShuffle {

    public static void main(String args[]) {
        int[] solutionArray = {1, 2, 3, 4, 5, 6, 16, 15, 14, 13, 12, 11};

        shuffleArray(solutionArray);
        for (int i = 0; i < solutionArray.length; i++) {
            System.out.print(solutionArray[i] + " ");
        }
        System.out.println();
    }

    /**
     * Implementing Fisher–Yates shuffle
     */
    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
