package com.brim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.brim.Font.SFNFTextView;
import com.brim.FotterFragmnet.Account;
import com.brim.FotterFragmnet.Activity;
import com.brim.FotterFragmnet.Budget;
import com.brim.FotterFragmnet.DashBoard;
import com.brim.FotterFragmnet.Reward;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    LinearLayout LL_Account,LL_Budget,LL_Reward,LL_Activity,LL_Dashboard;
    ImageView IMG_Dashboard,IMG_Activity,IMG_Reward,IMG_Budget,IMG_Account;
    SFNFTextView TXT_Account,TXT_Budget,TXT_Reward,TXT_Activity,TXT_Dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TXT_Account=(SFNFTextView)findViewById(R.id.TXT_Account);
        TXT_Budget=(SFNFTextView)findViewById(R.id.TXT_Budget);
        TXT_Reward=(SFNFTextView)findViewById(R.id.TXT_Reward);
        TXT_Activity=(SFNFTextView)findViewById(R.id.TXT_Activity);
        TXT_Dashboard=(SFNFTextView)findViewById(R.id.TXT_Dashboard);

        IMG_Dashboard=(ImageView)findViewById(R.id.IMG_Dashboard);
        IMG_Activity=(ImageView)findViewById(R.id.IMG_Activity);
        IMG_Reward=(ImageView)findViewById(R.id.IMG_Reward);
        IMG_Budget=(ImageView)findViewById(R.id.IMG_Budget);
        IMG_Account=(ImageView)findViewById(R.id.IMG_Account);

        LL_Account=(LinearLayout)findViewById(R.id.LL_Account);
        LL_Budget=(LinearLayout)findViewById(R.id.LL_Budget);
        LL_Reward=(LinearLayout)findViewById(R.id.LL_Reward);
        LL_Activity=(LinearLayout)findViewById(R.id.LL_Activity);
        LL_Dashboard=(LinearLayout)findViewById(R.id.LL_Dashboard);




        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        DashBoard search_basic = new DashBoard();
        fragmentTransaction.add(R.id.Container, search_basic);
        fragmentTransaction.commit();


    }


    public void ButoomChnager(Fragment fr)
    {
        LL_Account.setBackgroundColor(Color.WHITE);
        LL_Budget.setBackgroundColor(Color.WHITE);
        LL_Reward.setBackgroundColor(Color.WHITE);
        LL_Activity.setBackgroundColor(Color.WHITE);
        LL_Dashboard.setBackgroundColor(Color.WHITE);

        TXT_Account.setTextColor(Color.parseColor("#808080"));
        TXT_Budget.setTextColor(Color.parseColor("#808080"));
        TXT_Reward.setTextColor(Color.parseColor("#808080"));
        TXT_Activity.setTextColor(Color.parseColor("#808080"));
        TXT_Dashboard.setTextColor(Color.parseColor("#808080"));

        IMG_Dashboard.setImageResource(R.drawable.ic_dashboard);
        IMG_Activity.setImageResource(R.drawable.ic_activity);
        IMG_Reward.setImageResource(R.drawable.ic_rewards);
        IMG_Budget.setImageResource(R.drawable.ic_budget);
        IMG_Account.setImageResource(R.drawable.ic_account);



        if (fr instanceof DashBoard)
        {
            LL_Dashboard.setBackgroundColor(Color.parseColor("#F0FDFE"));
            TXT_Dashboard.setTextColor(Color.parseColor("#05C3DE"));
            IMG_Dashboard.setImageResource(R.drawable.ic_dashboard_navy);

        }

        if (fr instanceof Activity)
        {
            LL_Activity.setBackgroundColor(Color.parseColor("#F0FDFE"));
            TXT_Activity.setTextColor(Color.parseColor("#05C3DE"));
            IMG_Activity.setImageResource(R.drawable.icon_activity_navyx);

        }

        if (fr instanceof Reward)
        {
            LL_Reward.setBackgroundColor(Color.parseColor("#F0FDFE"));
            TXT_Reward.setTextColor(Color.parseColor("#05C3DE"));
            IMG_Reward.setImageResource(R.drawable.icon_rewards_navy);
        }

        if (fr instanceof Budget)
        {
            LL_Budget.setBackgroundColor(Color.parseColor("#F0FDFE"));
            TXT_Budget.setTextColor(Color.parseColor("#05C3DE"));
            IMG_Budget.setImageResource(R.drawable.icon_budget_navy);

        }

        if (fr instanceof Account)
        {
            LL_Account.setBackgroundColor(Color.parseColor("#F0FDFE"));
            TXT_Account.setTextColor(Color.parseColor("#05C3DE"));
            IMG_Account.setImageResource(R.drawable.icon_account_navy);

        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LL_Dashboard:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                DashBoard search_basic = new DashBoard();
                fragmentTransaction.replace(R.id.Container, search_basic);
                fragmentTransaction.commit();
                break;

            case R.id.LL_Activity:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new Activity());
                fragmentTransaction.commit();
                break;

            case R.id.LL_Reward:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new Reward());
                fragmentTransaction.commit();
                break;

            case R.id.LL_Budget:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new Budget());
                fragmentTransaction.commit();
                break;
                case R.id.LL_Account:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new Account());
                fragmentTransaction.commit();
                break;

        }
    }
}
