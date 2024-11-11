package kr.co.bootcal.mapper;

import kr.co.bootcal.domain.MemberVO;
import kr.co.bootcal.domain.MgroupVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
	//@Select("select * from member")
	public List<MemberVO> getList();
	public List<MemberVO> getListByAdmin();
	public List<MemberVO> getGroupMember(MgroupVO mgroup);
	public List<MgroupVO> getMyGroup(MemberVO member);
	public int insert(MemberVO member);
	public List<MemberVO> searchName(MemberVO member);
	public MemberVO selectOne(MemberVO member);
	public int delete(MemberVO member);
	public int update(MemberVO member);
}
