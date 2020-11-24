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
public class Deltatree extends Plant
{
    public Deltatree(String name,Integer nutrients) {
        super(name, nutrients);
    }
    

    public Integer radiationNeed()
    {
        if(this.nutrients < 5)
        {
            return -4;
        }
        else if((this.nutrients>= 5) || (this.nutrients <= 10))
        {
            return -1;
        }
        else{
            return 0;
        }
    }
    
    public void radiate(Radiation r) 
    {
        if (r == Radiation.ALPHA)
        {
            this.nutrients -= 3;
        }
        else if(r==Radiation.DELTA)
        {
            this.nutrients += 4;
        }
        else if (r == Radiation.NoRadiation){
            this.nutrients -= 1;
        }
    }
}
