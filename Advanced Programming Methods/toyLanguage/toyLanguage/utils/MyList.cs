using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class MyList<T>:IMyList<T>
    {
        private IList<T> lst;

        public MyList()
        {
            lst = new List<T>();
        }

        public IList<T> Lst
        {
            get { return lst;}
            set { lst = value; }
        }

        public void Add(T elem)
        {
            lst.Add(elem);
        }

        public override String ToString()
        {
            string s = "";
            foreach(T el in lst)
            {
                s += el.ToString() + " ";
                s += System.Environment.NewLine;
            }
            return s;
        }
    }
}
