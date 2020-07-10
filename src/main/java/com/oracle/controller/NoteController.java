package com.oracle.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.entity.Note;
import com.oracle.entity.PageBean;
import com.oracle.entity.Producttype;
import com.oracle.entity.Users;
import com.oracle.service.NoteService;
import com.oracle.service.UserService;
import com.oracle.util.MD5Util;

/**
 *Created by ChenKeJun on 2020/7/9
 */
@Controller
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	
	@RequestMapping("/getnotebypage")
	public String getUsersByPage(@RequestParam(name="page",defaultValue="1") int page,Model model,
			String title, String content, String publisher){
		int pagesize = 5;
		PageBean<Note> pb =  noteService.getAllNotesByPage(page,pagesize,title,content,publisher);
		model.addAttribute("note", pb);
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("publisher", publisher);
		return "note";
	}

	@RequestMapping("/delnote")
	public String delNoteById(int id){
		noteService.deleteNoteById(id);
		return "redirect:getnotebypage";
	}
	
	@RequestMapping("/getnotebyid")
	public String toUpdatePage(int id,Model model){
		Note note = noteService.getNoteById(id);
		model.addAttribute("note", note);
		return "updatenote";
	}
	
	@RequestMapping("/updatenote")
	public String updateUser(Note note){
		noteService.updateNote(note);
		return "redirect:getnotebypage";
	}
	
	@RequestMapping(value="/addnote",method=RequestMethod.GET)
	public String toAddNote(){
		return "addnote";
	}
		
	@RequestMapping(value="/addnote",method=RequestMethod.POST)
	public String addNote(Note note){
		note.setPublishdate(new Date());
		noteService.addNote(note);
		return "redirect:getnotebypage";
	}
		
		
	
}
