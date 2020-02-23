package com.kiran2kirve.gojekassignment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kiran2kirve.gojekassignment.R;
import com.kiran2kirve.gojekassignment.models.AuthorList;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AuthorList> mResultList = new ArrayList<AuthorList>();
    private Context mContext;


    public RecyclerAdapter(Context context,List<AuthorList> resultList) {
        mResultList=resultList;
        mContext = context;
    }


    public RecyclerAdapter(Context context) {

        mContext = context;
    }

    public void renderList(List<AuthorList> resultList) {
        mResultList.clear();
        mResultList.addAll(resultList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_card, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolder) viewHolder).mtextAuthor.setText(mResultList.get(i).getAuthor());
        ((ViewHolder) viewHolder).mtextName.setText(mResultList.get(i).getName()+"");
        ((ViewHolder) viewHolder).mtextDescription.setText(mResultList.get(i).getDescription()+"");
        ((ViewHolder) viewHolder).mtextStar.setText(mResultList.get(i).getStars()+"");
        ((ViewHolder) viewHolder).mtextForks.setText(mResultList.get(i).getForks()+"");

        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Object strImage=mResultList.get(i).getAvatar();
        Log.e(""+strImage,"");
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mResultList.get(i).getAvatar())
                .into(((ViewHolder) viewHolder).mImagePicture);
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


    private class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImagePicture;
        private TextView mtextAuthor, mtextName,mtextDescription,mtextStar,mtextForks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImagePicture =  itemView.findViewById(R.id.imagePicture);
            mtextAuthor = (TextView) itemView.findViewById(R.id.textAuthor);
            mtextName = (TextView) itemView.findViewById(R.id.textName);
            mtextDescription = (TextView) itemView.findViewById(R.id.textDescription);
            mtextStar = (TextView) itemView.findViewById(R.id.textStar);
            mtextForks = (TextView) itemView.findViewById(R.id.textForks);

        }
    }
}

