using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage.model
{
    abstract class Command
    {
        private string key, description;
        public Command(string key, string description)
        {
            this.key = key;
            this.description = description;
        }
        public abstract void Execute();
        public string Key
        {
            get { return key; }
            set { key = value; }
        }

        public string Description
        {
            get { return description; }
            set { description = value; }
        }
    }
}
