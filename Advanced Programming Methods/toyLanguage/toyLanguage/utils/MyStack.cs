using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class MyStack<T> : IMyStack<T>
    {
        private Stack<T> stk;
        public MyStack()
        {
            stk = new Stack<T>();
        }

        public Stack<T> Stk
        {
            get { return stk; }
            set { stk = value; }
        }

        public void Push(T v)
        {
            stk.Push(v);
        }

        public T Pop()
        {
            return stk.Pop();
        }

        public bool IsEmpty()
        {
            if (stk.Count == 0)
                return true;
            return false;
        }

        public override String ToString()
        {
            String s = "";
            foreach(T e in stk)
            {
                s += e.ToString()+" ";
                s += System.Environment.NewLine;
            }
            return s;
        }
    }
}
