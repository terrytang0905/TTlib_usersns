package com.newroad.user.sns.service.openmessage.lib.base;

import com.newroad.user.sns.service.openmessage.entity.DataStore;

public abstract class SenderWapper {

	protected DataStore requestData = new DataStore();

	public abstract ISender getSender();
}
