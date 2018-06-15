package com.example.wegua.datingapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wegua.datingapp.ViewModel.MatchViewModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import com.example.wegua.datingapp.Model.Match;

public class MatchesFragment extends Fragment {

    private static final String TAG = MatchesFragment.class.getSimpleName();
    private static MatchViewModel matchView = new MatchViewModel();
    private ArrayList<Match> matchData = new ArrayList<>();
    private LocationManager locationManager;
    private Location currentLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

    RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
    locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

     Bundle bundle = new Bundle();
     ContentAdapter adapter = new ContentAdapter(matchData);

     if(isLocationEnabled()) {
         if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                 ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
             locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
             currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             matchView.getMatch(
                     (ArrayList<Match> matches) -> {
                         ArrayList<Match> foundMatches = new ArrayList<>();
                         for (int i = 0; i < matches.size(); ++i) {
                             double lat1 = Double.parseDouble(matches.get(i).lat);
                             double lon1 = Double.parseDouble(matches.get(i).longitude);
                             double lat2 = currentLocation.getLatitude();
                             double lon2 = currentLocation.getLongitude();

                             double distance = distance(lat1, lon1, lat2, lon2);
                             if (distance <= 400) {

                                 foundMatches.add(matches.get(i));
                             }
                         }

                         if (foundMatches.size() == 0) {
                             foundMatches = matches;
                             Toast toast = Toast.makeText(getActivity(), "No matches found in the given range.", Toast.LENGTH_SHORT);
                             toast.show();
                         }

                         bundle.putParcelableArrayList("matches", foundMatches);
                         ArrayList<Match> list = bundle.getParcelableArrayList("matches");
                         adapter.updateEmployeeListItems(matches);
                         recyclerView.setAdapter(adapter);

                     });
         }
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         return recyclerView;
     }
        return recyclerView;
    }

    public MatchesFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView picture;
        public TextView title;
        public ImageButton button;
        public Match match;

        public ViewHolder(LayoutInflater inflater, final ViewGroup parent) {
            super(inflater.inflate(R.layout.card, parent, false));
            title = (TextView) itemView.findViewById(R.id.card_title);
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            button = (ImageButton) itemView.findViewById(R.id.like_button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(match.liked ==false) {
                        String message = "You Liked " + title.getText();
                        Toast.makeText(v.getContext(), message,
                                Toast.LENGTH_LONG).show();
                        match.liked = true;
                    } else{
                        match.liked = false;
                    }
                    matchView.updateMatch(match);
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
      public ArrayList<Match> mMatch;

       public ContentAdapter(ArrayList<Match> list) {
           mMatch = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        public void updateEmployeeListItems(ArrayList<Match> match) {
            final MatchDiffCallback diffCallback = new MatchDiffCallback(this.mMatch, match);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            this.mMatch.clear();
            this.mMatch.addAll(match);
            diffResult.dispatchUpdatesTo(this);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.match = mMatch.get(position);
            holder.title.setText(holder.match.name);
            Picasso.get().load(mMatch.get(position).imageUrl).into(holder.picture);
            Boolean like = holder.match.liked;
           if(like == true){
                holder.button.setColorFilter(Color.RED);
            }else{
                holder.button.setColorFilter(Color.BLACK);
            }
        }
        @Override
        public int getItemCount() {
            return mMatch.size();
        }

    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {

            currentLocation = location;
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = dist * 180 / Math.PI;
        dist = dist * 60 * 1.1515;
        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
}
