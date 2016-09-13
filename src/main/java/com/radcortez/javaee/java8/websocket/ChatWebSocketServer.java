package com.radcortez.javaee.java8.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Roberto Cortez
 */
@ServerEndpoint("/chat/{channel}")
public class ChatWebSocketServer {
    private static final ConcurrentHashMap<String, Set<Session>> channels = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(final Session session, @PathParam("channel") final String channel) {
        channels.computeIfAbsent(channel, s -> ConcurrentHashMap.newKeySet())
                .add(session);
    }

    @OnClose
    public void onClose(final Session session, @PathParam("channel") final String channel) {
        Optional.ofNullable(channels.get(channel))
                .orElseThrow(IllegalStateException::new)
                .remove(session);
    }

    @OnMessage
    public void onMessage(final Session session, final String message, @PathParam("channel") final String channel) {
        if (message.startsWith("/")) {
            processCommand(session, message);
            return;
        }

        channels.getOrDefault(channel, Collections.emptySet())
                .stream()
                .filter(Session::isOpen)
                .filter(s -> !s.getId().equals(session.getId()))
                .forEach(s -> s.getAsyncRemote().sendText(getMessage(session, message, channel)));
    }

    private String getMessage(Session session, String message, String channel) {
        return "From " +
               session.getUserProperties().getOrDefault("nick", "unknown") +
               " to #" + channel + " " +
               message;
    }

    private void processCommand(final Session session, final String command) {
        if (command.startsWith("/nick")) {
            session.getUserProperties().putIfAbsent("nick", command.replace("/nick", "").trim());
        }
    }
}
