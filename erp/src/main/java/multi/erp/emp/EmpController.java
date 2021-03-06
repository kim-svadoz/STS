package multi.erp.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//emp테이블에서 작업하는 모든 내용에 대한 컨트롤러
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpController {
	@Autowired
	EmpService service;
	
	//로그인 페이지를 보기위한 요청
	@RequestMapping(value="/emp/login.do", method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	//실제 로그인 처리를 위한 요청
	//- 매개변수가 VO객체 : 스프링 내부의 DispatcherServlet에서 자동으로 입력된 파라미터들을 추출해서
	//					VO객체를 만들고 setter 메소드의 값으로 전달한다.
	@RequestMapping(value="/emp/login.do", method=RequestMethod.POST)
	public ModelAndView login(MemberVO loginUserInfo, 
			HttpServletRequest request) {
		System.out.println("로그인하기 위해서 사용자가 입력한 값==================="+loginUserInfo);
		ModelAndView mav = new ModelAndView();
		//user => DB연동 후 로그인된 사용자의 정보
		//loginUserInfo => 로그인 하기 위해서 사용자가 입력한 데이터를 VO로 만든 객체
		MemberVO user = service.login(loginUserInfo);
		//로그인한 사용자의 정보를 세션에 저장하는 작업
		//세션을 사용하기 위해서 매개변수에 HttpServletRequest를 정의
		//1. 세션을 생성 => getSession()을 이용해서 세션으랭성
		//			=> 무조건 생성해서 리턴 <처음 세션을 만들 때 사용>
		String viewName = "";
		if(user!=null) {
			//로그인 성공시
			HttpSession ses= request.getSession();
			//2. 세션에 데이터 공유
			ses.setAttribute("user", user);
			viewName = "login/ok";
		}else {
			//로그인 실패
			viewName = "login";
		}
		mav.setViewName(viewName);
		System.out.println(user.toString());
		return mav;
	}
	
	@RequestMapping("/emp/logout.do")
	public String logout(HttpSession session) {
		//로그아웃 처리를 위해 매개변수로 session을 받도록처리
		//사용하던 세션객체가 있으면 사용하던 세션 객체가 전달된다.
		if(session!=null) {
			//null이 아니라 세션이 존재하면 세션객체를해제
			session.invalidate();
		}
		return "redirect:/index.do";
	}
	
	@RequestMapping("/emp/insertView.do")
	public String insertView() {
		return "emp/insert";
	}
	// produces속성 : ajax요청 후 클라이언트로 전송할 데이터의 타입을 정의, application/text는 Ajax요청 후 클라이언트로 보내는 응답메시지의 타입이 text라는 뜻
	@RequestMapping(value="/emp/idCheck.do", method=RequestMethod.GET, produces="application/text;charset=utf-8")
	public @ResponseBody String idCheck(String id) {
		boolean state = service.idCheck(id);
		String result="";
		if(state) { // 이미 사용자가 입력한 아이디가 db에 저장되어 있다는 의미
			result = "사용불가능한 아이디";
		}else {
			result = "사용가능한 아이디";
		}
		return result;
	}
}