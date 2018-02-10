using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace toyLanguage
{
    class Repository:IRepository
    {
        private IList<PrgState> prgStates;
        private String logFilePath;
        private FileStream logFile;

        public Repository(string logFPath)
        {
            prgStates = new List<PrgState>();
            logFilePath = logFPath;
        }

        public String LogFilePath
        {
            get { return logFilePath; }
            set { logFilePath = value; }
        }

        public FileStream LogFile
        {
            get { return logFile; }
            set { LogFile = value; }
        }


        public PrgState GetCrtPrgState()
        {
            return prgStates.ElementAt(0);
        }

        public void SetCrtPrgState(PrgState p)
        {
            prgStates.Add(p);
        }

        public void AddPrgState(PrgState p)
        {
            prgStates.Add(p);
        }

        public void logPrgStateExec()
        {
            if (!File.Exists(LogFilePath))
            {
                using (logFile = File.Create(LogFilePath))
                using (TextWriter writer = new StreamWriter(logFile))
                {
                    PrgState p = GetCrtPrgState();
                    writer.WriteLine("Exe stack: ");
                    writer.WriteLine(p.ExeStack);
                    writer.WriteLine("Symbol table: ");
                    writer.WriteLine(p.SymTable);
                    writer.WriteLine("Messages:");
                    writer.WriteLine(p.Messages);
                    writer.WriteLine("------------------------------------------------------------------------");
                }
            }
            else
            {
                using (StreamWriter writer = File.AppendText(logFilePath))
                {
                    PrgState p = GetCrtPrgState();
                    writer.WriteLine("Exe stack: ");
                    writer.WriteLine(p.ExeStack);
                    writer.WriteLine("Symbol table: ");
                    writer.WriteLine(p.SymTable);
                    writer.WriteLine("Messages:");
                    writer.WriteLine(p.Messages);
                    writer.WriteLine("------------------------------------------------------------------------");
                }
            }
            
        }

    }
}
