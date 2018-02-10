using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace toyLanguage.model
{
    class CloseRFile:IStmt
    {
        private string expFileId;
        public CloseRFile(string e)
        {
            expFileId = e;
        }
        public string ExpFileId
        {
            get { return expFileId; }
            set { expFileId = value; }
        }

        public PrgState Execute(PrgState p)
        {
            IMyDictionary<string, int> symbolt = p.SymTable;
            int id;
            symbolt.TryGetValue(expFileId, out id);
            FileData fd;
            p.FileTable.TryGetValue(id, out fd);
            StreamReader sr = fd.FileDescriptor;
            sr.Close();
            p.FileTable.Remove(id);
            return p;
        }

        public override string ToString()
        {
            return "CloseRFile(" + expFileId + ")";
        }
    }
}
