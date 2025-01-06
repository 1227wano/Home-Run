package com.kh.baseball.common;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.notice.model.vo.Notice;

@Component
public class ModelAndViewUtil {
	
	public ModelAndView setViewNameAndData(String viewName, Map<String, Object> modelData) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(viewName);
		if(modelData != null) {
			mv.addAllObjects(modelData);
		}
		return mv;
	}

 
	
	
}
