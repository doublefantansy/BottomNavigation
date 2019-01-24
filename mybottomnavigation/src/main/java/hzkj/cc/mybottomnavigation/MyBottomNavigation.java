package hzkj.cc.mybottomnavigation;

import android.content.Context;
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

import java.util.List;
import java.util.Random;

public class MyBottomNavigation extends LinearLayout {
    List<BottomChild> bottomChildren;
    int unSelectedtextColor;
    int selectedTextColor;
    FragmentManager manager;
    int defalutIndex;
    Fragment currentFragement;
    OnClickBottomChildListener onClickBottomChildListener;
    private int id = new Random().nextInt(717);

    public void setOnClickBottomChildListener(OnClickBottomChildListener onClickBottomChildListener) {
        this.onClickBottomChildListener = onClickBottomChildListener;
    }

    public MyBottomNavigation(Context context) {
        super(context, null);
    }

    public MyBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initBottomChildren(FragmentManager manager, List<BottomChild> bottomChildren, int defalutIndex) {
        this.manager = manager;
        this.bottomChildren = bottomChildren;
        this.defalutIndex = defalutIndex;
        bottomChildren.get(this.defalutIndex)
                .setSelected(true);
        initView();
        initListenner();
    }

    public void setUnselectedTextColor(int unSelectedtextColor) {
        this.unSelectedtextColor = unSelectedtextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public void changeUi(BottomChild bottomChild) {
        bottomChild.getTextView()
                .setTextColor(bottomChild.isSelected() ? selectedTextColor : unSelectedtextColor);
        bottomChild.getTextView()
                .setTextSize(bottomChild.isSelected() ? 15 : 12);
        ((RelativeLayout.LayoutParams) bottomChild.getImageView()
                .getLayoutParams()).topMargin = bottomChild.isSelected() ? 5 : 10;
        bottomChild.getImageView()
                .setImageDrawable(bottomChild.isSelected() ? bottomChild.getSelectedSrc() : bottomChild.getSrc());
    }

    private void initView() {
        selectedTextColor = getResources().getColor(R.color.myBlue);
        unSelectedtextColor = getResources().getColor(R.color.gray);
        this.setOrientation(VERTICAL);
        LinearLayout fragmentLayout = new LinearLayout(getContext());
        fragmentLayout.setId(id);
        LinearLayout.LayoutParams fragmentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        fragmentParams.weight = 1;
        fragmentLayout.setLayoutParams(fragmentParams);
        this.addView(fragmentLayout);
        initFragment();
        LinearLayout bottomLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams bottomParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dipTopx(getContext(), 55));
        bottomLayout.setLayoutParams(bottomParams);
        this.addView(bottomLayout);
        for (BottomChild bottomChild : bottomChildren) {
            View view = LayoutInflater.from(getContext())
                    .inflate(R.layout.bottomnavigation, null);
            TextView textView = view.findViewById(R.id.text);
            ImageView imageView = view.findViewById(R.id.image);
            textView.setText(bottomChild.getName());
            imageView.setImageDrawable(bottomChild.getSrc());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
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
        manager.beginTransaction()
                .add(id, currentFragement)
                .commit();
    }

    private void switchFragment(Fragment targetFragment) {
        if (targetFragment == currentFragement) {
            return;
        }
        FragmentTransaction fragmentTransaction = manager.beginTransaction()
                .hide(currentFragement);
        if (targetFragment.isAdded()) {
            fragmentTransaction
                    .show(targetFragment)
                    .commit();
        } else {
            fragmentTransaction
                    .add(id, targetFragment)
                    .commit();
        }
        currentFragement = targetFragment;
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
                                    switchFragment(child.getFragment());
                                    onClickBottomChildListener.onClick(child, bottomChildren.indexOf(child));
                                }
                                changeUi(child);
                            }
                        }
                    });
        }
    }
}
