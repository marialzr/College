using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    interface IMyStack<T>
    {
        void Push(T v);
        T Pop();
        bool IsEmpty();
        String ToString();
    }
}
