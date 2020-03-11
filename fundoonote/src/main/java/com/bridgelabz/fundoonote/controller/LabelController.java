package com.bridgelabz.fundoonote.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.dto.LableDto;
import com.bridgelabz.fundoonote.dto.NoteDto;
import com.bridgelabz.fundoonote.dto.UpdateLabel;
import com.bridgelabz.fundoonote.entity.Label;
import com.bridgelabz.fundoonote.response.LabelResponse;
import com.bridgelabz.fundoonote.response.NoteResponse;
import com.bridgelabz.fundoonote.response.Response;
import com.bridgelabz.fundoonote.service.LabelService;

@RestController
@RequestMapping("/labels")
public class LabelController {

	@Autowired
	private LabelService labelService;

	/*
	 * API to add the Label Details
	 */
	@PostMapping(value = "/{token}/notes")
	public ResponseEntity<LabelResponse> createLabel(@RequestBody LableDto label, @PathVariable String token) {

		Label note = labelService.createLable(label, token);
		if (note != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new LabelResponse("Note Details Saved Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new LabelResponse("Already existing user"));
		}
	}

	/*
	 * API to add the  Notes To Label 
	 */
	@PostMapping(value = "/{lid}/{token}")
	public ResponseEntity<LabelResponse> addNotesToLabel(@RequestBody NoteDto label, @PathVariable String token,@PathVariable long lid) {

		Label lnote = labelService.addNotesToLabel(label, token,lid);
		if (lnote != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new LabelResponse("Note Details Saved Successfully To Label"));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new LabelResponse("Already existing user"));
		}
	}
	

	/*
	 * API to add the Existing notes to Label Details
	 */
	@PostMapping(value = "/{lid}/{token}/{noteId}")
	public ResponseEntity<LabelResponse> addExistingNotesToLabel(long noteId, String token, long labelId) {
		boolean label = labelService.addExistingNotesToLabel(noteId, token, labelId);
		if(label)
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new LabelResponse("Note added to the label"));
		else
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new LabelResponse("Note not added to the label"));
	}
	
	/*
	 * API to add the update Label Details
	 */
	@PutMapping(value = "/{id}/users/{token}")
	public ResponseEntity<LabelResponse> updateLabel(@PathVariable String token,@PathVariable long id, @RequestBody UpdateLabel dto) {
		Label label = labelService.updateLabel(token,id, dto);
		if (label != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new LabelResponse("Updated successfully"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LabelResponse("Note Not Exist"));
	}
	
	/*
	 * API to add the delete Label Details
	 */
	@DeleteMapping(value = "/{id}/{token}")
	public ResponseEntity<LabelResponse> deleteLabel(@PathVariable long id,@PathVariable String token) {
		List<Label> result = labelService.removeLabel(token,id);
		if (result != null) {
			// return result;
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new LabelResponse("Record Deleted succesfully"));
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
				.body(new LabelResponse("Already Deleted user"));
	}
	
	/*
	 * API to getting the Label Details By Id
	 */

	@GetMapping(value = "/notes/{id}")
	public ResponseEntity<LabelResponse> getLabel(@PathVariable long id) {
		Label result = labelService.getLableById(id);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("Note Title", result.getLableName())
					.body(new LabelResponse("200-OK",result));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LabelResponse("Label not existing"));
	}
	
	
	/*
	 * API to get The All label Details
	 */

	@GetMapping("/notes")
	public List<Label> getAllLables() {
		return labelService.getAllLables();
	}

		
	/*
	 * API to getting the Label Details By User_Id
	 */

	@GetMapping(value = "/user/{token}")
	public ResponseEntity<LabelResponse> getLabelByUserId(@PathVariable String token) {
		List<Label> result = labelService.getLableByUserId(token);
		if (result != null) {
			// return result;
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("Note Title", "sucess")
					.body(new LabelResponse("200-OK"));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LabelResponse("user not Exist"));
	}
	/*
	 * API to deleting the Note Details By _Id
	 */

}
