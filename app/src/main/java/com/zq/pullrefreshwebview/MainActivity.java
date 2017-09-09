package com.zq.pullrefreshwebview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zq.pullrefreshwebview.widget.PtrClassicFrameLayout;
import com.zq.pullrefreshwebview.widget.PtrDefaultHandler;
import com.zq.pullrefreshwebview.widget.PtrFrameLayout;
import com.zq.pullrefreshwebview.widget.PtrHandler;

/****
 * WebView 实现下拉加载
 */
public class MainActivity extends AppCompatActivity {

    private PtrClassicFrameLayout mPtrFrame;
    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView= (WebView) findViewById(R.id.webview);
        mPtrFrame= (PtrClassicFrameLayout) findViewById(R.id.rotate_header_web_view_frame);
        initView();
    }

    private void initView(){
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://"))
                {
                    return false;
                }
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1000);
            }

        });
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mWebView, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }
        });
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(true);

        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 1000);
    }
    private void updateData() {
        mWebView.loadUrl("http://blog.csdn.net/dickyqie");
    }

}
