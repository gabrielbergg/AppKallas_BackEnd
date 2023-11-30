package com.example.Kallas.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.Kallas.model.Diary;
import com.example.Kallas.model.User;
import org.springframework.hateoas.RepresentationModel;

public class TaskDto extends RepresentationModel<TaskDto> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nameService;
	private Double value;
	
	private User user;
	
	private Set<Diary> diary = new HashSet<>();
	
	public TaskDto(Long id, String nameService, Double value) {
		this.id = id;
		this.nameService = nameService;
		this.value = value;
	}
	
	public TaskDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDto other = (TaskDto) obj;
		return Objects.equals(id, other.id);
	}
}
