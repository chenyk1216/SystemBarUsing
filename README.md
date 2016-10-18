# SystemBarUsing

##**简单介绍**

**Toolbar** 是  Material Design 风格中的一个导航控件 ，Google 也大力推荐大家使用 Toolbar，以此来取代先前推出的 Actionbar 。Toolbar不仅包含了ActionBar最近添加的大多数特性，同时添加推出了兼容库，使得在低版本设备上也可以使用Toolbar。与 Actionbar 相比，Toolbar 明显要灵活的多。它不像 Actionbar 一样，一定要固定在Activity的顶部，而是可以放到界面的任意位置。除此之外，在设计 Toolbar 的时候，Google也留给了开发者很多可定制修改的属性。

**Translucent System Bar** 其叫法很有歧义，叫其沉浸式状态栏也好，半透明状态栏也罢。怎么开心怎么叫，在这里暂且称之为半透明状态栏，毕竟目的是让其改变颜色，改变那千年不变的黑色背景。

在Android 4.4之后便引入了半透明状态栏的概念，此时状态栏最顶层有一层阴影，也就是平时我们所说的渐变效果。而在5.0以上谷歌又做了调整，不仅支持设置渐变效果，还是支持设置完全纯色的效果。


##**Toolbar**

根据google提供的api，这里主要说下toolbar常用的一些可定制的属性和使用方法，其效果图如下图所示：

