package com.example.hpc.utils.enums;

public enum JobPlan implements EnumProviderKey<Integer> {
    PLAN1(0), PLAN2(1);

    int key;

    public static JobPlan fromKey(int key) {
        return EnumUtil.fromKey(JobPlan.class, key);
    }

    JobPlan(int key) {
        this.key = key;
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
