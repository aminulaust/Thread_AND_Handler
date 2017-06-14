package aminulaust.com.threadhandlers;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            TextView showinfo=(TextView)findViewById(R.id.showthreadinfo);
            showinfo.setText("Hello Aminul");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnclick(View view) {

        Runnable r=new Runnable() {
            @Override
            public void run() {
                long futureTime=System.currentTimeMillis()+10000;
                while (System.currentTimeMillis()<futureTime){
                    synchronized (this){
                        try {
                            wait(futureTime-System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                handler.sendEmptyMessage(0);

            }
        };
        Thread aminThread=new Thread(r);
        aminThread.start();


    }


}
