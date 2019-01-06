package com.plixapp.czirjak.wildjavaapplication.login.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.plixapp.czirjak.wildjavaapplication.R;
import com.plixapp.czirjak.wildjavaapplication.common.Constants;
import com.plixapp.czirjak.wildjavaapplication.databinding.ActivityLoginBinding;
import com.plixapp.czirjak.wildjavaapplication.login.loginservice.LoginService;
import com.plixapp.czirjak.wildjavaapplication.login.loginservice.LoginWebService;
import com.plixapp.czirjak.wildjavaapplication.login.requests.LoginResponse;
import com.plixapp.czirjak.wildjavaapplication.wilds.ui.WildsActivity;
import com.rainy.networkhelper.future.ExecutionFuture;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private ExecutionFuture<LoginResponse> loginRequest;
    private LoginService loginService = new LoginWebService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.emailSignInButton.setOnClickListener(view -> {
            binding.loginProgress.setVisibility(View.VISIBLE);
            binding.emailSignInButton.setEnabled(false);
            loginRequest = loginService.login(binding.email.getText().toString(), binding.password.getText().toString(), this);
            loginRequest.enqueue(succesResponse ->
            {
                binding.loginProgress.setVisibility(View.GONE);
                binding.emailSignInButton.setEnabled(true);
                Intent intent = new Intent(this, WildsActivity.class);
                intent.putExtra(Constants.HUNTERID, succesResponse.getData().getHunter());
                intent.putExtra(Constants.LOGINSUCCESS, true);
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top).toBundle());
            }, error ->
            {

                binding.loginProgress.setVisibility(View.GONE);
                binding.emailSignInButton.setEnabled(true);
                if (error.getLocalizedMessage().contains(Constants.NO_CONNECTIONS_ERROR)) {
                    if (binding.email.getText().toString().equals(Constants.DEFAULT_EMAIL) && binding.password.getText().toString().equals(Constants.DEFAULT_PASSWORD)) {
                        Intent intent = new Intent(this, WildsActivity.class);
                        intent.putExtra(Constants.LOGINSUCCESS, false);
                        startActivity(intent, ActivityOptions.makeCustomAnimation(this, R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top).toBundle());
                    } else {
                        Toast.makeText(this, R.string.error_invalid_credentials, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginRequest != null) loginRequest.cancel(true);
    }
}
