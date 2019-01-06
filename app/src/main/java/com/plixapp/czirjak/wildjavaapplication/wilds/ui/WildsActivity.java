package com.plixapp.czirjak.wildjavaapplication.wilds.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.plixapp.czirjak.wildjavaapplication.R;
import com.plixapp.czirjak.wildjavaapplication.common.Constants;
import com.plixapp.czirjak.wildjavaapplication.databinding.ActivityWildsBinding;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsrequests.WildsResponse;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsservice.WildsService;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsservice.WildsWebService;
import com.rainy.networkhelper.future.ExecutionFuture;

/**
 * A wilds screen that offers to show the wilds.
 */
public class WildsActivity extends AppCompatActivity implements OnWildItemClickListener {

    private ActivityWildsBinding binding;
    private WildsService wildsService = new WildsWebService();
    private ExecutionFuture<WildsResponse> getWildsRequest;
    private String hunterId;
    boolean successLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wilds);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Toast.makeText(this, getString(R.string.unknown_issue), Toast.LENGTH_LONG).show();
            } else {
                loadFromExtras(extras);
                load();

            }
        } else {
            loadFromSavedinstanceState(savedInstanceState);
            load();
        }

    }

    private void load() {
        if (successLogin) {
            loginUser(hunterId);
        } else {
            loadWildsFromDatabase();
        }
    }

    private void loadFromSavedinstanceState(Bundle savedInstanceState) {
        hunterId = (String) savedInstanceState.getSerializable(Constants.HUNTERID);
        successLogin = savedInstanceState.getBoolean(Constants.LOGINSUCCESS);
    }

    private void loadFromExtras(Bundle extras) {
        hunterId = extras.getString(Constants.HUNTERID);
        successLogin = extras.getBoolean(Constants.LOGINSUCCESS);
    }

    private void loadWildsFromDatabase() {
        binding.recyclerView.setAdapter(new WildsAdapter(wildsService.getWildsFromDataBase(), this));
    }

    private void loginUser(String hunterId) {
        binding.loginProgress.setVisibility(View.VISIBLE);
        wildsService.cleanDatabase();
        wildsService.getWildsAndLoadToDatabase(hunterId, Constants.DEFAULT_PASSWORD, this).enqueue(success -> {
            binding.recyclerView.setAdapter(new WildsAdapter(success.getWildsList().wilds, this));
            binding.loginProgress.setVisibility(View.GONE);
        }, error -> {
            Toast.makeText(this, "Hiba:" + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            binding.loginProgress.setVisibility(View.GONE);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wildsService.closeDatabase();
        if (getWildsRequest != null) getWildsRequest.cancel(true);
    }

    @Override
    public void click(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}
