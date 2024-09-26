
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Threading;
using System.Threading.Tasks;

namespace ConsoleApplication
{

    public class Program
    {
        public static void Main(string[] args)
        {
            var n = Int32.Parse(args[0]);
            var tasks = new List<Task>();
            var channel = new Bucket();
            var getter = new Getter(channel);
            var putter = new Putter(channel, n);
            tasks.Add(Task.Run(() => getter.get()));
            tasks.Add(Task.Run(() => putter.put()));
            Task.WaitAll(tasks.ToArray());
        }
    }

    public class Bucket
    
    {
        public static Object myLock = new object();
        static int? value;
        private bool valueExists;

        public void put(int? v)
        {
            lock(myLock) {
                while (valueExists) {
                    Monitor.Wait(myLock);
                }
                value = v;
                valueExists = true;
                Monitor.PulseAll(myLock);
            }
        }

        public int? get()
        {
            lock(myLock) {
                while (!valueExists)
                {
                    Monitor.Wait(myLock);
                }
                
                var v = value;
                valueExists = false;
                Monitor.PulseAll(myLock);     
                return v;
            }
            
        }

    }

    public class Putter {
        private Bucket bucket;
        private int n;
        public Putter(Bucket _bucket, int _n)
        {
            bucket = _bucket;
            n = _n;
        }

        public void put()
        {
            for (var i =1; i<=n; i++)
            {
                Thread.Sleep(1000);
                bucket.put(i);
            }
            bucket.put(null);
        }
    }

    public class Getter {
        private Bucket bucket;

        public Getter(Bucket _bucket)
        {
            bucket = _bucket;
        
        }

        public void get()
        {
            int? v;
            while ((v = bucket.get()) != null) 
            {
                Console.WriteLine(v);
            }
        }
    }

}