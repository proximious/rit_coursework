/// <summary>
/// @author: Alex Iacob ai9388
/// @filename: Program.cs
///
/// Main class runner for CSCI 251 project 2 : Prime Number Generator
/// </summary>

using System.Diagnostics;
using System.Numerics;
using System.Security.Cryptography;

/// <summary>
/// Contains the main method for the program
/// </summary>
namespace PrimeGen
{
    using Extensions;

    /// <summary>
    /// Class containing functions to parse user input, run the program,
    /// make pre-checks, and print the usage statement
    /// </summary>
    public class Program
    {
        /// <summary>
        /// Parses user input and calls all other functions
        /// </summary>
        /// <param name="args">command line arguments
        /// </param>
        static void Main(string[] args)
        {
            Program program = new Program();

            if (args.Length < 1 || args.Length > 2)
            {
                // bad number of arguments
                Console.WriteLine("Incorrect number of arguments");
                program.PrintUsage();
            }
            else
            {
                try
                {
                    // parsing for the number of bits
                    var bits = Int32.Parse(args[0]);

                    if (bits % 8 == 0 && bits >= 32)
                    {
                        // if the count is not give, it is defaulted to 1
                        var count = 1;

                        // getting the actual count
                        if (args.Length > 1)
                        {
                            count = Int32.Parse(args[1]);
                        }

                        // running the generator
                        program.RunPrimeGenerator(bits, count);
                    }
                    else
                    {
                        // number of bits is wrong
                        Console.WriteLine("Incorrect bits");
                        program.PrintUsage();
                    }
                }
                catch (Exception)
                {
                    program.PrintUsage();
                }
            }
        }

        /// <summary>
        /// Runs the Prime Generator
        /// </summary>
        /// <param name="bits">int bitlength of each prime generated</param>
        /// <param name="count">int number of primes to generate</param>
        private void RunPrimeGenerator(int bits, int count)
        {
            Console.WriteLine("BitLength: " + bits + "bits");
            Stopwatch stopWatch = new Stopwatch();
            List<int> checks = this.SieveOfEratosthenes(bits); 
            
            stopWatch.Start();
            PrimeGen primeGen = new PrimeGen();
            primeGen.GeneratePrimes(bits, count, checks);
            stopWatch.Stop();
            
            Console.WriteLine("Time to Generate: " + stopWatch.Elapsed);
            stopWatch.Reset();
        }

        /// <summary>
        /// Prints the usage statement
        /// </summary>
        private void PrintUsage()
        {
            Console.WriteLine("dotnet run <bits> <count=1>");
            Console.WriteLine("\t- bits - the number of bits of the prime number, this must be a");
            Console.WriteLine("\t  multiple of 8, and at least 32 bits.");
            Console.WriteLine("\t- count - the number of prime numbers to generate, defaults to 1");
        }

        /// <summary>
        /// Given a number of bits, returns all primes lesser than or equal to bits
        /// </summary>
        /// <param name="bits">int number of bits</param>
        /// <returns>List of prime ints less than or equal to bits</returns>
        public List<int> SieveOfEratosthenes(int bits)
        {
            Boolean[] primes = new bool[bits + 1];

            for (int i = 0; i < bits; i++)
            {
                primes[i] = true;
            }

            for (int j = 2; j * j <= bits; j++)
            {
                if (primes[j] == true)
                {
                    for (int i = j * j; i <= bits; i += j)
                    {
                        primes[i] = false;
                    }
                }
            }

            List<int> checks = new List<int>();

            for (int i = 2; i <= bits; i++)
            {
                if (primes[i] == true)
                {
                    checks.Add(i);
                }
            }
            return checks;
        }
    }

    /// <summary>
    /// Class containing utility functions for generating prime numbers
    /// </summary>
    public class PrimeGen
    {
        /// <summary>
        /// Pre-checks for prime numbers before they enter IsProbablyPrime
        /// </summary>
        /// <param name="checks">List of int primes to check against</param>
        /// <param name="bigInt">BigInteger prime to check for</param>
        /// <returns>Boolean true if prime, false if not</returns>
        private Boolean PreCheck(List<int> checks, BigInteger bigInteger)
        {
            var sqrt = Math.Pow(((double)bigInteger), 0.5);

            foreach (var item in checks)
            {
                if (item > sqrt || bigInteger % item == 0)
                {
                    return false;
                }
            }
            return true;
        }

