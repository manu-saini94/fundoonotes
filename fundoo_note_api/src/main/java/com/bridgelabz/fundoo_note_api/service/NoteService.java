package com.bridgelabz.fundoo_note_api.service;

import java.util.ArrayList;
import java.util.List;
import com.bridgelabz.fundoo_note_api.dto.NoteDto;
import com.bridgelabz.fundoo_note_api.dto.UpdateNote;
import com.bridgelabz.fundoo_note_api.entity.Noteinfo;

public interface NoteService {

	List<Noteinfo> getAllNotes();

	Noteinfo addNotes(NoteDto notes, String token);

	List<Noteinfo> updateNotes(String token, String id, UpdateNote updateDto);
	
	Noteinfo getNote(String id);

	List<Noteinfo> getNoteByUserId(String id);

	Noteinfo removeNotes(String token, String id);

	ArrayList<String> sortByName();

	List<String> ascSortByName();

	

	

}