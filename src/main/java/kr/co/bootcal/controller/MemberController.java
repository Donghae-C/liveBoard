package kr.co.bootcal.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.bootcal.domain.MemberVO;
import kr.co.bootcal.domain.MgroupListVO;
import kr.co.bootcal.domain.MgroupVO;
import kr.co.bootcal.service.MemberService;
import kr.co.bootcal.service.MgroupService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@AllArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
    private MemberService service;
    private MgroupService mgservice;

    @GetMapping(value = "/loginPage")
    public String loginPage(){
        return "/member/login";
    }

    @GetMapping("/registerPage")
    public String registerPage(){
        return "/member/memberregister";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        MemberVO member = (MemberVO)req.getSession().getAttribute("login");
        if(member != null){
            LocalDateTime nowTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-mm-dd HH:mm:ss");
            System.out.println(member.getM_id()+" 로그아웃 "+nowTime.format(formatter));
            HttpSession session = req.getSession(false);
            session.invalidate();
        }
        return "redirect:/";
    }
    @GetMapping("/myPage")
    public String myPage(HttpServletRequest req){
        MemberVO member = (MemberVO)req.getSession().getAttribute("login");
        if(member != null){
            System.out.println(member.getM_id()+" 마이페이지 진입");
            return "/member/mypage";
        }
        return "redirect:/";
    }
    @GetMapping("/updateProfile")
    public String updateProfile(HttpServletRequest req){
        MemberVO member = (MemberVO)req.getSession().getAttribute("login");
        if(member != null){
            System.out.println(member.getM_id()+" 정보수정페이지 진입");
            return "/member/updateProfile";
        }
        return "redirect:/";
    }

    @QueryMapping
    public MemberVO login(@Argument String id, @Argument String pw) {
        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-mm-dd HH:mm:ss");
        System.out.println(id+" 로그인 시도 "+ nowTime.format(formatter));
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = requestAttributes.getRequest();
        HttpSession session = req.getSession();
        MemberVO member = new MemberVO();
        member.setM_id(id);
        member.setM_pw(pw);
        if(service.login(member)) {
            member = service.searchOne(member);
            List<MgroupVO> gList =service.getMyGroup(member);
            System.out.println(id+" 로그인 성공 "+nowTime.format(formatter));
            session.setAttribute("login", member);
            session.setAttribute("gList", gList);
            return member;
        }else {
            System.out.println(id+" 로그인 실패 "+nowTime.format(formatter));
            return null;
        }
    }

    @QueryMapping
    public MemberVO myPage(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = requestAttributes.getRequest();
        MemberVO member = (MemberVO)req.getSession().getAttribute("login");
        member = service.searchOne(member);
        return member;
    }
    @QueryMapping
    public boolean idCheck(@Argument String id){
        return false;
    }


    @MutationMapping
    public Boolean registMember(@Argument String id, @Argument String pw, @Argument String name, @Argument String email, @Argument boolean mpublic){
        boolean result;
        MemberVO member = new MemberVO();
        if(mpublic){
            member.setM_public(0);
        }else{
            member.setM_public(1);
        }
        member.setM_id(id);
        member.setM_pw(pw);
        member.setM_name(name);
        member.setM_email(email);
        if (service.register(member)){
            result = true;
        }else {
            result = false;
        }
        return result;
    }

    @MutationMapping
    public Boolean updateMember(@Argument String id, @Argument String pw, @Argument String name, @Argument String email, @Argument boolean mpublic){
        boolean result;
        MemberVO member = new MemberVO();
        if(mpublic){
            member.setM_public(0);
        }else{
            member.setM_public(1);
        }
        member.setM_id(id);
        member.setM_pw(pw);
        member.setM_name(name);
        member.setM_email(email);
        if (service.update(member)){
            result = true;
        }else {
            result = false;
        }
        return result;
    }

    @MutationMapping
    public Boolean deleteMember(@Argument String pw){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = requestAttributes.getRequest();
        MemberVO member = (MemberVO)req.getSession().getAttribute("login");
        boolean result = false;
        if(!member.getM_pw().equals(pw)){
            return result;
        }
        if(member != null){
            LocalDateTime nowTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-mm-dd HH:mm:ss");
            System.out.println(member.getM_id()+" 탈퇴시도 "+nowTime.format(formatter));
            HttpSession session = req.getSession(false);
            session.invalidate();
            result = service.delete(member);
            if(result){
                System.out.println(member.getM_id()+" 탈퇴성공 "+nowTime.format(formatter));    
            }else {
                System.out.println(member.getM_id()+" 탈퇴실패 "+nowTime.format(formatter));
            }
        }
        return result;
    }

    @QueryMapping
    public Boolean testLogin(@Argument String id){
        System.out.println(id);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = requestAttributes.getRequest();
        try {
            HttpSession session = req.getSession();
            session.setAttribute("nickname", id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
