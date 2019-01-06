package com.plixapp.czirjak.wildjavaapplication.wilds.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wilds);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
               // newString = null;
            } else {
                hunterId = extras.getString(Constants.HUNTERID);
            }
        } else {
            hunterId = (String) savedInstanceState.getSerializable(Constants.HUNTERID);
        }
        wildsService.cleanDatabase();
        wildsService.getWildsAndLoadToDatabase(hunterId, Constants.DEFAULT_PASSWORD, this).enqueue(success -> {
            binding.recyclerView.setAdapter(new WildsAdapter(success.getWildsList().wilds,this));
        }, error -> {
            Toast.makeText(this, "Hiba:" + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getWildsRequest != null) getWildsRequest.cancel(true);
    }

    @Override
    public void click(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}
