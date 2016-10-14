package com.lss.mina.simple;

import com.lss.mina.simple.simplesms.CmccSimpleCodecFactory;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Sean.liu on 2016/10/13.
 */
public class MyClient {
    public static void main(String[] args) {
        IoConnector client = new NioSocketConnector();
        client.setConnectTimeoutMillis(30000);

//        client.getFilterChain().addLast("codec",
//                new ProtocolCodecFilter(
//                        new TextLineCodecFactory(
//                                Charset.forName("UTF-8"),
//                                LineDelimiter.WINDOWS.getValue(),
//                                LineDelimiter.WINDOWS.getValue()
//                        )
//                ));


        client.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new CmccSimpleCodecFactory(Charset.forName("UTF-8"))
                ));


        client.setHandler(new CliendHandler());

        //future异步调用执行的放回值awaitUninterruptibly：异步转同步 listener:异步
        ConnectFuture connectFuture = client.connect(new InetSocketAddress(9123));

//        connectFuture.awaitUninterruptibly();

        connectFuture.addListener(new IoFutureListener<IoFuture>() {
            public void operationComplete(IoFuture ioFuture) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--------------------");
            }
        });

        System.out.println("connect successfully");


    }
}
