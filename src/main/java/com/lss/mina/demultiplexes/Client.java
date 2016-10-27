package com.lss.mina.demultiplexes;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * Created by Sean.liu on 2016/10/27.
 */
public class Client {

    public static void main(String[] args) throws Throwable {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        LoggingFilter loggingFilter = new LoggingFilter();
        loggingFilter.setSessionOpenedLogLevel(LogLevel.DEBUG);
        connector.getFilterChain().addLast("logger", loggingFilter);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new
                        MathProtocolCodecFactory(false)));
        connector.setHandler(new ClientHandler());
        connector.connect(new InetSocketAddress("localhost", 9123));
    }

}
