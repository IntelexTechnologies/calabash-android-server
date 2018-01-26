package sh.calaba.instrumentationbackend.actions.device;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class UiautomatorTouchObject implements Action {
    @Override
    public Result execute(String... args) {
        try {
            String className = args[0];
            String method = args[1];
            String value = args[2];
            String modifier = args.length >= 4 ? args[3] : "";
            if (method.equals("description")) {
                UiObject element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().className(className).description(value));
                if(modifier.equals("TopLeft")) {
                    element.clickTopLeft();
                } else {
                    element.click();
                }
            }
            else {
                Result.failedResult("The method " + method + " has not been implemented");
            }
        } catch (Exception e) {
            String message = e.getMessage();
            return Result.failedResult(message);
        }
        return new Result(true);
    }

    @Override
    public String key() {
        return "uiautomator_touch_object";
    }
}
