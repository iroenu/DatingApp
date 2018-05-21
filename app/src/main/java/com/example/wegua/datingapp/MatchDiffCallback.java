package com.example.wegua.datingapp;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.wegua.datingapp.Model.Match;

import java.util.List;

public class MatchDiffCallback extends DiffUtil.Callback {

    private final List<Match> mOldMatchList;
    private final List<Match> mNewMatchList;

    public MatchDiffCallback(List<Match> oldEmployeeList, List<Match> newEmployeeList) {
        this.mOldMatchList = oldEmployeeList;
        this.mNewMatchList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return mOldMatchList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewMatchList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldMatchList.get(oldItemPosition).getId() == mNewMatchList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Match oldMatch = mOldMatchList.get(oldItemPosition);
        final Match newMatch = mNewMatchList.get(newItemPosition);

        return oldMatch.getTitle().equals(newMatch.getTitle());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}


