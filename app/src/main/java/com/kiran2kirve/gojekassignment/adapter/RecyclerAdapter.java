package com.kiran2kirve.gojekassignment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kiran2kirve.gojekassignment.ExpandableList.ExpandListener;
import com.kiran2kirve.gojekassignment.ExpandableList.ExpandableLinearLayout;
import com.kiran2kirve.gojekassignment.R;
import com.kiran2kirve.gojekassignment.models.AuthorList;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AuthorList> mResultList = new ArrayList<AuthorList>();
    private Context mContext;
    private RecyclerView mrecyclerView;

   // private List<ExpandModel> data;

    private int lastExpandedCardPosition;
   // private int i=0;


    public RecyclerAdapter(Context context,List<AuthorList> resultList,RecyclerView recyclerView) {
        mResultList=resultList;
        mContext = context;
        mrecyclerView=recyclerView;
    }


    public RecyclerAdapter(Context context,RecyclerView recyclerView) {

        mrecyclerView=recyclerView;
        mContext = context;
    }

    public void renderList(List<AuthorList> resultList) {
        mResultList.clear();
        mResultList.addAll(resultList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
/*        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_card, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;*/


        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.layout_list_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        ((ViewHolder) viewHolder).mtextAuthor.setText(mResultList.get(position).getAuthor());
        ((ViewHolder) viewHolder).mtextName.setText(mResultList.get(position).getName()+"");
        ((ViewHolder) viewHolder).mtextDescription.setText(mResultList.get(position).getDescription()+"");
        ((ViewHolder) viewHolder).mtextStar.setText(mResultList.get(position).getStars()+"");
        ((ViewHolder) viewHolder).mtextForks.setText(mResultList.get(position).getForks()+"");

        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Object strImage=mResultList.get(position).getAvatar();
        Log.e(""+strImage,"");
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mResultList.get(position).getAvatar())
                .into(((ViewHolder) viewHolder).mImagePicture);

        if(mResultList.get(position).isExpanded()){
            ((ViewHolder) viewHolder).mlinearLayoutExpandableView.setVisibility(View.VISIBLE);
            ((ViewHolder) viewHolder).mlinearLayoutExpandableView.setExpanded(true);
        }
        else{
            ((ViewHolder) viewHolder).mlinearLayoutExpandableView.setVisibility(View.GONE);
            ((ViewHolder) viewHolder).mlinearLayoutExpandableView.setExpanded(false);
        }

    }

    @NonNull
    @Override
    public int getItemCount() {
       if (mResultList!= null) {
            return mResultList.size();

        }else
        {
            return 0;
        }
    }


/*    public void setData(List<ExpandModel> data) {
        this.mResultList = data;
    }

    public void addItem(int i) {
        data.add(i,new ExpandModel());
        if(i<=lastExpandedCardPosition)
            lastExpandedCardPosition++;
        notifyDataSetChanged();
    }

    public void deleteItem(int i) {
        data.remove(i);
        notifyDataSetChanged();
    }*/


    private class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImagePicture;
        private TextView mtextAuthor, mtextName,mtextDescription,mtextStar,mtextForks;
        private LinearLayout mlinearLayoutClicableView;
        private ExpandableLinearLayout mlinearLayoutExpandableView;



            ExpandListener expandListener = new ExpandListener() {
                @Override
                public void onExpandComplete() {
                    if (lastExpandedCardPosition != getAdapterPosition() && mrecyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition) != null) {
                        ((ExpandableLinearLayout) mrecyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition).itemView.findViewById(R.id.linearLayoutExpandableView)).setExpanded(false);
                        mResultList.get(lastExpandedCardPosition).setExpanded(false);
                        ((ExpandableLinearLayout) mrecyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition).itemView.findViewById(R.id.linearLayoutExpandableView)).toggle();
                    } else if (lastExpandedCardPosition != getAdapterPosition() && mrecyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition) == null) {
                        mResultList.get(lastExpandedCardPosition).setExpanded(false);
                    }
                    lastExpandedCardPosition = getAdapterPosition();
                }

                @Override
                public void onCollapseComplete() {

                }
            };



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImagePicture = itemView.findViewById(R.id.imagePicture);
            mtextAuthor = (TextView) itemView.findViewById(R.id.textAuthor);
            mtextName = (TextView) itemView.findViewById(R.id.textName);
            mtextDescription = (TextView) itemView.findViewById(R.id.textDescription);
            mtextStar = (TextView) itemView.findViewById(R.id.textStar);
            mtextForks = (TextView) itemView.findViewById(R.id.textForks);

            //Expandable view details
            mlinearLayoutClicableView = (LinearLayout) itemView.findViewById(R.id.linearLayoutClicableView);
            mlinearLayoutExpandableView = (ExpandableLinearLayout) itemView.findViewById(R.id.linearLayoutExpandableView);

            mlinearLayoutExpandableView.setExpandListener(expandListener);
            initializeClicks();

        }


        private void initializeClicks() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mlinearLayoutExpandableView.isExpanded()) {
                        mlinearLayoutExpandableView.setExpanded(false);
                        mlinearLayoutExpandableView.toggle();
                        mResultList.get(getAdapterPosition()).setExpanded(false);
                    } else {
                        mlinearLayoutExpandableView.setExpanded(true);
                        mResultList.get(getAdapterPosition()).setExpanded(true);
                        mlinearLayoutExpandableView.toggle();
                    }
                }
            });
        }
    }
}

