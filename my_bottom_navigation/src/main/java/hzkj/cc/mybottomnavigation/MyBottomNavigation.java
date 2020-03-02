package hzkj.cc.mybottomnavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyBottomNavigation extends LinearLayout {

    List<BottomChild> bottomChildren;

    int unSelectedtextColor;

    int selectedTextColor;

    FragmentManager manager;

    int defalutIndex;

    Fragment currentFragement;

    int currentIndex;

    OnClickBottomChildListener onClickBottomChildListener;

    List<Integer> colors = new ArrayList<>();

    private int id = new Random().nextInt(717);

    public void setOnClickBottomChildListener(OnClickBottomChildListener onClickBottomChildListener) {
        this.onClickBottomChildListener = onClickBottomChildListener;
        initListenner();
    }

    public MyBottomNavigation(Context context) {
        super(context, null);
    }

    public MyBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.cc);
        unSelectedtextColor = array.getInt(R.styleable.cc_unSelcetedTextColor,
                getResources().getColor(R.color.bottomNavigation_gray));
        selectedTextColor = array.getInt(R.styleable.cc_selcetedTextColor,
                getResources().getColor(R.color.bottomNavigation_myBlue));
        array.recycle();
    }

    public void initBottomChildren(FragmentManager manager, List<BottomChild> bottomChildren,
            int defalutIndex) {
        this.manager = manager;
        this.bottomChildren = bottomChildren;
        this.defalutIndex = defalutIndex;
        currentIndex = defalutIndex;
        bottomChildren.get(this.defalutIndex)
                .setSelected(true);
        initView();
        initListenner();
    }

    public void setTextColors(List<Integer> colors) {
        this.colors.addAll(colors);
    }

    public void changeUi(BottomChild bottomChild) {
        Integer color;
        if (colors.size() == 0) {
            color = selectedTextColor;
        } else {
            color = colors.get(bottomChildren.indexOf(bottomChild));
        }
        bottomChild.getTextView()
                .setTextColor(bottomChild.isSelected() ? color : unSelectedtextColor);
        bottomChild.getTextView()
                .setTextSize(bottomChild.isSelected() ? 15 : 12);
        ((RelativeLayout.LayoutParams) bottomChild.getImageView()
                .getLayoutParams()).topMargin = bottomChild.isSelected() ? 5 : 10;
        bottomChild.getImageView()
                .setImageDrawable(
                        bottomChild.isSelected() ? bottomChild.getSelectedSrc() : bottomChild.getSrc());
    }

    private void initView() {
//        selectedTextColor = getResources().getColor(R.color.bottomNavigation_myBlue);
//        unSelectedtextColor = getResources().getColor(R.color.bottomNavigation_gray);
        this.setOrientation(VERTICAL);
        LinearLayout fragmentLayout = new LinearLayout(getContext());
        fragmentLayout.setId(id);
        LinearLayout.LayoutParams fragmentParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        fragmentParams.weight = 1;
        fragmentLayout.setLayoutParams(fragmentParams);
        this.addView(fragmentLayout);
        initFragment();
        LinearLayout bottomLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams bottomParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, Util.dipTopx(getContext(), 60));
        bottomLayout.setLayoutParams(bottomParams);
        this.addView(bottomLayout);
        for (BottomChild bottomChild : bottomChildren) {
            View view = LayoutInflater.from(getContext())
                    .inflate(R.layout.bottomnavigation, bottomLayout, false);
            TextView textView = view.findViewById(R.id.text);
            ImageView imageView = view.findViewById(R.id.image);
            textView.setText(bottomChild.getName());
            textView.setTextColor(unSelectedtextColor);
            imageView.setImageDrawable(bottomChild.getSrc());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);
            bottomLayout.addView(view);
            bottomChild.setTextView(textView);
            bottomChild.setImageView(imageView);
            bottomChild.setView(view);
        }
    }

    private void initFragment() {
        currentFragement = bottomChildren.get(defalutIndex)
                .getFragment();
//    for (BottomChild bottomChild : bottomChildren) {
//      if (bottomChild.getFragment() == currentFragement) {
        for (final BottomChild bottomChild : bottomChildren) {
            manager.beginTransaction()
                    .add(id, bottomChild.getFragment()).commit();
            if (bottomChildren.indexOf(bottomChild) == currentIndex) {
                manager.beginTransaction().show(bottomChild.getFragment())
                        .commit();
            } else {
                manager.beginTransaction().hide(bottomChild.getFragment())
                        .commit();
            }
        }

//    }
    }

    private void switchFragment(Fragment targetFragment) {
        if (targetFragment == currentFragement) {
            return;
        }
        FragmentTransaction fragmentTransaction = manager.beginTransaction()
                .hide(currentFragement);
//        if (!targetFragment.isAdded()) {
//            fragmentTransaction.add(id, targetFragment);
//        }
        fragmentTransaction.show(targetFragment);
        fragmentTransaction.commit();
        currentFragement = targetFragment;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    private void initListenner() {
        for (final BottomChild bottomChild : bottomChildren) {
            changeUi(bottomChild);
            bottomChild.getView()
                    .setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (BottomChild child : bottomChildren) {
                                if (child != bottomChild) {
                                    child.setSelected(false);
                                } else {
                                    child.setSelected(true);
                                }
                                if (child.isSelected()) {
                                    currentIndex = bottomChildren.indexOf(child);
                                    switchFragment(child.getFragment());
                                    if (onClickBottomChildListener != null) {
                                        onClickBottomChildListener.onClick(child, bottomChildren.indexOf(child));
                                    }
                                }
                                changeUi(child);
                            }
                        }
                    });
        }
    }
}
