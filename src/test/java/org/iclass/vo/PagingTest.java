package org.iclass.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.dao.CommunityDao;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
@Slf4j
class PagingTest {

	@Test
	void test() {
		Paging page = new Paging(23, 258, 10);
		log.info("page 프로퍼티 계산 결과 : {}",page);
		
	}
	
	@Test
	void getPageList() {
		Paging page = new Paging(2, 292, 10);
		log.info("page 프로퍼티 계산 결과 : {}",page);
		
		CommunityDao dao = CommunityDao.getInstance();
		Map<String,Integer> map= new HashMap<>();
		map.put("start", page.getStartNo());
		map.put("end", page.getEndNo());

		List<Community> list = dao.pagelist(map);
		log.info("2page list : {}",list);
	}
	
	@Test
	void getArticle() {//idx 259 article
		CommunityDao dao = CommunityDao.getInstance();
		Community co = dao.selectByIdx(259);
		log.info("259page article : {}",co);
	}

}
