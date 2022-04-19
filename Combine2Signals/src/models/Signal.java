/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
public class Signal {
    
    public Point point;
    public int amp;
    public double period;
    public double angle;
    
    public Signal(int amp, double angle, double period) {
        this.amp = amp;
        this.angle = angle;
        this.period = period;
        this.point = new Point(amp, angle);
    }
    
}
