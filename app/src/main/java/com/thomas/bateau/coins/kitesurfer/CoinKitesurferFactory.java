package com.thomas.bateau.coins.kitesurfer;

import com.thomas.bateau.coins.CoinsFactory;
import com.thomas.bateau.coins.ResultCoinFragment;
import com.thomas.bateau.coins.SearchCoinFragment;

public class CoinKitesurferFactory extends CoinsFactory {

    @Override
    public ResultCoinFragment createFragmentResults() {return new ResultCoinKitesurferFragment();}

    @Override
    public SearchCoinFragment createFragmentSearch() {return new SearchCoinKitesurferFragment();}
}
