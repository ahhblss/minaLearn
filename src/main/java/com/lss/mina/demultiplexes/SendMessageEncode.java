package com.lss.mina.demultiplexes;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class SendMessageEncode implements MessageEncoder<SendMessage> {
    public void encode(IoSession ioSession, SendMessage sendMessage, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(10);
        buffer.putChar(sendMessage.getSymbol());
        buffer.putInt(sendMessage.getI());
        buffer.putInt(sendMessage.getJ());
        buffer.flip();
        protocolEncoderOutput.write(buffer);
    }
}
