using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class AssignStmt:IStmt
    {
        private String id;
        private Exp expr;
        public AssignStmt(String i, Exp e)
        {
            id = i;
            expr = e;
        }

        public String Id
        {
            get { return id; }
            set { id = value; }
        }

        public Exp Expr
        {
            get { return expr; }
            set { expr = value; }
        }
        public override String ToString()
        {
            return "("+id + " <- " + expr.ToString()+")";
        }
        public PrgState Execute(PrgState state)
        {
            IMyDictionary<string, int> sym = state.SymTable;
            IMyStack<IStmt> stk = state.ExeStack;
            int val = expr.Eval(sym);
            if (sym.Exists(id))
                sym.Update(id, val);
            else
                sym.Add(id, val);
            return state;
        }

       

    }
}
