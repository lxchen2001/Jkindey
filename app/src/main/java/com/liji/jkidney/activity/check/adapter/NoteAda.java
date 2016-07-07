package com.liji.jkidney.activity.check.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.check.MNote;

import java.util.List;

/**
 * Created by liji on 16-7-7.
 */
public class NoteAda extends BaseQuickAdapter<MNote> {

    public NoteAda(List<MNote> list) {
        super(R.layout.item_note, list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MNote note) {
        baseViewHolder.setText(R.id.item_txt_time, "" + note.getTime() + " — " + note.getTitle());
        baseViewHolder.setText(R.id.item_content_1, "" + note.getContent());

    }
}
