//
//  PluginFunction.h
//  SeAccountPlugin
//
//  Created by Steve Brenneis on 9/26/17.
//  Copyright © 2017 seComm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <Cordova/CDVPluginResult.h>

@protocol SEPluginFunction <NSObject>

@required

- (CDVPluginResult*)execute:(NSArray*)args;

@end
