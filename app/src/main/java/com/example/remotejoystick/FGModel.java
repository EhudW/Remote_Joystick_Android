package com.example.remotejoystick;

import java.io.PrintWriter;
import java.net.Socket;

public class FGModel {
    public ErrorEventHandler onError = null;
    private PrintWriter telnet = null;
    public FGModel() {}
    public void connect(String ipv4, int port){
    }
    public void updatePlaneData(float aileron, float elevator, float rudder, float throttle){

    }
    // need to use threadpool queue to do tasks,
    // each task can be :
    // 1.create socket 2.send data via tcp to fg 3.end the pool
    // updatePlaneData() -> new task of tcp sending package in the pool queue
    /* on error:
    try {
    } catch (Exception e){
        if (onError!=null)
            onError.handle(this, new ErrorEventArgs("Connection Error", e));
    }
    note! make sure the error won't occur again - since it will make infinity messages to the user
    */
    // which ViewModel is subscribed to
    // which MainActivity is subscribed to,
    // to show the user error msg (i.e. connection error)

    /* String ip="10.0.0.14"; int port = 5000;
        Socket fg=new Socket(ip,port);

        PrintWriter out=new PrintWriter(fg.getOutputStream(), true);



        ->(px,py,pa,pb) -? viewmodel (px-0.5)*2 -> model.sendtoFG()
        float v0=0,v1=1,v2=3,v3=3;
        out.print("set /controls/flight/aileron "++"\r\n");//[-1,1]
        out.print("set /controls/flight/elevator "+v1+"\r\n");//[-1,1]
        out.print("set /controls/flight/rudder "+v2+"\r\n");//[-1,1]
        out.print("set /controls/engines/current-engine/throttle "+v3+"\r\n");//[0,1]
        out.flush();

        out.close();
        //fg.close();

        */
}
