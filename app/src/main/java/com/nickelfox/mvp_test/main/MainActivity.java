package com.nickelfox.mvp_test.main;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.nickelfox.mvp_test.R;
import com.nickelfox.mvp_test.data.source.local.NewsDatabase;
import com.nickelfox.mvp_test.data.source.local.NewsListLocalRepository;
import com.nickelfox.mvp_test.data.source.remote.model.SamachaarModel;
import com.nickelfox.mvp_test.data.source.NewsListRepository;
import com.nickelfox.mvp_test.data.source.remote.NewsListRemoteRepository;
import com.nickelfox.mvp_test.util.ActivityUtils;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListFragmentInteractionListener {


    private MainContract.Presenter mPresenter;

    private MainFragment mainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if (mainFragment == null) {
            // Create the fragment
            mainFragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mainFragment, R.id.container);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mPresenter = new MainPresenter(NewsListRepository.getInstance(NewsListRemoteRepository.getInstance()
                ,NewsListLocalRepository.getInstance(NewsDatabase.getInstance(getApplicationContext()).newsDao())), mainFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(mainFragment);
        mPresenter.getTopHeadings();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onDestroy();
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


    @Override
    public void onListFragmentInteraction(SamachaarModel item) {

    }
}
