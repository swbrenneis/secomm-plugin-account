
var exec = require("cordova/exec");

var PLUGIN_NAME = "SeAccountPlugin";

var SeAccountPlugin = {
    newAccount: function (accountname, passphrase, callback, errorcb) {
        "use strict";
        exec(callback, errorcb, "SeAccountPlugin", "newAccount", [accountname, passphrase]);
    }
};

module.exports = SeAccountPlugin;

