package com.ktehun.springproj;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktehun.domain.PagingCriteria;
import com.ktehun.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 클래스가 Sprint-test(JUnit4) 와 함께 동작
@ContextConfiguration(
      locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
) // 위 경로에서 root-context.xml 파일을 찾아 로딩
public class DAOTest {

   @Inject
   private BoardDAO dao;
   
//   @Test
//   public void insertBoard() throws Exception{
//	  BoardVO vo = new BoardVO();
//      vo.setTitle("시험삼아");
//      vo.setContent("잘되야 될텐데..");
//      vo.setWriter("webpjj");
//      int i = dao.insert(vo);
//      if(i == 1) {
//         System.out.println("글 등록 성공");
//      }
//      
//   
//   }
//  @Test
//  public void testListPage() throws Exception{
//   int page = 1;
// System.out.println(dao.listBoardPaging(page).toString());
//}
 @Test
 public void testgetBoardCount() throws Exception {
	 System.out.println(dao.getTotalBoardCnt());
 }
}
