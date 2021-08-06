package com.renaissance.tech.service;

import com.renaissance.tech.dao.entity.Test;
import com.renaissance.tech.dao.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DiscardService {


	@Autowired
	private TestMapper testMapper;

	public Integer discard(String message) {


		final Test test = new Test();
		test.setName(String.valueOf(System.currentTimeMillis()));

		testMapper.insert(test);

		log.info("丢弃消息:{}", message);

		return test.getId();
	}
}
