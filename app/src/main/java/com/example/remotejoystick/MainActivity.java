package com.example.remotejoystick;

import android.os.Build;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private JoystickView joystickView=null;
    private ViewModel viewModel=null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.joystickView = (JoystickView) findViewById(R.id.joystickView);

        // DEBUG ONLY
        ((TextView)findViewById(R.id.debug)).setText(java.util.Calendar.getInstance().getTime().toString());

        this.viewModel = new ViewModel(new FGModel());
        this.joystickView.onChange=new JoystickView.JoystickEventHandler(){
            @Override
            public void handle(Object sender, JoystickView.JoystickEventArgs args) {
                viewModel.setValues_before_calc(args.px, args.py, args.pa, args.pb);
            }
        };
        final MainActivity self = this;
        this.viewModel.onError = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(self,"Connection Error",Toast.LENGTH_LONG).show();
            }
        };

        ((TextView)findViewById(R.id.ip_TextBox)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //if
                return false;
            }
        });
    }
    public static void main() throws Exception{
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
}
