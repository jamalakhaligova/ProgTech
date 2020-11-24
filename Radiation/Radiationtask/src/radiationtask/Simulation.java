/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiationtask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cemal
 */
public class Simulation 
{
    private final ArrayList<Plant> plants;
    Integer numOfDays;
    public Simulation()
    {
        plants = new ArrayList();
    }
    // read function reads data from file: number of plants,some data(name type nutrient) for each plant,
    // and number of days to simulate.
    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numberOfPlants = sc.nextInt();
        Integer counter = 0;
        while (sc.hasNextLine() && counter<numberOfPlants) {
            counter += 1;
            String name = sc.next();
            String type = sc.next();
            Integer nutrients = sc.nextInt();
            Plant plant;
            switch (type) {
                case "p":
                    plant = new Puff(name,nutrients);
                    break;
                case "d":
                    plant = new Deltatree(name,nutrients);
                    break;
                case "b":
                    plant = new Parabush(name,nutrients);
                    break;
                default:
                    throw new InvalidInputException();
            }
            plants.add(plant);
        }
        numOfDays = sc.nextInt();
                
                
                
    }
    
    //simulate function simulates behaviour of plants for different radiation types,
    // radiation type on the first day is NoRadiation
    public void simulate()
    {
        Radiation radiation = Radiation.NoRadiation;
        
        for(int i = 0; i < numOfDays; i++) {
            Integer stored_radiation = 0;
            for(int j=0;j<plants.size();j++)
            {
                plants.get(j).radiate(radiation);
                stored_radiation += plants.get(j).radiationNeed();
            }
            if(stored_radiation>=3)
            {
                radiation = Radiation.ALPHA;
            }
            else if(stored_radiation<=-3)
            {
                radiation = Radiation.DELTA;
            }
            else{
                radiation = Radiation.NoRadiation;
            }
            
        }
    }
    
    //after simulation reports survivor plants
    public void report() {
        Boolean alldead = true;
        for(int i=0;i<plants.size();i++)
        {
            if(plants.get(i).isLiving())
            {
                alldead = false;
                System.out.println("Survivor plant's name " + plants.get(i).getName() + " its nutrient is " + (plants.get(i).getNutrient()).toString());
            }
        }
        
        if(alldead)
        {
            System.out.println("There is no survivor plant.");
        }
        
        
    }
    
    public void clear() {
        plants.clear();
    }
    
    
    
    
}
