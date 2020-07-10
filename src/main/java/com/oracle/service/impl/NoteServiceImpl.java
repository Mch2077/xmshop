package com.oracle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entity.Note;
import com.oracle.entity.PageBean;
import com.oracle.mapper.NoteMapper;
import com.oracle.service.NoteService;


@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	private NoteMapper noteMapper;

	/**
	 * 根据商品分页查询显示仿写公告的相关功能
	 */
	@Override
	public PageBean<Note> getAllNotesByPage(int page, int pagesize, String title, String content, String publisher) {
		
		List<Note> notes = noteMapper.getAllNotesByPage(page,pagesize,title,content,publisher);
		
		PageBean<Note> pageBean = new PageBean<>();
		pageBean.setPage(page);
		pageBean.setPagesize(pagesize);
		pageBean.setList(notes);
		pageBean.setRowcount(getTotalRowCount());

		int rowcount = getTotalRowCount();
		if (rowcount % pagesize == 0)
			pageBean.setPages(rowcount / pagesize);
		else {
			pageBean.setPages(rowcount / pagesize + 1);
		}
		return pageBean;
	}
	
	private int getTotalRowCount(){
		int count = noteMapper.getRowCount();
		return count;
	}

	@Override
	public int deleteNoteById(int id) {
		return noteMapper.deleteNoteById(id);
	}

	@Override
	public int updateNote(Note note) {
        return noteMapper.updateNote(note);
	}

	@Override
	public Note getNoteById(int id) {
		return noteMapper.getNoteById(id);
	}

	@Override
	public int addNote(Note note) {
		// TODO Auto-generated method stub
		return noteMapper.addNote(note);
	}
	
	

	
	
}
