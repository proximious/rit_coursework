﻿using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Threading;

namespace M03_03
{
    class Program
    {
        static void Main(string[] args)
        {
            var threads = new List<Thread>();
            var timer = new Stopwatch();
            timer.Start();
            foreach (var file in args)
            {
                var sc = new SeqCounter(file);
                var pThread = new Thread(sc.Run);
                pThread.Start();
                threads.Add(pThread);
               
            }

            foreach (var th in threads)
            {
                th.Join();
            }
            timer.Stop();
            Console.WriteLine(timer.Elapsed.TotalSeconds+"s");
            
        }
    }
    
    
    public class SeqCounter
    {
        private string path;
        public static object myLock = new Object();

        public SeqCounter(string _path)
        {
            path = _path;
        }

        public void Run()
        {
            var c = new Counter(path);
            c.Count(); 
            lock (myLock)
            {
                Console.WriteLine(path);
                Console.Write("{0,5}", c.LineCount);
                Console.Write("{0,10}", c.WordCount);
                Console.Write("{0,15}", c.LetterCount);
                Console.WriteLine();
            }
        }
    }

    public class Counter
    {
        private string path;
        public int LineCount { get; set; }
        public int WordCount { get; set; }
        public int LetterCount { get; set; }
        

        public Counter(string _path)
        {
            path = _path;
        }

        public void Count()
        {
            try
            {
                var contents = File.ReadAllLines(path);
                LineCount = contents.Length;
                foreach (var line in contents)
                {
                    var words = line.Split();
                    WordCount += words.Length;
                    LetterCount += line.Length;
                }
            }
            catch (FileNotFoundException ex)
            {
                Console.WriteLine(ex.FileName+" Not Found!");
            }
        }
    }
}