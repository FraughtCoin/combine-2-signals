/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccomponents;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import models.Point;
import models.Signal;

/**
 *
 * @author Cristi
 */
public class CustomCanvas extends Canvas implements Runnable {

    private Signal s1;
    private Signal s2;
    private Signal s;
    public int xSize;
    public int ySize;
    public ArrayList<Point> list;

    public CustomCanvas(int x, int y) {
        this.xSize = x;
        this.ySize = y;
        this.s = new Signal(0, 0, 0);
        this.list = new ArrayList<Point>();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
//        g.drawLine(xSize / 2, 0, xSize / 2, ySize);
//        g.drawLine(0, ySize / 2, xSize, ySize / 2);
//        g.drawLine(xSize / 2, ySize / 2, xSize / 2 + s.point.x, ySize / 2 + s.point.y);
        int p1x = xSize / 2 + s1.point.x;
        int p1y = ySize / 2 + s1.point.y;
        g.fillOval(p1x, p1y, 5, 5);

        int p2x = xSize / 2 + s2.point.x;
        int p2y = ySize / 2 + s2.point.y;
        g.fillOval(p2x, p2y, 5, 5);

        int px = xSize / 2 + s.point.x;
        int py = ySize / 2 + s.point.y;
        g.fillOval(px, py, 8, 8);
        g.drawLine(p1x, p1y, px, py);
        g.drawLine(p2x, p2y, px, py);

        g.setColor(Color.red);
        Point prevPoint = null;
        if (!list.isEmpty()) {
            prevPoint = list.get(0);
        }
        for (Point p : list) {
            if (prevPoint != null) {
                g.drawLine(p.x + xSize / 2, p.y + ySize / 2, prevPoint.x + xSize / 2, prevPoint.y + ySize / 2);
                prevPoint = p;
            }
        }
    }

    @Override
    public void run() {
        double dt = 0.01;
        while (true) {
            s1.angle = s1.angle + 1 / s1.period * 2 * Math.PI * dt;
//            if (s1.angle > 2 * Math.PI) {
//                s1.angle = s1.angle - 2 * Math.PI;
//            }
            s1.point.x = (int) (s1.amp * Math.cos(s1.angle));
            s1.point.y = (int) (s1.amp * Math.sin(s1.angle));

            s2.angle = s2.angle + 1 / s2.period * 2 * Math.PI * dt;
//            if (s2.angle > 2 * Math.PI) {
//                s2.angle = s2.angle - 2 * Math.PI;
//            }
            s2.point.x = (int) (s2.amp * Math.cos(s2.angle));
            s2.point.y = (int) (s2.amp * Math.sin(s2.angle));

            s.amp = (int) Math.sqrt(Math.pow(s1.amp, 2) + Math.pow(s2.amp, 2) + 2 * s1.amp * s2.amp * Math.cos(s1.angle - s2.angle));
            s.angle = (s1.angle + s2.angle) / 2;
//            if (s.angle > 2 * Math.PI) {
//                s.angle = s.angle - 2 * Math.PI;
//            }
            s.point.x = (int) (s.amp * Math.cos(s.angle));
            s.point.y = (int) (s.amp * Math.sin(s.angle));
            Point p = new Point(s.point.x, s.point.y);
//            System.out.println(p.x+" "+p.y);
            if (list.size() < 420) {
                list.add(p);
            }
            try {
                repaint();
//                System.out.println(s1.point.x + " " + s1.point.y);
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void setS1(Signal s1) {
        this.s1 = s1;
//        System.out.println(this.s1.point.x);
    }

    public void setS2(Signal s2) {
        this.s2 = s2;
    }

}
