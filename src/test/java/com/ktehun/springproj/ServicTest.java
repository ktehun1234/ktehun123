package com.ktehun.springproj;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktehun.domain.BoardVO;
import com.ktehun.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 클래스가 Sprint-test(JUnit4) 와 함께 동작
@ContextConfiguration(
      locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
) // 위 경로에서 root-context.xml 파일을 찾아 로딩
public class ServicTest {
	@Inject
	public BoardService service;
//	@Test
//	public void insert() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("서비스단 테스트");
//		vo.setContent("test");
//		vo.setWriter("홍길동");
//		if(service.insert(vo)) {
//			System.out.println("글 등록 성공");
//		}else{
//			System.out.println("글 등록 실패");
//		};
//	}
//	@Test
//	public void Read() throws Exception{
//		System.out.println(service.read(3).toString());
//	}
//	@Test
//	public void modify() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setNo(1);
//		vo.setTitle("서비스 테스트");
//		vo.setContent("잘 되냐?");
//		if(service.modify(vo)) {
//			System.out.println("글 등록 성공");
//		}else{
//			System.out.println("글 등록 실패");
//		};
//	}
//	@Test
//	public void remove()throws Exception{
//		if(service.remove(1)) {
//			System.out.println("글 삭제성공");
//		}else{
//			System.out.println("글 등록 실패");
//		};
//	}
	
	@Test
	public void list()throws Exception{
		service.listAll();
	}
}
