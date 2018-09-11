package com.fakapow.tutrack;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class CategoryMainActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CategoryViewModel mCategoryViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private String parentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.category_recycler);
        
        //get parentId
        parentId = getIntent().getStringExtra("parentId");
        Log.d("PARENT", "onCreate: " + parentId);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);

        // specify an adapter (see also next example)
        //use Room for the names
        final CategoryAdapter mAdapter = new CategoryAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        CategoryViewModel mCategoryViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication(), parentId)).get(CategoryViewModel.class);
        mCategoryViewModel.findChildCategories(parentId).observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> categories) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setCategory(categories);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryMainActivity.this, NewCategoryActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Category category= new Category(data.getStringExtra(NewCategoryActivity.EXTRA_REPLY), parentId);
            CategoryViewModel mCategoryViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication(), parentId)).get(CategoryViewModel.class);
            mCategoryViewModel.insert(category);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Empty! Not saved.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public class MyViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private Application mApplication;
        private String mParam;


        public MyViewModelFactory(Application application, String param) {
            mApplication = application;
            mParam = param;
        }


        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new CategoryViewModel(mApplication, mParam);
        }
    }
}
