package com.example.remotejoystick;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FGModel {
    public ErrorEventHandler onError = null;
    private volatile PrintWriter telnet = null;
    private ExecutorService es = null;
    private long lastTime = 0;
    private int withinTime = 0;
    public FGModel() {
        this.es = Executors.newFixedThreadPool(1);
    }
    public void connect(String ipv4, int port) {
        if (telnet != null)
            telnet.close();
        telnet = null;
        es.execute(() -> {
            try {
                Socket fg = new Socket();
                // check HERE that the fg server is reachable
                fg.connect(new InetSocketAddress(ipv4, port),2000);
                telnet = new PrintWriter(fg.getOutputStream(), true);
            } catch (Exception e) {
                if (onError!=null)
                    onError.handle(this, new ErrorEventArgs("Connection Error", e));
            }
        });
    }
    private PrintWriter getTelnet() {return this.telnet;}

    public void updatePlaneData(float aileron, float elevator, float rudder, float throttle){
        // within 10 milliSeconds, allow addition of only new 2 task for ExecutorService to send tcp packets only
        long currTime = System.currentTimeMillis();
        if (currTime - lastTime < 10) {
            withinTime++;
        } else {
            withinTime = 0;
        }
        this.lastTime = currTime;
        if (withinTime > 2) return;

        // telnet_for_now is final reference to PrintWriter, this.getTelnet may be changed meanwhile
        final PrintWriter telnet_for_now = this.getTelnet();
        if (telnet_for_now != null) {
            es.execute(() -> {
                    // send data only if telnet_for_now it is still the current telnet [telnet_for_now is for sure not null]
                    if (telnet_for_now != this.getTelnet()) {
                        return;
                    }

                    telnet_for_now.print("set /controls/flight/aileron " + aileron + "\r\n");//[-1,1]
                    telnet_for_now.print("set /controls/flight/elevator " + elevator + "\r\n");//[-1,1]
                    telnet_for_now.print("set /controls/flight/rudder " + rudder + "\r\n");//[-1,1]
                    telnet_for_now.print("set /controls/engines/current-engine/throttle " + throttle + "\r\n");//[0,1]
                    telnet_for_now.flush();

                    if (telnet_for_now.checkError()) {
                        onError.handle(this, new ErrorEventArgs("Disconnected", null));
                        // manually close the unreachable socket, and it will ensure not spamming onError.handle()
                        this.telnet = null;
                    }
            });
        }
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
