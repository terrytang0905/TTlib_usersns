package com.newroad.user.sns.service.openmessage.demo;

import com.newroad.user.sns.service.openmessage.utils.ConfigLoader;
import com.newroad.user.sns.service.openmessage.config.AppConfig;
import com.newroad.user.sns.service.openmessage.lib.ADDRESSBOOKMail;

public class AddressBookMailSubscribe {

	public static void main(String[] args) {

		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Mail);
		ADDRESSBOOKMail addressbook = new ADDRESSBOOKMail(config);
		addressbook.setAddress("leo@apple.cn", "leo");
		addressbook.subscribe();
	}	
}
