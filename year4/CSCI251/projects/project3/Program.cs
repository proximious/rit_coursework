/// <summary>
/// @author: Alex Iacob ai9388
/// @filename: Program.cs
///
/// Main class runner for CSCI 251 project 3 : Secure Messaging
/// </summary>

using System.Text.Json;
using System.Numerics;  
using System.Text;

/// <summary>
/// Contains the Program and Messenger classes
/// </summary>
namespace Messenger
{
    /// <summary>
    /// Class containing functions to parse user input, run the program,
    /// and print the usage statement
    /// </summary>
    class Program
    {
        /// <summary>
        /// Parses user input and calls all other functions
        /// </summary>
        /// <param name="args">string array of command line arguments</param>
        /// <returns>async Task when all async HTTP functions are complete</returns>
        static async Task Main(string[] args)
        {
            Program program = new Program();
            if (args.Length != 2 && args.Length != 3)
            {
                program.PrintUsage();
                return;
            }

            Messenger messenger = new Messenger();

            var arg = args[0];

            if (arg == "keyGen" && args.Length == 2)
            {
                int keysize = 0;
                try
                {
                    keysize = Int32.Parse(args[1]);
                }
                catch
                {
                    program.PrintUsage();
                }
                messenger.KeyGen(keysize);
            }
            else if (arg == "sendKey" && args.Length == 2)
            {
                await messenger.SendKey(args[1]);
            } 
            else if (arg == "getKey" && args.Length == 2)
            {
                await messenger.GetKey(args[1]);
            }
            else if (arg == "sendMsg" && args.Length == 3)
            {
                await messenger.SendMsg(args[1], args[2]);
            }
            else if (arg == "getMsg" && args.Length == 2)
            {
                await messenger.GetMsg(args[1]);
            }
            else
            {
                program.PrintUsage();
                return;
            }
            return;
        }

        /// <summary>
        /// Prints the usage statement
        /// </summary>
        private void PrintUsage()
        {
            Console.WriteLine("dotnet run <option> <other arguments>");
            Console.WriteLine("\t- keyGen keysize - generate a keypair of size keysize bits");
            Console.WriteLine("\t- sendKey email - sends public key in public.key to the email");
            Console.WriteLine("\t- getKey email - gets the public key of the email");
            Console.WriteLine("\t- sendMsg email plaintext - encrypts plaintext message and sends it to the email");
            Console.WriteLine("\t- getMsg email - decrypts the encrypted message sent to the email and prints it");
        }
    }

    /// <summary>
    /// Class containing functions to generate a key, send a key
    /// over a client, get a key, send a message, and get a message
    /// </summary>
    class Messenger
    {
        private HttpClient client = new HttpClient();
        private PrivateKey privateKey = new PrivateKey();
        private PublicKey publicKey = new PublicKey();
        public String KeyURL = "http://kayrun.cs.rit.edu:5000/Key/";
        public String MessageURL = "http://kayrun.cs.rit.edu:5000/Message/";
        public String PvtKey = "private.key";
        public String PubKey = "public.key";

        /// <summary>
        /// Serializable class to represent a PrivateKey
        /// </summary>
        class PrivateKey
        {
            public List<String>? email { get; set; }
            public String? key { get; set; }
        }

        /// <summary>
        /// Serializable class to represent a PublicKey
        /// </summary>
        class PublicKey
        {
            public String? email { get; set; }
            public String? key { get; set; }
        }

        /// <summary>
        /// Serializable class to represent a Message
        /// </summary>
        class Message
        {
            public String? email { get; set; }
            public String? content { get; set; }
        }

        /// <summary>
        /// Get the mod inverse of two BigIntegers
        /// Code taken from Prof. Brown
        /// </summary>
        /// <param name="a">BigInteger</param>
        /// <param name="n">BigInteger</param>
        /// <returns>mod inverse of a and n</returns>
        static BigInteger modInverse(BigInteger a, BigInteger n)
        {
            BigInteger i = n, v = 0, d = 1;
            while (a > 0)
            {
                BigInteger t = i / a, x = a;
                a = i % x;
                i = x;
                x = d;
                d = v - t * x;
                v = x;
            }
            v %= n;
            if (v < 0) v = (v + n) % n;
            return v;
        }

