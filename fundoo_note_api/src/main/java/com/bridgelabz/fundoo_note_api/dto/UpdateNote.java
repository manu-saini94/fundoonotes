package com.bridgelabz.fundoo_note_api.dto;

public class UpdateNote {

	public String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "UpdateNote [title=" + title + "]";
	}

}
