package com.bridgelabz.fundoo_note_api.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private LocalDateTime date;
	@Column
	private boolean isVerified;
	@Column
	private long number;
	
	 @OneToMany(cascade = CascadeType.ALL,targetEntity = Noteinfo.class,fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
	 private List<Noteinfo> note;

	@ManyToMany
	//@JoinTable(name= "Collabarate",joinColumns = {@JoinColumn (name="note_id")},inverseJoinColumns = {@JoinColumn (name="user_id")})
	private List<Noteinfo> collablare;

	 @OneToMany(cascade = CascadeType.ALL,targetEntity = Label.class,fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
	 private List<Label> label;
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public List<Noteinfo> getNote() {
		return note;
	}

	public void setNote(List<Noteinfo> note) {
		this.note = note;
	}

	public List<Noteinfo> getCollablare() {
		return collablare;
	}

	public void setCollablare(List<Noteinfo> collablare) {
		this.collablare = collablare;
	}
	
}
