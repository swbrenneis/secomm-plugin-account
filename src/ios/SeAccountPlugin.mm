#import "SeAccountPlugin.h"
#import <SeAccountPlugin/SeAccountPlugin.h>

@implementation SeAccountPlugin : CDVPlugin

- (void) pluginInitialize {
}

- (void) newAccount:(CDVInvokedUrlCommand*)command {

    AccountCreator *creator = [[AccountCreator] init];

}

@end
