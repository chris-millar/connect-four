package com.schrismillar.connect4.util;

import java.util.Scanner;

public class ConsoleScanner {
    private final Scanner scanner;

    public ConsoleScanner() {
        scanner = new Scanner(System.in);
    }

    public int nextInt() {
        return scanner.nextInt();
    }
}
