/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronassignment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author cemal
 */
public class Trail {
    private int x, y;
    Rectangle rec;

    /**
     * Initializes (x, y) plane coordinates of position
     * @param x
     * @param y
     */
    public Trail(int x, int y) {
        this.x = x;
        this.y = y;
        rec = new Rectangle (x,y);
    }

    /**
     * Getters and Setters
     */
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public boolean collides(int other_X,int other_Y) {
        Rectangle rect = new Rectangle(x, y, 20, 20);
        Rectangle otherRect = new Rectangle(other_X, other_Y, 80, 80);        
        return rect.intersects(otherRect);
    }
}
