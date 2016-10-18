package cn.chenyk.systembar.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import cn.chenyk.systembar.Base.BaseActivity;
import cn.chenyk.systembar.R;
import cn.chenyk.systembar.Tools.ToastTool;


/**
 * Created by chenyk on 2016/10/13.
 * toolbar示例页面
 */
public class ToolbarActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleNavigationIcon(R.mipmap.tool_home);
        setTitleBgColor(R.color.colorPrimary);
        setLogoIcon(R.mipmap.ic_launcher);
        setMainTitle("主标题");
        setSubTitle("子标题");
    }

    @Override
    protected int getMenuLayoutId() {
        return R.menu.menu_main;
    }

    @Override
    public boolean callbackOnMenuAction(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_test:
                ToastTool.showNativeShortToast(ToolbarActivity.this, "测试");
                return true;
            case R.id.menu_search:
                ToastTool.showNativeShortToast(ToolbarActivity.this, "搜索");
                return true;
            case R.id.menu_settings:
                ToastTool.showNativeShortToast(ToolbarActivity.this, "设置");
                return true;
            case R.id.menu_check_update:
                ToastTool.showNativeShortToast(ToolbarActivity.this, "检查更新");
                return true;
            case R.id.menu_about:
                ToastTool.showNativeShortToast(ToolbarActivity.this, "关于");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 重写Navigation回调
     *
     * @param view
     */
    @Override
    protected void callbackOnClickNavigationAction(View view) {
        ToastTool.showNativeShortToast(ToolbarActivity.this, "首页");
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_statusbar_layout;
    }

    @Override
    public void initViews() {

    }
}
