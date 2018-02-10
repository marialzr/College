using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    abstract class Exp
    {
        abstract public int Eval(IMyDictionary<String, int> sym);
        abstract public override String ToString();
    }
}
