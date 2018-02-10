using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using toyLanguage.model;

namespace toyLanguage.view
{
    class Interpreter
    {
        static void Main(string[] args)
        {
            IStmt stmt1 = new CompStmt(new CompStmt(
                                            new OpenRFile("varf", "read.txt"),
                                            new ReadFile(new VarExp("varf"), "varc")
                                        ),
                                       new CompStmt(
                                           new PrintStmt(new VarExp("varc")),
                                           new CloseRFile("varf")
                                           )

                );
            IMyStack<IStmt> exec = new MyStack<IStmt>();
            IMyDictionary<String, int> symbolT = new MyDictionary<String, int>();
            IMyList<int> msg = new MyList<int>();
            IMyFileTable<int, FileData> filet = new MyFileTable<int, FileData>();
            PrgState prg1 = new PrgState(exec, symbolT, msg, filet, stmt1);

            IRepository repo1 = new Repository("log1.txt");
            Controller ctr1 = new Controller(repo1);
            repo1.AddPrgState(prg1);
            TextMenu menu = new TextMenu();
            menu.AddCommand(new ExitCommand("0", "exit"));
            menu.AddCommand(new RunExample("1", stmt1.ToString(), ctr1));
            menu.Show();
        }
    }
}
