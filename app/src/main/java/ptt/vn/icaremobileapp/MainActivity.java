package ptt.vn.icaremobileapp;

import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.api.CompositeManager;
import ptt.vn.icaremobileapp.drawer.DrawerAdapter;
import ptt.vn.icaremobileapp.drawer.DrawerModel;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.utils.Toolbaruz;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    private ConstraintLayout drawer;
    private ListView drawerList;
    private DrawerAdapter drawerAdapter;

    private ImageView toolbarRight, toolbarLeft;
    private TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Full Window
        Window window = getWindow();
        if (window != null) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorMain));
            }
        }

        //Drawer
        setupDrawer();
        //Toolbar
        setupToolbar();

        //Listener BackStack
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int position = fragmentManager.getBackStackEntryCount();
                if (position > 0) {
                    FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(position - 1);
                    Toolbaruz.setToolbar(MainActivity.this, backEntry, toolbarTitle, toolbarLeft, toolbarRight);
                }
            }
        });

        //Default
        Fragmentuz.replaceFragment(fragmentManager, Fragmentez.DASHBOARD, false, R.id.mainFrame, null, Directionez.NEXT);
        Toolbaruz.setToolbar(MainActivity.this, Fragmentez.DASHBOARD, toolbarTitle, toolbarLeft, toolbarRight);

    }

    private void setupDrawer() {
        final List<DrawerModel> lstDrawer = new ArrayList<>();
        lstDrawer.add(new DrawerModel(Fragmentez.DASHBOARD, R.drawable.ic_dash, getString(R.string.screen_dashboard)));
        lstDrawer.add(new DrawerModel(Fragmentez.INPATIENT, R.drawable.ic_ipt, getString(R.string.screen_inpatient)));
        lstDrawer.add(new DrawerModel(Fragmentez.RECEIVING, R.drawable.ic_ipt, getString(R.string.screen_receiving)));

        drawerList = findViewById(R.id.drawerList);
        drawerAdapter = new DrawerAdapter(this, lstDrawer);
        drawerList.setAdapter(drawerAdapter);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        drawer = findViewById(R.id.drawer);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                drawerLayout.closeDrawer(drawer);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        switch (lstDrawer.get(position).getFzg()) {
                            case INPATIENT:
                                Fragmentuz.replaceFragment(fragmentManager, Fragmentez.INPATIENT, false, R.id.mainFrame, null, Directionez.NEXT);
                                Toolbaruz.setToolbar(MainActivity.this, Fragmentez.INPATIENT, toolbarTitle, toolbarLeft, toolbarRight);
                                break;
                            case DASHBOARD:
                                Fragmentuz.replaceFragment(fragmentManager, Fragmentez.DASHBOARD, false, R.id.mainFrame, null, Directionez.NEXT);
                                Toolbaruz.setToolbar(MainActivity.this, Fragmentez.DASHBOARD, toolbarTitle, toolbarLeft, toolbarRight);
                                break;
                            case RECEIVING:
                                Fragmentuz.replaceFragment(fragmentManager, Fragmentez.RECEIVING, false, R.id.mainFrame, null, Directionez.NEXT);
                                Toolbaruz.setToolbar(MainActivity.this, Fragmentez.RECEIVING, toolbarTitle, toolbarLeft, toolbarRight);
                                break;
                        }

                    }
                }, 200);
            }
        });
    }


    private void setupToolbar() {
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarRight = findViewById(R.id.toolbarRight);
        toolbarLeft = findViewById(R.id.toolbarLeft);

        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = fragmentManager.getBackStackEntryCount();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    drawerLayout.openDrawer(drawer);
                }
            }
        });

        toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        CompositeManager.dispose();
        super.onDestroy();
    }
}
