package kr.co.bootcal.service;

import kr.co.bootcal.domain.MemberVO;
import kr.co.bootcal.domain.MessageVO;
import kr.co.bootcal.mapper.MessageMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageMapper mapper;
    @Override
    public List<MessageVO> getUnreadMessage(MemberVO member) {
        return mapper.getUnreadMessage(member);
    }

    @Override
    public List<MessageVO> getAllMessage(MemberVO member) {
        return mapper.getAllMessage(member);
    }

    @Override
    public boolean sendMessage(MessageVO message) {
        int result = mapper.sendMessage(message);
        boolean result2 = false;
        if(result > 0){
            result2 = true;
        }
        return result2;
    }

    @Override
    public int readOneMessage(MessageVO message) {
        return mapper.readOneMessage(message);
    }

    @Override
    public int readAllMessage(MemberVO member) {
        return mapper.readAllMessage(member);
    }

    @Override
    public int deleteMessage(MessageVO message) {
        return mapper.deleteMessage(message);
    }

    @Override
    public int countMessage(MemberVO member) {
        return mapper.countMessage(member);
    }
}
