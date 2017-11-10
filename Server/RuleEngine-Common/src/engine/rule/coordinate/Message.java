/**
 * Created by Felton 2016年4月12日
 */
package engine.rule.coordinate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author felton
 *
 */
public class Message {

	public String name;
	public String status;
	public Map<String, Object> extraInfo;
	public Date time;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(Map<String, Object> extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	public void addExtraInfo(String key, Object value)
	{
		if(extraInfo == null)
		{
			extraInfo = new HashMap<>();
		}
		extraInfo.put(key, value);
	}
}
