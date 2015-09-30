package com.newroad.user.sns.service.openmessage.demo;

import com.newroad.user.sns.service.openmessage.utils.ConfigLoader;
import com.newroad.user.sns.service.openmessage.config.AppConfig;
import com.newroad.user.sns.service.openmessage.lib.ADDRESSBOOKMessage;

public class AddressBookMessageUnSubscribe {

	public static void main(String[] args) {

		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		ADDRESSBOOKMessage addressbook = new ADDRESSBOOKMessage(config);
		addressbook.setAddress("186****1889");
		addressbook.unsubscribe();
	}	
}
