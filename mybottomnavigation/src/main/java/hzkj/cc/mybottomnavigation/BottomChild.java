package hzkj.cc.mybottomnavigation;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.service.dreams.DreamService;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BottomChild {
    String name;
    int unSelectedtextColor;
    int selectedTextColor;
    Drawable src;
    Drawable selectedSrc;
    boolean isSelected;
    RelativeLayout relativeLayout;
    ImageView imageView;
    TextView textView;

    public BottomChild(String name, Drawable src, Drawable selectedSrc) {
        this.name = name;
        this.src = src;
        this.selectedSrc = selectedSrc;
        isSelected = false;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
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

    public int getUnSelectedtextColor() {
        return unSelectedtextColor;
    }

    public void setUnSelectedtextColor(int unSelectedtextColor) {
        this.unSelectedtextColor = unSelectedtextColor;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
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
