package catfish.notification.messagegeneration;

import catfish.base.StartupConfig;

public class MemcachedUtils {

	private static final String MEMCACHED_USERNAME = "catfish.memcached.username";
	private static final String MEMCACHED_PASSWORD = "catfish.memcached.password";
	private static final String MEMCACHED_HOST = "catfish.memcached.host";
	private static final String MEMCACHED_PORT = "catfish.memcached.port";
	private static final String MEMCACHED_EXPIRETIME = "catfish.memcached.expiretime";

	public static Integer getMemcachedExpiretime() {
		return StartupConfig.getAsInt(MEMCACHED_EXPIRETIME);
	}

	public static String getMemcachedUsername() {
		return StartupConfig.get(MEMCACHED_USERNAME);
	}

	public static String getMemcachedPassword() {
		return StartupConfig.get(MEMCACHED_PASSWORD);
	}

	public static String getMemcachedHost() {
		return StartupConfig.get(MEMCACHED_HOST);
	}

	public static String getMemcachedPort() {
		return StartupConfig.get(MEMCACHED_PORT);
	}

	public static int getMaxCount() {
		return 1;
		//return StartupConfig.get(MAX_COUNT);
	}
}
