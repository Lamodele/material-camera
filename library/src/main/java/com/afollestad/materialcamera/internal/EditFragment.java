package com.afollestad.materialcamera.internal;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialcamera.R;

/**
 * Created by BUSOLA on 7/3/2017.
 */

public class EditFragment extends Fragment implements TextView.OnEditorActionListener {

    private EditText editText;
    private int count = 0;

    interface LicensePlayback{
        void LicenseBack (String license);
    }

    public static EditFragment newInstance(String title){
        EditFragment fragment = new EditFragment();
        fragment.setRetainInstance(true);
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.mcam_fragment_edit_text, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.Edit_License);
        editText.setOnEditorActionListener(this);
        editText.setHint("LICENSE 1");
        count++;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            String text = editText.getText().toString();
            if(text != null){
                // listener interface
                LicensePlayback listener = (LicensePlayback) getActivity();
                listener.LicenseBack (text);
                // dismiss keyboard on OK
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                // clear EditText
                editText.setText("");
                count++;
                editText.setHint("LICENSE" + " " + count);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }
}
