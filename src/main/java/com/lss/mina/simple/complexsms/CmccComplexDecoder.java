package com.lss.mina.simple.complexsms;

import com.lss.mina.simple.simplesms.SmsObject;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Administrator on 2016/10/16 0016.
 */
public class CmccComplexDecoder extends CumulativeProtocolDecoder{

    private final Charset charset;
    private final AttributeKey CONTEXT = new AttributeKey(getClass(),"context");

    public CmccComplexDecoder(Charset charset) {
        this.charset = charset;
    }

    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        Context context = getContext(ioSession);
        CharsetDecoder decoder = charset.newDecoder();

        int matchCount = context.getMatchCount();
        int line = context.getLine();
        IoBuffer buffer = context.getInnerBuffer();

        String statusLine = context.getStatusLine(),sender = context.getSender(),receiver =
                context.getReceiver(),length = context.getLength(),sms = context.getSms();

        while(ioBuffer.hasRemaining()){
            byte b = ioBuffer.get();
            matchCount++;
            buffer.put(b);

            if(b==10 && line<4){
                if (line == 0){
                    buffer.flip();

                    statusLine = buffer.getString(matchCount,decoder);
                    statusLine = statusLine.substring(0,statusLine.length()-1);

                    matchCount = 0;
                    buffer.clear();
                    context.setStatusLine(statusLine);
                }
                else if(line == 1){
                    buffer.flip();
                    sender = buffer.getString(matchCount,decoder);
                    sender = sender.substring(0,sender.length()-1);

                    matchCount = 0;
                    buffer.clear();
                    context.setSender(sender);
                }
                else if (line == 2){
                    buffer.flip();
                    receiver = buffer.getString(matchCount,decoder);
                    receiver = receiver.substring(0,receiver.length()-1);

                    matchCount = 0;
                    buffer.clear();
                    context.setReceiver(receiver);
                }
                else if(line == 3){
                    buffer.flip();
                    length = buffer.getString(matchCount,decoder);
                    length = length.substring(0,length.length()-1);

                    matchCount = 0;
                    buffer.clear();
                    context.setLength(length);
                }

                line++;
            }else if(line ==4){
                if (matchCount == Long.parseLong(length.split(": ")[1])){
                    buffer.flip();

                    sms = buffer.getString(matchCount,decoder);
                    context.setSms(sms);

                    context.setMatchCount(matchCount);
                    context.setLine(line);
                    break;
                }


            }

            context.setMatchCount(matchCount);
            context.setLine(line);

        }

        if (line==4 && Long.parseLong(context.getLength().split(": ")[1])==context.getMatchCount()){
            SmsObject smsObject = new SmsObject();

            smsObject.setSender(sender.split(": ")[1]);
            smsObject.setReceiver(receiver.split(": ")[1]);
            smsObject.setMessage(sms);
            protocolDecoderOutput.write(smsObject);
            context.reset();
            return true;
        }else{
            return false;
        }
    }

    private Context getContext(IoSession session){
        Context context = (Context) session.getAttribute(CONTEXT);
        if (context == null){
            context = new Context();
            session.setAttribute(CONTEXT,context);

        }
        return  context;
    }
}
