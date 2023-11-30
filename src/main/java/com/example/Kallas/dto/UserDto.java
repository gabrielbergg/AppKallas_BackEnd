package com.example.Kallas.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.Kallas.model.Diary;
import com.example.Kallas.model.Task;
import org.springframework.hateoas.RepresentationModel;

public class UserDto extends RepresentationModel<UserDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String phone;
	
	private List<Task> tasks = new ArrayList<>();
	
	private List<Diary> diaries = new ArrayList<>();
	
	public UserDto(Long id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	public UserDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
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
		UserDto other = (UserDto) obj;
		return Objects.equals(id, other.id);
	}
}