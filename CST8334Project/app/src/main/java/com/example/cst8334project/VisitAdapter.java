package com.example.cst8334project;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cst8334project.domain.Visit;

import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.ViewHolder>{

    private List<Visit> visitList;
    private int listItemLayout;

    public VisitAdapter(int layoutId, List<Visit> visitList){
        listItemLayout = layoutId;
        this.visitList = visitList;
    }

    @Override
    public int getItemCount() {
        return visitList == null ? 0 : visitList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView createDate = holder.createdDate;
        createDate.setText("Created: " + visitList.get(listPosition).getCreatedDate().toString());
        TextView visitTime = holder.serviceType;
        visitTime.setText("Visit Time: : " + visitList.get(listPosition).getServiceType());
        TextView note = holder.note;
        note.setText("Note: " + visitList.get(listPosition).getUserNote());
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView createdDate;
        public TextView serviceType;
        public TextView note;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            createdDate = itemView.findViewById(R.id.visit_create_date);
            serviceType = itemView.findViewById(R.id.service_type);
            note = itemView.findViewById(R.id.visit_note);
        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition());
        }
    }

}
