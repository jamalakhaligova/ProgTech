/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiationtask;

import java.io.FileNotFoundException;

/**
 *
 * @author cemal
 */
public class Radiationtask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Simulation sm = new Simulation();
        try {
            sm.read("data.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        sm.simulate();
        sm.report();
        sm.clear();
    }
    
}
