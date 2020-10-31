package com.example.nasa.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nasa.R;
import com.example.nasa.src.ApplicationClass;
import com.example.nasa.src.main.interfaces.MainActivityView;
import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.main.models.TechTransfer;

import static com.example.nasa.src.ApplicationClass.date;

public class ApodFrag extends Fragment implements MainActivityView {
    private View view;
    private TextView mTvTitle;
    private TextView mTvDate;
    private TextView mTvExplanation;
    private ImageView mIvApod;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_apod, container, false);

        mTvTitle = (TextView) view.findViewById(R.id.apod_title);
        mIvApod = (ImageView) view.findViewById(R.id.apod_image);
        mTvDate = (TextView) view.findViewById(R.id.apod_date);
        mTvExplanation = (TextView) view.findViewById(R.id.apod_explanation);

        //bundle 값 가져오기
        Bundle extra = getArguments();
        if (extra != null) {
            String title = extra.getString("apod_title");
            String date = extra.getString("apod_date");
            String explanation= extra.getString("apod_explanation");
            String image = extra.getString("apod_image");

            //bundle 값 넣어주기
            mTvTitle.setText(title);
            mTvDate.setText(date);
            mTvExplanation.setText(explanation);
            Glide.with(this).load(image).into(mIvApod);

            Button apod_btn = (Button) view.findViewById(R.id.apod_btn);
            apod_btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = (EditText)view.findViewById(R.id.apod_edit_text);
                    ApplicationClass.date = String.valueOf(editText.getText());
                    tryGetTest();
                    Log.d("click", String.valueOf(editText.getText()));
                }
            });
        }



        return view;
    }



    private void tryGetTest() {
        final MainService mainService = new MainService(this);
        mainService.getApod();
    }

    @Override
    public void apodGetSuccess(APOD apod) {
        //새로운 apod를 불러올 때
        mTvTitle.setText(apod.getTitle());
        mTvDate.setText(apod.getDate());
        mTvExplanation.setText(" "+apod.getExplanation());
        Glide.with(this).load(apod.getUrl()).into(mIvApod);


    }

    @Override
    public void apodGetFailure(String message) {

    }

    @Override
    public void techTransferGetSuccess(TechTransfer techTransfer) {

    }

    @Override
    public void techTransferGetFailure(String message) {

    }
}
