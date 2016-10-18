package cn.chenyk.systembar.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.chenyk.systembar.Tools.CommTool;

/**
 * Created by chenyk on 2016/10/18.
 * 状态栏视图
 */

public class StatusBarView extends View {

    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @return 状态栏矩形条
     */
    private static StatusBarView createStatusBarView(Activity activity, int color) {
        StatusBarView statusBarView = new StatusBarView(activity);
        statusBarView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, CommTool.getStatusBarHeight(activity)));
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }

    /**
     * 添加状态栏
     *
     * @param activity
     * @param color
     */
    public static void addStatusBarView(Activity activity, int color) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        if (decorView.getChildAt(0) instanceof StatusBarView) {
            decorView.getChildAt(0).setBackgroundColor(color);
        } else {
            StatusBarView statusView = createStatusBarView(activity, color);
            decorView.addView(statusView);
        }
    }
}