![toolbar可定制属性和效果](http://img.blog.csdn.net/20161014105203439)

###**兼容包支持**

Toolbar的使用需要v7包的支持，需在build.gradle文件中添加如下代码即可使用，代码如下：

```
compile 'com.android.support:appcompat-v7:23.4.0'
```



###**toolbar控件添加**
新建资源布局文件，添加toolbar控件。按照以往习惯，大多数app的页面标题内容会设置在标题栏的中间位置，然而toolbar控件并没有提供相关Api设置居中的标题，这里可以采用以下的方式添加自定义标题。
```
	<android.support.v7.widget.Toolbar
        android:id="@+id/tool_bar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:minHeight="?attr/actionBarSize">

        <TextView
            android:id="@+id/toolbar_title_tv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="自定义"
            android:textColor="#ffffff"
            android:textSize="18sp" />
    </android.support.v7.widget.Toolbar>
```
注：笔者在toolbar的中间放置了一个textview，但textview的位置不是一定居中的，这取决于toolbar给予textview的位置大小。

###**属性定制**

1 . navigation button（导航栏图标）
	
```
	//设置图标资源
	toolbar.setNavigationIcon(iconRes);
	
	//点击事件监听回调 
	toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
            }
        });
```

2 . logo icon（app logo）

```
	toolbar.setLogo(resId);//设置图标资源
```

3 . title and subtitle（主标题和子标题）

```
	 toolbar.setTitle(title);//设置主标题内容
	 toolbar.setTitle(subtitle);//设置子标题内容
	 toolbar.setTitleTextColor(color);//设置主标题字体颜色
	 toolbar.setSubtitleTextColor(color);//设置子标题字体颜色
```

4 . action menu（动作菜单）
```
	//设置menu资源
	toolbar.inflateMenu(R.menu.menu_main);
	//右边菜单item监听回调
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
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
        });
```

菜单资源文件menu_main.xml，定义了几个动作，其内容如下：

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/menu_test"
        android:title="测试"
        app:showAsAction="ifRoom" />
    <item
        android:id="@+id/menu_search"
        android:icon="@mipmap/tool_search"
        android:title="搜索"
        app:showAsAction="always" />
    <item
        android:id="@+id/menu_settings"
        android:title="设置"
        app:showAsAction="never" />
    <item
        android:id="@+id/menu_check_update"
        android:title="检查更新"
        app:showAsAction="never" />
    <item
        android:id="@+id/menu_about"
        android:title="关于"
        app:showAsAction="never" />
</menu>

```

注：action menu共两部分，一部分为操作栏，另一部分为溢出菜单。这里定义了五个item选项，其中app:showAsAction属性是用来指定这个item是放置在操作栏上，还是溢出菜单中。它的值主要有以下三种：
>a. 当值为“ifRoom”时，如果操作栏有空间放置，则item放置在操作栏上，否则放置到溢出菜单中。
>b. 当值为“always”时，item将总是放置在操作栏上。
>c. 当值为“never”时，item将总是放置在溢出菜单中。

![操作栏](http://img.blog.csdn.net/20161014145158005)

![溢出菜单](http://img.blog.csdn.net/20161014145209514)

6 . 其他样式修改

a.背景颜色

```
	toolbar.setBackgroundColor(bgColor);//修改toolbar背景颜色
```

b.字体颜色和字体大小
通过style.xml资源文件自定义相关样式，即可更改toolbar控件相关属性，代码如下：
```
	<!-- toolbar主题样式 -->
    <style name="ToolbarTheme" parent="@style/ThemeOverlay.AppCompat.ActionBar">
        <!-- menu中showAsAction设置为ifRoom和always的item项（即操作栏上）字体颜色 -->
        <item name="actionMenuTextColor">#00ad56</item>
        <!-- menu中showAsAction设置为ifRoom和always的item项（即操作栏上）字体大小 -->
        <item name="actionMenuTextAppearance">@style/ToolbarMenuTextSize</item>
        <!-- 设置标题、menu中所有item字体颜色 -->
        <item name="android:textColorPrimary">@android:color/white</item>
        <!-- 设置子标题字体颜色 -->
        <!--<item name="android:textColorSecondary">#ffad56</item>-->
    </style>
```

c.溢出菜单背景颜色、字体颜色
通过style.xml资源文件自定义相关样式，即可更改toolbar控件相关属性，代码如下：

```
    <!-- toolbar菜单中showAsAction为ifRoom的item项的文字尺寸 -->
    <style name="ToolbarMenuTextSize" parent="@style/TextAppearance.AppCompat.Menu">
        <item name="android:textSize">16sp</item>
    </style>

    <!-- toolbar标题字体样式 -->
    <style name="ToolbarTitleStyle" parent="@style/TextAppearance.Widget.AppCompat.Toolbar.Title">
        <item name="android:textSize">16sp</item>
    </style>

    <!-- toolbar弹出菜单样式 -->
    <style name="ToolbarPopupTheme" parent="@style/ThemeOverlay.AppCompat">
        <item name="android:colorBackground">#ffffaa</item>
        <item name="android:textColor">#fa4081</item>
        <item name="android:textSize">20sp</item>
    </style>
```
注：其使用方式为，在toolbar布局中添加如下代码即可，不过需要注意的是，还需要再根布局中添加代码` xmlns:app="http://schemas.android.com/apk/res-auto"`以下代码设置才会生效。
```
    app:popupTheme="@style/ToolbarPopupTheme"
    app:subtitleTextAppearance="@style/ToolbarTitleStyle"
    app:theme="@style/ToolbarTheme"
    app:titleTextAppearance="@style/ToolbarTitleStyle"
```

##**Translucent System Bar**

###**不同版本具有的特点**
关于半透明状态栏，在不用的Android版本之间也有着不同的效果。其特点如下所示：
>1 . Android 4.4以下不支持半透明状态栏效果
>2 . Android 4.4以上5.0以下（不包括5.0）可使状态栏变色，效果为颜色渐变，可设置透明度
>3 . Android5.0以上可使状态栏变色，即可设置渐变效果，亦可让其显示纯色效果，同时支持透明度设置

**Android 4.4效果图：**

![Android 4.4效果](http://img.blog.csdn.net/20161018113633434)

**Android 5.0以上效果图**

![Android 5.0以上效果](http://img.blog.csdn.net/20161018112946994)

下面看下动图的效果，如下所示：
![statusbar效果图.gif](http://upload-images.jianshu.io/upload_images/2369466-497cfb0782a848ec.gif?imageMogr2/auto-orient/strip)

###**代码实现**
1 . 新建状态栏管理类StatusBarManager，定义枚举类型TintType，编写带四参数的构造方法，同时根据Andorid版本的不同对窗口进行配置

```
    public enum TintType {
        GRADIENT, PURECOLOR  //渐变，纯色   ps:纯色效果仅适用于android 5.0以上
    }

    /**
     * 构造函数
     *  @param activity
     * @param tintType
     * @param alpha
     * @param statusBarColor
     */
    private StatusBarManager(Activity activity, TintType tintType, int alpha, int statusBarColor) {
        this.mActivity = activity;
        this.mTintType = tintType;
        this.mAlpha = alpha;
        this.mStatusBarColor = CommTool.getResultColor(mActivity, statusBarColor);
        windowConfig();
    }

    /**
     * 窗口相关配置
     */
    private void windowConfig() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            if (TintType.PURECOLOR == mTintType) {
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                mActivity.getWindow().setStatusBarColor(CommTool.calculateColorWithAlpha(mStatusBarColor, mAlpha));//设置状态栏颜色
            } else if (TintType.GRADIENT == mTintType) {
                mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                addStatusBarView(mActivity, CommTool.calculateColorWithAlpha(mStatusBarColor, mAlpha));
            }
            //透明底部导航栏
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            addStatusBarView(mActivity, CommTool.calculateColorWithAlpha(mStatusBarColor, mAlpha));
        }
    }
