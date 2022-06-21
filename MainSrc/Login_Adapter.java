package com.example.restaurant_reservation2;

import android.content.Context;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Login_Adapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabls;

    public Login_Adapter(@NonNull FragmentManager fm, Context context, int totalTabls) {
        super(fm);
        this.context = context;
        this.totalTabls = totalTabls;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Login_frag loginfrag= new Login_frag();
                return loginfrag;
            case 1:
                Signup_frag singupfrag=new Signup_frag();
                return singupfrag;
            default: return  null;
        }
    }
    @Override
    public int getCount() {
        return totalTabls;
    }
}