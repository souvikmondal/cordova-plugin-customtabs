var exec = require('cordova/exec');

var PLUGIN_NAME = 'customtabs';

var customtabs = {

    isAvailable(success, error) {
        exec(success, error, PLUGIN_NAME, "isAvailable", []);
    },

    launch(url, success, error) {
        exec(success, error, PLUGIN_NAME, "launch", [url]);
    },

    launchInBrowser(url, success, error) {
        exec(success, error, PLUGIN_NAME, "launchInBrowser", [url]);
    },

    close(success, error) {
        exec(success, error, PLUGIN_NAME, "close", []);
    },

};


module.exports = customtabs;
