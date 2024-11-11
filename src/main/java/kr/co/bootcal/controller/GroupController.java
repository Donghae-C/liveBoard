package kr.co.bootcal.controller;

import kr.co.bootcal.domain.MgroupListVO;
import kr.co.bootcal.service.MemberService;
import kr.co.bootcal.service.MgroupService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/group/*")
public class GroupController {
    private MgroupService service;
    private MemberService mservice;

    @GetMapping("/gListPage")
    public String moveGroupList(Model model){
        List<MgroupListVO> gllist = service.getAllGroup();
        model.addAttribute("gllist", gllist);
        return "/group/grouplist";
    }
    @QueryMapping
    public List<MgroupListVO> callGroupList(){
        List<MgroupListVO> gllist = service.getAllGroup();
        return gllist;
    }

}
