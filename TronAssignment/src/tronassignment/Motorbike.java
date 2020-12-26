/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronassignment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author cemal
 */
public class Motorbike extends Sprite {
    
    private double velx;
    private double vely;
    private ArrayList<Trail> pos = new ArrayList<>();
    private Trail tr;
    
    public Motorbike(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
    
    
    public void move() {
        if ((velx < 0 && x >= 0) || (velx > 0 && x + width <= 800)) {
            x += velx;
        }
        
        if ((vely < 0 && y >= 0) || (vely > 0 && y + height <= 600)) {
            y += vely;
        }
        shift(x,y);
    }
    
    public void drawTrail(Graphics g,Color c) {
        for(int i=0; i<pos.size(); i++) {
            g.setColor(c);
            g.fillRect(pos.get(i).getX(),pos.get(i).getY(),20,20);
        }
    }

    public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
        this.vely = 0;
    }
    
    public double getVely() {
        return vely;
    }

    public void setVely(double vely) {
        this.vely = vely;
        this.velx = 0;
    }
    
    public void shift(int x,int y){
        tr = new Trail(x,y);
        pos.add(tr);
        
    }
    public ArrayList<Trail> getTrail(){
        return this.pos;
    }
   
    
    
}
