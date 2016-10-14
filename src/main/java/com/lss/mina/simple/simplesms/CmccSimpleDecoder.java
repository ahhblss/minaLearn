package com.lss.mina.simple.simplesms;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Sean.liu on 2016/10/14.
 */
public class CmccSimpleDecoder extends CumulativeProtocolDecoder {

    private final Charset charset;

    public CmccSimpleDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        CharsetDecoder decoder = charset.newDecoder();
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);

        int matchCount = 0;
        String statusLine ="",sender="",receiver="",sms="",length="";
        int i = 1;

        while (ioBuffer.hasRemaining()){

            byte b = ioBuffer.get();
            buffer.put(b);

            //10==> ascii '\n'
            if (b==10 && i<5){
                matchCount++;

                if (i==1){
                    buffer.flip();
                    statusLine = buffer.getString(matchCount,decoder);
                    statusLine = statusLine.substring(0,statusLine.length()-1);

                    matchCount = 0;
                    buffer.clear();
                }

                else if(i==2){
                    buffer.flip();
                    sender = buffer.getString(matchCount,decoder);
                    sender = sender.substring(0,sender.length()-1);

                    matchCount=0;
                    buffer.clear();

                }
                else if(i==3){
                    buffer.flip();
                    receiver = buffer.getString(matchCount,decoder);
                    receiver = receiver.substring(0,receiver.length()-1);

                    matchCount = 0;
                    buffer.clear();
                }
                else if(i==4){
                    buffer.flip();
                    length = buffer.getString(matchCount,decoder);
                    length = length.substring(0,length.length()-1);

                    matchCount = 0;
                    buffer.clear();
                }
                i++;
            }
            else if(i==5){
                matchCount++;
                //sms内容的长度
                if (matchCount==Long.parseLong(length.split(": ")[1])){

                    buffer.flip();
                    sms = buffer.getString(matchCount,decoder);
                    i++;
                    break;
                }
            }
            else {
                matchCount++;
            }

        }

        SmsObject smsObject = new SmsObject();
        smsObject.setSender(sender.split(": ")[1]);
        smsObject.setReceiver(receiver.split(": ")[1]);
        smsObject.setMessage(sms);
        protocolDecoderOutput.write(smsObject);

        return false;
    }
}
