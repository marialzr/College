using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class ArithExp:Exp
    {
        private Char op;
        private Exp exp1;
        private Exp exp2;

        public ArithExp(Exp e1, Exp e2, Char o)
        {
            op = o;
            exp1 = e1;
            exp2 = e2;
        }

        public Exp Exp1
        {
            get { return exp1; }
            set { exp1 = value; }
        }

        public Exp Exp2
        {
            get { return exp2; }
            set { exp2 = value; }
        }

        public override int Eval(IMyDictionary<String, int> st)
        {
            switch (op)
            {
                case '+':
                    return exp1.Eval(st) + exp2.Eval(st);
                case '-':
                    return exp1.Eval(st) - exp2.Eval(st);
                case '*':
                    return exp1.Eval(st) * exp2.Eval(st);
                case '/':
                    return exp1.Eval(st) / exp2.Eval(st);
                default:
                    throw new Exception("The operator must be valid!");
            }
        }

        public override string ToString()
        {
            return "("+exp1.ToString()+" "+ op + " "+exp2.ToString()+")";
        }

    }
}
