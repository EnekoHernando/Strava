package data.domain;


public enum Sport {
	
	RUNNING((short) 1), CYCLING((short) 2);
	
	private short value;

    private Sport(short value)
    {
        this.value = value;
    }

    public short getValue()
    {
        return value;
    }
}
