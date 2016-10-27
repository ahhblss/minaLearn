package com.lss.mina.demultiplexes;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class ClientHandler extends IoHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(ClientHandler.class);

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        SendMessage sm = new SendMessage();
        sm.setI(100);
        sm.setJ(99);
        sm.setSymbol('+');
        session.write(sm);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        ResultMessage rs = (ResultMessage) message;
        LOGGER.info(String.valueOf(rs.getResult()));
    }
}
