/**
 * Created by Felton 2016年4月12日
 */
package engine.rule.coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author felton
 *
 */
public class Workflow {
	private String _id;
	private String appId;
	private String scene;
	private List<Message> received;
	private List<Message> sent;
	private Map<String, List<String>> possibles;
	
	public void addReived(Message msg){
		if(received == null)
		{
			received = new ArrayList<>();
		}
		received.add(msg);
	}
	public void addSent(Message msg){
		if(sent == null)
		{
			sent = new ArrayList<>();
		}
		sent.add(msg);
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	public List<Message> getReceived() {
		return received == null ? new ArrayList<Message>() : received;
	}
	public void setReceived(List<Message> received) {
		this.received = received;
	}
	public List<Message> getSent() {
		return sent == null ? new ArrayList<Message>() : sent;
	}
	public void setSent(List<Message> sent) {
		this.sent = sent;
	}
	public Map<String, List<String>> getPossibles() {
		return possibles;
	}
	public void setPossibles(Map<String, List<String>> possibles) {
		this.possibles = possibles;
	}
}
