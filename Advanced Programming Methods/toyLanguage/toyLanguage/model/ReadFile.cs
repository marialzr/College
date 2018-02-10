using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace toyLanguage.model
{
    class ReadFile:IStmt
    {
        private Exp expFileId;
        private string varName;
        public ReadFile(Exp e,string vn)
        {
            varName = vn;
            expFileId = e;
        }
        public string VarName
        {
            get { return varName; }
            set { varName = value; }
        }

        public Exp ExpFileId
        {
            get { return expFileId; }
            set { expFileId = value; }
        }

        public PrgState Execute(PrgState p)
        {
            IMyDictionary<String, int> symbolT = p.SymTable;
            int id = expFileId.Eval(symbolT);
            FileData fd;
            p.FileTable.TryGetValue(id, out fd);
            StreamReader bf = fd.FileDescriptor;
            String s = bf.ReadLine();
            int number;
            if (s.Equals(""))
                number = 0;
            else
                Int32.TryParse(s, out number);

            if (symbolT.Exists(varName))
                symbolT.Update(varName, number);
            else
                symbolT.Add(varName, number);
            return p;

        }

        public override string ToString()
        {
            return "( ReadFile(" + this.expFileId + "," + this.varName + "))";
        }

    }
}
