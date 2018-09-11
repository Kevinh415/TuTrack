package com.fakapow.tutrack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final LayoutInflater mInflater;
    private List<Category> mCategories; // Cached copy of words
    private Context context;

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private Button viewButton;
        private Category position;

        private CategoryViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.name);
            viewButton = itemView.findViewById(R.id.view_button);
            viewButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("PLEASE", "onClick: " + position.getCategoryId());
                    Intent intent = new Intent(context, CategoryMainActivity.class);
                    intent.putExtra("parentId", position.getCategoryId());
                    context.startActivity(intent);
                }
            });
        }
    }

    //interface
    public interface OnClickImageListener{
        void onClick();
    }

    CategoryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.single_card_name, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        if (mCategories != null) {
            holder.position = mCategories.get(position);
            holder.wordItemView.setText(holder.position.getCategoryString());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setCategory(List<Category> category){
        mCategories = category;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCategories != null)
            return mCategories.size();
        else return 0;
    }
}
