/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;



/**
 *
 * @author jamala
 */
public class TronGUI {
    private String[]  cols = {"black","white","gray","red","green","blue","yellow","magenta","cyan"};
    private JFrame frame;
    private GameEngine gameArea;    

    public TronGUI() {
        frame = new JFrame("Tron");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //name color

        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenuItem menuHighScore = new JMenuItem("HighScore Table");
        JMenuItem menuRestart = new JMenuItem(new AbstractAction("Restart") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.restart();
            }
        });
        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        menuGame.add(menuGameExit);
        menuGame.add(menuRestart);
        menuBar.add(menuGame);
        frame.setJMenuBar(menuBar);
        
        menuHighScore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg) {
                JFrame tableFrame = new JFrame();
                int size = 0;
                try {
                    size = gameArea.getHs().getHighScores().size();
                } catch (SQLException ex) {
                    Logger.getLogger(TronGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableFrame.setTitle("High Scores");
                String[] header = {"Name", "Score"};
                String[][] body = new String[size][2];
                
                try {
                for (int i = 0; i < size; i++) {
                    body[i][0] = gameArea.getHs().getHighScores().get(i).getName();
                    body[i][1] = Integer.toString(gameArea.getHs().getHighScores().get(i).getScore());
                }
                } catch (SQLException ex) {
                    Logger.getLogger(TronGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                //Creation of table and Scroll object
                JTable table = new JTable(body, header);
                JScrollPane sp = new JScrollPane(table);
                tableFrame.add(sp);
                tableFrame.setSize(625, 300);
                tableFrame.setVisible(true);
                
            }
        });
        
        menuGame.add(menuHighScore);
        //ask first player name and color
        String firstPlayerName;
        firstPlayerName = JOptionPane.showInputDialog("What is first player name? ");
        String fCol = (String)JOptionPane.showInputDialog(null, "Pick a color for first player:", 
                "Choose color", JOptionPane.QUESTION_MESSAGE, null, cols, cols[3]);
        Color firstColor;
        try {
            Field field = Class.forName("java.awt.Color").getField(fCol);
            firstColor = (Color)field.get(null);
        } catch (Exception e) {
            firstColor = Color.RED; // Not defined
        }
        
        
        //ask second player name and color
        String secondPlayerName;
        secondPlayerName = JOptionPane.showInputDialog("What is second player name? ");

        String sCol = (String)JOptionPane.showInputDialog(null, "Pick a color for second player:", 
                "Choose color", JOptionPane.QUESTION_MESSAGE, null, cols, cols[2]);
        Color secondColor;
        try {
            Field field = Class.forName("java.awt.Color").getField(sCol);
            secondColor = (Color)field.get(null);
        } catch (Exception e) {
            secondColor = Color.BLUE; // Not defined
        }

 
        
        gameArea = new GameEngine(firstPlayerName,firstColor,secondPlayerName,secondColor);
        frame.getContentPane().add(gameArea);
        frame.getContentPane().add(gameArea.getTurnLabel(), BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    


}

   
