package com.lss.mina.simple;

import com.lss.mina.simple.simplesms.SmsObject;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sean.liu on 2016/10/13.
 */
public class MyIoHandler extends IoHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MyIoHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        SmsObject sms = (SmsObject) message;
        log.info("receive msg:"+sms.getMessage());
    }
}
