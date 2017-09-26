#ifndef RSAPUBLICKEY_H_INCLUDED
#define RSAPUBLICKEY_H_INCLUDED

#include "PublicKey.h"
#include "BigInteger.h"

namespace CK {

class RSAPublicKey : public PublicKey {

    private:
        RSAPublicKey();
        RSAPublicKey(const RSAPublicKey& other);
        RSAPublicKey& operator= (const RSAPublicKey& other);

    public:
        RSAPublicKey(const BigInteger& n, const BigInteger& e);
        ~RSAPublicKey();

    public:
        int getBitLength() const;
        const BigInteger& getPublicExponent() const;
        const BigInteger& getModulus() const;

    private:
        BigInteger exp; // e
        BigInteger mod; // n
        int bitLength;

};

}

#endif  // RSAPUBLICKEY_H_INCLUDED
