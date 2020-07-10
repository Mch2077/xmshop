package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Note;

public interface NoteMapper {

	List<Note> getAllNotesByPage(@Param("page")int page, @Param("pagesize")int pagesize, @Param("title")String title, @Param("content")String content, @Param("publisher")String publisher);

	int getRowCount();

	int deleteNoteById(int id);

	int updateNote(Note note);

	Note getNoteById(int id);

	int addNote(Note note);

}
