/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubicclock;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 *
 * @author a0jckr
 */
public class RubicClockGUI {
    //GridLayout

    private JFrame frame;
    private JPanel boardPanel;
    clockBoardGUI cbg;
    
    
    
    public RubicClockGUI() {
        frame = new JFrame("Rubic Clocks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel();
        
        frame.add(boardPanel);
        cbg = new clockBoardGUI();
        frame.getContentPane().add(cbg.getBoardPanel(),
                            BorderLayout.CENTER);
        frame.getContentPane().add(cbg.getStepPanel(),BorderLayout.SOUTH);
        frame.pack();
    
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem newGameItem = new JMenuItem("New game");
        gameMenu.add(newGameItem);
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(cbg.getBoardPanel());
                    frame.getContentPane().remove(cbg.getStepPanel());
                    cbg = new clockBoardGUI();
                    frame.getContentPane().add(cbg.getBoardPanel(),
                            BorderLayout.CENTER);
                    frame.getContentPane().add(cbg.getStepPanel(), BorderLayout.SOUTH);
                    frame.pack();
            }
        });
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
}
