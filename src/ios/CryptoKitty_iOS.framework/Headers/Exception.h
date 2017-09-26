#ifndef EXCEPTION_H_INCLUDED
#define EXCEPTION_H_INCLUDED

#include <exception>
#include <string>

#ifdef __MACH__
#define EXCEPTION_THROW_SPEC throw()
#else
#define EXCEPTION_THROW_SPEC noexcept
#endif

namespace CK {

class Exception  : public std::exception {

    protected:
        Exception() {}
        Exception(const std::string& msg) : message(msg) {}
        Exception(const Exception& other)
                : message(other.message) {}

    private:
        Exception& operator= (const Exception& other);

    public:
        ~Exception() {}

    public:
        const char *what() const EXCEPTION_THROW_SPEC { return message.c_str(); }

    private:
        std::string message;

};

}

#endif // BADPARAMETEREXCEPTION_H_INCLUDED
