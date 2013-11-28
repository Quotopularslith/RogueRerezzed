
package entity;

import dungeon.Room;
import java.util.Random;

/**
 *
 * @author Torri
 */
public class Spawner {
    /**
     * spawns stuff
     * @param am
     * @param lvl
     * @param r 
     */
    public static void spawner(int am,int lvl,Room r){
        RogueEntity e = new RogueEntity(r.l);
        Random rand = new Random();
        int type = rand.nextInt(4);
        for(int i=0;i<am;i++){
            if(type==0){
                e = new entity.mob.Snake(lvl,r,r.l);
            }else if(type==1){
                e = new entity.mob.Bandit(lvl,r,r.l);
            }else if(type==2){
                e = new entity.mob.Fish(lvl,r,r.l);
            }else if(type==3){
                e = new entity.mob.Goblin(lvl, r, r.l);
            }
            r.l.addEntity(e);
        }
    }
}