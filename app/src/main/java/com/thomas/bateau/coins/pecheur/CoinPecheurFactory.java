package com.thomas.bateau.coins.pecheur;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.CoinsFactory;
import com.thomas.bateau.coins.ResultCoinFragment;
import com.thomas.bateau.coins.SearchCoinFragment;

public class CoinPecheurFactory extends CoinsFactory {

    @Override
    public ResultCoinFragment createFragmentResults() {
        return new ResultCoinPecheurFragment();
    }

    @Override
    public SearchCoinFragment createFragmentSearch() {
        return new SearchCoinPecheurFragment();
    }

    @Override
    public int getTextPresentation() {
        return R.string.TextCoinPeche;
    }
}
