#import <Cordova/CDVPlugin.h>

@interface SeAccountPlugin : CDVPlugin

- (void) closeSession:(CDVInvokedUrlCommand*)command;

- (void) closeVault:(CDVInvokedUrlCommand*)command;

- (void) createVault:(CDVInvokedUrlCommand*)command;

- (void) openSession:(CDVInvokedUrlCommand*)command;

- (void) finish:(CDVInvokedUrlCommand*)command;

- (void) request:(CDVInvokedUrlCommand*)command;

@end
