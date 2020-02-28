package com.bridgelabz.fundoo_note_api.serviceimplementation;

import java.util.List;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo_note_api.dto.Login;
import com.bridgelabz.fundoo_note_api.dto.NoteDto;
import com.bridgelabz.fundoo_note_api.entity.Noteinfo;
import com.bridgelabz.fundoo_note_api.entity.User;
import com.bridgelabz.fundoo_note_api.repository.NoteRepository;
import com.bridgelabz.fundoo_note_api.repository.UserRepository;
import com.bridgelabz.fundoo_note_api.service.NoteService;
import com.bridgelabz.fundoo_note_api.utility.JwtGenerator;

@Service
public class NoteImplementation implements NoteService {
private Noteinfo note=new Noteinfo();
	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JwtGenerator generate;
	
	@Transactional
	@Override
	public List<Noteinfo> getAllNotes(String id) {
		List<Noteinfo> notes = new ArrayList<>();   
		noteRepository.findAllById(Integer.parseInt(id)).forEach(notes::add);
		return notes;
	}

	@Transactional
	@Override
	public Noteinfo addNotes(NoteDto notes,String token) {

		Integer id=(Integer)generate.parseJWT(token);
		User user = userRepository.getUserById(id);
		if(user!=null){
			Noteinfo note = (Noteinfo)modelMapper.map(notes, Noteinfo.class);
			
			note.setColour("ash");
			note.setIsArchieved(0);
			note.setCreatedDateAndTime(LocalDateTime.now());
			note.setIsPinned(0);
			note.setIsTrashed(0);
			note.setReminder(null);
			note.setTitle(notes.getTitle());
			note.setDescription(notes.getDescription());
			return noteRepository.save(note);
		}
	    //BeanUtils.copyProperties(notes,Noteinfo.class);
		return null;
	}

	@Transactional
	@Override
	public Noteinfo getNote(String id) {
		Login userlogindto = new Login();
		List<Noteinfo> list = this.getAllNotes(id);

		for (Noteinfo ls : list) {
			if (ls.getNoteId() == Integer.parseInt(id)) {
				userlogindto.setEmail(ls.getTitle());
				userlogindto.setPassword(ls.getDescription());
				return ls;
			}
		}
		return null;

	}

	@Transactional
	@Override
	public void removeNotes(Noteinfo notes, String id) {
		List<Noteinfo> list = this.getAllNotes(id);
		for (Noteinfo ls : list) {
			if (ls.getNoteId() == (Integer.parseInt(id))) {
				noteRepository.delete(ls);

			}
		}
	}


}