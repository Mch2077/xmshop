package com.oracle.service;

import com.oracle.entity.Note;
import com.oracle.entity.PageBean;

public interface NoteService {


	int deleteNoteById(int id);

	int updateNote(Note note);

	Note getNoteById(int id);

	PageBean<Note> getAllNotesByPage(int page, int pagesize, String title, String content, String publisher);

	int addNote(Note note);

}
