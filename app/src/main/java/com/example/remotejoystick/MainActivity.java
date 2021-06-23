package com.example.remotejoystick;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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
        // load xml
        setContentView(R.layout.activity_main);
        // get the JoystickView within the loaded xml
        this.joystickView = (JoystickView) findViewById(R.id.joystickView);

        // DEBUG ONLY
        ((TextView)findViewById(R.id.debug)).setText(java.util.Calendar.getInstance().getTime().toString());
        // create ViewModel and bind this view changes to set the model view propeties
        this.viewModel = new ViewModel(new FGModel());
        // anonymous classes can refer local final variables
        final MainActivity self = this;
        // subscribe to viewModel onError event (thrown when there is network error)
        this.viewModel.onError = new ErrorEventHandler() {
            @Override
            public void handle(Object sender, ErrorEventArgs args) {
                Toast.makeText(self, args.description, Toast.LENGTH_LONG).show();
            }
        };
        // bind viewModel to joystick data
        this.joystickView.onChange=new JoystickView.JoystickEventHandler(){
            @Override
            public void handle(Object sender, JoystickView.JoystickEventArgs args) {
                viewModel.setValues_from_joystick(args.px, args.py, args.pa, args.pb);
            }
        };
        // bind viewModel to ip_TextBox text
        ((TextView)findViewById(R.id.ip_TextBox)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }
            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setIP(s.toString());
            }
        });
        // bind viewModel to port_TextBox text
        ((TextView)findViewById(R.id.port_TextBox)).addTextChangedListener(new TextWatcher() {
            //private int prevPort = 6000;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }
            // wikipedia: tcp ports within [1,65535]
            @Override
            public void afterTextChanged(Editable s) { viewModel.setPort(s.toString());  }
        });

        // set onClick event handle to click on connect_Button
        ((Button)findViewById(R.id.connect_Button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // viewModel.connect() will call viewModel.onError.handle()
                // if the port property of viewModel is invalid tcp port
                // we already set onError as handler which tell the user about the problem
                viewModel.connect();
            }
        });
    }
}
