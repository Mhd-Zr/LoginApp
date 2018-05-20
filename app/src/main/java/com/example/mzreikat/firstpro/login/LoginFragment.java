package com.example.mzreikat.firstpro.login;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mzreikat.firstpro.HomeActivity;
import com.example.mzreikat.firstpro.R;

import java.util.Objects;

import butterknife.ButterKnife;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment
{
    // UI references
    AutoCompleteTextView mEmailView;
    EditText mPasswordView;
    View mProgressView;
    View mLoginFormView;
    RadioButton mRadioButton;
    TextView mSignUpView;

    boolean cancel = false;
    View focusView = null;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.inject(Objects.requireNonNull(getActivity()));

        ImageView imageView = fragmentView.findViewById(R.id.fragmentLogin_logo);
        final View rootView = getActivity().findViewById(R.id.loginActId);

        //Detect if the keyboard is visible
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();

                // if more than 200 dp, it's probably a keyboard...
                if (heightDiff > dpToPx(getActivity(), 200)) {
                    // ...hide the logo form the screen
                    imageView.setVisibility(View.GONE);
                }
                else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });

        mEmailView = fragmentView.findViewById(R.id.fragmentLogin_email);
        mPasswordView = fragmentView.findViewById(R.id.fragmentLogin_password);
        mProgressView = fragmentView.findViewById(R.id.fragmentLogin_loginProgress);
        mLoginFormView = fragmentView.findViewById(R.id.fragmentLogin_scrollViewLoginForm);
        mRadioButton = fragmentView.findViewById(R.id.fragmentLogin_radioBtn);

        Button loginBtn = fragmentView.findViewById(R.id.fragmentLogin_loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                attemptLogin();
            }
        });

        mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRadioButton.isSelected()) {
                    mRadioButton.setSelected(false);
                    mRadioButton.setChecked(false);
                }
                else {
                    mRadioButton.setSelected(true);
                    mRadioButton.setChecked(true);
                }
            }
        });

        mSignUpView = fragmentView.findViewById(R.id.fragmentLogin_signUp);
        mSignUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.login_root_fragment, new NewUserFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return fragmentView;
    }
    @Override
    public void onPause() {
        super.onPause();
        mEmailView.setText(null);
        mPasswordView.setText(null);
        mRadioButton.setSelected(false);
        mRadioButton.setChecked(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        showKeyboard();
        showProgress(false);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        checkValidation();

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            String str = mEmailView.getText().toString();
            intent.putExtra("Email", str);
            startActivity(intent);
        }
    }

    /* Shows the progress UI and hides the login form */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress (final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = 200 + getResources().getInteger(android.R.integer.config_longAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    //Check Email Validation
    private boolean isEmailValid (String email){
        return email.contains("u");
    }
    //Check Password Validation
    private boolean isPasswordValid (String password){
        return password.length() > 4;
    }

    private void checkValidation () {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        cancel = false;
        focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    protected void hideKeyboard() {
        try {
            InputMethodManager inputManager =
                    (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
            Objects.requireNonNull(inputManager).hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        catch (Exception e) {
            //do nothing.
        }
    }

    protected void showKeyboard() {
        try {
            ((InputMethodManager) Objects.requireNonNull(Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE)))
                    .showSoftInput(getActivity().getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);
        }
        catch (Exception e) {
            // Do nothing
        }
    }
}