package com.lss.mina.demultiplexes;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class ResultMessageEncode implements MessageEncoder<ResultMessage> {
    public void encode(IoSession ioSession, ResultMessage resultMessage, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(4);
        buffer.putInt(resultMessage.getResult());
        buffer.flip();
        protocolEncoderOutput.write(buffer);
    }
}
