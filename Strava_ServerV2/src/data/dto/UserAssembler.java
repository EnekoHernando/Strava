package data.dto;

import data.domain.User;

//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class UserAssembler {
	private static UserAssembler instance;

	private UserAssembler() { }
	
	public static UserAssembler getInstance() {
		if (instance == null) {
			instance = new UserAssembler();
		}

		return instance;
	}
	public UserDTO userToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setBirthdate(user.getBirthdate());
		dto.setWeight(user.getWeight());
		dto.setHeight(user.getHeight());
		dto.setMaxHeartRate(user.getMaxHeartRate());
		dto.setHeartRateAtRest(user.getHeartRateAtRest());
		dto.setToken(user.getToken());
		dto.setChallengeA(ChallengeAssembler.getInstance().mapToDTO(user.getChallengeA()));
		dto.setTraininSL(TrainingSessionAssembler.getInstance().categoryToDTO(user.getTraininSL()));
		return dto;
	}
}