package com.example.Kallas.controller;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Kallas.dto.UserDto;
import com.example.Kallas.model.User;
import com.example.Kallas.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for System Users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	@Operation(summary = "List all users", description = "List all users",
	tags = {"Users"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
			content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
			}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Page<UserDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit) {
		Pageable pageable = PageRequest.of(page, limit);
		return ResponseEntity.ok().body(userService.findAll(pageable));
	}

	@GetMapping(value = "/findByName/{name}")
	@Operation(summary = "List a user by name", description = "List a user by name",
	tags = {"Users"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
			content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
			}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Page<UserDto>> findByName(
			@PathVariable(value = "name") String name,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit) {
		Pageable pageable = PageRequest.of(page, limit);
//		List<UserDto> list = userService.findAll();
		return ResponseEntity.ok().body(userService.findByName(name, pageable));
	}


	@GetMapping(value = "/{id}")
	@Operation(summary = "Find a user", description = "Find a user",
			tags = {"Users"}, responses = {
					@ApiResponse(description = "Sucess", responseCode = "200",
							content = @Content(schema = @Schema(implementation = UserDto.class))
					),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<UserDto> findById(@PathVariable(value = "id") Long id) {
		UserDto user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	@Operation(summary = "Create a user", description = "Create a user",
			tags = {"Users"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
		userService.create(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update a user", description = "Update a user",
			tags = {"Users"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<UserDto> update(@PathVariable(value = "id") Long id, @RequestBody UserDto user) {
		userService.update(id, user);
		
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a user", description = "Delete a user",
			tags = {"Users"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}