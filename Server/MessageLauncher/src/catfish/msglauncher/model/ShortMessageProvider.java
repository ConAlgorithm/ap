package catfish.msglauncher.model;

import catfish.msglauncher.sender.DaHanSanTongCollectionSender;
import catfish.msglauncher.sender.DaHanSanTongShortMessageSender;
import catfish.msglauncher.sender.MessageSender;

public enum ShortMessageProvider {

	DaHanSanTong(1, DaHanSanTongCollectionSender.class);
	
	private final int value;
	private final Class<? extends MessageSender> providerClass;
	
	private ShortMessageProvider(int value, Class<? extends MessageSender> providerClass) {
		this.value = value;
		this.providerClass = providerClass;
	}
	
	public int getValue() {
		return value;
	}
	
	public static ShortMessageProvider getProviderByValue(int value) {
		switch(value) {
			case 1: return DaHanSanTong;
			default: return null;
		}
	}
	
	public Class<? extends MessageSender> getProviderClass() {
		return providerClass;
	}
}
