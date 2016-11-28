package com.edu.nchu.convert.ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Alen on 2016/11/28.
 */
public class ClockTime extends JFrame {
    private JPanel pan;
    private double r1 = 160, r2 = 165, rm1 = 158, rm2 = 167, hr = 100,
            mr = 130, sr = 167;

    public ClockTime() {
        super("Time demo");
        this.setBounds(200, 200, 410, 410);
        getMinLab();

        pan = new MyPanel();
        this.add(pan);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyThread th = new MyThread();
        th.setDaemon(true);
        th.start();

    }

    class MyPanel extends JPanel {
        // 实现表盘的绘制
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            Graphics2D g2 = (Graphics2D) g;

            BasicStroke bs = null;

            // 打开抗锯齿
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Line2D line[] = getMinLab();
            for (int i = 0; i < line.length; i++) {
                bs = new BasicStroke(2, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND);
                g2.setStroke(bs);
                if (i % 5 == 0) {
                    bs = new BasicStroke(4, BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND);
                    g2.setStroke(bs);

                }
                g2.draw(line[i]);
                // 画钟面上显示的数字
//               g2.drawString("12", xCenter - 6, yCenter - radius + 12);
//               g2.drawString("3", xCenter + radius - 12, yCenter + 4);
//               g2.drawString("6", xCenter - 4, yCenter + radius - 8);
//               g2.drawString("9", xCenter - radius + 4, yCenter + 6);

            }

            // 计算时针 虽然使用到一些过时的方法，但是在jdk6的环境下目前还可以使用
            Date date = new Date();
            int s = date.getSeconds();

            double m = date.getMinutes() + s / 60.0;

            double h = date.getHours() + m / 60.0;
            System.out.println(h);

            double hy = Math.sin(Math.PI / 6 * (h % 12 - 3)) * hr + 200;
            double hx = Math.cos(Math.PI / 6 * (h % 12 - 3)) * hr + 200;
            bs = new BasicStroke(6, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND);
            g2.setStroke(bs);
            Line2D.Double hline = new Line2D.Double(200, 200, hx, hy);
            g2.draw(hline);
            // 计算分针
            double my = Math.sin(Math.PI / 30 * (m - 15)) * mr + 200;
            double mx = Math.cos(Math.PI / 30 * (m - 15)) * mr + 200;
            bs = new BasicStroke(4, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND);
            g2.setStroke(bs);
            Line2D.Double mline = new Line2D.Double(200, 200, mx, my);
            g2.draw(mline);
            // 计算秒针
            double sy = Math.sin(Math.PI / 30 * (s - 15)) * sr + 200;
            double sx = Math.cos(Math.PI / 30 * (s - 15)) * sr + 200;
            bs = new BasicStroke(2, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND);
            g2.setStroke(bs);
            Line2D.Double sline = new Line2D.Double(200, 200, sx, sy);
            g2.draw(sline);

            // 画表盘

            Ellipse2D.Double el = new Ellipse2D.Double(10, 10, 360, 360);
            g2.draw(el);
        }
    }

    // 计算表盘上的刻度
    public Line2D[] getMinLab() {
        Line2D[] lineM = new Line2D[60];

        for (int i = 0; i < lineM.length; i++) {
            double x1, y1, x2, y2;
            double b1 = Math.sin(Math.PI / 30 * i);
            double b2 = Math.cos(Math.PI / 30 * i);
            if (i % 5 != 0) {
                y1 = b1 * r1 + 200;
                y2 = b1 * r2 + 200;
                x1 = b2 * r1 + 200;
                x2 = b2 * r2 + 200;
            } else {
                y1 = b1 * rm1 + 200;
                y2 = b1 * rm2 + 200;
                x1 = b2 * rm1 + 200;
                x2 = b2 * rm2 + 200;
            }

            lineM[i] = new Line2D.Double(x1, y1, x2, y2);

        }

        return lineM;
    }

    // 实现走动的线程
    class MyThread extends Thread {
        public void run() {
            while (true) {
                pan.repaint();
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 执行
        new ClockTime();
    }
}