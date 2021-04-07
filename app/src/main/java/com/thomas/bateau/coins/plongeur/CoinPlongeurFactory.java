package com.thomas.bateau.coins.plongeur;

import com.thomas.bateau.coins.CoinsFactory;
import com.thomas.bateau.coins.ResultCoinFragment;
import com.thomas.bateau.coins.SearchCoinFragment;
import com.thomas.bateau.coins.plongeur.ResultCoinPlongeurFragment;
import com.thomas.bateau.coins.plongeur.SearchCoinPlongeurFragment;

public class CoinPlongeurFactory extends CoinsFactory {

    @Override
    public ResultCoinFragment createFragmentResults() {return new ResultCoinPlongeurFragment();}

    @Override
    public SearchCoinFragment createFragmentSearch() {return new SearchCoinPlongeurFragment();}
}


