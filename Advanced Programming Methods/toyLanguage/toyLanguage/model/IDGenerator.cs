using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage.model
{
    class IDGenerator
    {
        private static int id = 0;
        public IDGenerator() { }
        public static int GenerateId()
        {
            id++;
            return id;
        }
    }
}
