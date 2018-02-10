using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage.model
{
    class TextMenu
    {
        private IDictionary<String, Command> commands;
        public TextMenu()
        {
            commands = new Dictionary<String, Command>();
        }
        public void AddCommand(Command c)
        {
            commands.Add(c.Key, c);
        }
        private void PrintMenu() {
            foreach (Command com in commands.Values) {
                Console.WriteLine(com.Key, com.Description);
            }
        }

        public void Show()
        {
            while (true)
            {
                PrintMenu();
                Console.WriteLine("Input the option: ");
                String key = Console.ReadLine();
                commands.TryGetValue(key, out Command com);
                if (com == null)
                {
                    Console.WriteLine("Invalid Option");
                    continue;
                }
                com.Execute();
            }
        }
    }
}