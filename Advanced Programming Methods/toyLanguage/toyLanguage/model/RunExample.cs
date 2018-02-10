using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage.model
{
    class RunExample : Command
    {
        private Controller ctr;
        public RunExample(String key, String desc, Controller ctr) : base(key, desc)
        {
            this.ctr = ctr;
        }
        public override void Execute()
        {
            ctr.AllStep();
        }
    }
}
