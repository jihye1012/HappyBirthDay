package no2114_2127.project.myapplication;

import android.app.Dialog;
import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DecodePath;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class DecoFragment extends Fragment {
    private GridView DecoGridView;
    Dialog addLink;
//    TextView noBtn;
//    TextView yesBtn;
//    EditText inputLink;
    TextView cardName;
    TextView nameBirth;
//    String inputText;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;
    CollectionReference collectionRef, userCardColl;
    private CustomAdapter MainDecoGridAdapter;
    GridView mainDecoGridView;
    CardAdapter cardAdapter;
//    TextView noBtn;
//    TextView yesBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deco, container, false);
        View view2 = inflater.inflate(R.layout.dialog_input_link, container, false);
        View view3 = inflater.inflate(R.layout.main_grid_shortcut, container, false);
        mainDecoGridView=view.findViewById(R.id.main_deco_grid);
        cardName=view3.findViewById(R.id.tv_nickname);
        nameBirth=view3.findViewById(R.id.tv_name_birthday);
        addLink = new Dialog(getActivity());       // Dialog 초기화
        addLink.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        addLink.setContentView(R.layout.dialog_input_link);             // xml 레이아웃 파일과 연결

        // 파이어베이스
        db = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        inputLink=view2.findViewById(R.id.put_text);
//        noBtn = addLink.findViewById(R.id.btn_cancel);
//        yesBtn = addLink.findViewById(R.id.btn_add);
//
//        inputText = inputLink.getText().toString();

        userCardColl = db.collection("users").document(firebaseUser.getUid()).collection("userCard");

// 어댑터를 GridView에 설정
        mainDecoGridView = view.findViewById(R.id.main_deco_grid);
        cardAdapter = new CardAdapter();
        //","
        setAdapter();

        view.findViewById(R.id.link_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAddLink(); // 아래 showAddLink() 함수 호출
            }
        });


        //mainDecoGridView.setOnItemClickListener();

        return view;
    }
    String name;
    public void setAdapter(){
        List<String> documenPath = new ArrayList<String>();
        userCardColl.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("확인1", document.getId() + " => " + document.get("fieldName"));
                        // (String) Objects.requireNonNull(document.get("fieldName"))
                        //q47BnidbW3FygI43J09N
                       // db.collection("cards").document(Objects.requireNonNull(document.get("fieldName")).toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        documenPath.add((String)document.get("fieldName"));
                    }
                } else {
                    Log.d("확인", "Error getting documents: ", task.getException());
                }
                Log.d("확인2",documenPath.get(0));
                for (int i = 0; i< documenPath.size(); i++){

                    Log.d("확인3",documenPath.get(i));
                    String s =  documenPath.get(i).trim();;
                    if(s.equals("q47BnidbW3FygI43J09N")){
                        Log.d("확인5",documenPath.get(i)+"같은지 확인");
                    }
                    db.collection("cards")
                            .document(documenPath.get(i).trim())
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(Task<DocumentSnapshot> task) {
                                    Map<String, Object> data =  task.getResult().getData();
                                    //String name = (String) data.get("cardName");
                                    //Log.d("확인",task.getResult().get+"");
                                    Log.d("확인4",data.get("cardName")+"");
                                    Log.d("확인4",data.get("userName")+"");
                                    Log.d("확인4",data.get("BDay")+"");
                                    cardAdapter.addItme(new CardListItem("TO. "+data.get("cardName"), (String) data.get("BDay")));

                                }

                            });
               }
            }
        });
        mainDecoGridView.setAdapter(cardAdapter);
    }

    private List<MainDecoListItem> getData() {
        List<MainDecoListItem> data = new ArrayList<>();
        return data;
    }
    public void showAddLink(){

        addLink.show(); // 다이얼로그 띄우기
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Objects.requireNonNull(addLink.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        EditText inputLink=addLink.findViewById(R.id.put_text);
        TextView noBtn = addLink.findViewById(R.id.btn_cancel);
        TextView yesBtn = addLink.findViewById(R.id.btn_add);

        inputLink.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()<=0) {
                    Log.d("확인", "onTextChanged: ");
                    // 추가 버튼 비활성화
                    yesBtn.setClickable(false);
                    yesBtn.setBackground(null);
                    yesBtn.setBackgroundResource(R.drawable.rectangle_resource_activation_radadd_enabled);

                } else {
                    Log.d("확인2", "onTextChanged: ");
                    yesBtn.setClickable(true);
                    yesBtn.setBackground(null);
                    yesBtn.setBackgroundResource(R.drawable.rectangle_resource_activation_radadd);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                if (inputLink.getText().toString().length()<=0) {
//
//                    // 추가 버튼 비활성화
//                    yesBtn.setClickable(false);
//                     yesBtn.setBackground(null);
//                    yesBtn.setBackgroundResource(R.drawable.rectangle_resource_activation_radadd_enabled);
//                } else {
//
//                    yesBtn.setClickable(true);
//                    yesBtn.setBackground(null);
//                    yesBtn.setBackgroundResource(R.drawable.rectangle_resource_activation_radadd);
//                }
            }
        });

        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */

        // 위젯 연결 방식은 각자 취향대로~
        // '아래 아니오 버튼'처럼 일반적인 방법대로 연결하면 재사용에 용이하고,
        // '아래 네 버튼'처럼 바로 연결하면 일회성으로 사용하기 편함.
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        // 취소 버튼

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 원하는 기능 구현
                addLink.dismiss(); // 다이얼로그 닫기
            }
        });
        //추가 버튼
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = inputLink.getText().toString();  // 가져온 링크
                if (inputText.length()>0) {
                    Log.d("확인", "onTextChanged: ");
                    // 입력된 텍스트가 비어 있는 경우
                    // yesBtn.setEnabled(true); // 추가 버튼 활성화
                    //yesBtn.setBackground(null);v

                    collectionRef = db.collection("users").document(firebaseUser.getUid()).collection("userCard");
                    collectionRef.whereEqualTo("fieldName", inputText)
                            .get()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                        // 겹치는 값이 이미 존재하는 경우 처리 로직을 수행
                                        Toast.makeText(getContext(), "이미 있는 링크 입니다.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // 겹치는 값이 없는 경우 값을 삽입
                                        Map<String, Object> data = new HashMap<>();
                                        data.put("fieldName", inputText);
                                        collectionRef.document().set(data);
                                        MainDecoGridAdapter.addItem(new MainDecoListItem(cardName, nameBirth));
                                        MainDecoGridAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    // 조회 실패 처리 로직을 수행
                                }
                            });

                    addLink.dismiss();

                }

                    //inputLink.setError("입력이 필요합니다.");


//                        MainDecoGridAdapter.addItem(new MainDecoListItem(cardName,nameBirth));
//                        MainDecoGridAdapter.notifyDataSetChanged();
//                        addLink.dismiss();
                    //}
                }
    });
//        inputLink.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (editable.toString().isEmpty()) {
//                    // 입력된 텍스트가 비어 있는 경우
//                    yesBtn.setEnabled(false); // 추가 버튼 비활성화
//                    // yesBtn.setBackground(null);
//                    yesBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_resource_activation_radadd_enabled));
//                } else {
//                    // 입력된 텍스트가 있는 경우
//                    yesBtn.setEnabled(true); // 추가 버튼 활성화
//                    //yesBtn.setBackground(null);
//                    yesBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_resource_activation_radadd));
//                }
//            }
//        });
    }

}







