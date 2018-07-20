package sh.calaba.instrumentationbackend.actions.device;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.BySelector;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class UiautomatorTouchCoordinates implements Action {
    @Override
    public Result execute(String... args) {
        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            boolean result = InstrumentationBackend.getUiDevice().click(x, y);
            if (!result) {
                return Result.failedResult("The touch did not succeed");
            }
        } catch (Exception e) {
            String message = e.getMessage();
            return Result.failedResult(message);
        }
        return new Result(true);
    }

    @Override
    public String key() {
        return "uiautomator_touch_coordinates";
    }
}
