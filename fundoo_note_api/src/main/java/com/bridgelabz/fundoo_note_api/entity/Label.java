package com.bridgelabz.fundoo_note_api.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long lId;
	@Column
	private String lableName;
	@Column
	private long userId;
	@Column
	private LocalDateTime UpdateDateAndTime;

    	@ManyToMany(targetEntity = Noteinfo.class)
	    private List<Noteinfo> note ;

		public long getlId() {
			return lId;
		}

		public void setlId(long lId) {
			this.lId = lId;
		}

		public String getLableName() {
			return lableName;
		}

		public void setLableName(String lableName) {
			this.lableName = lableName;
		}

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}

		public LocalDateTime getUpdateDateAndTime() {
			return UpdateDateAndTime;
		}

		public void setUpdateDateAndTime(LocalDateTime updateDateAndTime) {
			UpdateDateAndTime = updateDateAndTime;
		}

		public List<Noteinfo> getNote() {
			return note;
		}

		public void setNote(List<Noteinfo> note) {
			this.note = note;
		}
    	
}
