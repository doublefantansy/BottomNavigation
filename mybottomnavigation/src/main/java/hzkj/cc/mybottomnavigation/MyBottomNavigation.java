package hzkj.cc.mybottomnavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MyBottomNavigation extends LinearLayout {
    List<BottomChild> bottomChildren;
    int unSelectedtextColor;
    int selectedTextColor;
    OnClickBottomChildListener onClickBottomChildListener;

    public void setOnClickBottomChildListener(OnClickBottomChildListener onClickBottomChildListener) {
        this.onClickBottomChildListener = onClickBottomChildListener;
    }

    public MyBottomNavigation(Context context) {
        super(context, null);
    }

    public MyBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBottomChildren(List<BottomChild> bottomChildren, int defalutIndex) {
        this.bottomChildren = bottomChildren;
        bottomChildren.get(defalutIndex)
                .setSelected(true);
        init();
    }

    public void setUnselectedTextColor(int unSelectedtextColor) {
        this.unSelectedtextColor = unSelectedtextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public void changeUi(BottomChild bottomChild) {
        ((RelativeLayout.LayoutParams) bottomChild.imageView.getLayoutParams()).topMargin = bottomChild.isSelected == true ? Util.dipTopx(getContext(), 5) : Util.dipTopx(getContext(), 8);
        bottomChild.imageView.getLayoutParams().width = bottomChild.isSelected == true ? Util.dipTopx(getContext(), 36) : Util.dipTopx(getContext(), 24);
        bottomChild.textView.setTextSize(bottomChild.isSelected == true ? 15 : 12);
        bottomChild.imageView.setImageDrawable(bottomChild.isSelected == true ? bottomChild.selectedSrc : bottomChild.src);
        bottomChild.textView.setTextColor(bottomChild.isSelected == true ? selectedTextColor : unSelectedtextColor);
    }

    private void init() {
        int id = 0;
        unSelectedtextColor = getResources().getColor(R.color.gray);
        selectedTextColor = getResources().getColor(R.color.myBlue);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(layoutParams);
        for (BottomChild bottomChild : bottomChildren) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setBackground(getResources().getDrawable(R.drawable.ripper));
            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayoutParams.weight = 1;
            RelativeLayout.LayoutParams imagePaarms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imagePaarms.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imagePaarms.topMargin = Util.dipTopx(getContext(), 8);
            RelativeLayout.LayoutParams namePaarms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            namePaarms.addRule(RelativeLayout.CENTER_HORIZONTAL);
            final ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(bottomChild.src);
            imageView.setLayoutParams(imagePaarms);
            imageView.setId(++id);
            bottomChild.setImageView(imageView);
            final TextView textView = new TextView(getContext());
            textView.setText(bottomChild.name);
            textView.setId(++id);
            textView.setTextSize(12);
            textView.setTextColor(unSelectedtextColor);
            bottomChild.setTextView(textView);
            namePaarms.topMargin = Util.dipTopx(getContext(), 3);
            namePaarms.addRule(RelativeLayout.BELOW, imageView.getId());
            textView.setLayoutParams(namePaarms);
            relativeLayout.addView(imageView);
            relativeLayout.addView(textView);
            relativeLayout.setLayoutParams(linearLayoutParams);
            bottomChild.setRelativeLayout(relativeLayout);
            linearLayout.addView(relativeLayout);
        }
        this.addView(linearLayout);
        for (final BottomChild bottomChild : bottomChildren) {
            changeUi(bottomChild);
            bottomChild.relativeLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (BottomChild child : bottomChildren) {
                        if (child != bottomChild) {
                            child.isSelected = false;
                        } else {
                            child.isSelected = true;
                        }
                        if (child.isSelected) {
                            onClickBottomChildListener.onClick(child, bottomChildren.indexOf(child));
                        }
                        changeUi(child);
                    }
                }
            });
        }
    }
}
