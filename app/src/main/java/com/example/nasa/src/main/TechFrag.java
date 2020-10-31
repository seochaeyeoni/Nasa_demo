package com.example.nasa.src.main;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasa.R;
import com.example.nasa.src.ApplicationClass;
import com.example.nasa.src.main.adapter.TechTransferAdapter;
import com.example.nasa.src.main.interfaces.MainActivityView;
import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.main.models.TechTransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.nasa.src.ApplicationClass.*;

public class TechFrag extends Fragment implements MainActivityView {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private View view;

    private TechTransferAdapter techTransferAdapter;
    private ArrayList titleArrayList;
    private ArrayList descriptionArrayList;
    private ArrayList imageArrayList;

    //    private RadioButton r_btn1, r_btn2, r_btn3, r_btn4;
    private RadioGroup radioGroup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_tech, container, false);
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        recyclerView = (RecyclerView) view.findViewById(R.id.tech_rv);
        recyclerView.setHasFixedSize(true); //일정한 크기 사용
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        gridLayoutManager = new GridLayoutManager(getContext(), 2); //LinearLayoutManager 는 리스트뷰(가로/세로)
        recyclerView.setLayoutManager(gridLayoutManager);


        titleArrayList = new ArrayList<>();
        descriptionArrayList = new ArrayList<>();
        imageArrayList = new ArrayList<>();

        techTransferAdapter = new TechTransferAdapter(titleArrayList, descriptionArrayList, imageArrayList);
        recyclerView.setAdapter(techTransferAdapter);

//        //라디오 버튼 설정
//        r_btn1 = (RadioButton) view.findViewById(R.id.tech_radio_btn_1);
//        r_btn2 = (RadioButton) view.findViewById(R.id.tech_radio_btn_2);
//        r_btn3 = (RadioButton) view.findViewById(R.id.tech_radio_btn_3);
//        r_btn4 = (RadioButton) view.findViewById(R.id.tech_radio_btn_4);
        //라디오 그룹 설정
        radioGroup = (RadioGroup) view.findViewById(R.id.tech_radio_group);

//        //라디오 그룹 클릭 리스너
//        RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                switch (i) {
//                    case R.id.tech_radio_btn_1:
//                        search = "patent";
//                        break;
//                    case R.id.tech_radio_btn_2:
//                         search = "patent_issued";
//                         break;
//                    case R.id.tech_radio_btn_3:
//                         search = "software";
//                         break;
//                    case R.id.tech_radio_btn_4:
//                         search = "Spinoff";
//                         break;
//                    default:
//                }
//            }
//        });


        Button tech_transfer_btn = (Button) view.findViewById(R.id.tech_btn);
        tech_transfer_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.tech_radio_btn_1) {
                    ApplicationClass.search = "patent";
                } else if (id == R.id.tech_radio_btn_2) {
                    ApplicationClass.search = "patent_issued";
                } else if (id == R.id.tech_radio_btn_3) {
                    ApplicationClass.search = "software";
                } else if (id == R.id.tech_radio_btn_4) {
                    ApplicationClass.search = "Spinoff";
                }
                EditText editText = (EditText) view.findViewById(R.id.tech_edit_text);
                keyword = String.valueOf(editText.getText());
                Log.d("tech", keyword);
                tryGetTest();
            }
        });


        return view;
    }

    private void tryGetTest() {
        final MainService mainService = new MainService(this);
        mainService.getTechTransfer();
    }

    @Override
    public void apodGetSuccess(APOD apod) {

    }

    @Override
    public void apodGetFailure(String message) {

    }

    @Override
    public void techTransferGetSuccess(TechTransfer techTransfer) {

        if (techTransfer != null) {
            int count = Long.valueOf(techTransfer.getCount()).intValue();
            Log.d("tech", String.valueOf(count));

            for (int i = 0; i < count; i++) {
                titleArrayList.add(techTransfer.getResults().get(i).get(2));
                descriptionArrayList.add(techTransfer.getResults().get(i).get(3));
                imageArrayList.add(techTransfer.getResults().get(i).get(10));
            }
        }

        Log.d("tech", String.valueOf(titleArrayList));
        techTransferAdapter.notifyDataSetChanged();
    }

    @Override
    public void techTransferGetFailure(String message) {

    }
}
