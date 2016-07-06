package com.example.bottombar.sample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;
    private TextView mMessageView;
    protected Fragment mFrag;
    String flag = "";
    String TAG = "MainActivity :: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mMessageView = (TextView) findViewById(R.id.messageView);
        if (savedInstanceState == null) {
            FragmentTransaction t = this.getSupportFragmentManager()
                    .beginTransaction();
            mFrag = new FragmentOne();
            t.replace(R.id.frag_contanier, mFrag);
            t.commit();
        } else {
           /* mFrag = (ListFragment) this.getSupportFragmentManager()
                    .findFragmentById(R.id.frag_contanier);*/
        }



        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
               // mMessageView.setText(getMessage(menuItemId, false));

                getMessage(menuItemId, false);


                if(flag.equalsIgnoreCase("recents")){

                    FragmentTransaction t = MainActivity.this.getSupportFragmentManager()
                            .beginTransaction();
                    mFrag = new FragmentOne();
                    t.replace(R.id.frag_contanier, mFrag);
                    t.commit();
                }
                else if(flag.equalsIgnoreCase("favorites")){


                    FragmentTransaction t = MainActivity.this.getSupportFragmentManager()
                            .beginTransaction();
                    mFrag = new FragmentTwo();
                    t.replace(R.id.frag_contanier, mFrag);
                    t.commit();
                }

                else if(flag.equalsIgnoreCase("nearby")){


                    FragmentTransaction t = MainActivity.this.getSupportFragmentManager()
                            .beginTransaction();
                    mFrag = new FragmentThree();
                    t.replace(R.id.frag_contanier, mFrag);
                    t.commit();
                }

                else if(flag.equalsIgnoreCase("friends")){

                    FragmentTransaction t = MainActivity.this.getSupportFragmentManager()
                            .beginTransaction();
                    mFrag = new FragmentFour();
                    t.replace(R.id.frag_contanier, mFrag);
                    t.commit();
                }

                else {

                    FragmentTransaction t = MainActivity.this.getSupportFragmentManager()
                            .beginTransaction();
                    mFrag = new FragmentFive();
                    t.replace(R.id.frag_contanier, mFrag);
                    t.commit();
                }

                Toast.makeText(getApplicationContext(), getMessage(menuItemId, false), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

                getMessage(menuItemId, true);
                Toast.makeText(getApplicationContext(), getMessage(menuItemId, true), Toast.LENGTH_SHORT).show();
            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF5252");
        mBottomBar.mapColorForTab(4, "#FF9800");
    }

    private String getMessage(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
            case R.id.bb_menu_recents:
                message += "recents";
                flag ="recents";
                break;
            case R.id.bb_menu_favorites:
                message += "favorites";
                flag ="favorites";

                break;
            case R.id.bb_menu_nearby:
                message += "nearby";
                flag ="nearby";

                break;
            case R.id.bb_menu_friends:
                message += "friends";
                flag ="friends";

                break;
            case R.id.bb_menu_food:
                message += "food";
                flag ="food";

                break;
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }
}
