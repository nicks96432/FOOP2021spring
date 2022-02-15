package utils;

import java.util.Scanner;

/**
 * I recommend to use the same Scanner instance throughout the program to avoid
 * some I/O buffering issue. (e.g., `java.util.NoSuchElementException` thrown
 * during `Scanner`'s `nextInt()` or `nextLine()`)
 * 
 * @author - johnny850807@gmail.com (Waterball)
 */
public final class Inputs {
    public final static Scanner in = new Scanner(System.in);

    /**
     * 讀一個在範圍內的整數，如果讀到的不是就讀到是為止
     * 
     * @param startInclusive 要大於等於的數字
     * @param endExclusive   要小於的數字
     * @return 讀到的數字
     */
    public final static int nextIntRange(int startInclusive, int endExclusive) {
        while (true) {
            int result = in.nextInt();
            if (result >= startInclusive && result < endExclusive)
                return result;
        }

    }
}
