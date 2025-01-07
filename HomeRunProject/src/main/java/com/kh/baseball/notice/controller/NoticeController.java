package com.kh.baseball.notice.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.notice.model.service.NoticeService;
import com.kh.baseball.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

	private final NoticeService noticeService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("notices")
	public ModelAndView selectNoticeList(@RequestParam(value="page", defaultValue="1") int page) {
		Map<String, Object> map = noticeService.selectNoticeList(page);
		log.info("{}", map);
		return mv.setViewNameAndData("notice/list", map);
	}
	
	@GetMapping("insertForm")
	public String insertForm() {
		return "notice/insert_form";
	}
	
	@PostMapping("notices")
	public ModelAndView insertNotice(Notice notice, MultipartFile upfile, HttpSession session) {
		noticeService.insertNotice(notice, upfile);
		session.setAttribute("alertMsg", "게시글 등록 성공");
		return mv.setViewNameAndData("redirect:notices", null);
	}
	
	@GetMapping("notices/{id}")
	public ModelAndView selectNoticeById(@PathVariable(name="id") Long id) {
		Map<String, Object> responseData = noticeService.selectNoticeById(id);
		return mv.setViewNameAndData("notice/detail", responseData);
	}
	
	@PostMapping("notices/delete")
	public ModelAndView delteNotice(Long noticeNo, String attachMent, HttpSession session) {
		noticeService.deleteNotice(noticeNo, attachMent);
		session.setAttribute("alertMsg", "게시글이 삭제되었습니다.");
		return mv.setViewNameAndData("redirect:/notices", null);
	}
	
	@PostMapping("notices/update-form")
	public ModelAndView updateForm(Long noticeNo) {
		Map<String, Object> responseData = noticeService.selectNoticeById(noticeNo);
		return mv.setViewNameAndData("notice/update", responseData);
	}
	
	@PostMapping("notices/update")
	public ModelAndView updateNotice(Notice notice, MultipartFile upfile) {
		noticeService.updateNotice(notice, upfile);
		return mv.setViewNameAndData("redirect:/notices", null);
	}

	
}