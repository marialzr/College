using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage
{
    class MyDictionary<K,V>:IMyDictionary<K,V>
    {
        private IDictionary<K, V> dict;
        public MyDictionary()
        {
            dict = new Dictionary<K, V>();
        }

        public IDictionary<K,V> Dict
        {
            get { return dict; }
            set { dict = value; }
        }
        
        public void Add(K key, V val)
        {
            dict.Add(key, val);
        }

        public bool ContainsKey(K key)
        {
            return dict.ContainsKey(key);
        }
        
        public bool TryGetValue(K key, out V value)
        {
            return dict.TryGetValue(key, out value);
        }

        public override String ToString()
        {
            String s = "";
            foreach (KeyValuePair<K, V> kvp in dict)
            {
                s += kvp.Key.ToString() + " " + kvp.Value.ToString();
                s += System.Environment.NewLine;
            }
            return s;
        }

        public bool Exists(K key)
        {
            if (dict.ContainsKey(key))
                return true;
            return false;
        }

        public void Update(K key, V val)
        {
            dict[key] = val;
        }

        public void Remove(K key)
        {
            dict.Remove(key);
        }
    }
}
