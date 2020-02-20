console.log('[APP] Called config script');
// Inject CSP meta tag
var meta = document.createElement('meta');
meta.setAttribute('http-equiv', 'Content-Security-Policy');
meta.setAttribute('content', 'default-src * \'self\' \'unsafe-inline\' \'unsafe-eval\' blob: data: gap: file: filesystem: ws: wss:;');
document.head.appendChild(meta);

// Set Jellyfin app mode
window.appMode = 'android';

var deviceId;
var deviceName;
var appName;
var appVersion;

var features = [
    //'filedownload',
    'displaylanguage',
    'externalplayerintent',
    'subtitleappearancesettings',
    'sharing',
    'exit',
    'htmlaudioautoplay',
    'htmlvideoautoplay',
    'externallinks',
    'multiserver',
    'physicalvolumecontrol',
    'remotecontrol',
    'castmenuhashchange'
];

window.NativeShell = {
    getDeviceInformation: function(success, error) { success(JSON.parse(Native_Shell.getDeviceInformation())) },
    enableFullscreen: function(success, error) { success(Native_Shell.enableFullscreen()) },
    disableFullscreen: function(success, error) { success(Native_Shell.disableFullscreen()) },
    openUrl: function(url, target, success, error) { success(Native_Shell.openUrl(url, target)) },
    updateMediaSession: function(options, success, error) { success(Native_Shell.updateMediaSession(options)) },
    hideMediaSession: function(success, error) { success(Native_Shell.hideMediaSession()) },
    downloadFile: function(url, success, error) { success(Native_Shell.downloadFile(url)) },
    getPlugins: function(success, error) { success(Native_Shell.getPlugins()) },

    AppHost: {
        exit: function() {
            Native_Shell.exit();
        },
        supports: function(command) {
            return features.indexOf(command.toLowerCase()) != -1;
        },
        getSyncProfile: function(profileBuilder, item) {},//todo
        getDefaultLayout: function() {
            return 'mobile';
        },
        getDeviceProfile: function(profileBuilder, item) {},//todo
        init: function() {
            return new Promise(function(resolve, reject) {
                var result = Native_Shell.getDeviceInformation();
                // set globally so they can be used elsewhere
                deviceId = result.deviceId;
                deviceName = result.deviceName;
                appName = result.appName;
                appVersion = result.appVersion;
                //resolve
                resolve({
                    deviceId: result.deviceId,
                    deviceName: result.deviceName,
                    appName: result.appName,
                    appVersion: result.appVersion
                });
            });
        },
        deviceName: function() {
            return deviceName;
        },
        deviceId: function() {
            return deviceId;
        },
        appName: function() {
            return appName;
        },
        appVersion: function() {
            return appVersion;
        }
    }
};
