package kr.co.bootcal.mapper;

import kr.co.bootcal.domain.MemberVO;
import kr.co.bootcal.domain.MessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    public List<MessageVO> getUnreadMessage(MemberVO member);
    public List<MessageVO> getAllMessage(MemberVO member);
    public int sendMessage(MessageVO message);
    public int readOneMessage(MessageVO message);
    public int readAllMessage(MemberVO member);
    public int deleteMessage(MessageVO message);
    public int countMessage(MemberVO member);
}
