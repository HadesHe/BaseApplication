package com.avenging.hades.baseapplication.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avenging.hades.baseapplication.R;

import java.util.ArrayList;
import java.util.List;

//https://github.com/kaedea/android-mvp-pattern/blob/master/app/src/main/res/layout/activity_home.xml
public class MainActivity extends AppCompatActivity implements HomeContract.HomeView {

    private RecyclerView rvHome;
    private List<String> data=new ArrayList<>();
    private HomeAdapter mAdapter;
    private HomePresenterImp mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomePresenter=new HomePresenterImp();

        mHomePresenter.attachView(this);

        initView();
    }

    private void initView() {
        rvHome=(RecyclerView)findViewById(R.id.rvHome);
        mAdapter=new HomeAdapter(data);
        mAdapter.setListener(new HomeItemClickLitener() {
            @Override
            public void onItemClickListener(int position) {
                mHomePresenter.onItemClick(position);

            }
        });
        rvHome.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvHome.setAdapter(mAdapter);

        View loadingView=LayoutInflater.from(this).inflate(R.layout.item_empty_view,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePresenter.detachView();
    }

    public interface HomeItemClickLitener{
        void onItemClickListener(int position);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{

        private final List<String> mData;
        private HomeItemClickLitener mListener;

        public void setListener(HomeItemClickLitener listener){
            this.mListener=listener;

        }

        public HomeAdapter(List<String> data){
            this.mData=data;
        }

        @Override
        public HomeAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
            return new HomeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HomeViewHolder holder, final int position) {
            holder.tvHomeItemTitle.setText(mData.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onItemClickListener(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class HomeViewHolder extends RecyclerView.ViewHolder{

            private final TextView tvHomeItemTitle;
            public HomeViewHolder(View itemView) {
                super(itemView);
                tvHomeItemTitle=(TextView)itemView.findViewById(R.id.tvHomeItemTitle);
            }

        }
    }
}
