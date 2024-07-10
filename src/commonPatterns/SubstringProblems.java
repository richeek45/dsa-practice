package commonPatterns;
// Given two strings s and t of lengths m and n respectively, return the minimum window substring
// of s such that every character in t (including duplicates) is included in the window.
// If there is no such substring, return the empty string "".
// s = "ADOBECODEBANC", t = "ABC"

import java.util.Scanner;

public class SubstringProblems {
    public static String minWindow(String s, String t) {


        return "the end";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a scanner object
        System.out.println("Enter first string: ");

        String s = sc.nextLine(); // Read user input
        System.out.println("s = " + s);

        String t = sc.nextLine();
        System.out.println("t = " + t);

        String sol = minWindow(s, t);
        System.out.println(sol);
    }
}
