package com.lss.mina.demultiplexes;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class ResultMessageDecoder implements MessageDecoder {
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {
        if (ioBuffer.remaining() < 4)
            return MessageDecoderResult.NEED_DATA;
        else if (ioBuffer.remaining() == 4)
            return MessageDecoderResult.OK;
        else
            return MessageDecoderResult.NOT_OK;
    }

    public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        ResultMessage rm = new ResultMessage();
        rm.setResult(ioBuffer.getInt());
        protocolDecoderOutput.write(rm);
        return MessageDecoderResult.OK;
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }
}
