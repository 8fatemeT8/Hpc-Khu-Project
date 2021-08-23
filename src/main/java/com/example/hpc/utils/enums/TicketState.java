package com.example.hpc.utils.enums;

public enum  TicketState implements EnumProviderKey<Integer> {
    OPEN(0),SEEN(1),ANSWER(2),COMPLETE(3),CLOSE(4);

    int key;

    TicketState(int key) {
        this.key = key;
    }

    public static TicketState fromKey(Integer integer) {
        return EnumUtil.fromKey(TicketState.class, integer);
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
