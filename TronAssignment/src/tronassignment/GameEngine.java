/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronassignment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author cemal
 */
public class GameEngine extends JPanel {
    
    private HighScores hs;
    private final int FPS = 250;
    private final int BIKE_X = 40;
    private final int BIKE_Y = 150;
    private final int BIKE_WIDTH = 80;
    private final int BIKE_HEIGHT = 80;
    private final int BIKE_MOVEMENT = 2;
    
    private JLabel scoreLabel;
    
    String firstName;
    Color firstColor;
    String secondName;
    Color secondColor;

    
    
    
    private Motorbike motorbike1;
    private Motorbike motorbike2;
    private Player p1;
    private Player p2;
    private Timer newFrameTimer;
    
    
    private Image background;
    public GameEngine(String firstName,Color firstColor,String secondName,Color secondColor) {
        super();

        background = new ImageIcon("data/images/background.png").getImage();
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstColor = firstColor;
        this.secondColor = secondColor;

        
        scoreLabel = new JLabel( "Playing now: "+ firstName + " and " + secondName);
        
        try {   
            hs = new HighScores(10);
        } catch (SQLException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.getBike().setVelx(-BIKE_MOVEMENT);
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed A");
        this.getActionMap().put("pressed A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.getBike().setVelx(-BIKE_MOVEMENT);
            }
        });
        
        
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.getBike().setVelx(BIKE_MOVEMENT);
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed D");
        this.getActionMap().put("pressed D", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.getBike().setVelx(BIKE_MOVEMENT);
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.getBike().setVely(BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed S");
        this.getActionMap().put("pressed S", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.getBike().setVely(BIKE_MOVEMENT);
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.getBike().setVely(-BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed w");
        this.getActionMap().put("pressed w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.getBike().setVely(-BIKE_MOVEMENT);
            }
        });
        //timer
        
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }
        public HighScores getHs() {
            return hs;
        }
    
        public void restart() {

        Image first = new ImageIcon("data/images/first.jpg").getImage();
        motorbike1 = new Motorbike(BIKE_X, BIKE_Y, BIKE_WIDTH, BIKE_HEIGHT, first);
        p1 = new Player(firstName,firstColor,motorbike1);
        
        Image second = new ImageIcon("data/images/second.jpg").getImage();
        motorbike2 = new Motorbike(510,  BIKE_Y, BIKE_WIDTH, BIKE_HEIGHT, second);
        p2 = new Player(secondName,secondColor,motorbike2);
        }
        
        
        @Override
        protected void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs);
            grphcs.drawImage(background, 0, 0, 800, 600, null);
            p1.getBike().draw(grphcs);
            p2.getBike().draw(grphcs);
            p1.getBike().drawTrail(grphcs,firstColor);
            p2.getBike().drawTrail(grphcs,secondColor);
            
        }
        
        public JLabel getTurnLabel() {
            return scoreLabel;
        }

    private HighScores HighScores(int score) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            p1.getBike().move();
            p2.getBike().move();
            int score = 1;
            ArrayList<HighScore> scores = new ArrayList<>();
            try {
                scores = hs.getHighScores();
            } catch (SQLException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (p1.collapseTrail(p2) || p2.collapseTrail(p1) || p1.outOfArena() || p2.outOfArena()) {
                if(p1.collapseTrail(p2) || p2.outOfArena() )
                {
                    JOptionPane.showMessageDialog(null,firstName + " has won.Congrats!");
                    boolean name_exist = false;
                    try{
                        for(int i=0;i<scores.size();++i){
                            if(firstName.equals(scores.get(i).getName())){
                                name_exist = true;
                                break;
                            }
                        }
                        if(name_exist){
                            hs.increaseScore(firstName);
                        }else{
                            hs.putHighScore(firstName, 1);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(p2.collapseTrail(p1) || p1.outOfArena()){
                    
                    boolean name_exist = false;
                    try{
                        for(int i=0;i<scores.size();++i){
                            if(secondName.equals(scores.get(i).getName())){
                                name_exist = true;
                            }
                        }
                        if(name_exist){
                            hs.increaseScore(secondName);
                        }else{
                            hs.putHighScore(secondName, 1);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    JOptionPane.showMessageDialog(null,secondName + " has won.Congrats!");
                }
                restart();
            }
           
            repaint();
        }

    }
    

}
