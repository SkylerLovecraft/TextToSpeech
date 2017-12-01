package skylerlovecraft.texttospeech;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.speech.tts.TextToSpeech;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Locale;

public class WebViewActivity extends Activity {
    String webpage;
    WebView myWebView;
    static String html;
    Document document;
    String webpageText, text;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(skylerlovecraft.myinternetproject.R.layout.activity_web_view);
        myWebView = (WebView)findViewById(skylerlovecraft.myinternetproject.R.id.wvMyWebView);
        webpage = this.getIntent().getStringExtra("webpage");
        myWebView.loadUrl(webpage);
        html = readPageHTML(webpage);
        System.out.println(html);
        AsyncClass task = new AsyncClass();
        task.execute(new String[] { webpage });

        findViewById(skylerlovecraft.myinternetproject.R.id.btnSpeaker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPhasersToStun(text);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private class AsyncClass extends AsyncTask<String, Void, String> {
        String html;
        //
        @Override
        protected String doInBackground(String... strings) {
            Document doc = null;
            try {
                doc = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("do in background: " + doc.body().text());
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
    public String readPageHTML(String webpage) {
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

    public void setPhasersToStun(String str){

    textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            textToSpeech.setLanguage(Locale.US);
            textToSpeech.setPitch(1);
        }
    });
    textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
    }

}

