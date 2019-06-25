package hzkj.cc.bottomnavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.mybottomnavigation.OnClickBottomChildListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyBottomNavigation bottomNavigation = findViewById(R.id.a);
        List<BottomChild> bottomChildren = new ArrayList<>();
        bottomChildren.add(new BottomChild("考勤", new Fragment1(), getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomChildren.add(new BottomChild("工单", new Fragment2(), getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomChildren.add(new BottomChild("租车", new Fragment3(), getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
//        bottomChildren.add(new BottomChild("合同", new Fragment4(), getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
//        bottomChildren.add(new BottomChild("迟迟", new Fragment5(), getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomNavigation.initBottomChildren(getSupportFragmentManager(), bottomChildren, 1);
        bottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
            @Override
            public void onClick(BottomChild bottomChild, int position) {
                Toast.makeText(MainActivity.this, bottomNavigation.getCurrentIndex() + "", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        bottomNavigation.setTextColors(new ArrayList<Integer>() {
            {
                add(getColor(R.color.colorAccent));
                add(getColor(R.color.colorPrimaryDark));
                add(getColor(R.color.colorPrimary));
            }
        });
    }
}
