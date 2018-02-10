using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class VarExp:Exp
    {
        private String id;
        public VarExp(String i)
        {
            id = i;
        }

        public String Id
        {
            get { return id; }
            set { id = value; }
        }

        public override int Eval(IMyDictionary<String, int> sym)
        {
            if (sym.ContainsKey(id))
            {
                int v;
                sym.TryGetValue(id, out v);
                return v;
            }
            else
                throw new Exception("The element does not exist in the symbol table");
        }

        public override String ToString()
        {
            return id.ToString();
        }
    }
}
