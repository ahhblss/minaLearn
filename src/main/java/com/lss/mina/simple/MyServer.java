package com.lss.mina.simple;

import com.lss.mina.simple.simplesms.CmccSimpleCodecFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Sean.liu on 2016/10/13.
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);

        //编写过滤器，编解码
//        acceptor.getFilterChain().addLast("codec",
//                new ProtocolCodecFilter(
//                        new TextLineCodecFactory(Charset.forName("UTF-8"),
//                                LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new CmccSimpleCodecFactory(Charset.forName("UTF-8"))
                )
                );

        //log filter
//        LoggingFilter loggingFilter = new LoggingFilter();
//        loggingFilter.setSessionOpenedLogLevel(LogLevel.DEBUG);
//        acceptor.getFilterChain().addLast("log",loggingFilter);

        //自定义filter
        acceptor.getFilterChain().addLast("myFilter",new ReferenceCountingFilter(new MyFilter()));

        //ioHandler 业务代码
        acceptor.setHandler(new MyIoHandler());



        acceptor.bind(new InetSocketAddress(9123));
    }
}