        /// <summary>
        /// Generates a public and private key and stores them in files
        /// </summary>
        /// <param name="keysize">bitlength of keys
        /// </param>
        public void KeyGen(int keysize)
        {
            if (keysize < 1024 || keysize % 2 == 1)
            {
                Console.WriteLine("Keysize must be an even number above 1024");
                return;
            }

            var checks = new PrimeGen.Program().SieveOfEratosthenes(keysize);
            BigInteger[] primes = new PrimeGen.PrimeGen().GeneratePrimes(keysize / 2, 2, checks);
            var p = primes[0];
            var q = primes[1];
            var N = p * q;
            var r = (p - 1) * (q - 1);
            BigInteger E = 65537;
            var D = modInverse(E, r);
            
            var e = BitConverter.GetBytes(E.GetByteCount());
            var d = BitConverter.GetBytes(D.GetByteCount());
            var n = BitConverter.GetBytes(N.GetByteCount());

            if (BitConverter.IsLittleEndian)
            {
                Array.Reverse(e);
                Array.Reverse(d);
                Array.Reverse(n);
            }

            var publicKeyByteArray = e.Concat(E.ToByteArray().Concat(n).Concat(N.ToByteArray())).ToArray();
            this.publicKey = new PublicKey();
            this.publicKey.email = "";
            this.publicKey.key = Convert.ToBase64String(publicKeyByteArray);
            File.WriteAllText(PubKey, JsonSerializer.Serialize(this.publicKey));

            var privateKeyByteArray = d.Concat(D.ToByteArray().Concat(n).Concat(N.ToByteArray())).ToArray();
            this.privateKey = new PrivateKey();
            this.privateKey.email = new List<String>();
            this.privateKey.key = Convert.ToBase64String(privateKeyByteArray);
            File.WriteAllText(PvtKey, JsonSerializer.Serialize(this.privateKey));
        }

        /// <summary>
        /// Sends my public key to an email on the client
        /// </summary>
        /// <param name="email">String the email to send the public key to</param>
        /// <returns>async Task</returns>
        public async Task SendKey(String email)
        {
            try
            {
                if (File.Exists(PubKey) && File.Exists(PvtKey))
                {
                    this.publicKey = deserializePublicKey();
                    this.publicKey.email = email;
                    var publicKeyJSON = JsonSerializer.Serialize(this.publicKey);
                    var content = new StringContent(publicKeyJSON.ToString(), Encoding.UTF8, "application/json");

                    this.privateKey = deserializePrivateKey();

                    var url = KeyURL + email;

                    try
                    {
                        var response = await client.PutAsync(url, content);
                    }
                    catch(Exception)
                    {
                        Console.WriteLine("Error with awaiting client");
                    }
                    finally
                    {
                        Console.WriteLine("Key saved");
                        
                        if (!this.privateKey.email.Contains(email))
                        {
                            this.privateKey.email.Add(email);
                        }
                        File.WriteAllText(PvtKey, JsonSerializer.Serialize(this.privateKey));

                        client.Dispose();
                    }
                }
                else
                {
                    Console.WriteLine("public/private key does not exist.");
                }
            }
            catch
            {
                Console.WriteLine("public/private key does not exist.");
                return;
            }
        }

        /// <summary>
        /// Gets a public key from an email
        /// </summary>
        /// <param name="email">The email to get the public key from</param>
        /// <returns>async Task</returns>
        public async Task GetKey(String email)
        {
            try
            {
                var url = KeyURL + email;
                var response = await client.GetAsync(url);

                if (response.EnsureSuccessStatusCode().IsSuccessStatusCode)
                {
                    try
                    {
                        var res = JsonDocument.Parse(await response.Content.ReadAsStringAsync());
                        var JSON = JsonSerializer.Serialize(res.RootElement);
                        PublicKey newPublicKey = JsonSerializer.Deserialize<PublicKey>(JSON);
                        var fileName = email + ".key";
                        File.WriteAllText(fileName, JsonSerializer.Serialize(newPublicKey));
                    }
                    catch
                    {
                        Console.WriteLine("Error with writing data to file");
                    }
                    finally
                    {
                        client.Dispose();
                    }
                }
                else
                {
                    Console.WriteLine("Client/file error");
                    return;
                }
            }
            catch
            {
                Console.WriteLine("Client/file error");
                return;
            }
        }

