package engine.rule.test.service.object;

import java.util.Date;

public class TestCaseFile
{
	private String file;
	private Date time;
	
	public TestCaseFile(String file, Date time)
	{
		this.file = file;
		this.time = time;
	}
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
