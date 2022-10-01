package com.company;

import java.util.LinkedList;
import java.util.List;

public class MyStack {
    private final LinkedList<Integer> stack;

    public MyStack() {
        stack = new LinkedList<Integer>();
    }

    public void push(int x) {
        stack.addLast(x);
    }

    public int pop() {
        if (stack.size() == 0) {
            System.out.println("Стек пуст");
            return -1;
        } else {
            int temp = stack.getLast();
            stack.removeLast();
            return temp;
        }
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }

    public void print() {
        for (Integer integer : stack) {
            System.out.println(integer);
        }
    }

    public LinkedList<Integer> getStack() {
        LinkedList<Integer> temp = new LinkedList<Integer>();
        for (int i = 0; i < stack.size(); i++) {
            temp.addLast(stack.get(i));
        }
        return temp;
    }

    @Override
    public String toString() {
        String temp = "";
        for (Integer integer : stack) {
            temp += integer;
            temp += " ";
        }
        return temp;
    }
}
