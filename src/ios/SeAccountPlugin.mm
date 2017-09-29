#import "SeAccountPlugin.h"
#import <SeAccountPlugin/SeAccountPlugin.h>

@implementation SeAccountPlugin : CDVPlugin

- (void) pluginInitialize {

    [FunctionFactory initialize];

}

- (void) newAccount:(CDVInvokedUrlCommand*)command {

    NSArray *args = command.arguments;
    id<SEPluginFunction> function = [SEFunctionFactory getFunction:command.methodName];
    CDVPluginResult *pluginResult = [function execute:args];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}

@end
