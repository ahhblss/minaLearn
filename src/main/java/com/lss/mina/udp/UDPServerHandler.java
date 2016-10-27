package com.lss.mina.udp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class UDPServerHandler extends IoHandlerAdapter {
    private final static Logger log = LoggerFactory
            .getLogger(UDPServerHandler.class);
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        IoBuffer buffer = (IoBuffer) message;
        String msg = buffer.getString(3,
                Charset.forName("UTF-8").newDecoder());
        log.info("The message received is [" + msg + "]");
    }
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.debug("******************* Session Closed!");
    }
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.debug("******************* Session Created!");
    }
    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        log.debug(session + "******************* Session Idle!");
    }
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("******************* Session Opened!");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        log.error(cause.getMessage());
        session.close(false);
    }
    @Override
    public void messageSent(IoSession session, Object message) throws
            Exception {
        log.debug("******************* messageSent!");
    }
}
