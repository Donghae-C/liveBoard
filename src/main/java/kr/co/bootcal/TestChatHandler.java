package kr.co.bootcal;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TestChatHandler extends TextWebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message);
        System.out.println(session.getId());
        Map<String, Object> attribute = session.getAttributes();
        String nickname = (String) attribute.get("nickname");
        String id = nickname;
        String msg = message.getPayload();
        CLIENTS.entrySet().forEach( arg->{
            try {
                arg.getValue().sendMessage(new TextMessage(id + " : " + msg));
            } catch (IOException e){
                e.printStackTrace();
            }
        });

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CLIENTS.remove(session.getId());
    }

    public void sendMessageToAll(String message){
        CLIENTS.values().forEach(session ->{
            if(session.isOpen()){
                try{
                    session.sendMessage(new TextMessage(message));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
