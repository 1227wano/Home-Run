package com.kh.baseball.question.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.question.model.vo.Question;

@Mapper
public interface QuestionMapper {

	int selectTotalCount();
	
	List<Question> selectQuestionList(RowBounds rowBounds);
	
	
	
	
}
