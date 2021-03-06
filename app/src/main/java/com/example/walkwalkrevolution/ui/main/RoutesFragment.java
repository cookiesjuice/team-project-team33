package com.example.walkwalkrevolution.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkwalkrevolution.ui.main.routeslist.PersonalRouteSection;
import com.example.walkwalkrevolution.R;
import com.example.walkwalkrevolution.ui.main.routeslist.RouteSection;
import com.example.walkwalkrevolution.cloud.ICloudAdapter;
import com.example.walkwalkrevolution.routemanagement.IRouteManagement;
import com.example.walkwalkrevolution.routemanagement.Route;
import com.example.walkwalkrevolution.routemanagement.TeammateRoute;
import com.example.walkwalkrevolution.ui.main.routeslist.TeamRouteSection;
import com.example.walkwalkrevolution.walktracker.WalkInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class RoutesFragment extends Fragment implements Observer, ICloudAdapter.ITeammateRoutesListener, RouteSection.ClickListener, ICloudAdapter.IDatabaseObserver {
    public static final String TAG = "RoutesFragment";

    TabFragment tabFragment;
    IRouteManagement routesManager;
    WalkInfo walkInfo;
    RecyclerView rvRoutes;
    FloatingActionButton fab;
    SectionedRecyclerViewAdapter sectionedAdapter;
    PersonalRouteSection personalRoutes;
    TeamRouteSection teammateRoutes;
    View view;
    ICloudAdapter db;

    public RoutesFragment(TabFragment tabFragment, IRouteManagement routesManager, WalkInfo walkInfo, ICloudAdapter db) {
        this.tabFragment = tabFragment;
        this.routesManager = routesManager;
        this.walkInfo = walkInfo;
        this.db = db;

        personalRoutes = new PersonalRouteSection(tabFragment.tabActivity, this);
        personalRoutes.setRoutes(((Iterable<Route>) routesManager).iterator());

        teammateRoutes = new TeamRouteSection(tabFragment.tabActivity, this, personalRoutes);

        sectionedAdapter = new SectionedRecyclerViewAdapter();
        sectionedAdapter.addSection(personalRoutes);
        sectionedAdapter.addSection(teammateRoutes);

        ((Observable) routesManager).addObserver(this);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_routes, container, false);


        rvRoutes = view.findViewById(R.id.rvRoutes);
        rvRoutes.setHasFixedSize(true);
        rvRoutes.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvRoutes.addItemDecoration(new DividerItemDecoration(rvRoutes.getContext(), DividerItemDecoration.VERTICAL));

        // These are causing problems with Espresso
        rvRoutes.setAdapter(sectionedAdapter);

        fab = view.findViewById(R.id.floatingActionButton);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabFragment.tabActivity.launchEnterRouteInfo(false);
            }
        });

        db.addObserver(this);
        if(db.userSet()) {
            update();
        }
        return view;
    }

    // should be updating when observable calls

    @Override
    public void update(Observable o, Object arg) {
        personalRoutes.setRoutes((Iterator) arg);
        sectionedAdapter.notifyDataSetChanged();
    }

    @Override
    public void update(ArrayList<TeammateRoute> teamRoutes) {
        teammateRoutes.setRoutes(teamRoutes.iterator());
        sectionedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRootViewClicked(Route route, RouteSection routeSection) {
        tabFragment.tabActivity.launchRouteInfo(route, routeSection == personalRoutes);
    }

    @Override
    public void update() {
        db.getTeamRoutes(this);
    }
}
