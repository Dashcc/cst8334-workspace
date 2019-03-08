package com.example.cst8334project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
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

        visitServiceImp = new VisitServiceImpl(this);
        itemList = visitServiceImp.findAllVisits();
        VisitAdapter va = new VisitAdapter(R.layout.recyclerview_visits, itemList);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_visits);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(va);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
