package engine.rule.test.data;

import com.google.gson.Gson;

public class Message implements IMessage{
	
	public int line;
	public String debug;
	public String expected;
	public String real;
	
	
	public Message(){
		
	}
	
	public Message(int line, String debug, String expected, String real)
	{
		this.line = line;
		this.debug = debug;
		this.expected = expected;
		this.real = real;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public String getReal() {
		return real;
	}

	public void setReal(String real) {
		this.real = real;
	}

	@Override
	public String getMessage() {
		return new Gson().toJson(this);
	}
}
