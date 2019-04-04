package ptt.vn.icaremobileapp.api;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by kingpes on 8/18/18.
 */

public class CompositeManager {
    private static CompositeDisposable compositeDisposable;

    static void add(Disposable disposable){
        getCompositeDisposable().add(disposable);
    }

    public static void dispose(){
        getCompositeDisposable().dispose();
    }

    private static CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }
}
