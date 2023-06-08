package no2114_2127.project.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CerdMakePageActivity extends AppCompatActivity {
    String CerdMakeTitle = "의 생일카드";

    int cerdMakeFrameLayoutId;
    //Layout
    FrameLayout cerdMakeFrameLayout;
    //TextView
    TextView cerdMakeSubtitleTextView;
    //ImageView
    ImageView backButtonImageView, nextButtonImageView, stageImageView, cancelButtonImageView;
    Fragment CakeMakePageFragment, CakeMakeingPageFragment, CakeMakeWritingPageFragment,
            PolaroidMakePageFragment, PolaroidMakeingPageFragment,
            VideoUploadFragment, VideoUploadingPragment,
            AwardMakePageFragment, AwardMakeChooseFragment, AwardMakeChooseFormFragment, AwardMakeWritingPageFragment,
            FromFinishPageFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerd_make_page);
        cerdMakeFrameLayoutId = R.id.cerd_make_frame_layout;
        // Layout find View by id
        cerdMakeFrameLayout = findViewById(R.id.cerd_make_frame_layout);
        // textView find View by id

        // ImageView find View by id
        cancelButtonImageView = findViewById(R.id.cancel_button_image_view);
        backButtonImageView = findViewById(R.id.back_button_image_view);
        stageImageView = findViewById(R.id.stage_image_view);


        // Fragent
        CakeMakePageFragment = new CakeMakePageFragment();
        PolaroidMakePageFragment = new PolaroidMakePageFragment();
        AwardMakePageFragment = new AwardMakePageFragment();
        VideoUploadFragment = new VideoUploadFragment();
        FromFinishPageFragment = new FromFinishPageFragment();
        // setOnClick
        // backButtonImageView setOnClick
       cancelButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StageClass.stage = 0;
                finish();
            }
        });
        Log.d("확인 : ", StageClass.stage+"번");
        startFragment();


    }

    public void startFragment(){

        switch (StageClass.stage){
            case 0:
                //케이크
                CerdClass.cakeClass = new CakeClass();
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, CakeMakePageFragment).commitAllowingStateLoss();
                backButtonImageView.setVisibility(View.INVISIBLE);
                break;
            case 1:
                //폴라로이드
                CerdClass.polaroidClass = new PolaroidClass();
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, PolaroidMakePageFragment).commitAllowingStateLoss();
                stageImageView.setImageResource(R.drawable.cerd_make_page2);
                break;
            case 2:
                //동영상
                CerdClass.videoClass = new VideoClass();
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, VideoUploadFragment).commitAllowingStateLoss();
                stageImageView.setImageResource(R.drawable.cerd_make_page3);
                break;
            case 3:
                //상장
                CerdClass.awardClass = new AwardClass();
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, AwardMakePageFragment).commitAllowingStateLoss();
                stageImageView.setImageResource(R.drawable.cerd_make_page4);
                break;
            case 4:
                //마무리
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, FromFinishPageFragment).commitAllowingStateLoss();
                stageImageView.setImageResource(R.drawable.cerd_make_page5);
                break;
        }
    }
}