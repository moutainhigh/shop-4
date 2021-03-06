package com.qutu.talk.adapter.taskcenter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.task.MySection;

import java.util.List;

public class DHJiLuAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public DHJiLuAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.head_text, item.header);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MySection item) {
        helper.setText(R.id.name, item.t.getTitle())
                .setText(R.id.price, "-" + item.t.getJinbi() + "钻石");
    }
}
