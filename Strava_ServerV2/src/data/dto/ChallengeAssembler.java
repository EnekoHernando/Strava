package data.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	public Map<ChallengeDTO, Float> mapToDTO(Map<Challenge, Float> challengesA){
		Map<ChallengeDTO, Float> map = new TreeMap<>();
		for(Challenge c: challengesA.keySet()) {
			map.put(this.challengeToDTO(c), challengesA.get(c));
		}
		return map;
	}
	public boolean equalsDTO(Challenge c, ChallengeDTO c2) {
		return c.getName().equals(c2.getName()) && c.getSport().equals(SportAssembler.getInstance().dtoToSport(c2.getSport()));
	}
}
