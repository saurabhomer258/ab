package com.example.saurabhomer.bornbhukkad;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.saurabhomer.bornbhukkad.Interface.ItemClickListerner;
import com.example.saurabhomer.bornbhukkad.Model.Food;
import com.example.saurabhomer.bornbhukkad.ViewHolder.FoodViewHolder;
import com.example.saurabhomer.bornbhukkad.common.Common;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String categoryId="";

    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;


    //Search Functionality

    FirebaseRecyclerAdapter<Food,FoodViewHolder> searchAdapter;
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Toast.makeText(FoodList.this, "ok", Toast.LENGTH_SHORT).show();
        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList =database.getReference("Food");


        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent here
        if(getIntent()!=null)
            categoryId =getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId !=null){
            if(Common.isConnectedToInterner(getBaseContext()))
                loadListFood(categoryId);
            else
            {
                Toast.makeText(FoodList.this, "Please Check Your Connection!!!!", Toast.LENGTH_SHORT).show();
                return ;
            }
        }

        //Search
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
        materialSearchBar.setHint("Enter your food");
        //materialSearchBar.setSpeechMode(false);No need , becuz we already use in xml
        loadSuggest();//Write function to load Sugeest from Firebase
        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //When user type their text , we will change suggestlist
                List<String> suggest = new ArrayList<String>();
                for(String search:suggestList)//Loop in SuggestList
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()));
                        suggest.add(search);
                }

                materialSearchBar.setLastSuggestions(suggest);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {

            @Override
            public void onSearchStateChanged(boolean enabled) {
                //When search Bar is close
                //Restore original  adapter

                if(!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                //When search finish
                //Show Result of search adapter

                startSearch(text);
            }
            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void startSearch(CharSequence text) {
        searchAdapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(
                Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("Name").equalTo(text.toString())//Compare Name

        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);

                final Food local = model;

                viewHolder.setItemClickListerner(new ItemClickListerner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start New Activity

                        Intent foodDetail =new Intent(FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("foodId",adapter.getRef(position).getKey());  //send Food id to new activity
                        startActivity(foodDetail);

                    }
                });
            }
        };

        recyclerView.setAdapter(searchAdapter);//Set adapter For Recycler View is Search result




    }


    private void loadSuggest() {
        foodList.orderByChild("MenuId").equalTo(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Food item = postSnapshot.getValue(Food.class);
                    suggestList.add(item.getName());//Add name of food to suggest list
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuId")) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);

                final Food local = model;

                viewHolder.setItemClickListerner(new ItemClickListerner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start New Activity

                        Intent foodDetail =new Intent(FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());  //send Food id to new activity
                        startActivity(foodDetail);

                    }
                });
            }
        };
        
        recyclerView.setAdapter(adapter);
    }

}
