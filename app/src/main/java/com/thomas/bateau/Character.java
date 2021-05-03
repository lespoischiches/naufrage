package com.thomas.bateau;

public enum Character {
    SKIPPER("skipper"),
    FISHER("fisher"),
    DIVER("diver"),
    KITTER("kitter"),
    SCIENTIST("scientist");

    String fileName;
    Character(String fileName)
    {
        this.fileName =fileName;
    }

}