```
注：4.4以上需要自己绘制一个与状态栏同高的矩形条，并通过addview的方法添加至屏幕上，同时设置其背景颜色达到预期的效果。而5.0以上api中提供了setStatusBarColor（int color）的方法可直接设置状态的颜色。

2 . 新建StatusBarView视图类，主要生成一个与状态栏同等大小的视图并添加至顶部和颜色的设置，代码如下：

```
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
```

3 . 定义builder配置内部类，根据实际情况对状态栏属性进行自由组装，代码如下：

```
	/**
     * builder配置类
     */
    public static class builder {
        private static final int DEFAULT_ALPHA = 60;  //默认透明度数值
        private Activity activity;
        private TintType tintType = TintType.PURECOLOR;  //默认纯色效果
        private int alpha = DEFAULT_ALPHA;
        private int statusBarColor;

        /**
         * 构造方法
         *
         * @param activity
         */
        public builder(Activity activity) {
            this.activity = activity;
        }

        /**
         * 设置色彩类型
         *
         * @param tintType
         * @return
         */
        public builder setTintType(TintType tintType) {
            this.tintType = tintType;
            return this;
        }

        /**
         * 设置透明度
         *
         * @param alpha
         * @return
         */
        public builder setAlpha(int alpha) {
            if (alpha >= 0 & alpha <= 255)
                this.alpha = alpha;
            return this;
        }

        /**
         * 设置状态栏背景颜色
         *
         * @param StatusBarColor
         * @return
         */
        public builder setStatusBarColor(int StatusBarColor) {
            this.statusBarColor = StatusBarColor;
            return this;
        }

        /**
         * 创建StatusBarManager对象并返回
         *
         * @return
         */
        public StatusBarManager create() {
            return new StatusBarManager(activity, tintType, alpha, statusBarColor);
        }
    }
```

最后通过链式调用的方式自由组装配置，支持纯色和渐变效果设置、支持设置透明度等，调用方式如下代码所示：

```
  new StatusBarManager.builder(this)
                .setStatusBarColor(mStatusBarColor)//状态栏颜色
                .setTintType(mTintType)//色彩类型：纯色、渐变
                .setAlpha(mAlpha)//不透明度
                .create();
```
注：最终效果展示时，会发现布局内容已浸入到状态栏中，这是个需要注意的地方，我们只需在布局文件的根布局中添加` android:fitsSystemWindows="true" `即可。当然如果把图片当背景的话，可将其设置为**false**，这个视情况而定。

关于toolbar和Translucent System Bar就介绍到这里,下面是将两者进行配套使用,并进行了封装,以便在以后的开发过程中快速调用。

##**封装调用**

1 . 新建base_activity_layout.xml资源布局文件，页面由toolbar和正文内容两部分组成，代码如下：

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/base_ll"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    android:orientation="vertical">

    <android.support.v7.widget.Toolbar
        android:id="@+id/tool_bar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:minHeight="?attr/actionBarSize"
        app:popupTheme="@style/ToolbarPopupTheme"
        app:subtitleTextAppearance="@style/ToolbarTitleStyle"
        app:theme="@style/ToolbarTheme"
        app:titleTextAppearance="@style/ToolbarTitleStyle">
        <TextView
            android:id="@+id/toolbar_title_tv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="自定义"
            android:textColor="#ffffff"
            android:textSize="18sp" />
    </android.support.v7.widget.Toolbar>

    <FrameLayout
        android:id="@+id/base_content"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:background="#ffffff" />
</LinearLayout>
```

2 . 新建基类BaseActivtiy，主要提供的功能有：

a . Navigation Button监听回调，这里默认操作为返回并销毁当前页面。如需定义其他操作，则callbackOnClickNavigationAction（View v）方法即可

```
	 //左边Navigation Button监听回调
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackOnClickNavigationAction(v);
            }
        });

	/**
     * Navigation Button点击回调，默认回退销毁页面，其他操作子类可重写
     *
     * @param view
     */
    protected void callbackOnClickNavigationAction(View view) {
        onBackPressed();
    }
```
b . Action menu布局添加以及监听回调，默认无menu选项

```
	  //右边菜单item监听回调
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return callbackOnMenuAction(item);
            }
        });
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
	     * 为toolbar设置menu项
	     */
	    private void setInflateMenu() {
	        if (getMenuLayoutId() > 0)
	            toolbar.inflateMenu(getMenuLayoutId());
	    }
```

c . 各种set操作，如标题、子标题、自定义标题字体颜色设置、图标设置、menu布局设置等

```
	/**
     * 为toolbar设置menu项
     */
    private void setInflateMenu() {
        if (getMenuLayoutId() > 0)
            toolbar.inflateMenu(getMenuLayoutId());
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

```
d . 根据实际情况隐藏toolbar、获取toolbar控件设置属性

```
    /**
     * 隐藏标题栏
     */
    protected void hideToolbar() {
        if (toolbar.getVisibility() == View.VISIBLE)
            toolbar.setVisibility(View.GONE);
    }
    /**
     * 获取toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

```

3 . 调用方法

>a . 子类继承BaseActivity基类，实现抽象方法getContentViewID（）和initViews（）
>b . 根据实际情况设置相关属性，如标题、导航图标等
>c . 如果需要设置action menu，则重写方法getMenuLayoutId（）返回menu布局即可，item点击回调，只要重写方法callbackOnMenuAction(MenuItem item)并返回true值即可。
>d . 透明状态栏默认纯色半透明，如需修改可通过`setTitleBgColor(int color)`修改，或者调用状态栏管理类，通过链试调用的方式对状态栏进行配置

BaseActivity完整代码

```
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


```


**写在最后，希望以上内容能帮到您，不定时总结和记录个人的经验。如果喜欢的话可以关注我，非常感谢您的支持。同时，欢迎加入移动开发学习交流qq群 ： 450302004，互相学习。**



