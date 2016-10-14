package com.lss.mina.simple.simplesms;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * Created by Sean.liu on 2016/10/14.
 */
public class CmccSimpleCodecFactory implements ProtocolCodecFactory {

    private final CmccSimpleEncoder cmccSimpleEncoder;
    private final CmccSimpleDecoder cmccSimpleDecoder;

    public CmccSimpleCodecFactory() {
        this(Charset.defaultCharset());
    }

    public CmccSimpleCodecFactory(Charset charset) {
        this.cmccSimpleDecoder = new CmccSimpleDecoder(charset);
        this.cmccSimpleEncoder = new CmccSimpleEncoder(charset);
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return cmccSimpleDecoder;
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return cmccSimpleEncoder;
    }
}
