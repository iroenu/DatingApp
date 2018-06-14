package com.example.wegua.datingapp;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wegua.datingapp.Entity.Setting;

import java.lang.ref.WeakReference;
import java.util.List;

public class SettingFragment extends Fragment{

    private static final String TAG = SettingFragment.class.getSimpleName();

    public EditText name, mile, rangeLow, rangeHigh;
    public Spinner hour, minute, gender;
    public Switch privacy;
    public Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        name = view.findViewById(R.id.name);
        hour = view.findViewById(R.id.hour);
        minute = view.findViewById(R.id.minute);
        mile = view.findViewById(R.id.mile);
        gender = view.findViewById(R.id.gender);
        rangeLow = view.findViewById(R.id.rangeLow);
        rangeHigh = view.findViewById(R.id.rangeHigh);
        privacy = view.findViewById(R.id.privacy);
        save = view.findViewById(R.id.saveButton);

        save.setOnClickListener(v -> {

                try {
                int low = Integer.parseInt(rangeLow.getText().toString());
                int high = Integer.parseInt(rangeHigh.getText().toString());
                int searchMile = Integer.parseInt(mile.getText().toString());
                if(searchMile >= 0) {
                    if (low < high) {
                        updateDatabase(v);
                    } else {
                        Toast.makeText(v.getContext(), "Please enter the correct age range. From low to High.",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "Search mile must greater or equal to 0 mile.",
                            Toast.LENGTH_LONG).show();
                }
                } catch (NumberFormatException e) {
                    Toast.makeText(v.getContext(), "Please enter only numbers for age range and search mile.",
                            Toast.LENGTH_LONG).show();
                }

        });

        new GetSettings(this,"user").execute();

        return view;
    }

    public void updateDatabase(View view) {
        Setting fakeNewSetting = new Setting();

        fakeNewSetting.setDisplayName(this.name.getText().toString());

        fakeNewSetting.setHour(Integer.parseInt(this.hour.getSelectedItem().toString()));
        fakeNewSetting.setMinute(Integer.parseInt(this.minute.getSelectedItem().toString()));
        fakeNewSetting.setSearchMile(this.mile.getText().toString());
        fakeNewSetting.setGender((String) this.gender.getSelectedItem());
        fakeNewSetting.setAgeRangeLow(Integer.parseInt(rangeLow.getText().toString()));
        fakeNewSetting.setAgeRangeHigh(Integer.parseInt(rangeHigh.getText().toString()));
        fakeNewSetting.setPrivacy(this.privacy.isChecked());

        new UpdateSettingTask(this, fakeNewSetting).execute();
    }

    public static int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    private static class GetSettings extends AsyncTask<Void, Void, Setting> {
        private WeakReference<Fragment> weakFragment;
        private String user;



        GetSettings(Fragment fragment, String users){
            weakFragment = new WeakReference<>(fragment);
            this.user = users;
        }

        @Override
        protected Setting doInBackground(Void... voids){
            Fragment fragment = weakFragment.get();
            if(fragment == null){
                return null;
            }

            AppDatabase db = AppDatabaseSingleton.getDatabase(fragment.getContext());

            List<Setting> settings = db.settingDao().loadAllByIds(user);

            if(settings.size() <= 0 || settings.get(0) == null){
                return null;
            }

            return settings.get(0);
        }

        @Override
        protected void onPostExecute(Setting settings) {
            SettingFragment fragment = (SettingFragment) weakFragment.get();
            if(settings == null || fragment == null) {
                return;
            }
            fragment.name.setText(settings.getDisplayName());
            fragment.hour.setSelection(getIndex(fragment.hour,String.valueOf(settings.getHour())));
            fragment.minute.setSelection(getIndex(fragment.minute,String.valueOf(settings.getMinute())));
            fragment.mile.setText("" + settings.getSearchMile());
            fragment.gender.setSelection(getIndex(fragment.gender,settings.getGender()));
            fragment.rangeLow.setText("" + settings.getAgeRangeLow());
            fragment.rangeHigh.setText("" + settings.getAgeRangeHigh());
            fragment.privacy.setSelected(!settings.isPrivacy());
        }
    }

    private static class UpdateSettingTask extends AsyncTask<Void,Void,Setting> {
        private WeakReference<Fragment> weakFragment;
        private Setting settings;

        UpdateSettingTask(Fragment fragment, Setting settings) {
            weakFragment = new WeakReference<>(fragment);
            this.settings = settings;
        }

        @Override
        protected Setting doInBackground(Void... voids) {
            Fragment fragment = weakFragment.get();
            if(fragment == null) {
                return null;
            }

            AppDatabase db = AppDatabaseSingleton.getDatabase(fragment.getContext());

            db.settingDao().insertAll(settings);
            return settings;
        }

        @Override
        protected void onPostExecute(Setting settings) {
            SettingFragment fragment = (SettingFragment) weakFragment.get();
            if(settings == null || fragment==null) {
                Toast.makeText(weakFragment.get().getContext(), "Settings not updated", Toast.LENGTH_SHORT).show();
                return;
            }
                fragment.name.setText(settings.getDisplayName());
                fragment.hour.setSelection(getIndex(fragment.hour,String.valueOf(settings.getHour())));
                fragment.minute.setSelection(getIndex(fragment.minute,String.valueOf(settings.getMinute())));
                fragment.mile.setText(""+ settings.getSearchMile());
                fragment.gender.setSelection(getIndex(fragment.gender,settings.getGender()));
                fragment.rangeLow.setText("" + settings.getAgeRangeLow());
                fragment.rangeHigh.setText("" + settings.getAgeRangeHigh());
                fragment.privacy.setSelected(!settings.isPrivacy());

                Toast.makeText(weakFragment.get().getContext(), "Settings updated", Toast.LENGTH_SHORT).show();

/*
            SettingFragment fragment = (SettingFragment) weakFragment.get();
            if(settings == null || fragment == null) {
                return;
            }


            fragment.user.setText(settings.getUser());
            fragment.hour.setSelection(getIndex(fragment.hour,String.valueOf(settings.getHour())));
            fragment.minute.setSelection(getIndex(fragment.minute,String.valueOf(settings.getMinute())));
            fragment.mile.setText(""+ settings.getSearchMile());
            fragment.gender.setSelection(getIndex(fragment.gender,settings.getGender()));
            fragment.rangeLow.setText("" + settings.getAgeRangeLow());
            fragment.rangeHigh.setText("" + settings.getAgeRangeHigh());
            fragment.privacy.setSelected(!settings.isPrivacy());
*/
        }

    }

}
