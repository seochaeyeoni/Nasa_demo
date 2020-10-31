package com.example.nasa.src.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nasa.R;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class EarthFrag extends Fragment {
    private View view;
    private TextView mTvDateAsset;
    private TextView mTvIdAsset;
    private ImageView mIvAsset;
    private ImageView mIvEPIC;
    private TextView mTvCaptionEPIC;
    private TextView mTvDateEPIC;
    private TextView mTvCentroidCoordEPIC;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_earth, container, false);

        mTvCaptionEPIC = (TextView) view.findViewById(R.id.epic_caption);
        mIvEPIC = (ImageView) view.findViewById(R.id.epic_image);
        mTvDateEPIC = (TextView) view.findViewById(R.id.epic_date);
        mTvCentroidCoordEPIC = (TextView) view.findViewById(R.id.epic_centroid_coord);
        mIvAsset = (ImageView) view.findViewById(R.id.asset_image);
        mTvDateAsset = (TextView) view.findViewById(R.id.asset_date);
        mTvIdAsset = (TextView) view.findViewById(R.id.asset_id);


        //bundle 값 가져오기
        Bundle extra = getArguments();
        if (extra != null) {
            ArrayList epic_caption = extra.getParcelableArrayList("epic_caption");
            ArrayList epic_image = extra.getParcelableArrayList("epic_image");
            ArrayList epic_date = extra.getParcelableArrayList("epic_date");
            ArrayList epic_lat = extra.getParcelableArrayList("epic_lat");
            ArrayList epic_lon = extra.getParcelableArrayList("epic_lon");
            Log.d("splash", String.valueOf(extra));

            String[] date_processing = (String[]) epic_date.toArray(new String[epic_date.size()]);
            for (String s : date_processing) { System.out.println(s); }
            String[] date_processed = new String[epic_date.size()];
            for (int i=0; i<epic_date.size(); i++) {
                date_processed[i] = (date_processing[i].substring(0,10)).replace("-","/");
            }
            Log.d("splash", date_processed[0]);


            String asset_date = extra.getString("asset_date");
            String asset_id = extra.getString("asset_id");
            String asset_image = extra.getString("asset_image");

            //bundle 값 넣어주기
            mTvCaptionEPIC.setText((CharSequence) epic_caption.get(0));
            Picasso.get().load("https://api.nasa.gov/EPIC/archive/natural/"+date_processed[0]+"/png/" + epic_image.get(0) + ".png?api_key=N6z00feDaoh1VgRcZJwtCME4bOf6KdF1hMJc9G4U").into(mIvEPIC);
            mTvDateEPIC.setText((CharSequence) epic_date.get(0));
            mTvCentroidCoordEPIC.setText("centroid_coord / lat : " + epic_lat.get(0) + " / lon : " + epic_lon.get(0));
            mTvDateAsset.setText(asset_date);
            mTvIdAsset.setText(asset_id);
            Picasso.get().load(asset_image).into(mIvAsset);

        }

        return view;

    }

}

