/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubicclock;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author a0jckr
 */
public class clockBoardGUI {
    boolean done = false;
    int steps = 0;
    private JPanel stepPanel;
    private JLabel[][] clockBoard;
    private JPanel clockboardPanel;
    private JLabel stepLabel;
    private ArrayList<Integer> indices = new ArrayList<Integer>( 
            Arrays.asList(0,2,4)); 
    private final Random random = new Random();
    Integer randint;
    
    public clockBoardGUI() {
        
        clockboardPanel = new JPanel();
        clockboardPanel.setLayout(new GridLayout(5, 5));
        clockBoard = new JLabel[5][5];
        stepLabel = new JLabel("0 steps");
        stepPanel = new JPanel();
        stepPanel.add(stepLabel);
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if(indices.contains(j) && i!= 1 && i != 3)
                {
                    clockBoard[i][j] = startClock();
                    clockBoard[i][j].setPreferredSize(new Dimension(80, 40));
                    clockboardPanel.add(clockBoard[i][j]);  
                }
                else{
                    if((i==1 || i==3)&& !indices.contains(j))
                    {
                        JButton button = new JButton();
                        button.addActionListener(new ButtonListener(i, j));
                        button.setPreferredSize(new Dimension(40, 40));
                        clockboardPanel.add(button);
                    }
                    else{
                        JLabel placeholder = new JLabel(" ");
                        placeholder.setPreferredSize(new Dimension(80, 40));
                        clockboardPanel.add(placeholder);  
                        
                    }
                }   
            }
        }

    }
        
        class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            updateBoard(x-1,y-1);
            updateBoard(x-1,y+1);
            updateBoard(x+1,y-1);
            updateBoard(x+1,y+1);
            if (!done){
                steps++;
                stepLabel.setText(Integer.toString(steps) + " steps");
            }
            
        }
          
        
        
    } 
      
    public void updateBoard(int i,int j)
    {
        String data = clockBoard[i][j].getText();
        int num = Integer.parseInt(data);
        if (clockBoard[i][j].getText() != null && !done) {
            if (num!=12){
                ++num;
                clockBoard[i][j].setText(Integer.toString(num));
            }
            else{
                if(num==12 && !isOver())
                {
                    num=1;
                    clockBoard[i][j].setText(Integer.toString(num));
                }
                else{
                    if(isOver()){
                        done = true;
                        JOptionPane.showMessageDialog(clockboardPanel, "You have won in "+ getSteps() +" steps.", "Congrats!",
                        JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
   
        }
    }

    public boolean isOver() {  
        boolean all = clockBoard[0][0].getText() == "12";
        String data;
        int num;
        for (int i = 0; i < 5; i+=2) {
            for (int j = 0; j < 5; j+=2) {
                data = clockBoard[i][j].getText();
                num = Integer.parseInt(data);
                    if(num != 12){
                        return false;
                    }
                }
                
            }
        return true;
    }
    
    private JLabel startClock(){
        randint = random.nextInt(12);
        //randint = 0;
        JLabel clock = new JLabel(Integer.toString(randint));
        return clock;
    }
    
    public JPanel getBoardPanel() {
        return clockboardPanel;
    }
    
    public JPanel getStepPanel() {
        return stepPanel;
    }
    
    public int getSteps(){
        return steps;
    }
      
}
