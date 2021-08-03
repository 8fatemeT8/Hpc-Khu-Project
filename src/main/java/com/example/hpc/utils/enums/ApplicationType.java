package com.example.hpc.utils.enums;

public enum ApplicationType implements EnumProviderKey<Integer> {
    JAVA(0), CPP(1), C(2), PYTHON(3), MATLAB(4), THREE_D_MAX(5), C_SHARP(6);

    int key;

    ApplicationType(int key) {
        this.key = key;
    }

    public static ApplicationType fromKey(Integer integer) {
        return EnumUtil.fromKey(ApplicationType.class, integer);
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
