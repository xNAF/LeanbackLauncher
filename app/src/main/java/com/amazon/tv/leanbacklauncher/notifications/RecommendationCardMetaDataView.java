package com.amazon.tv.leanbacklauncher.notifications;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class RecommendationCardMetaDataView extends RelativeLayout {
    public RecommendationCardMetaDataView(Context context) {
        super(context);
    }

    public RecommendationCardMetaDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecommendationCardMetaDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setVisibility(int visibility) {
        super.setVisibility(View.VISIBLE);
    }
}
