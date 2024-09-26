﻿using System;
using System.Diagnostics;
using System.IO;
using System.Threading.Tasks;

namespace M03_03
{
    class Program
    {
        static void Main(string[] args)
        {

            var totalLines = 0L;
            var totalWords = 0L;
            var totalChars = 0L;
            
            var di = new DirectoryInfo(".");

            var files = args;
            
            var timer = new Stopwatch();
            timer.Start();


            Parallel.For(0, 10, (i) =>
            {
                Console.WriteLine(i);
            });
            Parallel.ForEach(files, file =>
            {
                var counter = new Counter(file);
                counter.Count();
                totalLines += counter.LineCount;
                totalWords += counter.WordCount;
                totalChars += counter.LetterCount;
            });

            Console.WriteLine(totalLines);
            Console.WriteLine(totalWords);
            Console.WriteLine(totalChars);

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