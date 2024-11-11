package kr.co.bootcal.mapper;

import kr.co.bootcal.domain.CalendarVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CalendarMapper {
	public List<CalendarVO> getDate(CalendarVO calendar);
	public CalendarVO getCalByC_no(CalendarVO calendarVO);
	public List<CalendarVO> getMonth(@Param("c_month") String c_month, @Param("c_year") String c_year, @Param("g_name") String g_name);
	public int insertCal(CalendarVO calendar);
	public int deleteCal(CalendarVO calendar);
	public int updateCal(CalendarVO calendar);
	public List<CalendarVO> getMyMonth(@Param("c_month") String c_month, @Param("c_year") String c_year, @Param("g_name") String g_name, @Param("m_id") String m_id);
	public List<CalendarVO> getMyDate(CalendarVO calendar);
}
