package CLASS.Item;

import CLASS.Character.*;

public class Weapon extends Item {
    private int power;

    public Weapon(String name, String description, int power){                          //default constructors for Instantiating Weapon
        super(name, description);
        this.power = power;
    }

    public int getPower(){return this.power;}                                           //method that return the power of a Instanciated Weapon

    public void use(Hero hero){                                                         //method that allow the Hero to equip/desequip a Weapon
        if ((hero.getWeaponEquip()) == this)    { hero.unequipWeapon(); }
        else                                    { hero.equipNewWeapon(this); }
    }
}