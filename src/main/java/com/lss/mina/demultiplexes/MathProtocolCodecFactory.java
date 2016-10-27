package com.lss.mina.demultiplexes;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class MathProtocolCodecFactory extends DemuxingProtocolCodecFactory {
    public MathProtocolCodecFactory(boolean server) {
        if (server) {
            super.addMessageEncoder(ResultMessage.class,
                    ResultMessageEncode.class);
            super.addMessageDecoder(SendMessageDecoderPositive.class);
            super.addMessageDecoder(SendMessageDecoderNegative.class);
        } else {
            super
                    .addMessageEncoder(SendMessage.class,
                            SendMessageEncode.class);
            super.addMessageDecoder(ResultMessageDecoder.class);
        }
    }
}
