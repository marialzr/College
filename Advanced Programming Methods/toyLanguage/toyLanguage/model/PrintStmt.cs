using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class PrintStmt:IStmt
    {
        private Exp expr;
        public PrintStmt(Exp e)
        {
            expr = e;
        }

        public Exp Expr
        {
            get { return expr;}
            set { expr = value; }
        }

        public override String ToString()
        {
            return "( Print " + expr.ToString() + " )";
        }

        public PrgState Execute(PrgState state)
        {
            IMyDictionary<string, int> sym = state.SymTable;
            IMyList<int> msg = state.Messages;
            int res = expr.Eval(sym);
            msg.Add(res);
            return state;
        }

        
    }
}
