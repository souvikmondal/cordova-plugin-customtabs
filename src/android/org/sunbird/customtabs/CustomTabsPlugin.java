package org.sunbird.customtabs;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class CustomTabsPlugin extends CordovaPlugin {

  private static final String ACTION_IS_AVAILABLE = "isAvailable";
  private static final String ACTION_LAUNCH = "launch";
  private static final String ACTION_CLOSE = "close";

  private static CallbackContext tokenCallbackContext;

  private CustomTabsHelper customTabsHelper;
  private boolean isCustomTabsAvailable;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    customTabsHelper = new CustomTabsHelper();
    isCustomTabsAvailable = customTabsHelper.initCustomTabs(cordova.getActivity());
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    boolean handled = false;
    switch (action) {
      case ACTION_IS_AVAILABLE:
        if (isCustomTabsAvailable) {
          callbackContext.success();
        } else {
          callbackContext.error("Custom tabs not available.");
        }
        handled = true;
        break;
      case ACTION_LAUNCH:
        tokenCallbackContext = callbackContext;
        String url = args.getString(0);
        customTabsHelper.launchUrl(url, cordova.getActivity());
        handled = true;
        break;
      case ACTION_CLOSE:
        handled = true;
        break;
    }
    return handled;
  }

  public static void onTokenRecieved(String token) {
    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK,token);
    pluginResult.setKeepCallback(true);
    tokenCallbackContext.sendPluginResult(pluginResult);
  }
}