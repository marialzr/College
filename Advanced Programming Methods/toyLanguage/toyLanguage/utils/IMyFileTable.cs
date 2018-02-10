using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace toyLanguage.model
{
    interface IMyFileTable<K,V>
    {
        void Add(K key, V val);
        bool ContainsKey(K key);
        bool TryGetValue(K key, out V value);
        String ToString();
        bool Exists(K key);
        void Update(K key, V val);
        IDictionary<K, V> GetAll();
        void Remove(K key);
    }
}
