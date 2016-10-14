package com.lss.mina.simple;

import com.lss.mina.simple.simplesms.SmsObject;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Sean.liu on 2016/10/13.
 */
public class CliendHandler extends IoHandlerAdapter {
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        SmsObject smsObject = new SmsObject();
        smsObject.setSender("liushunshun");
        smsObject.setReceiver("liuliu");
        smsObject.setMessage("哈哈 for test");
        session.write(smsObject);
    }
}
