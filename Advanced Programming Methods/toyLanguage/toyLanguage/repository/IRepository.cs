using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    interface IRepository
    {
        PrgState GetCrtPrgState();
        void SetCrtPrgState(PrgState p);
        void AddPrgState(PrgState p);
        void logPrgStateExec();
    }
}
