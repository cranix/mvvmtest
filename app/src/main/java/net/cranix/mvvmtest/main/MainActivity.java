package net.cranix.mvvmtest.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import net.cranix.mvvmtest.R;
import net.cranix.mvvmtest.databinding.ActivityMainBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {


    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainAdapter adapter = new MainAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        disposable = viewModel.recyclerViewData
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    Log.e("cranix", "success:"+list);
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                });

        viewModel.requestList()
                .subscribe(
                        list -> {
                            Log.e("cranix", "success:"+list);
                        },
                        Throwable::printStackTrace);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}

