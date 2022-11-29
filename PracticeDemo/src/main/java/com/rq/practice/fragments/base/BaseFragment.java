package com.rq.practice.fragments.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 *  BaseFragment
 *  Fragment基类
 *
 * @author rock you
 * @version [1.0.0 2018.6.4]
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;

    // 当前显示的fragment
    private Fragment mContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(getLayoutID(), container, false);
            bindView();
            initData();
        }

        ViewParent viewParent = mView.getParent();
        if (viewParent != null){
            // 防止多次加载View
            // 在使用FragmentTabHost的时候，每次选中fragment都会调用一次onCreateView
            ((ViewGroup)viewParent).removeView(mView);
        }
        return mView;
    }

    public static <T> Fragment newInstance(Class<T> clazz, Bundle bundle){
        Fragment fragment = null;
        try {
            fragment = (Fragment) clazz.newInstance();
            if (bundle != null){
                fragment.setArguments(bundle);
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    public static <T> Fragment newInstance(Class<T> clazz){
        return newInstance(clazz, null);
    }

    public abstract int getLayoutID();

    public abstract void bindView();

    public abstract void initData();

    private View getCurrentView(){
        return mView;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int id){
        T t = getCurrentView().findViewById(id);
        if (t == null){
            throw new IllegalArgumentException("id not found!");
        }
        return (T) t.findViewById(id);
    }

    protected void startActivity(Class<? extends Activity> clazz){
        startActivity(new Intent(getActivity(), clazz));
    }


    protected void addHideFragment(Fragment to, int id) {
        if (mContent != to) {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager == null) {
                return;
            }
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                // 隐藏当前的fragment，add下一个到Activity中
                if (mContent == null) {
                    transaction.add(id, to).commitAllowingStateLoss();
                } else {
                    transaction.hide(mContent).add(id, to).commitAllowingStateLoss();
                }
            } else {
                // 隐藏当前的fragment，显示下一个
                transaction.hide(mContent).show(to).commitAllowingStateLoss();
            }
            mContent = to;
        }
    }

}
