package com.example.Kallas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.Kallas.model.Task;
import com.example.Kallas.model.User;
import org.springframework.hateoas.RepresentationModel;

public class DiaryDto extends RepresentationModel<DiaryDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String client_name;
	private LocalDateTime time_appointment;
	private Double total_value;
	
	private User user;
	
	private Set<Task> tasks = new HashSet<>();
	
	public DiaryDto(Long id, String client_name,LocalDateTime time_appointment, User user) {
		this.id = id;
		this.client_name = client_name;
		this.time_appointment = time_appointment;
		this.user = user;
	}

	public DiaryDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public LocalDateTime getTime_appointment() {
		return time_appointment;
	}

	public void setTime_appointment(LocalDateTime time_appointment) {
		this.time_appointment = time_appointment;
	}

	public Double getTotal_value() {
		return total_value;
	}

	public void setTotal_value(Double total_value) {
		this.total_value = total_value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
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
		DiaryDto other = (DiaryDto) obj;
		return Objects.equals(id, other.id);
	}
}
