package com.example.hpc.utils.enums;

public enum UserRoles implements EnumProviderKey<Integer> {
	STUDENT(0), MASTER(1), ADMIN(2), SYS_ADMIN(3);

	private int key;

	UserRoles(int key) {
		this.key = key;
	}

	public static UserRoles fromKey(int key) {
		return EnumUtil.fromKey(UserRoles.class, key);
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
