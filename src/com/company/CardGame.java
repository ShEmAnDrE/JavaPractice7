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

    private final ArrayList<Integer> numbers = new ArrayList<Integer>();

    private void startGame() {
        int counter = 0;
        int firstCard = 0;
        int secondCard = 0;
        boolean winner = false;
        MyStack stackFirst = new MyStack();
        MyStack stackSecond = new MyStack();
        LinkedList<Integer> tempStack;

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < 5; i++) {
            stackFirst.push(numbers.remove((int) (Math.random() * (5 - i))));
        }

        for (int i = 0; i < 5; i++) {
            stackSecond.push(numbers.remove((int) (Math.random() * (5 - i))));
        }
        label1.setText(stackFirst.toString());
        label2.setText(stackSecond.toString());

        while (true) {
            if (counter == 106) {
                labelTable.setText("Botva");
                break;
            }
            if (stackFirst.size() == 0) {
                labelTable.setText("Second win");
                break;
            }
            if (stackSecond.size() == 0) {
                labelTable.setText("First win");
                break;
            }

            /*label1.setText(stackFirst.toString());
            label1.repaint(1000);
            label1.updateUI();

            label2.setText(stackSecond.toString());
            label2.repaint(1000);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            firstCard = stackFirst.pop();
            secondCard = stackSecond.pop();

            //labelTable.setText(firstCard + "   " + secondCard);
            //labelTable.repaint(1000);

            //label1.setText(stackFirst.toString());
            //label1.repaint(1000);

            //label2.setText(stackSecond.toString());
            //label2.repaint(1000);

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            if (firstCard > secondCard || (firstCard == 0 && secondCard == 9)) {
                //labelTable.setText(firstCard + " > " + secondCard);
                //labelTable.repaint(1000);
                winner = true;
            }
            if (secondCard > firstCard || (secondCard == 0 && firstCard == 9)) {
                //labelTable.setText(firstCard + " < " + secondCard);
                //labelTable.repaint(1000);
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
                //label1.repaint(1000);
            } else {
                tempStack = stackSecond.getStack();
                stackSecond.clear();
                stackSecond.push(secondCard);
                stackSecond.push(firstCard);
                while (tempStack.size() > 0) {
                    stackSecond.push(tempStack.pollFirst());
                }
                //label2.setText(stackSecond.toString());
                //label2.repaint(1000);
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

        label2Start = new JLabel("2-й игрок");
        label2Start.setFont(new Font("Serif",Font.PLAIN,25));
        label2Start.setBounds(450,100,450,100);
        label2Start.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label2Start);

        label1 = new JLabel();
        label1.setFont(new Font("Serif",Font.PLAIN,25));
        label1.setBounds(0,200,450,100);
        label1.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label1);

        label2 = new JLabel();
        label2.setFont(new Font("Serif",Font.PLAIN,25));
        label2.setBounds(450,200,450,100);
        label2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label2);

        labelTable = new JLabel();
        labelTable.setFont(new Font("Serif",Font.PLAIN,25));
        labelTable.setBounds(0,300,900,600);
        labelTable.setHorizontalAlignment(JTextField.CENTER);
        panel.add(labelTable);

        button.addActionListener(e -> {
            startGame();
        });
        setVisible(true);
    }
}
