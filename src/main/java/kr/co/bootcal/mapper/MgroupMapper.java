package kr.co.bootcal.mapper;

import kr.co.bootcal.domain.MgroupListVO;
import kr.co.bootcal.domain.MgroupVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgroupMapper {
	public MgroupVO getOneGroup(MgroupVO mgroup);
	public List<MgroupListVO> getList();
	public int insertMyCalGroup(MgroupVO mgroup);
	public int makeGroupList(MgroupListVO mgroupList);
	public int makeGroup(MgroupVO mgroup);
	public MgroupListVO getGroupByNo(MgroupListVO mgroupList);
	public MgroupListVO getGroupByName(MgroupListVO mgroupList);
	public int deleteGroup(MgroupListVO mgroupList);

}
