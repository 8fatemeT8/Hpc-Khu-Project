package com.example.hpc.utils.enums;

public interface EnumProviderKey<TKey> {

    TKey toKey();

    String getName();
}
