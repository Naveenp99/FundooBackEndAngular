package com.bdlabz.fundoo.Dto;

public class LabelDto {

	private long noteId;
	private String titlename;

	public String getTitlename() {
		return titlename;
	}

	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

	@Override
	public String toString() {
		return "LabelDto [noteId=" + noteId + ", titlename=" + titlename + "]";
	}


	
	
}
