/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import dungeon.Room;

/**
 *
 * @author Mnenmenth
 */
public class Bandit extends RogueHostileEntity{
    public Bandit(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Bandit",3,20,3,2);
    }
}