package com.example.remotejoystick;

public class ViewModel {
    private FGModel model;
    private float aileron = 0;
    private float elevator = 0;
    private float throttle = 0;
    private float rudder = 0;

    public Runnable onError = null;

    public void setValues_before_calc(float px, float py, float pa, float pb) {
        setValues((px-0.5f)*2,(py-0.5f)*2,(pa-0.5f)*2, pb);
    }
    private void setValues(float aileron, float elevator, float rudder, float throttle) {
        //model.updatePlaneData(aileron,elevator,rudder,throttle);
    }
    /*
    private void setAileron(float value)
    private void setRudder(float value)
    private void setThrottle(float value)
    private void setElevator(float value)
    */
    public ViewModel(FGModel model){
        this.model = model;
    }
    //
}
