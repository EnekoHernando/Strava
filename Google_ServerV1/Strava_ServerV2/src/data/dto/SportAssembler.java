package data.dto;

import data.domain.Sport;

public class SportAssembler {
	private static SportAssembler instance;
	
	private SportAssembler() {}
	
	public static SportAssembler getInstance() {
		if(instance == null) {
			instance = new SportAssembler();
		}
		return instance;
	}
	public SportDTO sportToDTO(Sport s) {
		switch (s.name()) {
		case "RUNNING":
			return SportDTO.RUNNING;
		case "CYCLING":
			return SportDTO.CYCLING;
		default:
			return null;
		}
	}
	public Sport dtoToSport(SportDTO sd) {
		switch (sd.name()) {
		case "RUNNING":
			return Sport.RUNNING;
		case "CYCLING":
			return Sport.CYCLING;
		default:
			return null;
		}
	}
}
