/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package com.mycompany.brackets;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class CheckBrackets {
    public static void main(String[] args) throws IOException {
//        InputStreamReader input_stream = new InputStreamReader(System.in);
//        BufferedReader reader = new BufferedReader(input_stream);
//        String text = reader.readLine();

            Scanner in = new Scanner(System.in);
            String text = in.nextLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            if (next != '(' && next != ')' && next != '{' && next != '}' &&
                    next != '[' && next != ']') {
                continue;
            }
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (opening_brackets_stack.isEmpty()) {
                    System.out.println(position + 1);
                    System.exit(0);
                }
                // Process closing bracket, write your code here
                Bracket newest = opening_brackets_stack.pop();
                if ( ! newest.Match(next)) {
                    System.out.println(position + 1);
                    System.exit(0);
                }
            }
        }

        // Printing answer, write your code here
        System.out.println(opening_brackets_stack.isEmpty() ? "Success" : opening_brackets_stack.peek().position + 1);
    }
}
