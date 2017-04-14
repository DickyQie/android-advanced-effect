# Android 之WebView实现下拉刷新和其他相关刷新功能
 <p>最近项目中需要用到WebView下拉刷新的功能，经过查找资料终于完成了此功能，现在拿出来和大家分享一下。希望对大家有所帮助。</p> 
<p>效果如下图：</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201704/1041439-20170413180719892-999209982.gif"></p> 
<p>&nbsp;</p> 
<p>&nbsp;</p> 
<p>代码：</p> 
<p>&nbsp;</p> 
<p>activity.xml</p> 
<pre><code class="language-html">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.zq.pullrefreshwebview.MainActivity"&gt;
    &lt;com.zq.pullrefreshwebview.widget.PtrClassicFrameLayout
        android:id="@+id/rotate_header_web_view_frame"
        xmlns:cube_ptr="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/white"
        cube_ptr:ptr_duration_to_close="200"
        cube_ptr:ptr_duration_to_close_header="1000"
        cube_ptr:ptr_keep_header_when_refresh="true"
        cube_ptr:ptr_pull_to_fresh="false"
        cube_ptr:ptr_ratio_of_header_height_to_refresh="1.2"
        cube_ptr:ptr_resistance="1.8"&gt;
        &lt;WebView
            android:id="@+id/webview"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            /&gt;
    &lt;/com.zq.pullrefreshwebview.widget.PtrClassicFrameLayout&gt;
&lt;/RelativeLayout&gt;</code></pre> 
<p>&nbsp;Activity.Java</p> 
<pre><code class="language-java">/****
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
                mPtrFrame.refreshComplete();
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
        }, 100);
    }
    private void updateData() {
        mWebView.loadUrl("https://my.oschina.net/zhangqie/blog");
    }

}</code></pre> 
<p>权限：</p> 
<pre><code class="language-html">
&lt;uses-permission android:name="android.permission.INTERNET" /&gt;
&lt;uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /&gt;
&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /&gt;

</code></pre> 
<p><strong><span style="color:#800000">注：上图效果是参考他人写的</span></strong></p> 
<p>更多控件实现下拉刷新效果图：&nbsp; 可以参考 <a href="https://github.com/DickyQie/android-advanced-effect/tree/pull-down-refresh" target="_blank" rel="nofollow">https://github.com/DickyQie/android-advanced-effect/tree/pull-down-refresh </a>&nbsp; 我就是参考此案例来实现的</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201704/1041439-20170414114146611-1508771868.png"></p> 
<p>&nbsp;</p> 
<p>&nbsp;</p> 
<p>更多实现方式 https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh</p> 
<p>&nbsp;</p> 
<span id="OSC_h1_1"></span>
