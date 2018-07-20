package sh.calaba.instrumentationbackend.actions.device;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.BySelector;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class UiautomatorWaitForExists implements Action {
    @Override
    public Result execute(String... args) {
        try {
            String className = args[0];
            long waitMs = Long.parseLong(args[1]);
            String method = args[2];
            String value = args[3];
            UiObject element = null;
            if (method.equals("description")) {
                element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().className(className).description(value));
            }
            else if (method.equals("text")) {
                element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().className(className).text(value));
            }
            else if (method.equals("resourceid")) {
                element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().resourceId(value));
            }
            else {
                return Result.failedResult("The method " + method + " has not been implemented");
            }
            return new Result(element.waitForExists(waitMs));
        } catch (Exception e) {
            String message = e.getMessage();
            return Result.failedResult(message);
        }
    }

    @Override
    public String key() {
        return "uiautomator_wait_for_exists";
    }
}
