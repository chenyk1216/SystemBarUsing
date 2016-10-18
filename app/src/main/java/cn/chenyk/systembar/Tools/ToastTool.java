package cn.chenyk.systembar.Tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chenyk on 2016/6/28.
 * Toast工具类
 */
public class ToastTool {
    /**
     * 原生Toast短显示
     *
     * @param context
     * @param tips
     */
    public static void showNativeShortToast(Context context, String tips) {
        Toast.makeText(context, tips, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生Toast长显示
     *
     * @param context
     * @param tips
     */
    public static void showNativeLongToast(Context context, String tips) {
        Toast.makeText(context, tips, Toast.LENGTH_LONG).show();
    }


}
