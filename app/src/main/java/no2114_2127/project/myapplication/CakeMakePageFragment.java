package no2114_2127.project.myapplication;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CakeMakePageFragment extends Fragment {
    // textView button
    TextView decorationButtonTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cake_make_page, container, false);
        //button find view by id
        decorationButtonTextView = view.findViewById(R.id.decoration_button_text_view);
        decorationButtonTextView.setOnClickListener(onClickListener);

        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public static CakeMakePageFragment cakeMakePageInstance() {
        return new CakeMakePageFragment();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CardMakeingPageActivity.class);

            switch (v.getId()){
                case R.id.decoration_button_text_view:
                    getActivity().finish();
                    startActivity(intent);
                break;
            }
        }
    };
}