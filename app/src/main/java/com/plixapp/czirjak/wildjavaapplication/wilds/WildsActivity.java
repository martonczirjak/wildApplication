package com.plixapp.czirjak.wildjavaapplication.wilds;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.plixapp.czirjak.wildjavaapplication.R;
import com.plixapp.czirjak.wildjavaapplication.databinding.ActivityWildsBinding;
import com.plixapp.czirjak.wildjavaapplication.login.requests.LoginResponse;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsrequests.WildsResponse;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsservice.WildsService;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsservice.WildsWebService;
import com.rainy.networkhelper.future.ExecutionFuture;

public class WildsActivity extends AppCompatActivity {

    private ActivityWildsBinding binding;
    private WildsService wildsService = new WildsWebService();
    private ExecutionFuture<WildsResponse> getWildsRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wilds);
        wildsService.cleanDatabase();
        wildsService.getWildsAndLoadToDatabase("-", "-", this).enqueue(success -> {
            Toast.makeText(this, "Letöltés sikeres", Toast.LENGTH_SHORT).show();
        }, error -> {
            Toast.makeText(this, "Hiba" + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getWildsRequest != null) getWildsRequest.cancel(true);
    }
}
