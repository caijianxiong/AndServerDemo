package com.personal.cjx.andserverdemo;

import android.content.Context;
import android.util.Log;

import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class AndServerManager {
    private static final String TAG = "AndServerManager";

    private Server mServer;
    private static final int PORT=8080;

    /**
     * Create server.
     */
    public AndServerManager(Context context) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName("0.0.0.0"); //获取IP地址 0.0.0.0
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        mServer = AndServer.webServer(context)
                .inetAddress(inetAddress) //地址
                .port(PORT) //端口
                .timeout(10, TimeUnit.SECONDS) //延迟10s
                .listener(new Server.ServerListener() { //监听Server
                    @Override
                    public void onStarted() {
                        // TODO The server started successfully.
                        Log.d(TAG, "onStarted: ");

                    }

                    @Override
                    public void onStopped() {
                        // TODO The server has stopped.
                        Log.d(TAG, "onStarted: ");
                    }

                    @Override
                    public void onException(Exception e) {
                        Log.e(TAG, "onException: ", e);
                        // TODO An exception occurred while the server was starting.
                    }
                })
                .build();
    }

    /**
     * Start server.
     */
    public void startServer() {
        if (mServer.isRunning()) {
            // TODO The server is already up.
            Log.i(TAG, "startServer: The server is already up");
        } else {
            Log.i(TAG, "server start");
            mServer.startup();
            Log.i(TAG, "server started");
        }
    }

    /**
     * Stop server.
     */
    public void stopServer() {
        if (mServer.isRunning()) {
            mServer.shutdown();
        } else {
            Log.w("AndServer", "The server has not started yet.");
        }
    }

}
