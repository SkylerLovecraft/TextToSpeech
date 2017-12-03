package skylerlovecraft.texttospeech;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.speech.tts.TextToSpeech;
import android.webkit.WebViewClient;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Locale;

public class WebViewActivity extends Activity {
    String webpage;
    WebView myWebView;
    static String html;
    String text;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(skylerlovecraft.texttospeech.R.layout.activity_web_view);
        myWebView = (WebView)findViewById(skylerlovecraft.texttospeech.R.id.wvMyWebView);
=======
        setContentView(R.layout.activity_web_view);
        myWebView = (WebView)findViewById(R.id.wvMyWebView);
>>>>>>> de81a4f85484052e1d442f5f6dcc14ef50de5eef
        webpage = this.getIntent().getStringExtra("webpage");
        myWebView.loadUrl(webpage);

        //AsyncClass for Jsoup in background thread
        AsyncClass task = new AsyncClass();
        task.execute(new String[] { webpage });
<<<<<<< HEAD
        myWebView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.setPitch(1);
            }
        });
        findViewById(skylerlovecraft.texttospeech.R.id.btnTTS).setOnClickListener(new View.OnClickListener() {
=======

        findViewById(R.id.btnSpeaker).setOnClickListener(new View.OnClickListener() {
>>>>>>> de81a4f85484052e1d442f5f6dcc14ef50de5eef
            @Override
            public void onClick(View v) {
                readPageContent(text);
            }
        });
        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeItStop();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private class AsyncClass extends AsyncTask<String, Void, String> {
        String html;
        //We do the Jsoup stuff in the background to prevent a long wait on the UI thread
        //returns string;
        @Override
        protected String doInBackground(String... strings) {
            Document doc = null;
            try {
                doc = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("do in background: " + doc.body().text());
            text = doc.body().text();
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        public AsyncClass() {
            super();
        }
    }
    /*public String readPageHTML(String webpage) {
        Ion.with(getApplicationContext())
                .load(webpage)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        html = result;
                        System.out.println(html);
                    }
                });
        return html;
    }
*/
    public void readPageContent(String str){
    System.out.println("content to speak " + str);
    textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void makeItStop() {
    textToSpeech.stop();
    }

}

