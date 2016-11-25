package com.edu.nchu.convert.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Border布局的小样 Created by fujianjian on 2016/11/25.
 */
public class BorderLyoutDemo extends JFrame {

    private JPanel  contentPane;
    private JButton westButton;
    private JButton southButton;
    private JButton eastButton;
    private JButton northButton;
    private JButton centreButton;

    public BorderLyoutDemo(){
        this.contentPane=new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.contentPane.setLayout(new BorderLayout(0,0));
        this.setContentPane(contentPane);
        this.westButton=new JButton("西部");
        this.southButton=new JButton("南部");
        this.eastButton=new JButton("东部");
        this.northButton=new JButton("北部");
        this.centreButton=new JButton("中部");
        this.contentPane.add(westButton,BorderLayout.WEST);
        this.contentPane.add(southButton,BorderLayout.SOUTH);
        this.contentPane.add(northButton,BorderLayout.NORTH);
        this.contentPane.add(eastButton,BorderLayout.EAST);
        this.contentPane.add(centreButton,BorderLayout.CENTER);

        this.setTitle("边界布局");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 450, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        BorderLyoutDemo example = new BorderLyoutDemo();
    }
}
