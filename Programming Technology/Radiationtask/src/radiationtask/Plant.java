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
public abstract class Plant {
    
    public Plant(String name,Integer nutrients)
    {
        this.name = name;
        this.nutrients = nutrients;
    }
    
    protected String name;
    protected Integer nutrients;

    public Boolean isLiving()
    {
        return this.nutrients>0;
    }
    public String getName()
    {
        return this.name;
    }
    
    public Integer getNutrient()
    {
        return this.nutrients;
    }
    
    
    public abstract Integer radiationNeed();
    // radiationNeed is needed to determine next day's radiation 
    public abstract void radiate(Radiation r);
    // radiate is needed to assign nutrient's value based on the radiation type
    
}