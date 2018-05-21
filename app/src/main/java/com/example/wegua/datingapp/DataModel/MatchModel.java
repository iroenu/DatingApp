package com.example.wegua.datingapp.DataModel;

import com.example.wegua.datingapp.Model.Match;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class MatchModel {

    private DatabaseReference mDatabase;
    private HashMap<DatabaseReference, ValueEventListener> listeners;

    public MatchModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listeners = new HashMap<>();
    }

    public void addMatch(Match match) {
        DatabaseReference matchesRef = mDatabase.child("matches");
        matchesRef.push().setValue(match);
    }

    public void getMatch(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
        // This is where we can construct our path
        DatabaseReference matchRef = mDatabase.child("matches");
        ValueEventListener matchListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChangedCallback.accept(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataErrorCallback.accept(databaseError);
            }
        };
       matchRef.addValueEventListener(matchListener);
        listeners.put(matchRef, matchListener);
    }

    public void updateMatchById(Match match) {
        DatabaseReference matchesRef = mDatabase.child("matches");
        matchesRef.child(match.matchId).setValue(match);
    }


    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(Query::removeEventListener);
    }
}
