package android.com.dishcounts.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.com.dishcounts.Fragments.*;

public class ImageViewPagerAdapter extends FragmentPagerAdapter {
    public static int totalPage = 4;
    private Context _context;

    public ImageViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        _context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch (position) {
            case 0:
                f = new Onboarding1();
                break;
            case 1:
                f = new Onboarding2();
                break;
            case 2:
                f = new Onboarding3();
                break;
            case 3:
                f = new Onboarding4();
        }
        return f;
    }

    @Override
    public int getCount() {
        return totalPage;
    }
}