        /// <summary>
        /// Generates a random BigInteger of bits length
        /// </summary>
        /// <param name="bits">bitlength of BigInteger generated</param>
        /// <returns>a random BigInteger</returns>
        private BigInteger GenerateRandomBigInt(int bits)
        {
            var rng = RandomNumberGenerator.Create();
            byte[] byteList = new byte[bits / 8];
            rng.GetBytes(byteList);
            return new BigInteger(byteList, true);
        }

        /// <summary>
        /// Creates an infinitely enumerable object to be used in the
        /// parallel for each loop later
        /// </summary>
        /// <returns>True</returns>
        private IEnumerable<bool> InfiniteEnum()
        {
            while (true)
            {
                yield return true;
            }
        }

        /// <summary>
        /// Generates count primes each bits bitlength
        /// </summary>
        /// <param name="bits">int bitlength of each prime generated</param>
        /// <param name="count">int number of primes to generate</param>
        public void GeneratePrimes(int bits, int count, List<int> checks)
        {
            Object stateLock = new Object();
            var numPrimes = 0;
            HashSet<BigInteger> primes = new HashSet<BigInteger>();

            // code taken from Microsoft Documentation
            CancellationTokenSource cts = new CancellationTokenSource();
            ParallelOptions po = new ParallelOptions();
            po.CancellationToken = cts.Token;

            Parallel.ForEach(InfiniteEnum(), po, (inf, state) =>
            {
                BigInteger bigInt = GenerateRandomBigInt(bits);

                if (PreCheck(checks, bigInt))
                {
                    if (numPrimes >= count)
                    {
                        state.Stop();
                        po.CancellationToken.ThrowIfCancellationRequested();
                    }

                    if (bigInt.IsProbablyPrime())
                    {
                        lock (stateLock)
                        {
                            if (numPrimes < count)
                            {
                                numPrimes++;
                                if (numPrimes != count)
                                {
                                    Console.WriteLine(numPrimes + ": " + bigInt+ "\n");
                                }
                                else
                                {
                                    Console.WriteLine(numPrimes + ": " + bigInt);
                                }
                            }
                            else
                            {
                                state.Stop();
                                po.CancellationToken.ThrowIfCancellationRequested();
                            }
                        }
                    }
                }
            });
        }
    }
}

/// <summary>
/// Contains Extension methods used by PrimeGen
/// </summary>
namespace Extensions
{
    /// <summary>
    /// Class containing extension method to check if a number is prime
    /// </summary>
    public static class BigIntegerExtension
    {
        /// <summary>
        /// Class containing extension method to check if a number is prime
        /// </summary>
        /// <param name="value">BigInteger value to check if prime</param>
        /// <param name="rounds">int rounds for checking primality</param>
        /// <returns>Boolean true if value is prime, false if not</returns>
        public static Boolean IsProbablyPrime(this BigInteger value, int rounds = 10)
        {
            BigInteger d = value - 1;
            int s = 0;

            while (d % 2 == 0)
            {
                d /= 2;
                s += 1;
            }

            Byte[] bytes = new Byte[value.ToByteArray().LongLength];

            BigInteger a;

            for (int i = 0; i < rounds; i++)
            {
                var Gen = new Random();
                do
                {
                    Gen.NextBytes(bytes);
                    a = new BigInteger(bytes);
                } while (a < 2 || a >= value - 2);

                BigInteger x = BigInteger.ModPow(a, d, value);

                if (x == 1 || x == (value - 1))
                {
                    continue;
                }

                for (int r = 1; r < s; r++)
                {
                    x = BigInteger.ModPow(x, 2, value);

                    if (x == 1)
                    {
                        return false;
                    }
                    if (x == value - 1)
                    {
                        break;
                    }
                }

                if (x != (value - 1))
                {
                    return false;
                }
            }
            return true;
        }
    }
}