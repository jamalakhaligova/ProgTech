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
import javax.swing.ImageIcon;
import javax.swing.JPanel;




/**
 *
 * @author Jamala
 */
public class Player{
    
    private String name;
    private Integer score;
    private Color color; 
    private Motorbike bike;
    
    
    Player(String name,Color color,Motorbike newbike)
    {
        this.name = name;
        this.color = color;
        this.score = 0;
        this.bike = newbike;
    }
    
    
    public String getName(){
        if(this.name != ""){
            return this.name;
        }
        return "noname";
    }
    
    public void setScore(Integer sc){
        this.score = sc;
    }
    
    public Integer getScore(){
        return this.score;
    }

    
    public Motorbike getBike(){
        return this.bike;
    }
    
    public boolean collapseTrail(Player other_player){
        
        boolean crossedTrail = false;
        ArrayList<Trail> tr1 = this.getBike().getTrail();
         
        for(int i=0;i<tr1.size();i++)
        {
            crossedTrail = (tr1.get(i).collides(other_player.getBike().getX(), other_player.getBike().getY()));
            if (crossedTrail){
                break;
            }
        }
        
        return crossedTrail; 
    }
    
    public boolean outOfArena(){
        boolean outofBound = (this.getBike().getX()>720 || this.getBike().getY()>520 || this.getBike().getX()<0 || this.getBike().getY()<0);
        return outofBound;
    }
    
    
    
    
}
