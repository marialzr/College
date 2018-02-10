using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class Controller
    {
        private IRepository repo;

        public Controller(IRepository r)
        {
            repo = r;
        }

        public IRepository Repo
        {
            get { return repo; }
            set { repo = value; }
        }

        public PrgState OneStep(PrgState state)
        {
            IMyStack<IStmt> stk = state.ExeStack;
            if (stk.IsEmpty())
                throw new Exception("The execution stack is empty");
            IStmt crtStmt = stk.Pop();
            return crtStmt.Execute(state);
        }

        public void AllStep()
        {
            PrgState prg = repo.GetCrtPrgState();
            while (!prg.ExeStack.IsEmpty())
            {
                PrgState p = OneStep(prg);
                Console.WriteLine(p);
                repo.logPrgStateExec();
            }
        }
    }
}
