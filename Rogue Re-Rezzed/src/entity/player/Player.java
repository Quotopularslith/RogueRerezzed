package entity.player;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import entity.mob.RogueHostileEntity;
import entity.npc.RogueNPC;
import render.Sprite;
import ui.GamePlay;
import util.Direction;

/**
 * Hello! This is you!
 * @author Torri
 */
public class Player extends RogueEntity{
    public boolean dead=false;
    public int maxMana=100;//mana=magic points
    public double mana=50;
    public int kills=0;
    boolean attack=false;
    public int currinv=0;
    public Item[] pinv;
    public double xp=0;
    public int xplevels=1;
    public int rep=0;
    public int gold=0;
    public Player(Level l1){
        super(l1);
        if(pinv==null){
            pinv = new Item[10];
            for(int i=0;i<pinv.length;i++){
                pinv[i]=new Item(0,this,0,l);
            }
        }
        for(int k=0;k<pinv.length;k++){
            if(pinv[k].name.equalsIgnoreCase("empty")){
                currinv=k;
                break;
            }
        }
        maxhealth=100;
        mana=(int) (maxMana/1.5);
        health=(float) (maxhealth);
        updateStats();
        sp = new Sprite("Player");
        Room r = l1.getRoom(0);
        spawn(r);
    }
    @Override
    public void death(){
        dead=true;
    }
    @Override
    public void turn(){
        l=Rogue.getCurrentLevel();
        if(health<=0){
            dead=true;
        }else if(health<maxhealth){
            health+=0.1*xplevels;
        }
        if(xplevels==0) xplevels = 1;
        if(xp%(10*xplevels)==0 && xp>1){
            updateStats();
            xp=0;
            xplevels++;
            health=maxhealth;
            mana=maxMana;
        }
        for(int i=0;i<pinv.length;i++){
            if(pinv[i].name.equalsIgnoreCase("Empty")){
                currinv=i;
                break;
            }
        }
        for (int j=0;j<l.getItems().size();j++) {
            Item i = l.getItems().get(j);
            if(i.x==this.x && i.y==this.y && i.id!=0 && pinv[currinv]!=null){
                if(i instanceof Gold){
                    gold+=i.id;
                    i.death();
                    continue;
                }
                GamePlay.pickup = i;
            }
        }
        attack=false;
        if(Rogue.mm.ki.keyBind[0]){//up
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueNPC  && re.x==this.x && re.y==this.y-1){
                    RogueNPC t = (RogueNPC) re;
                    t.action();
                }
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y-1){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp+=(re.lvl/xplevels);
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x && i.y==this.y-1 && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.UP);
            }
        }
        if(Rogue.mm.ki.keyBind[1]){//down
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueNPC  && re.x==this.x && re.y==this.y+1){
                    RogueNPC t = (RogueNPC) re;
                    t.action();
                }
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y+1){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp+=(re.lvl/xplevels);
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x && i.y==this.y+1 && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.DOWN);
            }
        }
        if(Rogue.mm.ki.keyBind[2]){//right
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueNPC  && re.x==this.x+1 && re.y==this.y){
                    RogueNPC t = (RogueNPC) re;
                    t.action();
                }
                if(re instanceof RogueHostileEntity && re.x==this.x+1 && re.y==this.y){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp+=(re.lvl/xplevels);
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x+1 && i.y==this.y && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.RIGHT);
            }
        }
        if(Rogue.mm.ki.keyBind[3]){//left
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueNPC  && re.x==this.x-1 && re.y==this.y){
                    RogueNPC t = (RogueNPC) re;
                    t.action();
                }
                if(re instanceof RogueHostileEntity && re.x==this.x-1 && re.y==this.y){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp+=(re.lvl/xplevels);
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x-1 && i.y==this.y && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.LEFT);
            }
        }
    }
    public void updateStats(){
        maxAtt=2*xplevels;
        maxMana=100+(xplevels);
        maxhealth=100+2*(xplevels-1);
        maxDefence=(xplevels);
        boolean curr=false;
        for (int i=0;i<pinv.length;i++) {
            if(pinv[i]!=null){
                pinv[i].setParent(this);
                if(pinv[i].name.equalsIgnoreCase("EMPTY") && !curr){
                    currinv=i;
                    curr=true;
                }else if(pinv[i].equip==true){
                    maxhealth+=pinv[i].stats[3];
                    maxMana+=pinv[i].stats[2];
                    maxDefence+=pinv[i].stats[1];
                    maxAtt+=pinv[i].stats[0];
                }
            }
        }
    }
}
