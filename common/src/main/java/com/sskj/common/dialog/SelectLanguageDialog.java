package com.sskj.common.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.common.R;
import com.sskj.common.R2;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.data.LanguageType;
import com.sskj.common.utils.ClickUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectLanguageDialog extends BottomSheetDialog {

    @BindView(R2.id.coin_list)
    RecyclerView coinList;


    private BaseAdapter<LanguageType> coinAdapter;


    private OnSelectListener onSelectListener;


    public SelectLanguageDialog(@NonNull Context context, OnSelectListener onSelectListener) {
        super(context);
        setContentView(R.layout.common_dialog_coin);
        ButterKnife.bind(this);
        this.onSelectListener = onSelectListener;
        initView();
    }


    private void initView() {
        coinList.setLayoutManager(new LinearLayoutManager(getContext()));
        coinAdapter = new BaseAdapter<LanguageType>(R.layout.common_item_coind, null, coinList) {
            @Override
            public void bind(ViewHolder holder, LanguageType item) {
                holder.setText(R.id.name, item.getTitle());
                holder.itemView.setOnClickListener(view -> {
                    if (onSelectListener != null) {
                        onSelectListener.onSelect(SelectLanguageDialog.this, item);
                    }
                });
            }
        };
        ClickUtil.click(findViewById(R.id.tvCancel), (v) -> {
            dismiss();
        });
    }


    public SelectLanguageDialog setData(List<LanguageType> data) {
        coinAdapter.setNewData(data);
        return this;
    }

    public interface OnSelectListener {
        void onSelect(SelectLanguageDialog dialog, LanguageType coin);
    }

}
