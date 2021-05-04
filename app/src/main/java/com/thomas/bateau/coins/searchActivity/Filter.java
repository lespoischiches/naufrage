package com.thomas.bateau.coins.searchActivity;


    @FunctionalInterface
    public interface Filter<T, U,V> {


        public V apply(T t, U u);
    }


