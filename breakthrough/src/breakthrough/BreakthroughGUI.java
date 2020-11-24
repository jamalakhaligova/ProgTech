/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakthrough;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BreakthroughGUI {
     private JPanel Controllers;
     private JPanel FieldPanel;
     private JPanel field[][];
     private JFrame game;
     private JFrame menu;
     private JLabel black;
     private JLabel white;
     private JLabel turn;
     private int currentX;
     private int currentY;
     private int cntBlack;
     private int cntWhite;
     private int boardSize;
     private Boolean moveWhite = true;
     private Boolean moveBlack = false;
     
     public BreakthroughGUI() {
         start();
     }
     
     public void start(){
         menu = new JFrame("Select the Board Size");
         menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         menu.setPreferredSize(new Dimension(300, 200));
         menu.setLocation(530, 280);
         
         JPanel layout = new JPanel();
         layout.setLayout(new GridLayout(3,1));
         menu.add(layout);
         
         JButton size1 = new JButton("6*6");
         JButton size2 = new JButton("8*8");
         JButton size3 = new JButton("10*10");
         
         size1.addActionListener(new ControllersAl("6"));
         size2.addActionListener(new ControllersAl("8"));
         size3.addActionListener(new ControllersAl("10"));
         
         layout.add(size1);
         layout.add(size2);
         layout.add(size3);
          
         menu.getContentPane().add(BorderLayout.CENTER, layout);
         menu.pack();
         menu.setVisible(true);
     }
     
     public void make(){         
         game = new JFrame ("Breakthrough");
         game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         game.setPreferredSize(new Dimension(695, 695));
         Dimension position = Toolkit.getDefaultToolkit().getScreenSize();
         int positionX = Math.max(0, (position.width  - 695)/2);
         int positionY = Math.max(0, (position.height - 695)/2);
         game.setLocation(positionX, positionY);
        
         FieldPanel = new JPanel();
         field = new JPanel[boardSize][boardSize];
         FieldPanel.setLayout(new GridLayout(boardSize, boardSize));
         
         Controllers = new JPanel();
         black = new JLabel("Black points: 0");
         JButton downButton = new JButton("Left");
         JButton upButton = new JButton("Forward");
         JButton rightButton = new JButton("Right");
         white = new JLabel("White points: 0");
         turn = new JLabel("          Now  moving: WHITE");
         
         Controllers.add(black, BorderLayout.CENTER);
         Controllers.add(downButton, BorderLayout.CENTER);
         Controllers.add(upButton, BorderLayout.CENTER);
         Controllers.add(rightButton, BorderLayout.CENTER);
         Controllers.add(white, BorderLayout.CENTER);
         Controllers.add(turn, BorderLayout.CENTER);
         
         downButton.addActionListener(new ControllersAl("Left"));
         upButton.addActionListener(new ControllersAl("Forward"));
         rightButton.addActionListener(new ControllersAl("Right"));
         
         cntWhite = 0;
         cntBlack = 0;
         
         //creating fields
         for(int i=0; i<boardSize; ++i){
             for(int j=0; j<boardSize; ++j){
                 JPanel board = new JPanel();
                 board.setBorder(BorderFactory.createLineBorder(Color.PINK));
                 field[i][j] = board;
                 FieldPanel.add(field[i][j]);
             }
         }
         
         // placing the dolls on the board
         for(int i=0; i<2; ++i){
             for(int j=0; j<boardSize; ++j){
                 JButton black_button = new JButton("Black");
                 black_button.setBackground(Color.black);
                 black_button.addActionListener(new BreakthroughActionListener(i,j));
                 field[i][j].add(black_button, BorderLayout.CENTER);
             }
         }
         
         for(int i=boardSize-2; i<boardSize; ++i){
             for(int j=0; j<boardSize; j++){
                 JButton white_button = new JButton("White");
                 white_button.setBackground(Color.white);
                 white_button.addActionListener(new BreakthroughActionListener(i,j));
                 field[i][j].add(white_button, BorderLayout.CENTER);
             }
         }
     }
     
     public void displayGame(){
         game.getContentPane().add(BorderLayout.SOUTH, Controllers);
         game.getContentPane().add(BorderLayout.CENTER, FieldPanel);
         game.pack();
         game.setVisible(true);
     }
     
     public void move(int fromX, int fromY ,int toX, int toY){
         if(field[toX][toY].getComponentCount() == 0){
             JButton jb = (JButton) field[fromX][fromY].getComponent(0);
             field[fromX][fromY].remove(0);
             field[toX][toY].add(jb);
             
             ActionListener[] listeners = jb.getActionListeners();
             for(ActionListener listener : listeners){
                 jb.removeActionListener(listener);
             }
             jb.addActionListener(new BreakthroughActionListener(toX,toY));
             
             game.repaint();
             
             for(int b=0; b<boardSize; ++b){
                 if(!(field[0][b].getComponentCount() == 0)){
                     if(field[0][b].getComponent(0).getBackground() == Color.WHITE){
                         game.dispose();
                         JOptionPane.showMessageDialog(game, "The winner is: White\n" + "The score is: " + cntWhite,  "End", JOptionPane.INFORMATION_MESSAGE);
                         start();
                     }
                 }
             }
             for(int c=0; c<boardSize; ++c){
                 if(!(field[boardSize-1][c].getComponentCount() == 0)){
                     if(field[boardSize-1][c].getComponent(0).getBackground() == Color.BLACK){
                         game.dispose();
                         JOptionPane.showMessageDialog(game, "The winner is: Black\n" + "The score is: " + cntBlack,  "End", JOptionPane.INFORMATION_MESSAGE);
                         start();
                     }
                 }
             }
             
             if(cntWhite == (2*boardSize)){
                 game.dispose();
                 JOptionPane.showMessageDialog(game, "The winner is: White\n" + "The score is: " + cntWhite,  "End", JOptionPane.INFORMATION_MESSAGE);
                 start();
             }
             if(cntBlack == (2*boardSize)) {
                 game.dispose();
                 JOptionPane.showMessageDialog(game, "The winner is: Black\n" + "The score is: " + cntBlack,  "End", JOptionPane.INFORMATION_MESSAGE);
                 start();
             }
         } else if((field[fromX][fromY].getComponent(0).getBackground() == Color.WHITE) && (field[toX][toY].getComponent(0).getBackground() == Color.BLACK)) {
             cntWhite++;
             white.setText("White score: " + cntWhite);
             
             field[toX][toY].removeAll();
             JButton jb = (JButton) field[fromX][fromY].getComponent(0);
             
             field[fromX][fromY].remove(0);
             field[toX][toY].add(jb);
             
             ActionListener[] listeners = jb.getActionListeners();
             for(ActionListener listener : listeners){
                 jb.removeActionListener(listener);
             }
             jb.addActionListener(new BreakthroughActionListener(toX,toY));
             
             game.repaint();
             
             for(int b=0; b<boardSize; ++b) {
                 if(!(field[0][b].getComponentCount() == 0)){
                     if(field[0][b].getComponent(0).getBackground() == Color.WHITE){
                         game.dispose();
                         JOptionPane.showMessageDialog(game, "The winner is: White\n" + "The score is: " + cntWhite,  "End", JOptionPane.INFORMATION_MESSAGE);
                         start();
                     }
                 }
             }
             for(int c=0; c<boardSize; ++c){
                 if(!(field[boardSize-1][c].getComponentCount() == 0)){
                     if(field[boardSize-1][c].getComponent(0).getBackground() == Color.BLACK){
                         game.dispose();
                         JOptionPane.showMessageDialog(game, "The winner is: Black\n" + "The score is: " + cntBlack,  "End", JOptionPane.INFORMATION_MESSAGE);
                         start();
                     }
                 }
             }
             
             if(cntWhite == (2*boardSize)){
                 game.dispose();
                 JOptionPane.showMessageDialog(game, "The winner is: White\n" + "The score is: " + cntWhite,  "End", JOptionPane.INFORMATION_MESSAGE);
                 start();
             }
             if(cntBlack == (2*boardSize)){
                 game.dispose();
                 JOptionPane.showMessageDialog(game, "The winner is: Black\n" + "The score is: " + cntBlack,  "End", JOptionPane.INFORMATION_MESSAGE);
                 start();
             }
         } 
         else if((field[fromX][fromY].getComponent(0).getBackground() == Color.BLACK) && (field[toX][toY].getComponent(0).getBackground() == Color.WHITE)){
             cntBlack++;
             black.setText("Black score: " + cntBlack);
             
             field[toX][toY].removeAll();
             JButton jb = (JButton) field[fromX][fromY].getComponent(0);
             
             field[fromX][fromY].remove(0);
             field[toX][toY].add(jb);
             
             ActionListener[] listeners = jb.getActionListeners();
             for(ActionListener listener : listeners){
                 jb.removeActionListener(listener);
             }
             jb.addActionListener(new BreakthroughActionListener(toX,toY));
             
             game.repaint();
             
             for(int b=0; b<boardSize; ++b){
                 if(!(field[0][b].getComponentCount() == 0)){
                     if(field[0][b].getComponent(0).getBackground() == Color.WHITE){
                         game.dispose();
                         JOptionPane.showMessageDialog(game, "The winner is: White\n" + "The score is: " + cntWhite,  "End", JOptionPane.INFORMATION_MESSAGE);
                         start();
                     }
                 }
             }
             for(int c=0; c<boardSize; ++c){
                 if(!(field[boardSize-1][c].getComponentCount() == 0)){
                 if(field[boardSize-1][c].getComponent(0).getBackground() == Color.BLACK){
                 game.dispose();
                 JOptionPane.showMessageDialog(game, "The winner is: Black\n" + "The score is: " + cntBlack,  "End", JOptionPane.INFORMATION_MESSAGE);
                 start();
                 }
             }
         }
             if(cntWhite == (2*boardSize)){
                 game.dispose();
                 JOptionPane.showMessageDialog(game, "The winner is: White\n" + "The score is: " + cntWhite,  "End", JOptionPane.INFORMATION_MESSAGE);
                 start();
             }
             if(cntBlack == (2*boardSize)){
                 game.dispose();
                 JOptionPane.showMessageDialog(game, "The winner is: Black\n" + "The score is: " + cntBlack,  "End", JOptionPane.INFORMATION_MESSAGE);
                 start();
             }
         }
     }
     
     public void moveForward(String player){
         if(player == "BLACK"){
             if(field[currentX][currentY].getComponent(0).getBackground() == Color.BLACK){
                 int newX = currentX+1;
                 move(currentX, currentY, newX, currentY);
             }
         } else if(player == "WHITE"){
             if(field[currentX][currentY].getComponent(0).getBackground() == Color.WHITE){
                 int newX = currentX-1;
                 move(currentX, currentY, newX, currentY);
             }   
         }
     }
     
     public void moveLeft(String player){
         if(player == "BLACK"){
             if(field[currentX][currentY].getComponent(0).getBackground() == Color.BLACK){
                 if(currentY != 0) {
                     int newX = currentX+1;
                     int newY = currentY-1;
                     move(currentX, currentY, newX, newY);
                 }
             }
         } else if(player == "WHITE"){
             if(field[currentX][currentY].getComponent(0).getBackground() == Color.WHITE){
                 if(currentY != 0) {
                     int newX = currentX-1;
                     int newY = currentY-1;
                     move(currentX, currentY, newX, newY);
                 }
             }
         }
     }
     
     public void moveRight(String player){
         if(player == "BLACK"){
             if(field[currentX][currentY].getComponent(0).getBackground() == Color.BLACK){
                 if(currentY != boardSize-1){
                     int newX = currentX+1;
                     int newY = currentY+1;
                     move(currentX, currentY, newX, newY);
                 }
             }
         }
         else if(player == "WHITE"){
             if(field[currentX][currentY].getComponent(0).getBackground() == Color.WHITE){
                 if(currentY != boardSize-1){
                     int newX = currentX-1;
                     int newY = currentY+1;
                     move(currentX, currentY, newX, newY);
                 }
             }
         }
     }
     
     class BreakthroughActionListener implements ActionListener {
         private int x, y;
         
         public BreakthroughActionListener(int x, int y){
             this.x = x;
             this.y = y;
         }
         
         @Override
         public void actionPerformed(ActionEvent event){
             currentX = this.x;
             currentY = this.y;
         }
     }
     
     class ControllersAl implements ActionListener {
         private String option;
         
         public ControllersAl(String option) {
             this.option = option;
         }
         
         // the menu
         @Override
         public void actionPerformed(ActionEvent event) {
             switch (option){
                 case "6":
                     boardSize = 6;
                     menu.dispose();
                     make();
                     displayGame();
                     break;
                 case "8":
                     boardSize = 8;
                     menu.dispose();
                     make();
                     displayGame();
                     break;
                 case "10":
                     boardSize = 10;
                     menu.dispose();
                     make();
                     displayGame();
                     break;
             }
             
             // handling controllers and whose turn to move
             if(field[currentX][currentY].getComponentCount() == 1 && field[currentX][currentY].getComponent(0).getBackground() == Color.WHITE){
                 if(moveWhite){
                     switch (option){
                         case "Left":
                             moveLeft("WHITE");
                             break;
                         case "Forward":
                             moveForward("WHITE");
                             break;
                         case "Right":
                             moveRight("WHITE");
                             break;
                     }
                     moveWhite = false;
                     moveBlack = true;
                     turn.setText("          Now  moving: BLACK");
                 }
             }
             
             if(field[currentX][currentY].getComponentCount() == 1 && field[currentX][currentY].getComponent(0).getBackground() == Color.BLACK){
                 if(moveBlack){
                     switch (option){
                         case "Left":
                             moveLeft("BLACK");
                             break;
                         case "Forward":
                             moveForward("BLACK");
                             break;
                         case "Right":
                             moveRight("BLACK");
                             break;
                     }
                     moveWhite = true;
                     moveBlack = false;
                     turn.setText("          Now  moving: WHITE");
                 }
             }
         }
     }
}