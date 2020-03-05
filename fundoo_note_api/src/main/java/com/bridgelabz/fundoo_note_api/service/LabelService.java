package com.bridgelabz.fundoo_note_api.service;

import java.util.ArrayList;
import java.util.List;
import com.bridgelabz.fundoo_note_api.dto.LableDto;
import com.bridgelabz.fundoo_note_api.dto.NoteDto;
import com.bridgelabz.fundoo_note_api.dto.UpdateLabel;
import com.bridgelabz.fundoo_note_api.entity.Label;

public interface LabelService {

	List<Label> getAllLables();

	Label createLable(LableDto notes, String token);

	List<Label> removeLabel(String token, String id);

	List<Label> getLableByUserId(String id);

	Label getLableById(String id);

	Label updateLabel(String id, String token, UpdateLabel LabelDto);

	ArrayList<String> sortByName();

	List<String> ascsortByName();

	Label addNotesToLabel(NoteDto notes, String token, String labelId);


}