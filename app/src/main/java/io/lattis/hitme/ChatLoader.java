package io.lattis.hitme;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;

import java.util.List;

import io.lattis.hitme.model.ChatNode;
import io.lattis.hitme.utils.QueryUtils;

public class ChatLoader extends AsyncTaskLoader<List<ChatNode>> {

    /**
     * Tag for log messages
     */
    private static final String TAG = ChatLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link ChatLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public ChatLoader(Context context, String url) {
        super(context);
        mUrl = url;
        Log.e(TAG, "ChatLoader: ");

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.e(TAG, "onStartLoading: ");
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<ChatNode> loadInBackground() {
        Log.e(TAG, "loadInBackground: ");
        if (mUrl == null) {
            return null;
        }

        List<ChatNode> Result = null;
        try {

            if (!ContactListActivity.previouslyLoaded) {
                QueryUtils.fetchChatData(mUrl);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Result;
    }
}
