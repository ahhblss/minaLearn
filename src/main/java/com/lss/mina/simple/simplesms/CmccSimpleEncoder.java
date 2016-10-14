package com.lss.mina.simple.simplesms;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by Sean.liu on 2016/10/14.
 */
public class CmccSimpleEncoder extends ProtocolEncoderAdapter {

    private final Charset charset;

    public CmccSimpleEncoder(Charset charset) {
        this.charset = charset;
    }

    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        SmsObject sms = (SmsObject) o;
        CharsetEncoder encoder = charset.newEncoder();
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);

        String statusLine = "M sip:wap.fetion.com.cn SIP-C/2.0";
        String sender = sms.getSender();
        String receiver = sms.getReceiver();
        String smsContent = sms.getMessage();

        buffer.putString(statusLine+"\n",encoder);
        buffer.putString("S: " + sender + '\n', encoder);
        buffer.putString("R: " + receiver + '\n', encoder);
        buffer.putString("L: " + (smsContent.getBytes(charset).length)+ "\n",encoder);
        buffer.putString(smsContent, encoder);

        buffer.flip();

        protocolEncoderOutput.write(buffer);

    }
}
