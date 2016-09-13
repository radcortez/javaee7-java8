package com.radcortez.javaee.java8.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

/**
 * @author Arun Gupta
 */
@ClientEndpoint
public class ChatClientEndpoint {
    @OnMessage
    public void onMessage(final String message) {
        System.out.println(message);
    }
}
