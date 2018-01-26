package sh.calaba.instrumentationbackend.actions.device;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class UiautomatorSetText implements Action {
    @Override
    public Result execute(String... args) {
        try {
            String valueToSet = args[0];
            int instance = Integer.parseInt(args[1]);
            UiObject element = InstrumentationBackend.getUiDevice().findObject(new UiSelector().className("android.widget.EditText").instance(instance));
            element.setText(valueToSet);
        } catch (Exception e) {
            String message = e.getMessage();
            return Result.failedResult(message);
        }
        return new Result(true);
    }

    @Override
    public String key() {
        return "uiautomator_set_text";
    }
}
