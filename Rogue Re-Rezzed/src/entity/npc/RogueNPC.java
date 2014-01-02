
package entity.npc;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import render.Sprite;
import util.Direction;

/**
 * Master for all new NPCs
 * @author Torri
 */
public class RogueNPC extends RogueEntity{
    public boolean hostile;
    /**
     * Creates NPC
     * @param l1 
     */
    public RogueNPC(Room r,Level l1) {
        super(l1);
        spawn(r);
        sp=new Sprite("npc");
        health=100;
        lvl=Rogue.numLevels;
    }
    public void action(){}
    @Override
    public void turn(){
        Direction pdir;
        boolean b = rand.nextBoolean();
        int d = rand.nextInt(3);
        if(b){
            switch (d){
                case 0:
                    pdir=Direction.UP;
                    break;
                case 1:
                    pdir=Direction.DOWN;
                    break;
                case 2:
                    pdir=Direction.LEFT;
                    break;
                case 3:
                    pdir=Direction.RIGHT;
                    break;
                default:
                    pdir=Direction.STOP;
                    break;
            }
        }else{
            pdir=Direction.STOP;
        }
        move(pdir);
    }
}
