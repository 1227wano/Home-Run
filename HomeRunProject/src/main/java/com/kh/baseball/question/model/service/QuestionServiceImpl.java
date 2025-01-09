package com.kh.baseball.question.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.QuestionNotFoundException;
import com.kh.baseball.question.model.dao.QuestionMapper;
import com.kh.baseball.question.model.vo.Question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

	private final QuestionMapper mapper;
	private final ServletContext context;
	
	public int getTotalCount() {
		int totalCount = mapper.selectTotalCount();
		
		if(totalCount == 0) {
			throw new QuestionNotFoundException("없어!!");
		}
		return totalCount;
	}
	
	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5, 5);
	}
	
	private List<Question> getQuestionList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectQuestionList(rowBounds);
	}
	
	@Override
	public Map<String, Object> selectQuestionList(int currentPage) {
		
		int totalCount = mapper.selectTotalCount();
		PageInfo pi = getPageInfo(totalCount, currentPage);
		
		List<Question> question = getQuestionList(pi);
		log.info("게시글목록 : {}", question);
		
		Map<String, Object> map = new HashMap();
		map.put("question", question);
		map.put("pageInfo", pi);

		return map;
	}

	@Override
	public void insertQuestion(Question question) {
		
	}

	@Override
	public Question selectById(Long questionNo) {
		return null;
	}

	@Override
	public void updateQuestion(Question question) {
		
	}

	@Override
	public void deleteQuestion(Long questionNo) {
		
	}

}
