package com.lss.mina.udp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.InetSocketAddress;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class UDPClient {
    public static void main(String[] args) throws CharacterCodingException {
        IoConnector connector = new NioDatagramConnector();
        connector.setHandler(new UDPClientHandler());
        ConnectFuture connFuture = connector.connect(new
                InetSocketAddress(
                "localhost", 9122));
        connFuture.awaitUninterruptibly();
        IoSession session = connFuture.getSession();
        IoBuffer buffer = IoBuffer.allocate(3);
        buffer.putString("OK!",
                Charset.forName("UTF-8").newEncoder());
        buffer.flip();
        WriteFuture future = session.write(buffer);
        future.awaitUninterruptibly();
        connector.dispose();
    }
}
