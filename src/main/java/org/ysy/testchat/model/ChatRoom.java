package org.ysy.testchat.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import org.ysy.testchat.service.ChatService;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions=new HashSet<>(); // 채팅방 멤버  session 목록

    @Builder
    public ChatRoom(String roomId,String name){
        this.roomId=roomId;
        this.name=name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender()+"님이 입장했습니다.");

        }
        sendMessage(chatMessage,chatService);

    }

    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session->chatService.sendMessage(session,message));

    }
}
