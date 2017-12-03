package skylerlovecraft.texttospeech;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final String LOGTAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        findViewById(R.id.btnNextActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }


    protected void nextActivity(){
        String myWebpage;
        EditText myEditText = (EditText) findViewById(R.id.etWebsite);
        myWebpage = myEditText.getText().toString();
        if(!myWebpage.startsWith("http://")){
            if(!myWebpage.startsWith("https://")){
                myWebpage = "https://" + myWebpage;
            }
        }
        Intent myWebViewActivity = new Intent(this,WebViewActivity.class);
        myWebViewActivity.putExtra("webpage",myWebpage);
        this.startActivity(myWebViewActivity);
    }

    protected void startHttpActivity(){
        String myWebpage;
        EditText myEditText = (EditText) findViewById(R.id.etWebsite);
        myWebpage = myEditText.getText().toString();
        if(!myWebpage.startsWith("http://")){
            if(!myWebpage.startsWith("https://")){
                myWebpage = "https://" + myWebpage;
            }
        }
        Intent myWebViewActivity = new Intent(this,HTTPClientActivity.class);
        myWebViewActivity.putExtra("webpage",myWebpage);
        this.startActivity(myWebViewActivity);
    }
}
