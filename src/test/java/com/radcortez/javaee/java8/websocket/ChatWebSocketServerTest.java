package com.radcortez.javaee.java8.websocket;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.net.URL;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class ChatWebSocketServerTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.websocket")
                         .addAsWebInfResource("beans.xml");
    }

    @ArquillianResource
    private URL base;

    @Test
    public void testChat() throws Exception {
        final Session sessionClient1 = connectToChannel("radcortez", "javaone");
        final Session sessionClient2 = connectToChannel("observer", "javaone");
        sessionClient1.getAsyncRemote().sendText("Hi! Is good to be here");
        sessionClient2.getAsyncRemote().sendText("Awesome");
    }

    private Session connectToChannel(final String nick, final String channel) throws Exception {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        final URI path = new URI("ws://" + base.getHost() + ":" + base.getPort() + base.getPath() + "chat/" + channel);
        final Session session = container.connectToServer(ChatClientEndpoint.class, path);
        session.getBasicRemote().sendText("/nick " + nick);
        return session;
    }
}
