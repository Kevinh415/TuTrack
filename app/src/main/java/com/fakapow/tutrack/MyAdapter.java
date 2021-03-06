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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.WordViewHolder> {


    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private Context context;

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private Button viewButton;
        private Word position;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.name);
            viewButton = itemView.findViewById(R.id.view_button);
            viewButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("PLEASE", "onClick: " + position.getWord());
                    Intent intent = new Intent(context, CategoryMainActivity.class);
                    intent.putExtra("parentId", position.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    //interface
    public interface OnClickImageListener{
        void onClick();
    }

    MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.single_card_name, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            holder.position = mWords.get(position);
            holder.wordItemView.setText(holder.position.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}
