package com.example.gabrielaangebrandt.mojerezije.listOfBills.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.gabrielaangebrandt.mojerezije.R;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.Bill;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.TitleParent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapterNonPaid extends com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter<RecyclerAdapterNonPaid.TitleParentViewHolder, RecyclerAdapterNonPaid.TitleChildViewHolder> {

    List<Bill> bills = new ArrayList<>();
    LayoutInflater inflater;

    public RecyclerAdapterNonPaid(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }


    @Override
    public TitleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.layout_parent_red, viewGroup, false);
        return new TitleParentViewHolder(view);
    }

    @Override
    public TitleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.layout_child_red, viewGroup, false);
        return new TitleChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, int i, Object o) {
        TitleParent title = (TitleParent) o;
        titleParentViewHolder.naslov.setText(title.getTitle());
    }

    @Override
    public void onBindChildViewHolder(TitleChildViewHolder titleChildViewHolder, int i, Object o) {
        Bill title = (Bill) o;
        titleChildViewHolder.datumDospijeca.setText(title.getDatumDospijeca());
        titleChildViewHolder.iznos.setText(title.getIznos());
        titleChildViewHolder.mjesec.setText(String.valueOf(title.getMjesec()));
        titleChildViewHolder.stanje.setText(title.getStanje());
        titleChildViewHolder.tvrtka.setText(title.getTvrtka());
    }

    public class TitleChildViewHolder extends ChildViewHolder {

        @BindView(R.id.tv_datumDospijecaUpis)
        TextView datumDospijeca;
        @BindView(R.id.tv_mjesecUpis)
        TextView mjesec;
        @BindView(R.id.tv_stanjeUpis)
        TextView stanje;
        @BindView(R.id.iznos)
        TextView iznos;
        @BindView(R.id.tv_tvrtkaUpis)
        TextView tvrtka;

        public TitleChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TitleParentViewHolder extends ParentViewHolder {
        @BindView(R.id.ib_expand_arrow)
        ImageButton arrow;
        @BindView(R.id.tv_naslov)
        TextView naslov;

        public TitleParentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
