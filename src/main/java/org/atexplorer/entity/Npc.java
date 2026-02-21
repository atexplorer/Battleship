package org.atexplorer.entity;

public class Npc extends Player{
    private NpcDifficulty difficulty;

    public Npc(NpcDifficulty difficulty){
        super();
        this.difficulty = difficulty;
    }
}
