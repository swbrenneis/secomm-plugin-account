//
//  FunctionFactory.h
//  SeAccountPlugin
//
//  Created by Steve Brenneis on 9/26/17.
//  Copyright © 2017 seComm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SEPluginFunction.h"

@interface SEFunctionFactory : NSObject

@property (class) NSMutableDictionary *factories;

+ (void)initializeFactories;

+ (id<SEPluginFunction>)getFunction:(NSString*)name;

@end
