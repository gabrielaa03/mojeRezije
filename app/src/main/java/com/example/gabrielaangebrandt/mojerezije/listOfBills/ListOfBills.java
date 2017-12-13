package com.example.gabrielaangebrandt.mojerezije.listOfBills;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.gabrielaangebrandt.mojerezije.R;
import com.example.gabrielaangebrandt.mojerezije.listOfBills.adapter.RecyclerAdapter;
import com.example.gabrielaangebrandt.mojerezije.login.Login;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.Bill;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.TitleCreator;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.TitleParent;
import com.example.gabrielaangebrandt.mojerezije.newBill.AddNewBill;
import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListOfBills extends AppCompatActivity {

    @BindView(R.id.expandableRecyclerViewNonPaidBills)
    RecyclerView nonPaidRecyclerView;
    @BindView(R.id.expandableRecyclerViewPaidBills)
    RecyclerView paidRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((RecyclerAdapter) paidRecyclerView.getAdapter()).onSaveInstanceState(outState);
//        ((RecyclerAdapter) nonPaidRecyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_bills);
        ButterKnife.bind(this);
        fab.bringToFront();
        nonPaidRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paidRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerAdapter paidAdapter = new RecyclerAdapter(this, initData(), true);
//        RecyclerAdapter unpaidAdapter = new RecyclerAdapter(this, initUnpaidData(), false);

        paidAdapter.setParentClickableViewAnimationDefaultDuration();
        paidAdapter.setParentAndIconExpandOnClick(true);
//        unpaidAdapter.setParentClickableViewAnimationDefaultDuration();
//        unpaidAdapter.setParentAndIconExpandOnClick(true);

        paidRecyclerView.setAdapter(paidAdapter);
        //nonPaidRecyclerView.setAdapter(unpaidAdapter);
    }

    private List<ParentObject> initData() {
        TitleCreator titleCreator = TitleCreator.get(this);
        assert titleCreator != null;
            List<TitleParent> titles = titleCreator.getAll();
        List<ParentObject> parentObjects = new ArrayList<>();
        for (TitleParent title : titles) {
            List<Object> childList = new ArrayList<>();
            List<Bill> allBills = RealmUtils.getUsersBills(SharedPrefs.getSharedPrefs("username", this));
            for (Bill bill : allBills) {
                if (bill.getStanje().equals("rb_placen")) {
                    childList.add(bill);
                }
            }

            title.setChildObjectList(childList);
            parentObjects.add(title);

        }
        return parentObjects;
    }

    private List<ParentObject> initUnpaidData() {
        TitleCreator titleCreator = TitleCreator.get(this);
        assert titleCreator != null;
        List<TitleParent> titles = titleCreator.getAll();
        List<ParentObject> parentObjects = new ArrayList<>();
        for (TitleParent title : titles) {
            List<Object> childList = new ArrayList<>();
            List<Bill> allBills = RealmUtils.getUsersBills(SharedPrefs.getSharedPrefs("username", this));
            for (Bill bill : allBills) {
                if (bill.getStanje().equals("rb_neplacen")) {
                    childList.add(bill);
                }
            }
            title.setChildObjectList(childList);
            parentObjects.add(title);
        }
        return parentObjects;
    }

    @OnClick(R.id.fab)
    public void addNew() {
        startActivity(new Intent(this, AddNewBill.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_bills, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.graph:
                startActivity(new Intent(this, Graph.class));
                break;
            case R.id.action_upute:
                startActivity(new Intent(this, Upute.class));
                break;
            case R.id.logout:
                SharedPrefs.setSharedPrefs("isLogged", "out", this);
                startActivity(new Intent(this, Login.class));
                Toast.makeText(this, R.string.odjava, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
