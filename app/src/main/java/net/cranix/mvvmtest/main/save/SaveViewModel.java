package net.cranix.mvvmtest.main.save;

import android.databinding.ObservableField;
import android.util.Log;

import net.cranix.mvvmtest.C;
import net.cranix.mvvmtest.model.Board;

import io.reactivex.Single;

public class SaveViewModel {
    public final ObservableField<String> text = new ObservableField<>();

    public Single<Board> requestSave() {
        String title = text.get();
        Log.e("cranix", "save : " + title);

        return C.getApi().save(title)
                .doOnSubscribe(v -> text.set(""));

    }
}
