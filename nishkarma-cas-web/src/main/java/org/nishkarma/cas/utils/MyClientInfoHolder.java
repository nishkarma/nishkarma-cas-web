package org.nishkarma.cas.utils;


public final class MyClientInfoHolder {

    private static final ThreadLocal<MyClientInfo> clientInfoHolder = new ThreadLocal<MyClientInfo>();

    public static void setClientInfo(final MyClientInfo clientInfo) {
        clientInfoHolder.set(clientInfo);
    }

    public static MyClientInfo getClientInfo() {
        return clientInfoHolder.get();
    }

    public static void clear() {
        clientInfoHolder.remove();
    }
}
