package com.gmail.pdnghiadev.ex8_1drawable.fragments;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.gmail.pdnghiadev.ex8_1drawable.R;
import com.gmail.pdnghiadev.ex8_1drawable.presenter.SignUpStep1Presenter;
import com.gmail.pdnghiadev.ex8_1drawable.presenter.SignUpStep1PresenterImpl;
import com.gmail.pdnghiadev.ex8_1drawable.untils.CustomDialog;
import com.gmail.pdnghiadev.ex8_1drawable.untils.UserInfoConstant;
import com.gmail.pdnghiadev.ex8_1drawable.view.SignUpStep1View;


/**
 * Created by PDNghiaDev on 11/25/2015.
 * Class perform 5 functions
 * 1 - Check for FirstName is empty
 * 2 - Check for LastName is empty
 * 3 - Check for Email is empty and invalid
 * 4 - Check for PhoneNumber is empty and phone's length
 * 5 - Check for the user have select gender
 */
public class SignUpStep1Fragment extends Fragment implements View.OnClickListener, SignUpStep1View {
    private Button mBtnNext;
    private EditText mFirstname, mLastname, mEmail, mPhonenumber;
    private String mStrFirstname, mStrLastname, mStrEmail, mStrPhonenumber;
    private RadioGroup mGender;
    private SignUpStep1Presenter signUpPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sign_up_step_1_fragment, container, false);

        loadComponents(view);

        signUpPresenter = new SignUpStep1PresenterImpl(this);
        mBtnNext.setOnClickListener(this);

        return view;
    }

    public void loadComponents(View view) {
        mBtnNext = (Button) view.findViewById(R.id.btnNext);
        mFirstname = (EditText) view.findViewById(R.id.editFirstName);
        mLastname = (EditText) view.findViewById(R.id.editLastName);
        mEmail = (EditText) view.findViewById(R.id.editEmail);
        mPhonenumber = (EditText) view.findViewById(R.id.editPhoneNumber);
        mGender = (RadioGroup) view.findViewById(R.id.rgGender);
    }


    @Override
    public void onClick(View view) {
        mStrFirstname = mFirstname.getText().toString();
        mStrLastname = mLastname.getText().toString();
        mStrEmail = mEmail.getText().toString();
        mStrPhonenumber = mPhonenumber.getText().toString();
        signUpPresenter.validateCredentials(mStrFirstname, mStrLastname, mStrEmail, mStrPhonenumber, checkGender());
    }

    public Boolean checkGender(){ // Check for Gender
        return mGender.getCheckedRadioButtonId() < 0;
    }

    @Override
    public void setFistnameError() {
        mFirstname.setError(getString(R.string.first_name_error));
    }

    @Override
    public void setLastnameError() {
        mLastname.setError(getString(R.string.last_name_error));
    }

    @Override
    public void setEmailError() {
        mEmail.setError(getString(R.string.email_error));
    }

    @Override
    public void setEmailInvalidError() {
        mEmail.setError(getString(R.string.email_invalid_error));
    }

    @Override
    public void setPhonenumberError() {
        mPhonenumber.setError(getString(R.string.phone_number_error));
    }

    @Override
    public void setPhonenumberInvalidError() {
        mPhonenumber.setError(getString(R.string.phone_number_invalid_error));
    }

    @Override
    public void setGenderError() {
        CustomDialog dialog = new CustomDialog("Please choose your gender");
        dialog.show(getFragmentManager(), UserInfoConstant.SIGNUP_STEP1);
    }

    @Override
    public void navigationToStep2() {
        SignUpStep2Fragment fragment = new SignUpStep2Fragment();
        Bundle bundle = new Bundle();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        bundle.putString(UserInfoConstant.FIRST_NAME, mStrFirstname);
        bundle.putString(UserInfoConstant.LAST_NAME, mStrLastname);
        bundle.putString(UserInfoConstant.EMAIL, mStrEmail);
        bundle.putString(UserInfoConstant.PHONE_NUMBER, mStrPhonenumber);
        fragment.setArguments(bundle);
        ft.replace(R.id.signUp, fragment);
        ft.addToBackStack(UserInfoConstant.SIGNUP_STEP2);
        ft.commit();
    }
}
