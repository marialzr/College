using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    interface IMyList<T>
    {
        void Add(T elem);
        String ToString();
    }
}
