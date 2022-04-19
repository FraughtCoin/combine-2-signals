/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Cristi
 */
public class Point {

    public int x;
    public int y;

    public Point(int amp, double angle) {
        this.x = amp * (int) Math.cos(angle);
        this.y = amp * (int) Math.sin(angle);
    }
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
