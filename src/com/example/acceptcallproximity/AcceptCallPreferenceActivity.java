package com.example.acceptcallproximity;

import com.example.acceptcallproximity.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class AcceptCallPreferenceActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

        private AcceptCallNotifier mNotifier;
       
        @SuppressWarnings("deprecation")
		@Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                mNotifier = new AcceptCallNotifier(this);
                mNotifier.updateNotification();
                SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
                sharedPreferences.registerOnSharedPreferenceChangeListener(this);
                addPreferencesFromResource(R.xml.preferences);

                if (tvTrigger == null || mPopImageView == null || mPopImageView.getVisibility() != VISIBLE || mPopImageView.getTag() != CONTENT_TRIGGER_TAG) {
                        if (tvTrigger != null)
                                tvTrigger.setVisibility(GONE);
                        return;
                }

                if (pillsManager != null) {
                        if (mPillViewContainer != null) {
                                mPillViewContainer.setVisibility(false, "killPillsManager");
                                mPillViewContainer.setVisibility(GONE);
                                mPillViewContainer.removeAllViews();
                                mPillViewContainer = null;
                        }
                        pillsManager.removePillsView("killPillsManager");
                        pillsManager = null;
                }

                if (pillsManager != null) {
                        pillsManager.removePillsView("onConfigurationChanged");
                        pillsManager = null;
                }
        }

        @Override
        protected void onDestroy() {
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
                super.onDestroy();
        }
       
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("enabled")) {
                        mNotifier.updateNotification();
                }
        }
}

