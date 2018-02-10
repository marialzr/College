using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class ConstExp : Exp
    {
        private int number;

        public ConstExp(int n)
        {
            number = n;
        }

        public int Number
        {
            get { return number; }
            set { number = value; }
        }

        public override int Eval(IMyDictionary<string, int> sym)
        {
            return number;
        }

        public override String ToString()
        {
            return number.ToString();
        }

    }
}
