package sh.calaba.instrumentationbackend.actions.device;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class UiautomatorSwipeObject implements Action {
    @Override
    public Result execute(String... args) {
        try {
            String className = args[0];
            String method = args[1];
            String value = args[2];
            String modifier = args[3];
            int steps = args.length >= 5 ? Integer.parseInt(args[4]) : 100;
            UiObject element = null;
            if (method.equals("text")) {
                element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().className(className).text(value));
            }
            else if (method.equals("description")) {
                element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().className(className).description(value));
            }
            else if(method.equals("resourceid")) {
                element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().resourceId(value));
            }
            else {
                return Result.failedResult("The method " + method + " has not been implemented");
            }
            if (modifier.equals("left")) {
                element.swipeLeft(steps);
            }
            else if (modifier.equals("right")) {
                element.swipeRight(steps);
            }
            else if (modifier.equals("up")) {
                element.swipeUp(steps);
            }
            else {
                element.swipeDown(steps);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            return Result.failedResult(message);
        }
        return new Result(true);
    }

    @Override
    public String key() {
        return "uiautomator_swipe_object";
    }
}
