package com.thomas.bateau.coins.skippeur;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.CoinsFactory;
import com.thomas.bateau.coins.ResultCoinFragment;
import com.thomas.bateau.coins.SearchCoinFragment;

public class CoinSkippeurFactory  extends CoinsFactory {

    @Override
    public ResultCoinFragment createFragmentResults() {return new ResultCoinSkippeurFragment();}

    @Override
    public SearchCoinFragment createFragmentSearch() {return new SearchCoinSkippeurFragment();}

    @Override
    public int getTextPresentation() {
        return R.string.TextCoinSkippeur;
    }
}

