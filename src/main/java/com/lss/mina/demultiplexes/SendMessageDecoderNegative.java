package com.lss.mina.demultiplexes;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class SendMessageDecoderNegative implements MessageDecoder {
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {
        if (ioBuffer.remaining() <2 ){
            return MessageDecoderResult.NEED_DATA;
        }
        else{
            char symbol = ioBuffer.getChar();
            if (symbol == '+'){
                return MessageDecoderResult.OK;
            }
            else{
                return MessageDecoderResult.NOT_OK;
            }
        }
    }

    public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setSymbol(ioBuffer.getChar());
        sendMessage.setI(ioBuffer.getInt());
        sendMessage.setJ(ioBuffer.getInt());
        protocolDecoderOutput.write(sendMessage);
        return MessageDecoderResult.OK;
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //do nothing now
    }
}
