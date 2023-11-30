package com.example.Kallas.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diary")
public class Diary implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String client_name;
	private LocalDateTime time_appointment;
	private Double total_value;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany
	@JoinTable(name = "diary_task",
    joinColumns = @JoinColumn(name = "diary_id"),
    inverseJoinColumns = @JoinColumn(name = "task_id"))
	private Set<Task> tasks = new HashSet<>();
	
	public Diary(Long id, String client_name,LocalDateTime time_appointment, User user) {
		this.id = id;
		this.client_name = client_name;
		this.time_appointment = time_appointment;
		this.user = user;
	}

	public Diary() {
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
		Diary other = (Diary) obj;
		return Objects.equals(id, other.id);
	}
}