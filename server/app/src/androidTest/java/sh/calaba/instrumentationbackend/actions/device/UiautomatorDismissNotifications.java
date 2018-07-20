package sh.calaba.instrumentationbackend.actions.device;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Until;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class UiautomatorDismissNotifications implements Action {

    @Override
    public Result execute(String... args) {
        try {
           UiDevice uiDevice = InstrumentationBackend.getUiDevice();
           UiSelector notificationStackScroller = new UiSelector().className("android.view.ViewGroup").resourceId("com.android.systemui:id/notification_stack_scroller");
           UiObject notificationStackScrollerObject = uiDevice.findObject(notificationStackScroller);
           UiObject clearButton = notificationStackScrollerObject.getChild(new UiSelector().className("android.widget.Button").resourceId("com.android.systemui:id/dismiss_text"));

           Boolean hasNotification = uiDevice.hasObject(By.pkg("com.intelex.intelexmobile").res("android:id/notification_main_column"));
           while (hasNotification == true && clearButton.exists() == false) {
               UiObject lastVisibleNotification = notificationStackScrollerObject.getChild(new UiSelector().instance(notificationStackScrollerObject.getChildCount() - 1));
               notificationStackScrollerObject.dragTo(lastVisibleNotification, 100);
           }

           if (clearButton.exists() == true) {
               clearButton.click();
           }
        }
        catch (Exception e) {
            String message = e.getMessage();
            return Result.failedResult(message);
        }
        return new Result(true);
    }

    @Override
    public String key() {
        return "uiautomator_dismiss_notifications";
    }
}
