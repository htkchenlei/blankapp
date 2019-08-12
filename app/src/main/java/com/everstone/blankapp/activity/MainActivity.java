package com.everstone.blankapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.everstone.blankapp.R;
import com.everstone.blankapp.adapter.FragmentAdapter;
import com.everstone.blankapp.fragment.ChinaFragment;
import com.everstone.blankapp.fragment.SportsFragment;
import com.everstone.blankapp.fragment.TechFragment;
import com.everstone.blankapp.fragment.WorldFragment;
import com.everstone.blankapp.util.AnimUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener{

    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    private ImageView user;
    private RadioGroup radioGroup;
    private RadioButton china, world, sports, tech;
    private List<Fragment> fragmentList;
    private Fragment chinaFragment, worldFragment, sportsFragment, techFragment;
    public static final int PAGE_CHINA = 0;
    public static final int PAGE_WORLD = 1;
    public static final int PAGE_SPORTS = 2;
    public static final int PAGE_TECH = 3;

    private TextView tv_1, tv_2, tv_3;
    private PopupWindow myPopupWindow;

    private AnimUtil animUtil;
    private float bgAlpha = 1f;
    private boolean bright = false;

    private static final long DURATION = 100;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        china.setChecked(true);
    }

    private void initView(){
        chinaFragment = new ChinaFragment();
        worldFragment = new WorldFragment();
        sportsFragment = new SportsFragment();
        techFragment = new TechFragment();
        fragmentList = new ArrayList<>();

        fragmentList.add(chinaFragment);
        fragmentList.add(worldFragment);
        fragmentList.add(sportsFragment);
        fragmentList.add(techFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        china = (RadioButton)findViewById(R.id.china);
        world = (RadioButton)findViewById(R.id.world);
        sports = (RadioButton)findViewById(R.id.sports);
        tech = (RadioButton)findViewById(R.id.technology);
        radioGroup.setOnCheckedChangeListener(this);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

        user = (ImageView)findViewById(R.id.user);
        user.setOnClickListener(this);
        myPopupWindow = new PopupWindow(this);
        animUtil = new AnimUtil();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.china:
                viewPager.setCurrentItem(PAGE_CHINA);
                china.setBackgroundColor(getResources().getColor(R.color.purple_dark));
                world.setBackgroundColor(getResources().getColor(R.color.purple));
                sports.setBackgroundColor(getResources().getColor(R.color.purple));
                tech.setBackgroundColor(getResources().getColor(R.color.purple));
                break;
            case R.id.world:
                viewPager.setCurrentItem(PAGE_WORLD);
                china.setBackgroundColor(getResources().getColor(R.color.purple));
                world.setBackgroundColor(getResources().getColor(R.color.purple_dark));
                sports.setBackgroundColor(getResources().getColor(R.color.purple));
                tech.setBackgroundColor(getResources().getColor(R.color.purple));
                break;
            case R.id.sports:
                viewPager.setCurrentItem(PAGE_SPORTS);
                china.setBackgroundColor(getResources().getColor(R.color.purple));
                world.setBackgroundColor(getResources().getColor(R.color.purple));
                sports.setBackgroundColor(getResources().getColor(R.color.purple_dark));
                tech.setBackgroundColor(getResources().getColor(R.color.purple));
                break;
            case R.id.technology:
                viewPager.setCurrentItem(PAGE_TECH);
                china.setBackgroundColor(getResources().getColor(R.color.purple));
                world.setBackgroundColor(getResources().getColor(R.color.purple));
                sports.setBackgroundColor(getResources().getColor(R.color.purple));
                tech.setBackgroundColor(getResources().getColor(R.color.purple_dark));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        showPop();
        Intent intent = new Intent(MainActivity.this, PopupActivity.class);
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.user:
                showPop();
                toggleBright();
                break;
            case R.id.tv_1:
                myPopupWindow.dismiss();
                bundle.putInt("id", view.getId());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.tv_2:
                myPopupWindow.dismiss();
                bundle.putInt("id", view.getId());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.tv_3:
                myPopupWindow.dismiss();
                bundle.putInt("id", view.getId());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void showPop() {
        // 设置布局文件
        myPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.pop_add, null));
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        myPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置pop透明效果
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        myPopupWindow.setAnimationStyle(R.style.pop_add);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        myPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        myPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        myPopupWindow.setOutsideTouchable(true);
        // 相对于user icon正下面，同时可以设置偏移量
        myPopupWindow.showAsDropDown(user, -100, 0);
        // 设置pop关闭监听，用于改变背景透明度
        myPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });

        tv_1 = myPopupWindow.getContentView().findViewById(R.id.tv_1);
        tv_2 = myPopupWindow.getContentView().findViewById(R.id.tv_2);
        tv_3 = myPopupWindow.getContentView().findViewById(R.id.tv_3);

        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
