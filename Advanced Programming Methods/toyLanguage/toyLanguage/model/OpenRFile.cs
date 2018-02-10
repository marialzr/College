using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using toyLanguage;
using System.IO;

namespace toyLanguage.model
{
    class OpenRFile:IStmt
    {
        private String var_file_id;
        private String fileName;
        public OpenRFile(String fid, String fn)
        {
            var_file_id = fid;
            fileName = fn;
        }

        public void Containsp(PrgState p)
        {
            try
            {
                IDictionary<int, FileData> d = p.FileTable.GetAll();
                    foreach (FileData fd in d.Values)
                    {
                        if (fd.FileName.Equals(fileName))
                        {
                            throw new Exception("The file is already open!");
                        }
                    }
               
            }
            catch(Exception e)
            {

            }
            
        }

        public PrgState Execute(PrgState p)
        {
            try
            {
                this.Containsp(p);
                StreamReader fs = new StreamReader("read.txt");
                FileData f = new FileData(fileName, fs);
                int id = IDGenerator.GenerateId();
                p.FileTable.Add(id, f);
                IMyDictionary<String, int> st = p.SymTable;
                if (st.ContainsKey(var_file_id))
                    st.Update(var_file_id, id);
                else
                    st.Add(var_file_id, id);
            }
           
            catch (NullReferenceException e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return p; 
        }

        public override string ToString()
        {
            return "(OpenRFile (" + this.var_file_id + "," + this.fileName + "))";
        }
    }
}
