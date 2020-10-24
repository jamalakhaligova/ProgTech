/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiationtask;

/**
 *
 * @author cemal
 */
public class Parabush extends Plant
{
    public Parabush(String name,Integer nutrients) {
        super(name, nutrients);
    }
    
    
    public Integer radiationNeed()
    {
        return 0;
    }
    
    public void radiate(Radiation r) 
    {
        if (r == Radiation.ALPHA)
        {
            this.nutrients += 1;
        }
        else if(r==Radiation.DELTA)
        {
            this.nutrients += 1;
        }
        else if (r == Radiation.NoRadiation){
            this.nutrients -= 1;
        }
    }
    
    
}
