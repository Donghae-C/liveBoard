package kr.co.bootcal.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CalendarVO {
	private int c_no;
	private LocalDate c_date;
	private LocalTime c_time;
	private String m_id;
	private String g_name;
	private String c_title;
	private String c_content;
	private String c_link;
}
