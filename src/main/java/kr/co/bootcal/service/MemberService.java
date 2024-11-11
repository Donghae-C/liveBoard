package kr.co.bootcal.service;

import kr.co.bootcal.domain.MemberVO;
import kr.co.bootcal.domain.MgroupVO;

import java.util.List;

public interface MemberService {
	public boolean register(MemberVO member);
	public MemberVO searchOne(MemberVO member);
	public boolean delete(MemberVO member);
	public boolean update(MemberVO member);
	public List<MemberVO> listAll();
	public List<MemberVO> listAllByAdmin();
	public List<MemberVO> listGroupMember(MgroupVO mgroup);
	public List<MemberVO> searchbyName(MemberVO member);
	public boolean login(MemberVO member);
	public List<MgroupVO> getMyGroup(MemberVO member);
}
