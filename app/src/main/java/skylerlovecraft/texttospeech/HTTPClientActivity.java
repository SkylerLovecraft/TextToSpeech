package skylerlovecraft.texttospeech;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;


public class HTTPClientActivity extends FragmentActivity implements DownloadCallback {

    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;

    protected WebView mWebView;

    private final int CHECK_CODE = 0x1;
    private String mResultString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(skylerlovecraft.texttospeech.R.layout.activity_httpclient);
        String website = this.getIntent().getStringExtra("webpage");
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), website);
        mWebView = (WebView)findViewById(skylerlovecraft.texttospeech.R.id.wvHttpClientView);
=======
        setContentView(R.layout.activity_httpclient);
        String website = this.getIntent().getStringExtra("webpage");
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), website);
        mWebView = (WebView)findViewById(R.id.wvHttpClientView);
>>>>>>> de81a4f85484052e1d442f5f6dcc14ef50de5eef
        checkTTS();
        startDownload();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void checkTTS(){
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, CHECK_CODE);
    }


    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }
    @Override
    public void updateFromDownload(String result) {
        mResultString+=result;
        if (result != null) {
            mWebView.loadData(result,"text/html",null);
            Log.d("HTTPClient","here1");
        } else {
            //mDataText.setText(getString(R.string.connection_error));
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            Log.d("HTTPClient","Here3");
            mNetworkFragment.cancelDownload();
        }
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                break;
            case Progress.CONNECT_SUCCESS:
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                break;
        }
    }
}
