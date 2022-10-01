package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CardGame extends JFrame {
    private JPanel panel;
    private JButton button;
    private JLabel Label1;
    private JLabel labelTable;
    private JLabel label2;
    int counter = 0;
    int firstCard = 0;
    int secondCard = 0;
    boolean winner = false;
    MyStack stackFirst = new MyStack();
    MyStack stackSecond = new MyStack();
    LinkedList<Integer> tempStack;
    private final ArrayList<Integer> numbers = new ArrayList<Integer>();

    private boolean startGame() {

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < 5; i++) {
            stackFirst.push(numbers.remove((int) (Math.random() * (5 - i))));
        }

        for (int i = 0; i < 5; i++) {
            stackSecond.push(numbers.remove((int) (Math.random() * (5 - i))));
        }

        if (counter == 106) {
            labelTable.setText("Botva");
            return false;
        }
        if (stackFirst.size() == 0) {
            labelTable.setText("Second win");
            return false;
        }
        if (stackSecond.size() == 0) {
            labelTable.setText("First win");
            return false;
        }

        Label1.setText(stackFirst.toString());
        label2.setText(stackSecond.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstCard = stackFirst.pop();
        secondCard = stackSecond.pop();
        labelTable.setText(firstCard + "   " + secondCard);
        Label1.setText(stackFirst.toString());
        label2.setText(stackSecond.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (firstCard > secondCard || (firstCard == 0 && secondCard == 9)) {
            labelTable.setText(firstCard + " > " + secondCard);
            winner = true;
        }
        if (secondCard > firstCard || (secondCard == 0 && firstCard == 9)) {
            labelTable.setText(firstCard + " < " + secondCard);
            winner = false;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (winner) {
            tempStack = stackFirst.getStack();
            stackFirst.clear();
            stackFirst.push(secondCard);
            stackFirst.push(firstCard);
            while (tempStack.size() > 0) {
                stackFirst.push(tempStack.pollFirst());
            }
            Label1.setText(stackFirst.toString());
        } else {
            tempStack = stackSecond.getStack();
            stackSecond.clear();
            stackSecond.push(secondCard);
            stackSecond.push(firstCard);
            while (tempStack.size() > 0) {
                stackSecond.push(tempStack.pollFirst());
            }
            label2.setText(stackSecond.toString());
        }
        counter++;
    }

    public CardGame() {
        super ("Card Game");
        setSize(900, 700);
        panel = new JPanel();
        setContentPane(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel.setLayout(null);

        button = new JButton("Начать");
        button.setFont(new Font("Serif",Font.PLAIN,25));
        panel.add(button);
        button.setBounds(0,0,900,100);

        Label1 = new JLabel("1-й игрок");
        Label1.setFont(new Font("Serif",Font.PLAIN,25));
        Label1.setBounds(0,100,350,600);
        Label1.setHorizontalAlignment(JTextField.CENTER);
        panel.add(Label1, BorderLayout.WEST);

        labelTable = new JLabel("Стол");
        labelTable.setFont(new Font("Serif",Font.PLAIN,25));
        labelTable.setBounds(350,100,200,600);
        labelTable.setHorizontalAlignment(JTextField.CENTER);
        panel.add(labelTable, BorderLayout.SOUTH);

        label2 = new JLabel("2-й игрок");
        label2.setFont(new Font("Serif",Font.PLAIN,25));
        label2.setBounds(550,100,350,600);
        label2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label2, BorderLayout.CENTER);
        setVisible(true);

        button.addActionListener(e -> {
            startGame();
        });
    }
}
