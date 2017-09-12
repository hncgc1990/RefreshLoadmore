package com.hncgc1990.refreshloadmore.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hncgc1990.refreshloadmore.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.btn_four)
    Button btnFour;
    @BindView(R.id.btn_five)
    Button btnFive;
    @BindView(R.id.btn_six)
    Button btnSix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                ListViewActivity.startListViewActivity(this);
                break;
            case R.id.btn_two:
                RecyclerviewActivity.startRecyclerviewActivity(this);
                break;
            case R.id.btn_three:
                CustomeLoadMoreActivity.startCustomeLoadMoreActivity(this);
                break;
            case R.id.btn_four:
                CustomeProgressActivity.startCustomeProgressActivity(this);
                break;
            case R.id.btn_five:
                FrameLayoutActivity.startFrameLayoutActivity(this);
                break;
            case R.id.btn_six:
                ConstraintLayoutActivity.startConstraintLayoutActivity(this);
                break;
            default:
                break;
        }
    }
}
