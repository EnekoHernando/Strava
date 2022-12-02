package data.domain;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public enum Sport {
	RUNNING, CYCLING
}
