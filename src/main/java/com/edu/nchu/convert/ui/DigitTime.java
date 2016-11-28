package com.edu.nchu.convert.ui;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Created by Alen on 2016/11/28.
 */
public class DigitTime extends JFrame implements Runnable{

    public static  JLabel lblTime;
    public static void main(String[] args) {

        DigitTime cd = new DigitTime();
        lblTime = new JLabel();
        cd.setLayout(null);
        cd.setBounds(200, 200, 280, 100);
        lblTime.setBounds(0, 0, 280, 80);
        lblTime.setFont(new Font("Times New Roman",Font.BOLD,60));
        lblTime.setBackground(Color.WHITE);
        lblTime.setForeground(Color.red);
        cd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread t = new Thread(cd);
        t.start();
        cd.add(lblTime);
        cd.setTitle("数字时钟");
        cd.setVisible(true);
    }

    public void run() {

        while(true){
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String s = sdf.format(d);
            lblTime.setText(s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();   }
        }

    }
}