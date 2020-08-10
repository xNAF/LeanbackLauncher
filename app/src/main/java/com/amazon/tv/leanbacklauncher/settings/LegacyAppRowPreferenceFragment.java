package com.amazon.tv.leanbacklauncher.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.leanback.app.GuidedStepSupportFragment;
import androidx.leanback.widget.GuidanceStylist;
import androidx.leanback.widget.GuidedAction;
import androidx.core.content.res.ResourcesCompat;
import android.text.InputType;
import android.widget.Toast;

import com.amazon.tv.firetv.leanbacklauncher.apps.AppCategory;
import com.amazon.tv.firetv.leanbacklauncher.apps.RowPreferences;
import com.amazon.tv.firetv.leanbacklauncher.util.FireTVUtils;

import com.amazon.tv.leanbacklauncher.MainActivity;
import com.amazon.tv.leanbacklauncher.R;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Set;


/**
 * Created by rockon999 on 3/25/18.
 */

public class LegacyAppRowPreferenceFragment extends GuidedStepSupportFragment {

    private static final int ACTION_ID_APPS = 50;
    private static final int ACTION_ID_APPS_ROW = 51;
    private static final int ACTION_ID_APPS_MAX = 52;
    private static final int ACTION_ID_RECOMENDATIONS = 100;
    private static final int ACTION_ID_INPUTS = 200;
    private static int startup = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState) {
        return new GuidanceStylist.Guidance(
        	getString(R.string.edit_row_title), // title
        	getString(R.string.select_app_customize_rows_title), // description
        	getString(R.string.settings_dialog_title), // breadcrumb (home_screen_order_action_title)
        	ResourcesCompat.getDrawable(getResources(), R.drawable.ic_settings_home, null));
    }

    public void onResume() {
        super.onResume();
        this.updateActions();
        startup++;
    }

    public void onPause() {
        super.onPause();
        startup = 0;
    }

    private int musicIndex, gameIndex, videoIndex, favIndex;

    public void onGuidedActionClicked(GuidedAction action) {

        long id = action.getId();
        long modId = (id - 1) / 2;
        int subId = (int) ((id - 1) % 2);
        int val = 1;
        boolean enabled = false;

        Activity activity = getActivity();
        Set<AppCategory> categories = RowPreferences.getEnabledCategories(activity);

        // Log.w("+++ action", action.toString());
        // Log.w("+++ action.id", ""+id);
        // Log.w("+++ modId", " "+modId);
        // Log.w("+++ subId", " "+subId);

        if (modId == favIndex) {
            switch (subId) {
                case 0:
                    enabled = RowPreferences.areFavoritesEnabled(activity);
                    // Log.w("+++ favorites.enabled", ""+enabled);
                    RowPreferences.setFavoritesEnabled(activity, !enabled);
                    break;
                case 1:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set fav.max ", ""+val);
                    RowPreferences.setFavoriteRowMax(activity, val);
                    break;
                case 2:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set fav.min ", ""+val);
                    RowPreferences.setFavoriteRowMin(activity, val);
            }
        } else if (modId == musicIndex) {
            switch (subId) {
                case 0:
                    enabled = categories.contains(AppCategory.MUSIC);
                    // Log.w("+++ music.enabled", ""+enabled);
                    RowPreferences.setMusicEnabled(activity, !enabled);
                    break;
                case 1:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set mucic.max ", ""+val);
                    RowPreferences.setRowMax(AppCategory.MUSIC, activity, val);
                    break;
                case 2:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set music.min ", ""+val);
                    RowPreferences.setRowMin(AppCategory.MUSIC, activity, val);
            }
        } else if (modId == videoIndex) {
            switch (subId) {
                case 0:
                    enabled = categories.contains(AppCategory.VIDEO);
                    // Log.w("+++ videos.enabled", ""+enabled);
                    RowPreferences.setVideosEnabled(activity, !enabled);
                    break;
                case 1:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set video.max ", ""+val);
                    RowPreferences.setRowMax(AppCategory.VIDEO, activity, val);
                    break;
                case 2:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set video.min ", ""+val);
                    RowPreferences.setRowMin(AppCategory.VIDEO, activity, val);
            }
        } else if (modId == gameIndex) {
            switch (subId) {
                case 0:
                    enabled = categories.contains(AppCategory.GAME);
                    // Log.w("+++ games.enabled", ""+enabled);
                    RowPreferences.setGamesEnabled(activity, !enabled);
                    break;
                case 1:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set game.max ", ""+val);
                    RowPreferences.setRowMax(AppCategory.GAME, activity, val);
                    break;
                case 2:
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set game.min ", ""+val);
                    RowPreferences.setRowMin(AppCategory.GAME, activity, val);
            }
        } else if (id == ACTION_ID_APPS_MAX) {
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set all.max ", ""+val);
                    RowPreferences.setAllAppsMax(activity, val);
        } else if (id == ACTION_ID_APPS_ROW) {
                    try {
                        val = Integer.parseInt(action.getDescription().toString());
                    } catch (NumberFormatException nfe) {
                        val = 1;
                    }
                    // Log.w("+++ set apps.max ", ""+val);
                    RowPreferences.setAppsMax(activity, val);
        } else if (id == ACTION_ID_RECOMENDATIONS) { // RECOMENDATIONS
            enabled = RowPreferences.areRecommendationsEnabled(activity);
            // Log.w("+++ recommendations.enabled", ""+enabled);
            RowPreferences.setRecommendationsEnabled(activity, !enabled);
            if (!enabled && FireTVUtils.isLocalNotificationsEnabled(activity)) {
                Toast.makeText(activity, activity.getString(R.string.recs_warning_sale), Toast.LENGTH_LONG).show();
            }
        } else if (id == ACTION_ID_INPUTS) { // INPUTS
            enabled = RowPreferences.areInputsEnabled(activity);
            // Log.w("+++ inputs.enabled", ""+enabled);
            RowPreferences.setInputsEnabled(activity, !enabled);
        }
        updateActions();
    }

    private void updateActions() {

        // Log.w("+++ updateActions()", "+++");
        ArrayList<GuidedAction> actions = new ArrayList<>();
        Activity activity = getActivity();
        Set<AppCategory> categories = RowPreferences.getEnabledCategories(activity);
        String statelabel;
        int i = 0;

        // RECOMENDATIONS
        boolean state = RowPreferences.areRecommendationsEnabled(activity);
        statelabel = (state) ? getString(R.string.preference_on) : getString(R.string.preference_off);
        actions.add(new GuidedAction.Builder(activity).id(ACTION_ID_RECOMENDATIONS).title(R.string.recs_row_title).description(statelabel).build());

        // INPUTS
        state = RowPreferences.areInputsEnabled(activity);
        statelabel = (state) ? getString(R.string.preference_on) : getString(R.string.preference_off);
        actions.add(new GuidedAction.Builder(activity).id(ACTION_ID_INPUTS).title(R.string.inputs_row_title).description(statelabel).build());

        // FAV
        state = RowPreferences.areFavoritesEnabled(activity);
        statelabel = (state) ? getString(R.string.preference_on) : getString(R.string.preference_off);
        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.favorites_row_title).description(statelabel).build());

        int[] constraints = RowPreferences.getFavoriteRowConstraints(activity);

        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.max_favorites_rows_title).description(Integer.toString(constraints[1])).descriptionEditable(true).descriptionEditInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED).build());

        favIndex = ((i - 1) / 2);

        // VIDEO
        state = categories.contains(AppCategory.VIDEO);
        statelabel = (state) ? getString(R.string.preference_on) : getString(R.string.preference_off);
        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.videos_row_title).description(statelabel).build());

        constraints = RowPreferences.getRowConstraints(AppCategory.VIDEO, activity);

        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.max_videos_rows_title).description(Integer.toString(constraints[1])).descriptionEditable(true).descriptionEditInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED).build());

        videoIndex = ((i - 1) / 2);

        // MUSIC
        state = categories.contains(AppCategory.MUSIC);
        statelabel = (state) ? getString(R.string.preference_on) : getString(R.string.preference_off);
        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.music_row_title).description(statelabel).build());

        constraints = RowPreferences.getRowConstraints(AppCategory.MUSIC, activity);

        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.max_music_rows_title).description(Integer.toString(constraints[1])).descriptionEditable(true).descriptionEditInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED).build());

        musicIndex = ((i - 1) / 2);

        // GAME
        state = categories.contains(AppCategory.GAME);
        statelabel = (state) ? getString(R.string.preference_on) : getString(R.string.preference_off);
        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.games_row_title).description(statelabel).build());

        constraints = RowPreferences.getRowConstraints(AppCategory.GAME, activity);

        actions.add(new GuidedAction.Builder(activity).id(++i).title(R.string.max_games_rows_title).description(Integer.toString(constraints[1])).descriptionEditable(true).descriptionEditInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED).build());

        gameIndex = ((i - 1) / 2);

        // ALL
        // actions.add(new GuidedAction.Builder(activity).id(ACTION_ID_APPS).title(R.string.apps_row_title).build());
        constraints = RowPreferences.getAllAppsConstraints(activity);
        int maxapps = RowPreferences.getAppsMax(activity);
        actions.add(new GuidedAction.Builder(activity).id(ACTION_ID_APPS_MAX).title(R.string.max_apps_rows_title).description(Integer.toString(constraints[1])).descriptionEditable(true).descriptionEditInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED).build());
		// Max Apps per row
        actions.add(new GuidedAction.Builder(activity).id(ACTION_ID_APPS_ROW).title(R.string.max_apps_title).description(Integer.toString(maxapps)).descriptionEditable(true).descriptionEditInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED).build());

        setActions(actions); // APPLY

        // REFRESH HOME BC
        if (startup > 0) {
            Intent Broadcast = new Intent(MainActivity.class.getName()); // ACTION
            Broadcast.putExtra("RefreshHome", true);
            activity.sendBroadcast(Broadcast);
        }
    }
}
