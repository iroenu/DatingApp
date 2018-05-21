package com.example.wegua.datingapp.ViewModel;

import com.example.wegua.datingapp.Model.Match;
import com.example.wegua.datingapp.DataModel.MatchModel;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MatchViewModel {

    private MatchModel matchModel;

    public MatchViewModel(){
        matchModel = new MatchModel();
    }

    public void addMatch(Match match) {
        matchModel.addMatch(match);
    }

    public void getMatch(Consumer<ArrayList<Match>> responseCallback) {
       matchModel.getMatch(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<Match> matchItems = new ArrayList<>();
                    for (DataSnapshot matchSnapshot : dataSnapshot.getChildren()) {
                        Match match = matchSnapshot.getValue(Match.class);
                        assert match != null;
                        match.matchId = matchSnapshot.getKey();
                        matchItems.add(match);
                    }
                    responseCallback.accept(matchItems);
                },
                (databaseError -> System.out.println("Error reading Matches: " + databaseError))
        );
    }

    public void updateMatch(Match match) {
        matchModel.updateMatchById(match);
    }
}
