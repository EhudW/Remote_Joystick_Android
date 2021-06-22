package com.example.remotejoystick;

import java.io.PrintWriter;
import java.net.Socket;

public class FGModel {
    public Runnable onError = null;
    private PrintWriter telnet = null;
    public FGModel() {}
    public void connect(String ipv4, int port){}
    public void updatePlaneData(float aileron, float elevator, float rudder, float throttle){}
    // need to use threadpool queue to do tasks,
    // each task can be :
    // 1.create socket 2.send data via tcp to fg 3.end the pool
    // updatePlaneData() -> new task of tcp sending package in the pool queue
    // class FGModel also need to has event onError
    // which ViewModel is subscribed to
    // which MainActivity is subscribed to,
    // to show the user error msg (i.e. connection error)
}
