package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;


@Controller
public class BookController {

	@RequestMapping(value = "/addlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[PhoneController.list]");
		
		//Dao 사용
		GuestbookDao guestbookDao = new GuestbookDao();
		
		//Dao의 메소드로 데이터 가져오기
		List<GuestbookVo> guestbookList = guestbookDao.getGuestbookList();
		System.out.println(guestbookList);
		
		//model 담기
		model.addAttribute("guestbookList", guestbookList);
		
		return "/WEB-INF/views/addlist.jsp";
	}
	//등록
		@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
		public String add(@ModelAttribute GuestbookVo guestbookVo) {
			System.out.println("[GuestController.add]");
			
			System.out.println(guestbookVo);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			
			guestbookDao.guestbookInsert(guestbookVo);
			
			return "redirect:/addlist";
		}
		
		
		//삭제폼
		@RequestMapping(value = "/deleteform", method = { RequestMethod.GET, RequestMethod.POST })
		public String deleteForm() {
			System.out.println("[GuestController.deleteForm]");
			
			return "/WEB-INF/views/deleteform.jsp";
		}
		
		//삭제
		@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST })
		public String delete(@ModelAttribute GuestbookVo guestbookVo) {
			System.out.println("[GuestController.delete]");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookDelete(guestbookVo);
			
			System.out.println(guestbookVo);			
			return "redirect:/addlist";

		}




}
