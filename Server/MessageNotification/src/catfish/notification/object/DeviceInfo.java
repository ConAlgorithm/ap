package catfish.notification.object;

import java.util.Map;

public class DeviceInfo {
	private String id;
	private String userId;
	// 申请渠道
	private String sourceType;
	// 设备类型
	private String deviceType;
	// 内网ip
	private String traceIp;
	// 用户mac地址
	private String macAddress;
	// Android设备, IMEI
	private String imei;
	// 操作系统
	private String os;
	// 操作系统版本
	private String osVersion;
	// Ip地址(外网)
	private String ip;
	// 设备号(pc,h5)
	private String eid;
	// 设备唯一标识(IOS)
	private String openUUID;
	// 设备唯一标识(Android)
	private String uuid;
	// 广告标识符
	private String idfa;
	// 客户端版本
	private String clientVersion;
	// 分辨率
	private String resolution;
	// 网络类型
	private String networkType;
	// 移动终端类型
	private String terminalType;
	// 纬度
	private Double latitude;
	// 经度
	private Double longitude;
	// 端口号
	private Integer port;
	//机型
	private String mobileType;
	//记录时间戳，格式yyyy-mm-dd hh24:MM
	private String timestamp;
	//appId
	private String appId;
	//扩展字段k-v放map
	private Map<String,Object> extend;

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getTraceIp() {
		return traceIp;
	}

	public void setTraceIp(String traceIp) {
		this.traceIp = traceIp;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getOpenUUID() {
		return openUUID;
	}

	public void setOpenUUID(String openUUID) {
		this.openUUID = openUUID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
