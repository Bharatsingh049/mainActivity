package com.nickelfox.mvp_test.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nickelfox.mvp_test.R;
import com.nickelfox.mvp_test.data.source.remote.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MainFragment extends Fragment implements MainContract.View {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private MainContract.Presenter mPresenter;

    private List<Article> businessList, entertainmentList, healthList, scienceList, sportsList, technologyList;

    private ProgressDialog mProgressDialog;

    /*@SuppressLint("StaticFieldLeak")
    private static MainFragmentRecyclerViewAdapter sMainFragmentRecyclerViewAdapter;*/

    @SuppressLint("StaticFieldLeak")
    private static HorizontalRecyclerViewAdapter businessAdapter, entertainmentAdapter, healthAdapter, scienceAdapter, sportsAdapter, technologyAdapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.getTopHeadings();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MainFragment newInstance() {

        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }*/
        //Initialising Lists
        businessList = new ArrayList<>();
        entertainmentList = new ArrayList<>();
        healthList = new ArrayList<>();
        scienceList = new ArrayList<>();
        sportsList = new ArrayList<>();
        technologyList = new ArrayList<>();

        //Initialising Adapters
        businessAdapter = new HorizontalRecyclerViewAdapter(businessList);
        entertainmentAdapter = new HorizontalRecyclerViewAdapter(entertainmentList);
        healthAdapter = new HorizontalRecyclerViewAdapter(healthList);
        scienceAdapter = new HorizontalRecyclerViewAdapter(scienceList);
        sportsAdapter = new HorizontalRecyclerViewAdapter(sportsList);
        technologyAdapter = new HorizontalRecyclerViewAdapter(technologyList);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        // Set the adapter
        view = initRecyclerView(view);

        //Context context = view.getContext();


        //recyclerView.setAdapter(sMainFragmentRecyclerViewAdapter);
        FloatingActionButton fab =
                (FloatingActionButton) Objects.requireNonNull(getActivity()).findViewById(R.id.fab);

        //fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPresenter.getTopHeadings();
            }
        });

        return view;
    }


    private View initRecyclerView(View view) {
        RecyclerView businessRecyclerView = view.findViewById(R.id.business_recyclerView);
        RecyclerView entertainmentRecyclerView = view.findViewById(R.id.entertainment_recyclerView);
        RecyclerView healthRecyclerView = view.findViewById(R.id.health_recyclerView);
        RecyclerView scienceRecyclerView = view.findViewById(R.id.science_recyclerView);
        RecyclerView sportsRecyclerView = view.findViewById(R.id.sports_recyclerView);
        RecyclerView technologyRecyclerView = view.findViewById(R.id.technology_recyclerView);

        businessRecyclerView.setAdapter(businessAdapter);
        entertainmentRecyclerView.setAdapter(entertainmentAdapter);
        healthRecyclerView.setAdapter(healthAdapter);
        scienceRecyclerView.setAdapter(scienceAdapter);
        sportsRecyclerView.setAdapter(sportsAdapter);
        technologyRecyclerView.setAdapter(technologyAdapter);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*@Override
    public void showList(List<Article> list) {
        int temp = list.size();
        //this.mList=list;
        //sMainFragmentRecyclerViewAdapter.setList(mList);
        Log.d("showList: ",temp+"");
    }*/

    @Override
    public void showBusinessList(@NonNull List<Article> businessList) {
        int temp = businessList.size();
        this.businessList = businessList;
        businessAdapter.setList(businessList);
        Log.d("showList: ", temp + "");
    }

    @Override
    public void showEntertainmentList(@NonNull List<Article> entertainmentList) {
        int temp = entertainmentList.size();
        this.entertainmentList = entertainmentList;
        entertainmentAdapter.setList(this.entertainmentList);
        Log.d("showList: ", temp + "");
    }

    @Override
    public void showHealthList(@NonNull List<Article> healthList) {
        int temp = healthList.size();
        this.healthList = healthList;
        healthAdapter.setList(this.healthList);
        Log.d("showList: ", temp + "");
    }

    @Override
    public void showScienceList(@NonNull List<Article> scienceList) {
        int temp = scienceList.size();
        this.scienceList = scienceList;
        scienceAdapter.setList(this.scienceList);
        Log.d("showList: ", temp + "");
    }

    @Override
    public void showSportsList(@NonNull List<Article> sportsList) {
        int temp = sportsList.size();
        this.sportsList = sportsList;
        sportsAdapter.setList(this.sportsList);
        Log.d("showList: ", temp + "");
    }

    @Override
    public void showTechnologyList(@NonNull List<Article> technologyList) {
        int temp = technologyList.size();
        this.technologyList = technologyList;
        technologyAdapter.setList(this.technologyList);
        Log.d("showList: ", temp + "");
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        Toast.makeText(getContext(), "Error : " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Article item);
    }
}
