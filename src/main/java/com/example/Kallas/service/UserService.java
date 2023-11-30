package com.example.Kallas.service;

import java.util.List;

import com.example.Kallas.controller.TaskController;
import com.example.Kallas.controller.UserController;
import com.example.Kallas.dto.TaskDto;
import com.example.Kallas.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Kallas.dto.UserDto;
import com.example.Kallas.mapper.ModellsMapper;
import com.example.Kallas.model.User;
import com.example.Kallas.repository.UserRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Page<UserDto> findAll(Pageable pageable) {

		var userPage = userRepository.findAll(pageable);

		var userDtoPage = userPage.map(p -> ModellsMapper.parseObj(p, UserDto.class));

		userDtoPage.map(p -> p.add(linkTo(methodOn(UserController.class).findById(p.getId())
		).withSelfRel()));

		for (User dto: userPage) {
			for (Task tk: dto.getTasks()) {
				tk.add(linkTo(methodOn(TaskController.class).findById(tk.getId())).withSelfRel());
			}
		}

		return userDtoPage;
	}

	public Page<UserDto> findByName(String name, Pageable pageable) {

		var userPage = userRepository.findByName(name, pageable);

		var userDtoPage = userPage.map(p -> ModellsMapper.parseObj(p, UserDto.class));

		userDtoPage.map(p -> p.add(linkTo(methodOn(UserController.class).findById(p.getId())
		).withSelfRel()));

		for (User dto: userPage) {
			for (Task tk: dto.getTasks()) {
				tk.add(linkTo(methodOn(TaskController.class).findById(tk.getId())).withSelfRel());
			}
		}

		return userDtoPage;
	}
	
	public UserDto findById(Long id) {
		User user = userRepository.findById(id).orElseThrow();
		UserDto dto = ModellsMapper.parseObj(user, UserDto.class);

		dto.add(linkTo(methodOn(UserController.class).findAll(0, 10)).withSelfRel());

		for (Task tk: user.getTasks()) {
			tk.add(linkTo(methodOn(TaskController.class).findById(tk.getId())).withSelfRel());
		}

		return dto;
	}
	
	public UserDto create(UserDto userDto) {
		User user = ModellsMapper.parseObj(userDto, User.class);
		UserDto dto = ModellsMapper.parseObj(userRepository.save(user), UserDto.class);

		dto.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());

		return dto;
	}
	
	public UserDto update(Long id, UserDto userDto) {
		User user = userRepository.findById(id).orElseThrow();
		
		user.setName(userDto.getName());
		user.setPhone(userDto.getPhone());
		
		UserDto dto = ModellsMapper.parseObj(userRepository.save(user), UserDto.class);
		dto.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());

		return dto;
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	
	
}
