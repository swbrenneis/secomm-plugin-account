//
//  CKSHA1.h
//  CryptoKitty_iOS
//
//  Created by Steve Brenneis on 10/4/17.
//  Copyright © 2017 CryptoKitty. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CKSHA1 : NSObject

@property (nonatomic, readonly) void *sha1;
@property (nonatomic, readonly) NSMutableData *accumulator;

- (instancetype) init;

- (void) dealloc;

- (NSData*) digest;

- (NSData*) digest:(NSData*)data;

- (void) reset;

- (void) update:(NSData*)data;

@end
