package com.example.cst8334project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.cst8334project.domain.Visit;
import com.example.cst8334project.userhistoryservice.VisitServiceImpl;

import java.util.List;


public class UserHistoryActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    List<Visit> itemList;
    VisitServiceImpl visitServiceImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);

        visitServiceImp = VisitServiceImpl.INSTANCE;
        itemList = visitServiceImp.findAllVisits();
        VisitAdapter va = new VisitAdapter(R.layout.recyclerview_visits, itemList);
        recyclerView = findViewById(R.id.recyclerview_visits);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(va);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.delete_history:
                visitServiceImp.clearAllVisits();
                Intent intent = new Intent(UserHistoryActivity.this, VolunteerInfoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
 }

