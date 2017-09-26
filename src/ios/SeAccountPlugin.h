#import <Cordova/CDVPlugin.h>

@interface SeAccountPlugin : CDVPlugin

- (void) pluginInitialize;

- (void) newAccount:(CDVInvokedUrlCommand*)command;

@end
