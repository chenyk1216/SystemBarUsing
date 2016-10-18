package cn.chenyk.systembar.Tools;

import android.app.Activity;
import android.graphics.Color;

/**
 * Created by chenyk on 2016/10/17.
 * 公用工具类
 */

public class CommTool {
    /**
     * 获取颜色值
     *
     * @param activity
     * @param object
     * @return
     */
    public static int getResultColor(Activity activity, Object object) {
        if (object instanceof String) {// "#666666"
            return Color.parseColor((String) object);
        } else if (object instanceof Integer) {
            if ((Integer) object > 0)// R.string.app_color
                return activity.getResources().getColor((Integer) object);
            else return (Integer) object;// Color.BLUE
        } else throw new IllegalStateException("The current color is not found");
    }

    /**
     * 获取文本值
     *
     * @param activity
     * @param object
     * @return
     */
    public static String getResultString(Activity activity, Object object) {
        if (object instanceof String)//"标题"
            return (String) object;
        else if (object instanceof Integer && (Integer) object > 0) //R.string.app_name
            return activity.getResources().getString((Integer) object);
        else throw new IllegalStateException("The current string is not found");
    }

    /**
     * 计算颜色值
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终颜色
     */
    public static int calculateColorWithAlpha(int color, int alpha) {
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }
}
