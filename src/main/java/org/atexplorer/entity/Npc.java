package org.atexplorer.entity;

public final class Npc extends Player{
    private NpcDifficulty difficulty;

    public Npc(NpcDifficulty difficulty){
        super("NPC");
        this.difficulty = difficulty;
    }
}
