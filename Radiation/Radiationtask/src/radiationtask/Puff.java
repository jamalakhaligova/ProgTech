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
public class Puff extends Plant
{
    public Puff(String name,Integer nutrients) {
        super(name, nutrients);
    }
    
    
    @Override
    public Boolean isLiving()
    {
        return this.nutrients<10 && this.nutrients>0;
    }
    public Integer radiationNeed()
    {
        return (10 - this.nutrients) ;
    }
    
    public void radiate(Radiation r) 
    {
        if (r == Radiation.ALPHA)
        {
            this.nutrients += 2;
        }
        else if(r==Radiation.DELTA)
        {
            this.nutrients -= 2;
        }
        else if (r == Radiation.NoRadiation){
            this.nutrients -= 1;
        }
    }
}

