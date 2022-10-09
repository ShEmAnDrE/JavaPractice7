package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CardGame extends JFrame {
    private JPanel panel;
    private JButton button;
    private JLabel label1Start;
    private JLabel label1;
    private JLabel labelTable;
    private JLabel label2Start;
    private JLabel label2;
    int firstCard = 0;
    int secondCard = 0;
    boolean winner = false;

    private final ArrayList<Integer> numbers = new ArrayList<Integer>();

    private void startGame() {
        int counter = 0;
        MyStack stackFirst = new MyStack();
        MyStack stackSecond = new MyStack();
        LinkedList<Integer> tempStack;

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < 5; i++) {
            stackSecond.push(numbers.remove((int) (Math.random() * (5 - i))));
        }

        for (int i = 0; i < 5; i++) {
            stackFirst.push(numbers.remove((int) (Math.random() * (5 - i))));
        }

        label1.setText(stackFirst.toString());
        label2.setText(stackSecond.toString());

        while (true) {

            if (counter == 106) {
                labelTable.setText("Botva : " + counter);
                break;
            }
            if (stackFirst.size() == 0) {
                labelTable.setText("Second win : Step = " + counter + ", Cards : " + stackSecond);
                break;
            }
            if (stackSecond.size() == 0) {
                labelTable.setText("First win : Step = " + counter + ", Cards : " + stackFirst);
                break;
            }

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            firstCard = stackFirst.pop();
            secondCard = stackSecond.pop();
            /*labelTable.setText(firstCard + "   " + secondCard);
            Label1.setText(stackFirst.toString());
            label2.setText(stackSecond.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            if ((firstCard == 0 && secondCard == 9) || (firstCard > secondCard && firstCard != 9 && secondCard != 0)) {
                //labelTable.setText(firstCard + " > " + secondCard);
                winner = true;
            }
            if ( (secondCard == 0 && firstCard == 9) || (secondCard > firstCard && secondCard != 9 && firstCard != 0)) {
                //labelTable.setText(firstCard + " < " + secondCard);
                winner = false;
            }

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            if (winner) {
                tempStack = stackFirst.getStack();
                stackFirst.clear();
                stackFirst.push(secondCard);
                stackFirst.push(firstCard);
                while (tempStack.size() > 0) {
                    stackFirst.push(tempStack.pollFirst());
                }
                //label1.setText(stackFirst.toString());
            } else {
                tempStack = stackSecond.getStack();
                stackSecond.clear();
                stackSecond.push(secondCard);
                stackSecond.push(firstCard);
                while (tempStack.size() > 0) {
                    stackSecond.push(tempStack.pollFirst());
                }
                //label2.setText(stackSecond.toString());
            }
            counter++;
        }
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

        label1Start = new JLabel("1-й игрок");
        label1Start.setFont(new Font("Serif",Font.PLAIN,25));
        label1Start.setBounds(0,100,450,100);
        label1Start.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label1Start);

        label1 = new JLabel();
        label1.setFont(new Font("Serif",Font.PLAIN,25));
        label1.setBounds(0,200,450,100);
        label1.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label1);

        labelTable = new JLabel("Стол");
        labelTable.setFont(new Font("Serif",Font.PLAIN,25));
        labelTable.setBounds(0,300,900,400);
        labelTable.setHorizontalAlignment(JTextField.CENTER);
        panel.add(labelTable, BorderLayout.SOUTH);

        label2Start = new JLabel("2-й игрок");
        label2Start.setFont(new Font("Serif",Font.PLAIN,25));
        label2Start.setBounds(450,100,450,100);
        label2Start.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label2Start);

        label2 = new JLabel();
        label2.setFont(new Font("Serif",Font.PLAIN,25));
        label2.setBounds(450,200,450,100);
        label2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label2);
        setVisible(true);

        button.addActionListener(e -> {
            startGame();
        });
    }
}
