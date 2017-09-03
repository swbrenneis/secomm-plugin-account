
var exec = require("cordova/exec");

var PLUGIN_NAME = "SeAccountPlugin";

var SeAccountPlugin = {
    closeSession: function (session) {
        "use strict";
        exec(null, null, "SeAccountPlugin", "closeSession", [session]);
    },
    closeVault: function (vault, errorcb) {
        "use strict";
        exec(null, errorcb, "SeAccountPlugin", "closeVault", [vault]);
    },
    createVault: function (accountname, passphrase, callback, errorcb) {
        "use strict";
        exec(callback, errorcb, "SeAccountPlugin", "createVault", [accountname, passphrase]);
    },
    openSession: function (callback, errorcb) {
        "use strict";
        exec(callback, errorcb, "SeAccountPlugin", "getSession", []);
    },
    finish: function (session, vault, callback, errorcb) {
        "use strict";
        exec(callback, errorcb, "SeAccountPlugin", "finish", [session, vault]);
    },
    request: function (session, vault, callback, errorcb) {
        "use strict";
        exec(callback, errorcb, "SeAccountPlugin", "request", [session, vault]);
    }
};

module.exports = SeAccountPlugin;

