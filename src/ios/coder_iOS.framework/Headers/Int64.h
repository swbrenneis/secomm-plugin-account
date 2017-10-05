#ifndef INT64_H_INCLUDED
#define INT64_H_INCLUDED

#include "ByteArray.h"
#include "Constants.h"

namespace coder {

class Int64 {

    public:
        Int64();
        Int64(int64_t v);
        //Int64(const ByteArray& encoded);
        Int64(const ByteArray& encoded, Endian endian = bigendian);
        Int64(const Int64& other);

        ~Int64();

    public:
        Int64& operator= (const Int64& other);

    public:
        // Decode a byte array in the native endian order.
        //void decode(const ByteArray& encoded);
        // Decode a byte array in the specified endian order.
        void decode(const ByteArray& encoded, Endian endian);
        // Returns an encoded array in the native endian order.
        //const ByteArray& getEncoded();
        // Returns an encoded array in the specified endian order.
        const ByteArray& getEncoded(Endian endian);
        // Returns a signed integer.
        int64_t getValue() const;
        // Set the integer value.
        void setValue(int64_t v);

    private:
        //void endianTest();
        void encode(Endian eType);

    private:
        int64_t value;
        ByteArray encoded;
        static Endian endian;

};

}

#endif  // INT64_H_INCLUDED
