package no2114_2127.project.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CerdMakePageActivity extends AppCompatActivity {
    public static int stage;
    String CerdMakeTitle = "의 생일카드";

    int cerdMakeFrameLayoutId;
    //Layout
    FrameLayout cerdMakeFrameLayout;
    //TextView
    TextView cerdMakeSubtitleTextView;
    //ImageView
    ImageView cancelButtonImageView;
    Fragment CakeMakePageFragment, CakeMakeingPageFragment, CakeMakeWritingPageFragment,
            PolaroidMakePageFragment, PolaroidMakeingPageFragment,
            VideoUploadFragment, VideoUploadingPragment,
            AwardMakePageFragment, AwardMakeChooseFragment, AwardMakeChooseFormFragment, AwardMakeWritingPageFragment,
            FromFinishPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerd_make_page);
        stage = 0;
        cerdMakeFrameLayoutId = R.id.cerd_make_frame_layout;
        // Layout find View by id
        cerdMakeFrameLayout = findViewById(R.id.cerd_make_frame_layout);
        // textView find View by id
        // ImageView find View by id
        cancelButtonImageView = findViewById(R.id.cancel_button_image_view);

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
                finish();
            }
        });

        switch (stage){
            case 0:
                //케이크
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, CakeMakePageFragment).commitAllowingStateLoss();
                break;
            case 1:
                //폴라로이드
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, PolaroidMakePageFragment).commitAllowingStateLoss();
                break;
            case 3:
                //동영상
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, VideoUploadFragment).commitAllowingStateLoss();
                break;
            case 4:
                //상장
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, AwardMakePageFragment).commitAllowingStateLoss();
                break;
            case 5:
                //상장
                getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, FromFinishPageFragment).commitAllowingStateLoss();
                break;
        }


    }
}