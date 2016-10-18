package cn.chenyk.systembar.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.chenyk.systembar.R;
import cn.chenyk.systembar.Tools.CommTool;
import cn.chenyk.systembar.Tools.StatusBarManager;


/**
 * Created by chenyk on 2016/6/25.
 * 页面基类
 * 默认：显示toolbar，标题居中，Navigation Button点击返回销毁当前页面，无menu菜单(可自行定义)
 */
public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout baseContent;
    private Toolbar toolbar;
    private TextView toolbarTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        initControlViews();
    }

    /**
     * 控件初始化操作
     */
    private void initControlViews() {
        baseContent = (FrameLayout) findViewById(R.id.base_content);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);

        //设置相关默认操作
        setTitleNavigationIcon(R.mipmap.icon_title_back);
        setTitleBgColor(R.color.base_title_color);

        setInflateMenu();

        baseContent.addView(LinearLayout.inflate(this, getContentViewID(), null));
        initViews();
        //左边Navigation Button监听回调
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackOnClickNavigationAction(v);
            }
        });
        //右边菜单item监听回调
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return callbackOnMenuAction(item);
            }
        });
    }

    /**
     * 为toolbar设置menu项
     */
    private void setInflateMenu() {
        if (getMenuLayoutId() > 0)
            toolbar.inflateMenu(getMenuLayoutId());
    }

    /**
     * 获取菜单资源id，默认无，子类可重写
     *
     * @return
     */
    protected int getMenuLayoutId() {
        return 0;
    }

    /**
     * 获取toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 设置主标题
     *
     * @param object
     */
    public void setMainTitle(Object object) {
        toolbar.setTitle(CommTool.getResultString(this, object));
    }

    /**
     * 设置子类标题
     *
     * @param object
     */
    public void setSubTitle(Object object) {
        toolbar.setSubtitle(CommTool.getResultString(this, object));
    }

    /**
     * 设置主标题字体颜色
     *
     * @param object
     */
    public void setMainTitleColor(Object object) {
        toolbar.setTitleTextColor(CommTool.getResultColor(this, object));
    }

    /**
     * 设置子标题字体颜色
     *
     * @param object
     */
    public void setSubTitleColor(Object object) {
        toolbar.setSubtitleTextColor(CommTool.getResultColor(this, object));
    }

    /**
     * 设置logoIcon
     *
     * @param resId
     */

    public void setLogoIcon(int resId) {
        toolbar.setLogo(resId);
    }

    /**
     * 设置中间标题
     *
     * @param object
     */
    public void setToolbarTitleTv(Object object) {
        toolbarTitleTv.setText(CommTool.getResultString(this, object));
    }

    /**
     * 设置标题栏背景颜色
     *
     * @param color
     */
    protected void setTitleBgColor(int color) {
        toolbar.setBackgroundColor(CommTool.getResultColor(this, color));
        //状态栏背景相关配置
        new StatusBarManager.builder(this)
                .setStatusBarColor(color)
                .create();
    }

    /**
     * 设置左边标题图标
     *
     * @param iconRes
     */
    public void setTitleNavigationIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    /**
     * 隐藏标题栏
     */
    protected void hideToolbar() {
        if (toolbar.getVisibility() == View.VISIBLE)
            toolbar.setVisibility(View.GONE);
    }

    /**
     * 不显示 NavigationButton
     */
    public void hideTitleNavigationButton() {
        toolbar.setNavigationIcon(null);
    }

    /**
     * Navigation Button点击回调，默认回退销毁页面，其他操作子类可重写
     *
     * @param view
     */
    protected void callbackOnClickNavigationAction(View view) {
        onBackPressed();
    }

    /**
     * menu点击回调，默认无，子类可重写
     *
     * @param item
     * @return
     */
    protected boolean callbackOnMenuAction(MenuItem item) {
        return false;
    }

    /**
     * 获取布局资源ID
     *
     * @return
     */
    public abstract int getContentViewID();


    /**
     * 控件初始化操作
     *
     * @return
     */
    public abstract void initViews();
}
