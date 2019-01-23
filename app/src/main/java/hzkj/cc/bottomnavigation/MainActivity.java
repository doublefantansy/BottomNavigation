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
        MyBottomNavigation bottomNavigation = findViewById(R.id.a);
        List<BottomChild> bottomChildren = new ArrayList<>();
        bottomChildren.add(new BottomChild("考勤", getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomChildren.add(new BottomChild("工单", getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomChildren.add(new BottomChild("租车", getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomChildren.add(new BottomChild("合同", getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomChildren.add(new BottomChild("迟迟", getResources().getDrawable(R.drawable.ic_bianji), getResources().getDrawable(R.drawable.ic_bianji_2)));
        bottomNavigation.setBottomChildren(bottomChildren,2);
//        bottomNavigation.setSelectedTextColor(getResources().getColor(R.color.colorPrimaryDark));
        bottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
            @Override
            public void onClick(BottomChild bottomChild, int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
