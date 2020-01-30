package test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController{
	@RequestMapping("/test.do")
	public ModelAndView test() {
		System.out.println("컨트롤러 요청완료");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg", "cash rule everything around me cream get that mony dolla dolla bill yo");
		mav.setViewName("test/result");
		return mav;
	}

}
