package com.lss.mina.demultiplexes;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class Server {
    public static void main(String[] args) throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor();
        LoggingFilter loggingFilter = new LoggingFilter();
        loggingFilter.setSessionOpenedLogLevel(LogLevel.DEBUG);
        acceptor.getFilterChain().addLast("log",loggingFilter);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
                5);
        acceptor.getFilterChain().addLast("logger", loggingFilter);
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new
                        MathProtocolCodecFactory(true)));
        acceptor.setHandler(new ServerHandler());
        acceptor.bind(new InetSocketAddress(9123));
    }
}
