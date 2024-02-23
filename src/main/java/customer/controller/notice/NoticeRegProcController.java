package customer.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.controller.Controller;
import customer.dao.NoticeDao;
import customer.vo.Notice;

public class NoticeRegProcController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("NoticeRegProcController");
		
		request.setCharacterEncoding("UTF-8");
		String title=request.getParameter("title");
		String content=request.getParameter("content");

		//Notice 객체생성
		Notice n=new Notice();
		n.setTitle(title);// title주입
		n.setContent(content); //content주입

		//NoticeDao 객체생성
		NoticeDao dao=new NoticeDao();
		dao.write(n); //NoticeDao클라스의 write메소드로 n을 보내준다.


		//목록으로 가기
		response.sendRedirect("notice.jsp");
		
		
	}

}
