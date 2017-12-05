package id.hw.labs.movieupdate.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;

import id.hw.labs.movieupdate.view.CustomToast;

/**
 * Created by HWAHYUDI on 12/08/2017.
 */

public class Util {
    /**
     * Workaround to get color from various versions of Android
     *
     * @param context context
     * @param id      color resource id
     * @return int color
     */
    @SuppressWarnings("deprecation")
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    /**
     * Workaround to get string from various versions of Android
     *
     * @param context context
     * @param id      string resource id
     * @return String string
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    public static void openUrl(String url, Context ctx) {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            ctx.startActivity(browserIntent);
        } catch (Exception e) {
            CustomToast.show(ctx, "Can't open url!");
        }
    }

    /**
     * Convert dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float dp2px(float dp, Context context) {
        //return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    @SuppressWarnings("deprecation")
    public static Spanned getHtmlSpan(String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(message);
        }
    }
}
