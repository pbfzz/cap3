package com.example.chapter3.homework;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import com.airbnb.lottie.LottieAnimationView;
import android.content.Context;

public class PlaceholderFragment extends Fragment {

    LottieAnimationView animationView;
    ListView  listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view= inflater.inflate(R.layout.fragment_placeholder , container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(new ListViewAdapter(container.getContext()));
        animationView=(LottieAnimationView)view.findViewById(R.id.animation_view);
        animationView.playAnimation();

        listView.setAlpha(0);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {

                // 这里会在 5s 后执行
                ObjectAnimator adptAnimator = ObjectAnimator.ofFloat(listView,
                        "alpha", 0, 1f);

                ObjectAnimator adptAnimator2 = ObjectAnimator.ofFloat(animationView,
                        "alpha", 1, 0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(adptAnimator,adptAnimator2);
                animatorSet.start();
                // listView.setVisibility(View.VISIBLE);
                animationView.pauseAnimation();
                //animationView.setVisibility(View.INVISIBLE);

                // listView.setVisibility(View.VISIBLE);
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
            }
        }, 5000);
    }
    public static class ListViewAdapter extends BaseAdapter {

        private Context mContext;

        public ListViewAdapter(Context context) {
            mContext = context;
        }


        @Override public int getCount() {
            return 40;
        }

        @Override public Object getItem(int position) {
            return null;
        }

        @Override public long getItemId(int position) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.item, null);

            } else {
                //!=null
                view = convertView;
            }

            return view;
        }
    }
}
