package com.schrismillar.connect4.util;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ConsoleScanner {

    public int nextInt() {
        return scanner().nextInt();
    }

    public String next() {
        return scanner().next();
    }

    public String nextLine() {
        return scanner().nextLine();
    }

    private Scanner scanner() {
        return new Scanner(System.in);
    }
}
