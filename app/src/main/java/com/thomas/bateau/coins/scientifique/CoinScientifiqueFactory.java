package com.thomas.bateau.coins.scientifique;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.CoinsFactory;
import com.thomas.bateau.coins.ResultCoinFragment;
import com.thomas.bateau.coins.SearchCoinFragment;

public class CoinScientifiqueFactory extends CoinsFactory {

    @Override
    public ResultCoinFragment createFragmentResults() {return new ResultCoinScientifiqueFragment();}

    @Override
    public SearchCoinFragment createFragmentSearch() {return new SearchCoinScientifiqueFragment();}

    @Override
    public int getTextPresentation() {
        return R.string.TextCoinScience;
    }
}
