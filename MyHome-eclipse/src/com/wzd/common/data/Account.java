package com.wzd.common.data;

import java.io.Serializable;
import java.sql.Date;

public class Account implements Serializable {

	private static final long serialVersionUID = -6322884645400585187L;

	public long persionId;
	public String loginName;
	public String password;
	public Date lastLoginTime;
	public long lastServerLoginTime;

	public Account(long persionId, String loginName, String password) {
		this.persionId = persionId;
		this.loginName = loginName;
		this.password = password;
	}

}
