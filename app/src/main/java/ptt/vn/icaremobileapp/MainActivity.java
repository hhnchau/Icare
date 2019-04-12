package ptt.vn.icaremobileapp;

import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.drawer.DrawerAdapter;
import ptt.vn.icaremobileapp.drawer.DrawerModel;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.fragment.Dashboard;
import ptt.vn.icaremobileapp.fragment.InpatientList;
import ptt.vn.icaremobileapp.model.filter.DataTypeOfValue;
import ptt.vn.icaremobileapp.model.filter.FieldName;
import ptt.vn.icaremobileapp.model.filter.Method;
import ptt.vn.icaremobileapp.model.filter.Operation;
import ptt.vn.icaremobileapp.model.filter.Para;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.utils.Fragmentuz;
import ptt.vn.icaremobileapp.utils.Toolbaruz;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    private ConstraintLayout drawer;
    private ListView drawerList;
    private DrawerAdapter drawerAdapter;

    private ImageView toolbarRight, toolbarLeft;
    private TextView toolbarTitle;


    private List<InpatientDomain> lstInpatientDomain;

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

        //Fragment
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int position = fragmentManager.getBackStackEntryCount();
                if (position > 0) {
                    FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(position - 1);
                    Toolbaruz.setToolbar(backEntry, toolbarTitle, toolbarLeft, toolbarRight);
                }

            }
        });




        Fragmentuz.addMainFrame(fragmentManager,null, Fragmentez.INPATIENT_LIST, R.id.mainFrame, Directionez.NEXT);




    }

    private void setupDrawer() {
        final List<DrawerModel> lstDrawer = new ArrayList<>();
        lstDrawer.add(new DrawerModel(Fragmentez.INPATIENT_LIST, R.drawable.ic_checked, "DS BENH NHAN"));
        lstDrawer.add(new DrawerModel(Fragmentez.DASHBOARD, R.drawable.ic_checked, "DASHBOARD"));


        drawerList = findViewById(R.id.drawerList);
        drawerAdapter = new DrawerAdapter(this, lstDrawer);
        drawerList.setAdapter(drawerAdapter);

        drawerLayout = findViewById(R.id.drawerLayout);
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        drawer = findViewById(R.id.drawer);




        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                drawerLayout.closeDrawer(drawer);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (Fragment fragment: fragmentManager.getFragments()) {
                            if (fragment instanceof InpatientList || fragment instanceof Dashboard) {
                            }
                            else {
                                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                            }
                        }

                        switch (lstDrawer.get(position).getFzg()){
                            case INPATIENT_LIST:
                                Fragmentuz.addMainFrame(fragmentManager, null, Fragmentez.INPATIENT_LIST, R.id.mainFrame, Directionez.NEXT);
                                //fragmentManager.popBackStack("DASHBOARD", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                                break;
                            case DASHBOARD:
                                Fragmentuz.addMainFrame(fragmentManager, null, Fragmentez.DASHBOARD, R.id.mainFrame, Directionez.NEXT);
                                //fragmentManager.popBackStack("INPATIENT_LIST", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                break;
                        }

                    }
                }, 100);
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
                if (fragmentManager.getBackStackEntryCount() > 1) {
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

}
