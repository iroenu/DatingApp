package com.example.wegua.datingapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.wegua.datingapp.Model.Match;

public class MatchesFragment extends Fragment {

    private static final String TAG = MatchesFragment.class.getSimpleName();
    private static MatchViewModel matchView = new MatchViewModel();
    private ArrayList<Match> matchData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        Bundle bundle = new Bundle();

//      ContentAdapter adapter = new ContentAdapter(bundle);
        ContentAdapter adapter = new ContentAdapter(matchData);

        matchView.getMatch(
                (ArrayList<Match> matches) -> {
                 bundle.putParcelableArrayList("matches", matches);
//                    ContentAdapter adapter = new ContentAdapter(bundle);
                    ArrayList<Match> list = bundle.getParcelableArrayList("matches");
                    adapter.updateEmployeeListItems(list);
                    recyclerView.setAdapter(adapter);

                }
        );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        private ArrayList<Match> mMatch;

       public ContentAdapter(ArrayList<Match> list) {
//       public ContentAdapter(Bundle matchlist) {
//            mMatch = matchlist.getParcelableArrayList("matches");
           mMatch = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        public void updateEmployeeListItems(List<Match> match) {
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

}
