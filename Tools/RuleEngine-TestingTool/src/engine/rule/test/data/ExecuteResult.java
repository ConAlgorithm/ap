package engine.rule.test.data;

public class ExecuteResult {

    private int total;
	
	private int executed;
	
	private int succeed;
	
	private int fail;
	
	public void total(int total)
    {
    	this.total = total;
    }
	
	public void addTotal()
    {
    	total ++;
    }
    
    public void addExecuted()
    {
    	executed ++;
    }
    
    public void addSucceed()
    {
    	succeed ++;
    }
    
    public void addFail()
    {
    	fail ++;
    }

	public int getTotal() {
		return total;
	}

	public int getExecuted() {
		return executed;
	}

	public int getSucceed() {
		return succeed;
	}

	public int getFail() {
		return fail;
	}
}
