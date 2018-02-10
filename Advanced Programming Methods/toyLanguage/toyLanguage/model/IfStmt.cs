using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class IfStmt:IStmt
    {
        private Exp expr;
        private IStmt ifs;
        private IStmt elses;

        public IfStmt(Exp ex, IStmt i, IStmt el)
        {
            expr = ex;
            ifs = i;
            elses = el;
        }

        public Exp Expr
        {
            get { return expr; }
            set { expr = value; }
        }

        public IStmt Ifs
        {
            get { return ifs; }
            set { ifs = value; }
        }

        public IStmt Elses
        {
            get { return elses; }
            set { elses = value; }
        }

        public PrgState Execute(PrgState state)
        {
            IMyStack<IStmt> stk = state.ExeStack;
            IMyDictionary<string, int> sym = state.SymTable;
            int val = expr.Eval(sym);
            if (val == 0)
            {
                stk.Push(elses);
            }
            else
            {
                stk.Push(ifs);
            }
            return state;
        }

        public override String ToString()
        {
            return "( If " + expr.ToString() + " then " + ifs.ToString() + " else " + elses.ToString() + ")";
        }
    }
}
