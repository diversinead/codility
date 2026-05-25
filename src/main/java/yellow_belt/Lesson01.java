package main.java.yellow_belt;

/*******************************************************************************
 *
 *  GOAL: Find longest sequence of zeros in binary representation of an integer
 *
 *  STEPS
 *  1.	Find the binary representation of the number.
 *  2.	Remove any trailing zeros
 *  3.	Split binary string by ‘1’ & create an array of zero strings
 *  4.	Loop thru String[] & find longest string of zeros
 *  5.	Return max.
 *
 *  KEY NOTES
 *  Integer.toBinaryString(n); returns binary string (does not have leading zeros)
 *
 *  To remove leading zeros:
 *  binary.replaceAll("^0+", "");
 *
 *  To remove both trailing zeros:
 *  binary.replaceAll("0+$", "");
 *
 *  To remove both leading & trailing zeros:
 *  binary.replaceAll("^0+|0+$", "");
 */

public class Lesson01 {

    public static int binaryGap(int n) {
        String binary = Integer.toBinaryString(n);

        // remove trailing zeros & split binary string by '1'
        String[] gaps = binary.replaceAll("0+$", "").split("1");

        int max = 0;
        for (String gap : gaps) {
            max = Math.max(max, gap.length());
        }
        return max;
    }
}
