package com.bridgelabz.fundoonote.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
import com.bridgelabz.fundoonote.dto.NoteDto;
import com.bridgelabz.fundoonote.dto.ReminderDto;
import com.bridgelabz.fundoonote.dto.UpdateNote;
import com.bridgelabz.fundoonote.entity.Noteinfo;
import com.bridgelabz.fundoonote.response.NoteResponse;
import com.bridgelabz.fundoonote.service.LabelService;
import com.bridgelabz.fundoonote.service.NoteService;

@RestController
@RequestMapping("/notes")
@PropertySource("classpath:message.properties")
public class NotesController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private LabelService labelService;
	
	@Autowired
	private Environment env;
	
	/*
	 * API to add the Note Details
	 */
	@PostMapping(value = "/users/{token}")
	public ResponseEntity<NoteResponse> createNotes(@RequestBody NoteDto notes, @PathVariable String token) {

		// notes.setUser(new User(Integer.parseInt(id)));
		Noteinfo note = noteService.addNotes(notes, token);
		if (note != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("201"),note,200));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new NoteResponse(env.getProperty("102"),notes,401));
		}
	}

	/*
	 * API to Update the Note Details By Id
	 */
	@PutMapping(value = "/{id}/users/{token}")
	public ResponseEntity<NoteResponse> updateNote(@PathVariable String token, @PathVariable String id,
			@RequestBody UpdateNote dto) {
		List<Noteinfo> note = noteService.updateNotes(token, id, dto);
		if (note != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("201"),note,200));
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new NoteResponse(env.getProperty("204"),note,200));
	}

	/*
	 * API to getting the Note Details By Id
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<NoteResponse> getNote(@PathVariable String id) {
		Noteinfo note = noteService.getNote(id);
		if (note != null) {
			// String token = generator.jwtToken(result.getId());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("201"),note,200));
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new NoteResponse(env.getProperty("204"),note,200));
	}
	/*
	 * API to getting the Note Details By User_Id
	 */

	@GetMapping(value = "/users/{token}")
	public ResponseEntity<NoteResponse> getNotesByUserId(@PathVariable String token) {
		List<Noteinfo> note = noteService.getNoteByUserId(token);
		if (note != null) {
			// return result;
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("211"),note,200));
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new NoteResponse(env.getProperty("102"),note,200));
	}

	/*
	 * API to deleting the Note Details By _Id
	 */
	@DeleteMapping(value = "/{noteId}/users/{token}")
	public ResponseEntity<NoteResponse> deleteNote(@PathVariable String noteId, @PathVariable String token) {
		Noteinfo note = noteService.removeNotes(token, noteId);
		
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("203"),note,200));
		
		
	}

	/*
	 * API for pin a Note
	 */
	@PutMapping("/pin/{noteId}/users/{token}")
	public ResponseEntity<NoteResponse> pin(@PathVariable String noteId, @PathVariable String token) {
		  Noteinfo note = noteService.pinNote(noteId, token);
		   return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("205"),note,200));
	}

	/* API for archieve a Note */
	@PutMapping("/archieve/{noteId}/users/{token}")
	public ResponseEntity<NoteResponse> archieve(@PathVariable String noteId, @PathVariable String token) {
		    Noteinfo note = noteService.archieveNote(noteId, token);
		    return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("206"),note,200));
	}

	/*
	 * API for updating color to a Note
	 */
	@PutMapping("/{colour}/{noteId}/users/{token}")
	public ResponseEntity<NoteResponse> addColour(@PathVariable String noteId, @PathVariable String colour,
			@PathVariable String token) {
		   String note = noteService.addColour(noteId, token, colour);
		   return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("207"),note,200));

	}

	/* API for getting all archieve notes Notes */
	@GetMapping("/notes/getAllArchieve/users/{token}")
	public ResponseEntity<NoteResponse> getArchieve(@PathVariable String token) {
		  List<Noteinfo> note = noteService.getarchieved(token);
		  return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("206"),note,200));
	}

	/*
	 * API for getting all trashed Notes
	 */
	@GetMapping("/getAlltrashed/users/{token}")
	public ResponseEntity<NoteResponse> getTrashed(@PathVariable String token) {
	        List<Noteinfo> note = noteService.getAlltrashednotes(token);
	        return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("203"),note,200));
	}

	/*
	 * API for getting all Pinned Notes
	 */
	@GetMapping("/getAllPinned/users/{token}")
	public ResponseEntity<NoteResponse> getPinned(@PathVariable String token) {
	        List<Noteinfo> note = noteService.getAllPinneded(token);
	        return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("205"),note,200));
	}

	/*
	 * API for adding remainder to Notes
	 */
	@PostMapping("/addremainder/{noteId}/users/{token}")
	public ResponseEntity<NoteResponse> addRemainder(@PathVariable String token, @PathVariable String noteId,
			@RequestBody ReminderDto remainder) {
		     String note = noteService.addReminder(noteId, token, remainder);
		     return ResponseEntity.status(HttpStatus.CREATED)
						.body(new NoteResponse(env.getProperty("206"),note,200));
	}

	/*
	 * API for removing remainder Notes
	 */
	@DeleteMapping("/removeRemainder/{noteId}/users/{token}")
	public ResponseEntity<NoteResponse> removeRemainder(@PathVariable String token, @PathVariable String noteId) {
		     String note = noteService.removeReminder(noteId, token);
		      return ResponseEntity.status(HttpStatus.CREATED)
						.body(new NoteResponse(env.getProperty("206"),note,200));
	}
	/*
	 * API to Sort the Note Details By Title in ascending order
	 */

	@GetMapping(value = "/notes/ascendingSortByTitle")
	public ResponseEntity<NoteResponse> ascSortByNoteTitle() {
		List<String> note = noteService.ascSortByName();
		 return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("212"),note,200));
	}
	
	/*
	 * API to Sort the Note Details By Title in descending order
	 */

	@GetMapping(value = "/notes/descSortByTitle")
	public ResponseEntity<NoteResponse> descSortByNoteTitle() {
		List<String> note = noteService.sortByName();
		 return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("213"),note,200));
	}
	
	/*
	 * API to Sort the Label Details By Name
	 */

	@GetMapping(value = "/ascendingSortByName")
	public ResponseEntity<NoteResponse> ascSortByLabelName() {
		List<String> note = labelService.ascsortByName();
		 return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("212"),note,200));
	}

	

	/*
	 * API to Sort the Label Details By Name
	 */

	@GetMapping(value = "/descSortByName")
	public ResponseEntity<NoteResponse> descSortByLabelName() {
		List<String> note = labelService.sortByName();
		 return ResponseEntity.status(HttpStatus.CREATED)
					.body(new NoteResponse(env.getProperty("213"),note,200));
	}
	/*
	 * API to get The All Note Details
	 */

	@GetMapping("/details")
	public List<Noteinfo> getAllNotes() {

		return noteService.getAllNotes();
	}

}
