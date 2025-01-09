package com.kh.baseball.question.model.service;

import java.util.Map;

import com.kh.baseball.question.model.vo.Question;

public interface QuestionService {

	Map<String, Object> selectQuestionList(int currentPage);
	
	void insertQuestion(Question question);
	
	Question selectById(Long questionNo);
	
	void updateQuestion(Question question);
	
	void deleteQuestion(Long questionNo);
}
