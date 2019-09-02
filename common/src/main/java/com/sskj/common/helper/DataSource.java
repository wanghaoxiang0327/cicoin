package com.sskj.common.helper;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据源
 *
 * @param <T>
 * @author Hey
 */
public abstract class DataSource<T> implements IDataSoucre<T> {


    private final int START_PAGE=1;

    private int page = START_PAGE;

    private int size = 10;

    private boolean hasMore;

    private Disposable disposable;

    public abstract Flowable<List<T>> loadData(int page);

    @Override
    public void refresh(LoadListener<T> loadListener) {
        page = START_PAGE;
        execute(loadListener, page);
    }

    @Override
    public void loadMore(LoadListener<T> loadListener) {
        execute(loadListener, ++page);
    }

    private void execute(LoadListener<T> loadListener, int page) {
        disposable = loadData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (data != null && data.size() == size) {
                        hasMore = true;
                    } else {
                        hasMore = false;
                    }
                    loadListener.onSuccess(data, page == START_PAGE);
                }, e -> {
                    loadListener.onFailure(e,page == START_PAGE);
                });
    }

    public void cancel() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public boolean hasMore() {
        return hasMore;
    }


}