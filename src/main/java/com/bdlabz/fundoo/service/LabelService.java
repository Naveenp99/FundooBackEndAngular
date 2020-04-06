package com.bdlabz.fundoo.service;

import java.util.List;
import com.bdlabz.fundoo.Dto.LabelDto;
import com.bdlabz.fundoo.entitymodel.Label;
import com.bdlabz.fundoo.entitymodel.Notes;

public interface LabelService {

	boolean createLabel(LabelDto dto, String token);
	
	boolean deleteLabel(String token, long id);
	
	boolean updateLabel(String token, long id, LabelDto dto);
	
	List<Label> getAllLabel(String token);
	
	Label getsingleLabel( String token, long labelId);
	
//	List<Notes> getAllNotes(String token, long labelid);
	
	boolean updatenoteIdinLabel( String token, long noteId, long labelId);
	
	List<Notes> getLabelBytitle( String token, String title);
	
}
