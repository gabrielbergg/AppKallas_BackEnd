package com.example.Kallas.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.Kallas.dto.TaskDto;
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

import com.example.Kallas.dto.DiaryDto;
import com.example.Kallas.service.DiaryService;
import com.example.Kallas.service.TotalDay;

@RestController
@RequestMapping("/diaries")
@Tag(name = "Diaries", description = "Endpoint to schedule users' service times")
public class DiaryController {

	@Autowired
	private DiaryService diaryService;
	
	@Autowired
	private TotalDay totalDay;
	
	@GetMapping
	@Operation(summary = "List all scheduled times", description = "List all scheduled times",
			tags = {"Diaries"},
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200",
							content = {@Content(array = @ArraySchema(schema = @Schema(implementation = DiaryDto.class)))
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Page<DiaryDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit) {

//		List<DiaryDto> list = diaryService.findAll();
		Pageable pageable = PageRequest.of(page, limit);
		
		return ResponseEntity.ok().body(diaryService.findAll(pageable));
	}

	@GetMapping(value = "/date/{time_appointment}")
	@Operation(summary = "List all scheduled times", description = "List all scheduled times",
			tags = {"Diaries"},
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200",
							content = {@Content(array = @ArraySchema(schema = @Schema(implementation = DiaryDto.class)))
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Page<DiaryDto>> findByDate(
			@PathVariable(value = "time_appointment") String time_appointment,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit) {

		Pageable pageable = PageRequest.of(page, limit);

		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate date = LocalDate.parse(time_appointment, dt);

		return ResponseEntity.ok().body(diaryService.findByDate(date, pageable));
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find a scheduled times", description = "Find a scheduled times",
			tags = {"Diaries"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = DiaryDto.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<DiaryDto> findById(@PathVariable(value = "id") Long id) {
		DiaryDto DiaryDto = diaryService.findById(id);
		
		return ResponseEntity.ok().body(DiaryDto);
	}
	
	@PostMapping
	@Operation(summary = "Create a scheduled times", description = "Create a scheduled times",
			tags = {"Diaries"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = DiaryDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<DiaryDto> create(@RequestBody DiaryDto DiaryDto) {
		diaryService.create(DiaryDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(DiaryDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(DiaryDto);
	}
	
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update a scheduled times", description = "Update a scheduled times",
			tags = {"Diaries"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = DiaryDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<DiaryDto> update(@PathVariable(value = "id") Long id, @RequestBody DiaryDto DiaryDto) {
		diaryService.update(id, DiaryDto);
		
		return ResponseEntity.ok().body(DiaryDto);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a scheduled times", description = "Delete a scheduled times",
			tags = {"Diaries"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = DiaryDto.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		diaryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/total")
	@Operation(summary = "Shows the total value of services for the day.", description = "Shows the total value of services for the day.",
			tags = {"Diaries"},
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200",
							content = {@Content(array = @ArraySchema(schema = @Schema(implementation = DiaryDto.class)))
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Double> calcularTotal(Pageable pageable){
        Page<DiaryDto> list = diaryService.findAll(pageable);
        Double dt = totalDay.totDay(list);

        return ResponseEntity.ok().body(dt);
    }
}
