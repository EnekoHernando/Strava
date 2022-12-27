package data.dto;

import java.util.ArrayList;
import java.util.List;
import data.domain.TrainingSession;

public class TrainingSessionAssembler {
	private static TrainingSessionAssembler instance;
	private TrainingSessionAssembler() {}
	
	public static TrainingSessionAssembler getInstance() {
		if(instance == null) {
			instance = new TrainingSessionAssembler();
		}
		return instance;
	}
	public TrainingSessionDTO trainingSessionToDTO(TrainingSession ts) {
		TrainingSessionDTO dto = new TrainingSessionDTO();
		dto.setTitle(ts.getTitle());
		dto.setSport(SportAssembler.getInstance().sportToDTO(ts.getSport()));
		dto.setDistance(ts.getDistance());
		dto.setStartDate(ts.getStartDate());
		dto.setFinishDate(ts.getFinishDate());
		dto.setDuration(ts.getDuration());
		dto.setChallenges(ChallengeAssembler.getInstance().categoryToDTO(ts.getChallenges()));
		return dto;
	}
	public List<TrainingSessionDTO> categoryToDTO(List<TrainingSession> ts){
		List<TrainingSessionDTO> dtos = new ArrayList<>();
		for(TrainingSession c: ts) {
			dtos.add(this.trainingSessionToDTO(c));
		}
		return dtos;
	}
}
