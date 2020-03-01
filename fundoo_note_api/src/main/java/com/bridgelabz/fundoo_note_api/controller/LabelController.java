package com.bridgelabz.fundoo_note_api.controller;

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
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo_note_api.dto.LableDto;
import com.bridgelabz.fundoo_note_api.dto.UpdateLabel;
import com.bridgelabz.fundoo_note_api.dto.UpdateNote;
import com.bridgelabz.fundoo_note_api.entity.Label;
import com.bridgelabz.fundoo_note_api.entity.Noteinfo;
import com.bridgelabz.fundoo_note_api.exception.UserNotFoundException;
import com.bridgelabz.fundoo_note_api.response.LabelResponse;
import com.bridgelabz.fundoo_note_api.response.NoteResponse;
import com.bridgelabz.fundoo_note_api.response.Response;
import com.bridgelabz.fundoo_note_api.service.LabelService;

@RestController
public class LabelController {

	@Autowired
	private LabelService labelService;


	/*
	 * API to add the Note Details
	 */
	@PostMapping(value = "/label/{token}/notes")
	public ResponseEntity<LabelResponse> createLabel(@RequestBody LableDto label, @PathVariable String token) {

		Label note = labelService.createLable(label, token);
		if (note != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new LabelResponse("Note Details Saved Successfully", note));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new LabelResponse("Already existing user",note));
		}
	}
	

	@PutMapping(value="/label/{id}")
	public ResponseEntity<NoteResponse> updateLabel(@PathVariable String id,@RequestBody UpdateLabel dto){
		  boolean label = labelService.updateLabel(id,dto);
		 if(label!=true){
			 return ResponseEntity.status(HttpStatus.ACCEPTED).body(new NoteResponse("Updated successfully", dto));
		 }
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new NoteResponse("Note Not Exist",  label));
	}
	/*
	 * API to get The All Note Details
	 */
	
	@GetMapping("/label/notes")
	public List<Label> getAllLables() {
		return labelService.getAllLables();
	}
	
	@DeleteMapping(value = "/label/{id}")
	public ResponseEntity<LabelResponse> deleteLabel(@PathVariable String id) {
		List<Label> result = labelService.removeLabel(id);
		if (result != null) {
			// return result;
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new LabelResponse("Record Deleted succesfully", result));
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
				.body(new LabelResponse("Already Deleted user",result));
	}
	
	/*
	 * API to getting the Note Details By Id
	 */

	@GetMapping(value = "/label/notes/{id}")
	public ResponseEntity<LabelResponse> getLabel(@PathVariable String id) {
		Label result = labelService.getLableById(id);
		if (result != null) {
			// String token = generator.jwtToken(result.getId());
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("Note Title", result.getLableName())
					.body(new LabelResponse("200-OK", result));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new LabelResponse("Label not existing",result));
	}
	/*
	 * API to getting the Note Details By User_Id
	 */

	@GetMapping(value = "/label/user/{id}")
	public ResponseEntity<LabelResponse> getLabelByUserId(@PathVariable String id) {
		List<Label> result = labelService.getLableByUserId(id);
		if (result != null) {
			// return result;
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("Note Title", "sucess")
					.body(new LabelResponse("200-OK", result));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new LabelResponse("user not Exist",result));
	}
	/*
	 * API to deleting the Note Details By _Id
	 */
	

}
