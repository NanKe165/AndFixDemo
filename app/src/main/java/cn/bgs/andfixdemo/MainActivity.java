package cn.bgs.andfixdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showToast(View view){
        Log.e(TAG, null );
    }

    public void andFix(View view) {
        AndFixUtils instance = AndFixUtils.getInstance(this, "1");
        instance.checkAndFix();
    }

}