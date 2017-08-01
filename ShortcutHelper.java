import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhosh on 31-07-2017.
 */

public class ShortcutHelper {

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static List<ShortcutInfo> buildShorCuts(@NonNull Context context, @NonNull SparseArray<AppShortCut> shortCutIds) {
        int length = shortCutIds.size();
        ShortcutInfo shortcutInfo;
        AppShortCut appShortCut;
        ArrayList<ShortcutInfo> shortcutInfos = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            appShortCut = shortCutIds.get(i);
            shortcutInfo = new ShortcutInfo.Builder(context, appShortCut.getId())
                    .setShortLabel(appShortCut.getName())
                    .setRank(i)
                    .setIcon(Icon.createWithResource(context, appShortCut.getIconRes()))
                    .setIntents(appShortCut.getIntent())
                    .build();
            shortcutInfos.add(shortcutInfo);
        }
        return shortcutInfos;
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static ShortcutManager shortcutManager(Context context) {
        return context.getSystemService(ShortcutManager.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static void setShortCuts(ShortcutManager shortcutManager, List<ShortcutInfo> shortcutInfos) {
        shortcutManager.setDynamicShortcuts(shortcutInfos);
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static boolean disableShortCuts(ShortcutManager shortcutManager, List<String> shortcutIds) {
        if (shortcutManager != null && shortcutIds != null) {
            shortcutManager.disableShortcuts(shortcutIds);
            return true;
        } else return false;
    }

    public static List<String> getShortCutIds(SparseArray<AppShortCut> appShortCuts) {
        int length = appShortCuts.size();
        ArrayList<String> id = new ArrayList<>();
        AppShortCut appShortCut;
        for (int i = 0; i < length; i++) {
            appShortCut = appShortCuts.get(i);
            id.add(appShortCut.getId());
        }
        return id;
    }

    public static class AppShortCut {

        private String id;

        private String name;

        private int iconRes;

        private Intent[] intent;

        public AppShortCut(String id, String name, int iconRes, Intent[] intent) {
            this.id = id;
            this.name = name;
            this.iconRes = iconRes;
            this.intent = intent;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIconRes() {
            return iconRes;
        }

        public void setIconRes(int iconRes) {
            this.iconRes = iconRes;
        }

        public Intent[] getIntent() {
            return intent;
        }

        public void setIntent(Intent[] intent) {
            this.intent = intent;
        }
    }


}
