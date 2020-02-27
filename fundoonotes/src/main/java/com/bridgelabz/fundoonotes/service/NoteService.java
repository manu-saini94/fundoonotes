package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.entity.Noteinfo;

public interface NoteService {

	public List<Noteinfo> getAllNotes(String id);

	public Noteinfo addNotes(NoteDto notes,String token);

	public Noteinfo getNote(String id);

	public void removeNotes(Noteinfo notes, String id);

	

}