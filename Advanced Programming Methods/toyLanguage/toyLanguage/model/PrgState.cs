using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using toyLanguage.model;

namespace toyLanguage
{
    class PrgState
    {
        IMyStack<IStmt> exeStack;
        IMyDictionary<String, int> symTable;
        IMyList<int> messages;
        IMyFileTable<int, FileData> fileTable;
        IStmt originalPrg;

        public PrgState(IMyStack<IStmt> exe, IMyDictionary<String, int> d, IMyList<int> l, IMyFileTable<int, FileData>ft, IStmt orig)
        {
            exeStack = exe;
            symTable = d;
            messages = l;
            fileTable = ft;
            originalPrg = orig;
            exeStack.Push(orig);
        }

        public IMyStack<IStmt> ExeStack
        {
            get { return exeStack; }
            set { exeStack = value; }
        }

        public IMyDictionary<string, int> SymTable
        {
            get { return symTable; }
            set { symTable = value; }
        }

        public IMyList<int> Messages
        {
            get { return messages; }
            set { messages = value; }
        }

        public IMyFileTable<int, FileData> FileTable
        {
            get { return fileTable; }
            set { fileTable = value; }
        }

        public override String ToString()
        {
            string s = "Execution Stack: ";
            s += System.Environment.NewLine;
            s += exeStack.ToString();
            s += "Symbol Table: ";
            s += System.Environment.NewLine;
            s += symTable.ToString();
            s += "Messages: ";
            s += System.Environment.NewLine;
            s += messages.ToString();
            s += "File table: ";
            s += System.Environment.NewLine;
            s += fileTable.ToString();
            s += "Original program: ";
            s += System.Environment.NewLine;
            s += originalPrg.ToString();
            s += System.Environment.NewLine;
            s += "--------------------------------------------------------------------";
            return s;  
        }
    }
}
