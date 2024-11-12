package kr.co.bootcal;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.bootcal.domain.TestBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TestBoardHandler implements WebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> boardCLIENTS = new ConcurrentHashMap<String, WebSocketSession>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<TestBoardVO> board = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        boardCLIENTS.put(session.getId(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payLoad = (String)message.getPayload();
        JSONObject jsonMessage = new JSONObject(payLoad);

        if(jsonMessage.getString("type").equals("write")){
            TestBoardVO testBoardVO = new TestBoardVO();
            String title = jsonMessage.getString("title");
            String content = jsonMessage.getString("content");
            LocalDateTime originRegDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
            String regDate = originRegDate.format(formatter);
            Map<String, Object> attribute = session.getAttributes();
            String author = (String) attribute.get("nickname");
            if(!jsonMessage.getString("imageFile").equals("x")){
                String imageFile = jsonMessage.getString("imageFile");
                testBoardVO.setImageFile(imageFile);
            }
            testBoardVO.setTitle(title);
            testBoardVO.setContent(content);
            testBoardVO.setAuthor(author);
            testBoardVO.setRegDate(regDate);
            board.add(testBoardVO);

            String data = objectMapper.writeValueAsString(board);
            boardCLIENTS.entrySet().forEach( arg->{
                try {
                    arg.getValue().sendMessage(new TextMessage(data));
                } catch (IOException e){
                    e.printStackTrace();
                }
            });

        }else if(jsonMessage.getString("type").equals("viewContent")){
            String num = jsonMessage.getString("number");
            int number = Integer.parseInt(num);
            TestBoardVO boardVO = board.get(number);
            System.out.println(boardVO);
            String data = objectMapper.writeValueAsString(boardVO);
            try {
                session.sendMessage(new TextMessage(data));
            } catch (IOException e){
                e.printStackTrace();
            }
        }else {
            String data = objectMapper.writeValueAsString(board);
            boardCLIENTS.entrySet().forEach( arg->{
                try {
                    arg.getValue().sendMessage(new TextMessage(data));
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        boardCLIENTS.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
