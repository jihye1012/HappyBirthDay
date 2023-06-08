package no2114_2127.project.myapplication;

import static android.app.PendingIntent.getActivity;

import static no2114_2127.project.myapplication.CerdClass.cakeClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CerdMakeingPageActivity extends AppCompatActivity {
    // image View
    ImageView backButtonImageView;
    LayoutInflater inflater;
    // Fragment
    // Fragment
    Fragment CakeMakeingPageFragment,
            PolaroidMakeingPageFragment,
            VideoUploadingFragment,
            AwardMakeingWritingPageFragment;

    // rollingPaper를 보여주기 위한 셋팅
    static LinearLayout rollingPaperLayout;
    static LayoutInflater inflater1;
    static EditText rollingPaperContent, rollingPaperFrom;
    static LinearLayout.LayoutParams params;
    static ImageView rollingPaperIcon;
    static TextView rollingPaperNextTextView, rollingPaperPreviousTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerd_makeing_page);
        backButtonImageView = findViewById(R.id.back_button_image_view);
        backButtonImageView.setOnClickListener(onClickListener);
        CakeMakeingPageFragment = new CakeMakeingPageFragment();
        PolaroidMakeingPageFragment = new PolaroidMakeingPageFragment();
        VideoUploadingFragment = new VideoUploadingFragment();
        AwardMakeingWritingPageFragment = new AwardMakeingWritingPageFragment();

        // rollingPaper를 보여주기 위한 셋팅
        inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rollingPaperLayout = (LinearLayout) inflater1.inflate(R.layout.rolling_paper_frames_bottom_sheet, null);
        rollingPaperLayout.setBackgroundColor(Color.parseColor("#99000000"));
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);

        //fragment 교체를 위한 switch문
        switch (StageClass.stage){
            case 0:
                //케이크
                cakeView();
                break;
            case 1:
                //폴라로이드
                polaroidView();
                break;
            case 2:
                //동영상
                videoView();
                break;
            case 3:
                //상장
                awardView();
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CerdMakeingPageActivity.this, CerdMakePageActivity.class);
            switch (view.getId()){
                case R.id.rolling_paper_next_button_text_view:
                    StageClass.stage = 1;   //완료시 폴라로이드 페이지 넘어가기 위한 코드
                case R.id.back_button_image_view:
                    finish();
                    startActivity(intent);
                    break;
                case R.id.rolling_paper_previous_button_text_view:
                    rollingPaperLayout.setVisibility(View.GONE);
                    break;
            }
        }
    };


    private void cakeView(){
        getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, CakeMakeingPageFragment).commitAllowingStateLoss();
        // rollingPaper를 보여주기 위한 셋팅
        addContentView(rollingPaperLayout, params);
        rollingPaperLayout.setVisibility(View.GONE);   //처음에는 없애둔다
        // rollingPaperIcon EditText 설정
        rollingPaperIcon = rollingPaperLayout.findViewById(R.id.rolling_paper_icon);
        // rollingPaperLayout EditText 설정
        rollingPaperContent = rollingPaperLayout.findViewById(R.id.rolling_paper_content);
        rollingPaperFrom = rollingPaperContent.findViewById(R.id.rolling_paper_from);
        rollingPaperContent.setText(cakeClass.getRollingPaper());

        // rollingPaperLayout textView 설정
        rollingPaperNextTextView = rollingPaperLayout.findViewById(R.id.rolling_paper_next_button_text_view);
        rollingPaperPreviousTextView = rollingPaperLayout.findViewById(R.id.rolling_paper_previous_button_text_view);
        rollingPaperNextTextView.setOnClickListener(onClickListener);
        rollingPaperPreviousTextView.setOnClickListener(onClickListener);
    }

    public static void rollingPaperSheet(){
        //rollingPapaerLinearLayout.setVisibility(View.VISIBLE);
        rollingPaperLayout.setVisibility(View.VISIBLE);//필요할때 다시 보여주는코드
    }

    private void polaroidView() {
        getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, PolaroidMakeingPageFragment).commitAllowingStateLoss();

    }

    private void videoView() {
        getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, VideoUploadingFragment).commitAllowingStateLoss();
    }
    private void awardView() {
        getSupportFragmentManager().beginTransaction().add(R.id.cerd_make_frame_layout, AwardMakeingWritingPageFragment).commitAllowingStateLoss();

    }

    public void replaceFragment(Fragment fragment) {
        //fragment 바꾸는 메소드
        getSupportFragmentManager().beginTransaction().replace(R.id.cerd_make_frame_layout, fragment).commitAllowingStateLoss();
    }
}