        /// <summary>
        /// Encrypts and sends a plaintext message to the client
        /// </summary>
        /// <param name="email">The email to send the encypted message to</param>
        /// <param name="plaintext">The message to encrypt</param>
        /// <returns>async Task</returns>
        public async Task SendMsg(String email, String plaintext)
        {
            try
            {
                var emailKeyFile = email + ".key";
                if (File.Exists(emailKeyFile))
                {
                    var newPublicBytes = File.ReadAllBytes(emailKeyFile);
                    var newPublicJSON = Encoding.UTF8.GetString(newPublicBytes);
                    PublicKey newPublicKey = JsonSerializer.Deserialize<PublicKey>(newPublicJSON);

                    var prep = PrepareKeys(newPublicKey.key);
                    var E = prep[0];
                    var N = prep[1];
                    var plaintextBigInt = new BigInteger(Encoding.UTF8.GetBytes(plaintext));

                    Message msg = new Message();
                    msg.email = email;
                    msg.content = Convert.ToBase64String(BigInteger.ModPow(plaintextBigInt, E, N).ToByteArray());
                    
                    var jsonMsg = JsonSerializer.Serialize(msg);
                    var content = new StringContent(jsonMsg.ToString(), Encoding.UTF8, "application/json");
                    var url = MessageURL + email;
                    var response = await client.PutAsync(url, content);
                    
                    if (response.EnsureSuccessStatusCode().IsSuccessStatusCode)
                    {
                        Console.WriteLine("Message written");
                        client.Dispose();
                    }
                    else
                    {
                        Console.WriteLine("Client error");
                        return;
                    }
                }
                else
                {
                    Console.WriteLine("Key does not exist for " + email);
                }
            }
            catch
            {
                Console.WriteLine("Key does not exist for " + email);
                return;
            }
        }

        /// <summary>
        /// Gets an encrypted message from an email on the client and decrypts it
        /// before printing the decrypted message
        /// </summary>
        /// <param name="email">Receiving email</param>
        /// <returns>async Task</returns>
        public async Task GetMsg(String email)
        {
            try
            {
                this.privateKey = deserializePrivateKey();

                if (this.privateKey.email.Contains(email))
                {
                    var url = MessageURL + email;
                    var response = await client.GetAsync(url);
                    var encryptedText = JsonSerializer.Deserialize<Message>(await response.Content.ReadAsStringAsync()).content;
                    var prep = PrepareKeys(this.privateKey.key);
                    var D = prep[0];
                    var N = prep[1];
                    try
                    {
                        var encryptedBigInt = new BigInteger(Convert.FromBase64String(encryptedText));
                        var decryptedBigInt = BigInteger.ModPow(encryptedBigInt, D, N);
                        Console.WriteLine(Encoding.UTF8.GetString(decryptedBigInt.ToByteArray()));
                    }
                    catch
                    {
                        Console.WriteLine("Error with encoding the decrypted BigInt");
                    }
                    finally
                    {
                        client.Dispose();
                    }
                }
                else
                {
                    Console.WriteLine("Key does not exist for " + email);
                }
            }
            catch
            {
                Console.WriteLine("Key does not exist for " + email);
                return;
            }
        }

        /// <summary>
        /// Gets E/N for public keys, D/N for private keys for encrypting/decrypting
        /// </summary>
        /// <param name="key">String key to prepare for encrypting/descrypting</param>
        /// <returns>async Task</returns>
        private List<BigInteger> PrepareKeys(String key)
        {
            var byteArray = Convert.FromBase64String(key);
            var pByteArray = byteArray.Take(4).ToArray();
            if (BitConverter.IsLittleEndian)
            {
                Array.Reverse(pByteArray);
            }
            var p = BitConverter.ToInt32(pByteArray);
            var P = new BigInteger(byteArray.Skip(4).Take(p).ToArray());
            var nByteArray = byteArray.Skip(4 + p).Take(4).ToArray();
            if (BitConverter.IsLittleEndian)
            {
                Array.Reverse(nByteArray);
            }
            var n = BitConverter.ToInt32(nByteArray);
            var N = new BigInteger(byteArray.Skip(8 + p).Take(n).ToArray());
            List<BigInteger> ret = new List<BigInteger>();
            ret.Add(P);
            ret.Add(N);
            return ret;
        }

        /// <summary>
        /// Deserializes the public key by reading in the bytes then encoding the bytes
        /// </summary>
        /// <returns>PublicKey object</returns>
        private PublicKey deserializePublicKey()
        {
            var publicBytes = File.ReadAllBytes(PubKey);
            var publicJSON = Encoding.UTF8.GetString(publicBytes);
            return JsonSerializer.Deserialize<PublicKey>(publicJSON);
        }

        private PrivateKey deserializePrivateKey()
        {
            var privateBytes = File.ReadAllBytes(PvtKey);
            var privateJSON = Encoding.UTF8.GetString(privateBytes);
            return JsonSerializer.Deserialize<PrivateKey>(privateJSON);
        }
    }
}