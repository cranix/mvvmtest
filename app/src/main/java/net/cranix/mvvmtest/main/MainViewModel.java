package net.cranix.mvvmtest.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import net.cranix.mvvmtest.C;
import net.cranix.mvvmtest.model.Board;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.processors.BehaviorProcessor;

public class MainViewModel extends ViewModel {
    public final BehaviorProcessor<List<Board>> recyclerViewData = BehaviorProcessor.create();

    public Single<List<Board>> requestList() {
        return C.getApi().getBoards()
                .doOnSuccess(list -> {
                    Log.e("cranix", "requestListSuccess.list:"+list);
                    recyclerViewData.onNext(list);
                });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e("cranix", "onCreate");

    }
}
