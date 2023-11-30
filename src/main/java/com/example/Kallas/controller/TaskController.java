package com.example.Kallas.controller;

import java.net.URI;
import java.util.List;

import com.example.Kallas.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Kallas.dto.TaskDto;
import com.example.Kallas.service.TaskService;

@RestController
@RequestMapping(value = "/tasks")
@Tag(name = "Tasks", description = "Endpoint for user tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	@Operation(summary = "List all tasks", description = "List all tasks",
			tags = {"Tasks"},
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200",
							content = {@Content(array = @ArraySchema(schema = @Schema(implementation = TaskDto.class)))
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<List<TaskDto>> findAll() {
		List<TaskDto> list = taskService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find a task", description = "Find a task",
			tags = {"Tasks"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = TaskDto.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<TaskDto> findById(@PathVariable(value = "id") Long id) {
		TaskDto TaskDto = taskService.findById(id);
		
		return ResponseEntity.ok().body(TaskDto);
	}
	
	@PostMapping
	@Operation(summary = "Create a task", description = "Create a task",
			tags = {"Tasks"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = TaskDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<TaskDto> create(@RequestBody TaskDto TaskDto) {
		taskService.create(TaskDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(TaskDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(TaskDto);
	}
	
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update a task", description = "Update a task",
			tags = {"Tasks"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = TaskDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<TaskDto> create(@PathVariable(value = "id") Long id, @RequestBody TaskDto taskDto) {
		taskService.update(id, taskDto);
		
		return ResponseEntity.ok().body(taskDto);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a task", description = "Delete a task",
			tags = {"Tasks"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = TaskDto.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		taskService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}




