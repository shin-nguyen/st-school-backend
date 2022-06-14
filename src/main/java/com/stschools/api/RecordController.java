package com.stschools.api;

import com.stschools.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/records")
@RequiredArgsConstructor
public class RecordController {
	private final RecordService recordService;

	@GetMapping("/all-user/{recordId}")
	public ResponseEntity<?> getAll(@PathVariable(value = "recordId") Long recordId){
		return ResponseEntity.ok().body(recordService.listAll(recordId));
	}

	@GetMapping("/{recordId}")
	public ResponseEntity<?> getRecord(@PathVariable(value = "recordId") Long recordId){
		return ResponseEntity.ok().body(recordService.getById(recordId));
	}

}
