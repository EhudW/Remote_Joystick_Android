package com.example.remotejoystick;

//import android.app.Dialog;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.*;
import androidx.annotation.RequiresApi;

public class JoystickView extends FrameLayout {
    public static class JoystickEventArgs {
        public final float px;
        public final float py;
        public final float pa;
        public final float pb;
        public JoystickEventArgs(float px, float py, float pa, float pb) {
            this.px = px;
            this.py = py;
            this.pa = pa;
            this.pb = pb;
        }
    }
    public static interface JoystickEventHandler {
        // all values between 0 to 1;
        // px,py is the joystick so it isn't accurate -> and probably won't be exactly 0 or 1
        // pa is horizontal bar, pb is vertical bar.
        void handle(Object sender, JoystickEventArgs args) ;
    }

    private float px=0.5f;
    private float py=0.5f;
    private float pa=0.5f;
    private float pb=0;

    public JoystickEventHandler onChange = null;
    public void resetValues() {
        //(findViewById(R.id.j)).layout(0,0,55,55);
        //((ImageView)(findViewById(R.id.j))).setForegroundGravity(Gravity.CENTER);
        // will automatically update pa pb and run (3 times) the updateObserver - which will raise this.onChange.handel()

        MotionEvent event = MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN,245,245,0);
        this.dispatchTouchEvent(event);
        //this.px = 0.5f;
        //this.py = 0.5f;
        ((SeekBar)(findViewById(R.id.seekBar_value_a))).setProgress(50);
        ((SeekBar)(findViewById(R.id.seekBar2_value_b))).setProgress(0);
    }
    private void updateObserver(){
        if (onChange != null)
            onChange.handle(this, new JoystickEventArgs(this.px, this.py, this.pa, this.pb));
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void init(Context c) {

    inflate(c ,R.layout.joystick_view,this);

    int rr =55;
        final JoystickView self = this;
        ((SeekBar)findViewById(R.id.seekBar_value_a)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                self.pa = progress/100f;
                self.updateObserver();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        ((SeekBar)findViewById(R.id.seekBar2_value_b)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                self.pb = progress/100f;
                self.updateObserver();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        //resetValues();
        //invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public JoystickView(Context context) {
        this(context, null);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public JoystickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public JoystickView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event == null ) {return true;}
        int dp = 1;
        float cx = 100*dp;
        float cy = 100*dp;
        float r = 55;//35*dp;
        float x=event.getX()-100;
        float y=event.getY()-100;
        int s = 0;
        int m = 290;
        if (x<r || y<r || x>m-r||y>m-r) return  true;
        (findViewById(R.id.j)).layout((int)(x-r)-s,(int)(y-r)-s,(int)(x+r)-s,(int)(y+r)-s);
         px = (x-r)/(m-r-r);
         py = (y-r)/(m-r-r);

        this.updateObserver();

        //invalidate();
        return  true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
