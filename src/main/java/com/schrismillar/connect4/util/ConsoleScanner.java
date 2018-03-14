package com.schrismillar.connect4.util;

import java.util.Scanner;

public class ConsoleScanner {

    public ConsoleScanner() {
    }

    public int nextInt() {
        return scanner().nextInt();
    }

    public String next() {
        return scanner().next();
    }

    private Scanner scanner() {
        return new Scanner(System.in);
    }
}
