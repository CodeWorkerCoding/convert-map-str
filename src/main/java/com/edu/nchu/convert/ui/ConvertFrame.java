package com.edu.nchu.convert.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 转换工具
 * Created by fujianjian on 2016/11/23.
 */
public class ConvertFrame extends JFrame {
    JPanel content = new JPanel();
    JButton transfer = new JButton("装换");
    JTextArea source = new JTextArea();
    JTextArea target = new JTextArea();

    public ConvertFrame() {
        setTitle("Map字符串转Json格式");
//        setSize(300, 400);
        this.setBounds(100, 100, 400, 300);
        content.setBorder(new EmptyBorder(5,5,5,5));
        content.setLayout(new BorderLayout(0,0));
        content.add(source, BorderLayout.WEST);
        content.add(target, BorderLayout.EAST);
        content.add(transfer, BorderLayout.CENTER);
        this.add(content);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ConvertFrame();
    }
}
