package kr.co.bootcal.service;

import kr.co.bootcal.mapper.MgroupMapper;
import kr.co.bootcal.domain.MgroupListVO;
import kr.co.bootcal.domain.MgroupVO;
import kr.co.bootcal.mapper.MgroupMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MgroupServiceImpl implements MgroupService {



	private final MgroupMapper mapper;
	
	@Override
	public MgroupVO getOneGroup(MgroupVO mgroup) {
		return mapper.getOneGroup(mgroup);
	}

	@Override
	public boolean insertMyCalGroup(MgroupVO mgroup) {
		int result = mapper.insertMyCalGroup(mgroup);
		boolean result2 = false;
		if(result > 0){
			result2 = true;
		}
		return result2;
	}

	@Override
	public boolean makeGroupList(MgroupListVO mgroupList) {
		int result = mapper.makeGroupList(mgroupList);
		boolean result2 = false;
		if(result > 0){
			result2 = true;
		}
		return result2;
	}

	@Override
	public boolean makeGroup(MgroupVO mgroup) {
		int result = mapper.makeGroup(mgroup);
		boolean result2 = false;
		if(result > 0){
			result2 = true;
		}
		return result2;
	}

	@Override
	public MgroupListVO getGroupByNo(MgroupListVO mgroupList) {
		return mapper.getGroupByNo(mgroupList);
	}

	@Override
	public MgroupListVO getGroupByName(MgroupListVO mgroupList) {
		return mapper.getGroupByName(mgroupList);
	}

	@Override
	public boolean deleteGroup(MgroupListVO mgroupList) {
		int result = mapper.deleteGroup(mgroupList);
		boolean result2 = false;
		if(result > 0){
			result2 = true;
		}
		return result2;
	}

	@Override
	public List<MgroupListVO> getAllGroup() {
		return mapper.getList();
	}

}
