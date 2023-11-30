package com.example.Kallas.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.Kallas.controller.DiaryController;
import com.example.Kallas.controller.TaskController;
import com.example.Kallas.controller.UserController;
import com.example.Kallas.dto.UserDto;
import com.example.Kallas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Kallas.dto.DiaryDto;
import com.example.Kallas.mapper.ModellsMapper;
import com.example.Kallas.model.Diary;
import com.example.Kallas.model.Task;
import com.example.Kallas.repository.DiaryRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DiaryService {

	@Autowired
	private DiaryRepository diaryRepository;
	
	public Page<DiaryDto> findAll(Pageable pageable){
//		List<DiaryDto> list = ModellsMapper.parseListObj(diaryRepository.findAll(), DiaryDto.class);

		var diaryPage = diaryRepository.findAll(pageable);

		var diaryDtoPage = diaryPage.map(p -> ModellsMapper.parseObj(p, DiaryDto.class));

		diaryDtoPage.map(p -> p.add(linkTo(methodOn(DiaryController.class).findById(p.getId())
		).withSelfRel()));

		for (DiaryDto it : diaryDtoPage) {
			total_value(it);
			update(it.getId(), it);

			User user = it.getUser();
			if (!user.hasLink("self")) {
				user.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
			}


			for (Task tk: it.getTasks()) {
				if (!tk.hasLink("self")) {
					tk.add(linkTo(methodOn(TaskController.class).findById(tk.getId())).withSelfRel());
				}
			}

//			it.add(linkTo(methodOn(DiaryController.class).findById(it.getId())).withSelfRel());
		}
		return diaryDtoPage;
	}

	public Page<DiaryDto> findByDate(LocalDate date, Pageable pageable){
//		List<DiaryDto> list = ModellsMapper.parseListObj(diaryRepository.findAll(), DiaryDto.class);

		var diaryPage = diaryRepository.findByDate(date, pageable);

		var diaryDtoPage = diaryPage.map(p -> ModellsMapper.parseObj(p, DiaryDto.class));

		diaryDtoPage.map(p -> p.add(linkTo(methodOn(DiaryController.class).findById(p.getId())
		).withSelfRel()));

		for (DiaryDto it : diaryDtoPage) {
			total_value(it);
			update(it.getId(), it);

			User user = it.getUser();
			if (!user.hasLink("self")) {
				user.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
			}


			for (Task tk: it.getTasks()) {
				if (!tk.hasLink("self")) {
					tk.add(linkTo(methodOn(TaskController.class).findById(tk.getId())).withSelfRel());
				}
			}

		}
		return diaryDtoPage;
	}
	
	public DiaryDto findById(Long id){
		Diary diary = diaryRepository.findById(id).orElseThrow();
		DiaryDto dto = ModellsMapper.parseObj(diary, DiaryDto.class);
		User user = dto.getUser();
		user.add(linkTo(methodOn(UserController.class).findById(diary.getUser().getId())).withSelfRel());

		for (Task tk: diary.getTasks()) {
			tk.add(linkTo(methodOn(TaskController.class).findById(tk.getId())).withSelfRel());
		}

//		dto.add(linkTo(methodOn(DiaryController.class).findAll()).withSelfRel());
		
		return dto;
	}
	
	public DiaryDto create(DiaryDto diaryDto){
		Diary diary = ModellsMapper.parseObj(diaryDto, Diary.class);

		Set set = diary.getTasks();
		diary.setTasks(set);

		DiaryDto dto = ModellsMapper.parseObj(diaryRepository.save(diary), DiaryDto.class);
		
		return dto;
	}
	
	public DiaryDto update(Long id, DiaryDto diaryDto){
		Diary diary = diaryRepository.findById(id).orElseThrow();
		diary.setTime_appointment(diaryDto.getTime_appointment());
		diary.setClient_name(diaryDto.getClient_name());
		diary.setTotal_value(diaryDto.getTotal_value());
		
		DiaryDto dto = ModellsMapper.parseObj(diaryRepository.save(diary), DiaryDto.class);
		
		return dto;
	}
	public void delete(Long id){
		diaryRepository.deleteById(id);
	}
	
	public void total_value(DiaryDto diaryDto) {
		Double tot = 0.0;
		
		for (Task t : diaryDto.getTasks()) {
			tot += t.getValue();
		}
		
		diaryDto.setTotal_value(tot);
	}
}
