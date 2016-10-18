package cn.chenyk.systembar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.chenyk.systembar.Base.BaseActivity;
import cn.chenyk.systembar.R;
import cn.chenyk.systembar.Tools.StatusBarManager;


/**
 * Created by chenyk on 2016/10/12.
 * 状态栏效果页面
 */
public class StatusBarActivity extends BaseActivity {
    public static String EXTRA_TINTTYPE_KEY = "extra_tintType_key";//色彩类型key
    public static String EXTRA_ALPHA_KEY = "extra_alpha_key";//透明度key
    public static String EXTRA_COLOR_KEY = "extra_Color_key";//颜色key

    private StatusBarManager.TintType mTintType;
    private int mAlpha;
    private int mStatusBarColor;

    /**
     * 快捷启动当前页面
     */
    public static void startCurrentActivity(Activity activity, String tintType, String alpha,
                                            String statusBarColor) {
        Intent intent = new Intent(activity, StatusBarActivity.class);
        intent.putExtra(EXTRA_TINTTYPE_KEY, tintType);
        intent.putExtra(EXTRA_ALPHA_KEY, alpha);
        intent.putExtra(EXTRA_COLOR_KEY, statusBarColor);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String tintTypeCode = intent.getStringExtra(EXTRA_TINTTYPE_KEY);
        mTintType = "0".equals(tintTypeCode) ? StatusBarManager.TintType.PURECOLOR
                : StatusBarManager.TintType.GRADIENT;
        mAlpha = Integer.valueOf(intent.getStringExtra(EXTRA_ALPHA_KEY));
        String colorCode = intent.getStringExtra(EXTRA_COLOR_KEY);
        mStatusBarColor = "red".equals(colorCode) ? R.color.title_color_red :
                "green".equals(colorCode) ? R.color.title_color_green : R.color.title_color_blue;
        setTitleBgColor(mStatusBarColor);
        setToolbarTitleTv("状态栏效果");
        new StatusBarManager.builder(this)
                .setStatusBarColor(mStatusBarColor)//状态栏颜色
                .setTintType(mTintType)//色彩类型：纯色、渐变
                .setAlpha(mAlpha)//不透明度
                .create();
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_statusbar_layout;
    }

    @Override
    public void initViews() {

    }
}
