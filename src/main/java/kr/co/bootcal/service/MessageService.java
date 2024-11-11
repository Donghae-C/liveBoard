package kr.co.bootcal.service;

import kr.co.bootcal.domain.MemberVO;
import kr.co.bootcal.domain.MessageVO;

import java.util.List;

public interface MessageService {
    public List<MessageVO> getUnreadMessage(MemberVO member);
    public List<MessageVO> getAllMessage(MemberVO member);
    public boolean sendMessage(MessageVO message);
    public int readOneMessage(MessageVO message);
    public int readAllMessage(MemberVO member);
    public int deleteMessage(MessageVO message);
    public int countMessage(MemberVO member);
}
