package com.example.Kallas.service;

import java.util.List;

import com.example.Kallas.controller.TaskController;
import com.example.Kallas.controller.UserController;
import com.example.Kallas.dto.UserDto;
import com.example.Kallas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Kallas.dto.TaskDto;
import com.example.Kallas.mapper.ModellsMapper;
import com.example.Kallas.model.Task;
import com.example.Kallas.repository.TaskRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public List<TaskDto> findAll() {
		List<TaskDto> list = ModellsMapper.parseListObj(taskRepository.findAll(), TaskDto.class);

		for (TaskDto dto: list) {
			User user = dto.getUser();

			if (!user.hasLink("self")) {
				user.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
			}

			dto.add(linkTo(methodOn(TaskController.class).findById(dto.getId())).withSelfRel());
		}
		
		return list;
	}
	
	public TaskDto findById(Long id) {
		Task task = taskRepository.findById(id).orElseThrow();
		TaskDto dto = ModellsMapper.parseObj(task, TaskDto.class);
		User user = dto.getUser();
		user.add(linkTo(methodOn(UserController.class).findById(task.getUser().getId())).withSelfRel());

		dto.add(linkTo(methodOn(TaskController.class).findAll()).withSelfRel());

		return dto;
	}
	
	public TaskDto create(TaskDto taskDto) {
		Task task = ModellsMapper.parseObj(taskDto, Task.class);
		task.setUser(task.getUser());
		TaskDto dto = ModellsMapper.parseObj(taskRepository.save(task), TaskDto.class);
		
		return dto;
	}
	
	public TaskDto update(Long id, TaskDto taskDto) {
		Task task = taskRepository.findById(id).orElseThrow();
		
		task.setNameService(taskDto.getNameService());
		task.setValue(taskDto.getValue());
		task.setUser(taskDto.getUser());

		TaskDto dto = ModellsMapper.parseObj(taskRepository.save(task), taskDto.getClass());

		return dto;
	}
	
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
}
