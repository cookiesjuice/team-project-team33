package com.example.walkwalkrevolution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.walkwalkrevolution.routemanagement.Route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RouteItemAdapter extends
        RecyclerView.Adapter<RouteItemAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        public TextView routeName;
        public TextView startLoc;
        public TextView dist;
        public TextView steps;
        public TextView time;
        public Button favoriteBtn;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            /*
            routeName = itemView.findViewById(R.layout.routeName);
            startLoc = itemView.findViewById(R.layout.startLoc);
             */

        }
    }

    private List<Route> routes;

    public RouteItemAdapter(Iterator it) {
        ArrayList<Route> routes = new ArrayList<>();
        while(it.hasNext()) { routes.add((Route)it.next());}
        this.routes = routes;
    }

    @Override
    public RouteItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        /*View routeView = inflater.inflate(R.layout., parent, false);

        ViewHolder viewHolder = new ViewHolder(routeView);
        return viewHolder;
        */

    }

    @Override
    public void onBindViewHolder(RouteItemAdapter.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return routes.size();
    }
}