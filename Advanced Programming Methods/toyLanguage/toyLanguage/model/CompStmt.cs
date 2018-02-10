using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class CompStmt : IStmt
    {
        private IStmt first;
        private IStmt snd;

        public CompStmt(IStmt f, IStmt s)
        {
            first = f;
            snd = s;
        }

        public IStmt First{
            get{return first;}
            set { first = value; }
        }

        public IStmt Snd
        {
            get { return snd; }
            set { snd = value; }
        }

        /*public override PrgState Execute(PrgState state)
         {
             return state;
         }*/

        public override String ToString()
        {
            return "(" + first.ToString() + " " + snd.ToString()+")";
        }

        public PrgState Execute(PrgState state)
        {
            IMyStack<IStmt> stk = state.ExeStack;
            stk.Push(snd);
            stk.Push(first);
            return state;
        }
    }
}
