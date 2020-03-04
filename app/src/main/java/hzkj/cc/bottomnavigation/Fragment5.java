package hzkj.cc.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment5 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment5, container, false);
    }
    @Override public void onHiddenChanged(boolean hidden) {

        if (!hidden) {
            System.out.println("show");

        } else {
            System.out.println("hidden");
        }
    }
}
