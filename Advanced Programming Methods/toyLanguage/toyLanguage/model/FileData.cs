using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace toyLanguage.model
{
    class FileData
    {
        private String fileName;
        private StreamReader fileDescriptor;
        public FileData(String n, StreamReader fs)
        {
            fileName = n;
            fileDescriptor = fs;
        }

        public String FileName
        {
            get { return fileName; }
            set { fileName = value; }
        }

        public StreamReader FileDescriptor
        {
            get { return fileDescriptor; }
            set { fileDescriptor = value; }
        }

        public override string ToString()
        {
            return fileName;
        }
    }
}
