package com.yambacode.bio.utils;

public interface TriPredicate<S,T,U> {
    boolean test(S s, T t, U u);
}
