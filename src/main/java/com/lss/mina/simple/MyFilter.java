package com.lss.mina.simple;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

/**
 * Created by Sean.liu on 2016/10/13.
 */
public class MyFilter implements IoFilter{
    public void destroy() throws Exception {
        System.out.println("destory");
    }

    public void exceptionCaught(NextFilter nextFilter, IoSession ioSession, Throwable throwable) throws Exception {
        System.out.println("exception");
        nextFilter.exceptionCaught(ioSession,throwable);
    }

    public void filterClose(NextFilter nextFilter, IoSession ioSession) throws Exception {
        System.out.println("filterClose");
        nextFilter.filterClose(ioSession);
    }

    public void filterWrite(NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        System.out.println("filterWrite");
        nextFilter.filterWrite(ioSession,writeRequest);
    }

    public void init() throws Exception {
        System.out.println("init");
    }

    public void inputClosed(NextFilter nextFilter, IoSession ioSession) throws Exception {
        System.out.println("inputClose");
        nextFilter.inputClosed(ioSession);
    }

    public void messageReceived(NextFilter nextFilter, IoSession ioSession, Object o) throws Exception {
        System.out.println("messageReceive");
        nextFilter.messageReceived(ioSession,o);
    }

    public void messageSent(NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        System.out.println("messageSend");
        nextFilter.messageSent(ioSession,writeRequest);
    }

    public void onPostAdd(IoFilterChain ioFilterChain, String s, NextFilter nextFilter) throws Exception {
        System.out.println("postAdd");
    }

    public void onPostRemove(IoFilterChain ioFilterChain, String s, NextFilter nextFilter) throws Exception {
        System.out.println("postRemove");
    }

    public void onPreAdd(IoFilterChain ioFilterChain, String s, NextFilter nextFilter) throws Exception {
        System.out.println("preAdd");
    }

    public void onPreRemove(IoFilterChain ioFilterChain, String s, NextFilter nextFilter) throws Exception {
        System.out.println("preRemove");
    }

    public void sessionClosed(NextFilter nextFilter, IoSession ioSession) throws Exception {
        System.out.println("sessionClosed");
        nextFilter.sessionClosed(ioSession);
    }

    public void sessionCreated(NextFilter nextFilter, IoSession ioSession) throws Exception {
        System.out.println("sessionCreate");
        nextFilter.sessionCreated(ioSession);
    }

    public void sessionIdle(NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        System.out.println("sessionIdle");
        nextFilter.sessionIdle(ioSession,idleStatus);
    }

    public void sessionOpened(NextFilter nextFilter, IoSession ioSession) throws Exception {
        System.out.println("sessionOpened");
        nextFilter.sessionOpened(ioSession);
    }
}
