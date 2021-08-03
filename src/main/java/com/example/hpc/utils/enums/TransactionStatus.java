package com.example.hpc.utils.enums;

public enum TransactionStatus implements EnumProviderKey<Integer> {
    SUCCESS(0),FAILED(1);
    private int key;

    TransactionStatus(int key) {
        this.key = key;
    }

    public static TransactionStatus fromKey(int key) {
        return EnumUtil.fromKey(TransactionStatus.class, key);
    }

    @Override
    public Integer toKey() {
        return key;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
