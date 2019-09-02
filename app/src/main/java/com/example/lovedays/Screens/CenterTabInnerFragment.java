package com.example.lovedays.Screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lovedays.R;
import com.example.lovedays.Utils.Const;

import static com.example.lovedays.Screens.AbsFragment.root;

/**
 * Created by KING JINHO on 2019-08-22
 */
public class CenterTabInnerFragment extends AbsFragment {
    public static final String TAG = CenterTabInnerFragment.class.getSimpleName();
    private Type type;

    public CenterTabInnerFragment(Type type) {
        this.type = type;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.centertab_inner_text_change, container, false);
        TextView title = view.findViewById(R.id.tv_title);
        EditText et_target = view.findViewById(R.id.et_newText);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        Button btnOk = view.findViewById(R.id.btn_ok);
        et_target.setPaintFlags(View.INVISIBLE);
        title.setText(type.getTitle());
        switch (type.getType()) {
            case "ME":
                et_target.setText(Const.myName);
                break;
            case "YOU":
                et_target.setText(Const.yourName);
                break;
            case "TEXT":
                et_target.setText(root.getString(R.string.in_love_since, Const.relationshipSince));
                break;
        }
        et_target.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                InputMethodManager imm = (InputMethodManager) root.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_target.getWindowToken(), 0);
                return false;
            }
        });

        btnCancel.setOnClickListener(v -> root.onBackPressed());
        btnOk.setOnClickListener(v -> {
            SharedPreferences preferences = root.getSharedPreferences(Const.USER, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            switch (type.getType()) {
                case "ME":
                    editor.putString(Const.MY_NAME, et_target.getText().toString());
                    break;
                case "YOU":
                    editor.putString(Const.HIS_HER_NAME, et_target.getText().toString());
                    break;
                default:
                    break;
            }
            editor.commit();
            root.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new MainScreenViewPagerGroup(), MainScreenViewPagerGroup.TAG)
                    .commit();
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

enum Type {
    TEXT("TEXT", root.getString(R.string.set_your_new_phrase)),
    ME("ME", root.getString(R.string.set_new_name)),
    YOU("YOU", root.getString(R.string.set_new_name));

    String type;
    String title;

    Type(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public static Type getEnum(String type) {
        switch (type) {
            case "TEXT":
                return TEXT;
            case "ME":
                return ME;
            case "YOU":
                return YOU;
            default:
                return null;
        }
    }
}
