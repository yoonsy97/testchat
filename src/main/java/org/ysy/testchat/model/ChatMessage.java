package org.ysy.testchat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    enum MessageType{
        ENTER,TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
