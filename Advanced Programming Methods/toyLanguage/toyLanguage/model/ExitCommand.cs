using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage.model
{
    class ExitCommand:Command
    {
        public ExitCommand(String key, String desc):base(key, desc)
        {
        }
        public override void Execute()
        {
            Environment.Exit(0);
        }
    }
}
