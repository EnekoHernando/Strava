package data.domain;


public enum Sport {
	
	RUNNING((short) 1), CYCLING((short) 2), BULLET_DOGE((short) 3);
	
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
