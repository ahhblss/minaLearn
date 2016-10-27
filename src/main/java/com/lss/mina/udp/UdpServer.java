package com.lss.mina.udp;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioDatagramAcceptor();
        acceptor.setHandler(new UDPServerHandler());
        acceptor.getFilterChain().addLast("logger", new
                LoggingFilter());

        ((DatagramSessionConfig) acceptor.getSessionConfig())
                .setReuseAddress(true);
        acceptor.bind(new InetSocketAddress(9122));
    }
}
