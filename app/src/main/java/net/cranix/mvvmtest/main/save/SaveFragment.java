package net.cranix.mvvmtest.main.save;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cranix.mvvmtest.R;
import net.cranix.mvvmtest.databinding.ActivityMainSaveBinding;
import net.cranix.mvvmtest.main.MainViewModel;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SaveFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityMainSaveBinding layout = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main_save, container, false);
        SaveViewModel model = new SaveViewModel();
        layout.setModel(model);

        layout.buttonSave.setOnClickListener(v -> {
            MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
            model.requestSave()
                    .compose(this::attachLoading)
                    .flatMap(a -> viewModel.requestList())
                    .subscribe();
        });

        return layout.getRoot();
    }

    private ProgressDialog pd;


    private void showLoading() {
        if (pd == null) {
            pd = new ProgressDialog(getActivity());
            pd.setMessage("loading...");
        }
        pd.show();
    }

    private void hideLoading() {
        pd.dismiss();
    }


    public <T> Single<T> attachLoading(Single<T> single) {
        return single
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(v -> {
                    showLoading();
                })
                .doFinally(() -> {
                    hideLoading();
                });
    }
}
