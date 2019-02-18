package ca.mcgill.ecse321.cooperator.dto;

import java.util.Collections;
import java.util.List;

public class CooperatorManagerDto {
	private String name;
	private List<UserEntityDto> users;

	public CooperatorManagerDto() {
	}

	@SuppressWarnings("unchecked")
	public CooperatorManagerDto(String name) {
		this(name, Collections.EMPTY_LIST);
	}
	
	public CooperatorManagerDto(String name, List<UserEntityDto> usersList) {
		this.name = name;
		this.users = usersList;
	}

	public String getName() {
		return this.name;
	}
	
	public List<UserEntityDto> getUsers(){
		return this.users;
	}
	
	public void addUser(UserEntityDto user) {
		this.users.add(user);
	}
}
