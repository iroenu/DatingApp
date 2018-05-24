package com.example.wegua.datingapp;

import android.support.v7.util.DiffUtil;

import com.example.wegua.datingapp.Model.Match;

import java.util.List;

public class MatchDiffCallback extends DiffUtil.Callback{

    private final List<Match> mOldMatchList;
    private final List<Match> mNewMatchList;


    public MatchDiffCallback(List<Match> oldMatchList, List<Match> newMatchList) {
        this.mOldMatchList = oldMatchList;
        this.mNewMatchList = newMatchList;
    }

    @Override
    public int getOldListSize() {
        return 0;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldMatchList.get(oldItemPosition).uid == mNewMatchList.get(
                newItemPosition).uid;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
