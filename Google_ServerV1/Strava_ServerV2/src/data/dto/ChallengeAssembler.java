package data.dto;

import java.util.ArrayList;
import java.util.List;

import data.domain.Challenge;

public class ChallengeAssembler {
	private static ChallengeAssembler instance;

	private ChallengeAssembler() { }
	
	public static ChallengeAssembler getInstance() {
		if (instance == null) {
			instance = new ChallengeAssembler();
		}
		
		return instance;
	}
	public ChallengeDTO challengeToDTO(Challenge challenge) {
		ChallengeDTO dto = new ChallengeDTO();
		dto.setName(challenge.getName());
		dto.setStartDate(challenge.getStartDate());
		dto.setEndDate(challenge.getEndDate());
		dto.setTargetDistance(challenge.getTargetDistance());
		dto.setSport(SportAssembler.getInstance().sportToDTO(challenge.getSport()));
		dto.setTargetTime(challenge.getTargetTime());
		return dto;
	}
	public List<ChallengeDTO> categoryToDTO(List<Challenge> challenges){
		List<ChallengeDTO> dtos = new ArrayList<>();
		for(Challenge c: challenges) {
			dtos.add(this.challengeToDTO(c));
		}
		return dtos;
	}
}
