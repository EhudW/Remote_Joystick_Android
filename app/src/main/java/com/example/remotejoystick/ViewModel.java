package com.example.remotejoystick;

import android.widget.Toast;

public class ViewModel {
    private FGModel model;
    private float aileron = 0;
    private float elevator = 0;
    private float throttle = 0;
    private float rudder = 0;
    private String IP = "";
    private String port = "";

    public ErrorEventHandler onError = null;

    public void setValues_from_joystick(float px, float py, float pa, float pb) {
        // all px,py,pa,pb are values between 0 to 1
        // convert px,py,pa  to values between -1 to 1
        setValues((px-0.5f)*2, (py-0.5f)*2, (pa-0.5f)*2, pb);
    }
    public void setValues(float aileron, float elevator, float rudder, float throttle) {
        this.aileron = aileron;
        this.elevator = elevator;
        this.rudder = rudder;
        this.throttle = throttle;
        // update the model for the changes:
        model.updatePlaneData(aileron,elevator,rudder,throttle);
    }
    public void setIP(String newVal) {
        this.IP = newVal;
    }
    public void setPort(String newVal) {
        this.port = newVal;
    }

    // return false if port is not representing valid tcp port
    public void connect() {
        int int_port = -1;
        try {
            int_port = Integer.parseInt(this.port);
        } catch ( Exception e) {
        }
        if (int_port <= 0 || int_port > 65535) {
            if (onError != null)
                onError.handle(this, new ErrorEventArgs("Tcp Port is int between 1 to 65535", null));
        } else {
            model.connect(this.IP, int_port);
        }
    }

    public ViewModel(FGModel model){
        this.model = model;
        final ViewModel self = this;
        // all errors of model go throw the view model:
        model.onError = new ErrorEventHandler() {
            @Override
            public void handle(Object sender, ErrorEventArgs args) {
                self.onError.handle(sender, args);
            }
        };
    }

    // never used, but for good practice:

    public float getAileron() { return aileron; }

    public float getElevator() { return elevator; }

    public float getThrottle() { return throttle; }

    public float getRudder() { return rudder; }

    public String getIP() { return IP; }

    public String getPort() { return port; }
}
