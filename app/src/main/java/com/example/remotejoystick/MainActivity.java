package com.example.remotejoystick;

import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;

import java.net.Socket;
import java.io.*;

public class MainActivity extends AppCompatActivity {
    public LinearLayout l=null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*
        new Thread(){
            @Override
            public void run() {
                super.run();
                try{ MainActivity.main();
            }catch(Exception e) {
                }
            }
        }.start();
*/


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
