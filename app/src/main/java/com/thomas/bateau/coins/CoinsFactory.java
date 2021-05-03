package com.thomas.bateau.coins;

public abstract class CoinsFactory {
    public abstract ResultCoinFragment createFragmentResults();
    public abstract SearchCoinFragment createFragmentSearch();
    public abstract int getTextPresentation();
}
