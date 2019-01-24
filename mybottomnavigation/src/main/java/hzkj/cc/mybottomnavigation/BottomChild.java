package hzkj.cc.mybottomnavigation;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.service.dreams.DreamService;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BottomChild {
    private String name;
    private Drawable src;
    private Drawable selectedSrc;
    private boolean isSelected;
    private View view;
    private ImageView imageView;
    private TextView textView;
    private Fragment fragment;

    public BottomChild(String name, Fragment fragment, Drawable src, Drawable selectedSrc) {
        this.name = name;
        this.fragment = fragment;
        this.src = src;
        this.selectedSrc = selectedSrc;
        isSelected = false;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Drawable getSelectedSrc() {
        return selectedSrc;
    }

    public void setSelectedSrc(Drawable selectedSrc) {
        this.selectedSrc = selectedSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getSrc() {
        return src;
    }

    public void setSrc(Drawable src) {
        this.src = src;
    }
}
