package com.hncgc1990.refreshloadmore.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hncgc1990.refreshloadmore.R;
import com.nguyenhoanglam.progresslayout.ProgressFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FrameLayoutActivity extends AppCompatActivity implements View.OnClickListener{




    @BindView(R.id.progressLayout)
    ProgressFrameLayout mProgressLayout;


    List<Integer> mIds;
    @BindView(R.id.btn_showLoading)
    Button btnShowLoading;
    @BindView(R.id.btn_showEmpty)
    Button btnShowEmpty;
    @BindView(R.id.btn_showError)
    Button btnShowError;
    @BindView(R.id.btn_showContent)
    Button btnShowContent;


    public static void startFrameLayoutActivity(Context context){
        Intent intent=new Intent(context,FrameLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        ButterKnife.bind(this);

        mIds = new ArrayList<>();
        mIds.add(R.id.toolbar);

        btnShowLoading.setOnClickListener(this);
        btnShowEmpty.setOnClickListener(this);
        btnShowError.setOnClickListener(this);
        btnShowContent.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_showLoading:
                mProgressLayout.showLoading(mIds);
                break;
            case R.id.btn_showEmpty:
                mProgressLayout.showEmpty(ContextCompat.getDrawable(this, R.drawable.ic_empty), "暂无数据", mIds);
                break;
            case R.id.btn_showError:
                mProgressLayout.showError(ContextCompat.getDrawable(this, R.drawable.ic_no_connection), "网络错误", "重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FrameLayoutActivity.this, "点击重试", Toast.LENGTH_SHORT).show();
                    }
                }, mIds);
                break;
            case R.id.btn_showContent:

                mProgressLayout.showContent();
                break;
            default:
                break;
        }
    }
}
