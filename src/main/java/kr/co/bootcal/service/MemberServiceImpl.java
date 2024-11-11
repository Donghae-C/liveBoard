package kr.co.bootcal.service;

import kr.co.bootcal.domain.MemberVO;
import kr.co.bootcal.domain.MgroupVO;
import kr.co.bootcal.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	

	private final MemberMapper mapper;

	@Override
	public boolean register(MemberVO member) {
		boolean result = false;
		if (mapper.insert(member) == 1){
			result = true;
		}else {
			result = false;
		}
		return result;
	}

	@Override
	public MemberVO searchOne(MemberVO member) {
		return mapper.selectOne(member);
	}

	@Override
	public boolean delete(MemberVO member) {
		int result = mapper.delete(member);
		boolean result2 = false;
		if(result > 0) {
			result2 = true;
		}
		return result2;
	}

	@Override
	public boolean update(MemberVO member) {
		MemberVO updated = mapper.selectOne(member);
		updated.setM_pw(member.getM_pw());
		updated.setM_name(member.getM_name());
		updated.setM_email(member.getM_email());
		updated.setM_public(member.getM_public());
		int result = mapper.update(updated);
		boolean result2 = false;
		if(result>0) {
			result2 = true;
		}
		return result2;
	}

	@Override
	public List<MemberVO> listAll() {
		
		return mapper.getList();
	}

	@Override
	public List<MemberVO> listAllByAdmin() {
		return mapper.getListByAdmin();
	}

	@Override
	public List<MemberVO> listGroupMember(MgroupVO mgroup) {
		
		return mapper.getGroupMember(mgroup);
	}

	@Override
	public List<MemberVO> searchbyName(MemberVO member) {
		return mapper.searchName(member);
	}

	@Override
	public boolean login(MemberVO member) {
		MemberVO check = mapper.selectOne(member);
		boolean result = false;
		if(check == null) {

		}else {

			if(!check.getM_pw().equals(member.getM_pw())) {

			}else {

				result = true;
			}
		}
		return result;
	}

	@Override
	public List<MgroupVO> getMyGroup(MemberVO member) {
		
		
		return mapper.getMyGroup(member);
	}

}
