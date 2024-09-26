/// <summary>
/// @author: Alex Iacob ai9388
/// @filename: Program.cs
///
/// Main class runner for CSCI 251 project 1: Disk Usage
/// </summary>

using System;
using System.Diagnostics;
using System.Threading.Tasks;
using System.Threading;
using System.IO;

class Program
{
    private int folderCount = 0;
    private int fileCount = 0;
    private long byteCount = 0;

    /// <summary>
    /// Gets the folder count
    /// </summary>
    ///  <returns> The number of folders </returns>
    public int getFolderCount()
    {
        return folderCount;
    }

    /// <summary>
    /// Gets the file count
    /// </summary>
    /// <returns> The number of files </returns>
    public int getFileCount()
    {
        return fileCount;
    }

    /// <summary>
    /// Gets the byte count
    /// </summary>
    /// <returns> The number of bytes </returns>
    public long getByteCount()
    {
        return byteCount;
    }

    /// <summary>
    /// Prints the usage statement for the user when they provide wrong command
    /// line arguments
    /// </summary>
    public void displayHelp()
    {
        Console.WriteLine("Usage: du [-s] [-p] [-b] <path>");
        Console.WriteLine("Summarize disk usage of the set of FILES, recursively for directories.");
        Console.WriteLine("You MUST specify one of the parameters, -s, -p, or -b");
        Console.WriteLine("-s\tRun in single threaded mode");
        Console.WriteLine("-p\tRun in parallel mode (uses all available processors)");
        Console.WriteLine("-b\tRun in both parallel and single threaded mode.");
        Console.WriteLine("\tRuns parallel followed by sequential mode");
    }

    /// <summary>
    /// Resets all of the counts to 0 to be able to run program again
    /// </summary>
    public void resetCounts()
    {
        folderCount = 0;
        fileCount = 0;
        byteCount = 0;
    }

    /// <summary>
    /// Prints the results of the program via the global variables and allows for
    /// usage on both single threaded and multithreaded running
    /// </summary>
    /// <param name="stopWatchTime"> The run time in seconds </param>
    /// <param name="runType"> The running type, either Sequential or Parallel </param>    
    public void printFormattedResults(double stopWatchTime, string runType)
    {
            string folderCt = string.Format("{0:#,###0}", getFolderCount());
            string fileCt = string.Format("{0:#,###0}", getFileCount());
            string byteCt = string.Format("{0:#,###0}", getByteCount());
            
            Console.WriteLine(runType + " Calculated in: " + stopWatchTime + "s");
    
            Console.WriteLine(folderCt + " folders, " + fileCt + " files, " + 
            byteCt + " bytes\n");
    }

    /// <summary>
    /// Runs through the directory and calculates the folder count, file count,
    /// and byte count to update the global variables
    ///
    /// Loop through all of the files in the root directory and count the number 
    /// of folders/subfolders in the root's directory
    ///
    /// After that loop finished, we have to go in and get the total number of bytes
    /// and number of files in the directory
    /// </summary>
    /// <param name="root"> The main root that we are heading down to run calculations </param>
    public void singleThreaded(DirectoryInfo root)
    {
        var files = root.GetFiles();
        var folders = root.GetDirectories();

        foreach(var file in files)
        {
            try 
            {
                FileInfo currentFile = new FileInfo(file.ToString());
                currentFile.Refresh();

                byteCount += currentFile.Length;
                fileCount++;
            } 
            catch (UnauthorizedAccessException)
            {
                // ignoring the exception
            }
            catch (DirectoryNotFoundException)
            {
                // ignoring the exception
            }
            catch (IOException)
            {
                // ignoring the exception
            }
        } 

        foreach(var folder in folders)
        {
            try 
            {
                DirectoryInfo newFolder = new DirectoryInfo(folder.ToString());
                newFolder.Refresh();

                folderCount++;
                singleThreaded(newFolder);
            }
            catch (UnauthorizedAccessException)
            {
                // ignoring the exception
            }
            catch (DirectoryNotFoundException)
            {
                // ignoring the exception
            }
            catch (IOException)
            {
                // ignoring the exception
            }
        }
    }

    /// <summary>
    /// Runs through the directory and calculates the folder count, file count,
    /// and byte count to update the global variables
    /// Uses Parallel.foreach loops to achieve the multithreaded capabilities
    /// </summary>
    /// <param name="root"> The main root that we are heading down to run calculations </param>
    public void multiThreaded(DirectoryInfo root)
    {
        var files = root.GetFiles();
        var folders = root.GetDirectories();

        Parallel.ForEach(files, file => 
        {
            try
            {
                FileInfo newFile = new FileInfo(file.ToString());
                newFile.Refresh();

                Interlocked.Add(ref byteCount, newFile.Length);
                Interlocked.Increment(ref fileCount);
            }
            catch (UnauthorizedAccessException)
            {
                // ignoring the exception
            }
            catch (DirectoryNotFoundException)
            {
                // ignoring the exception
            }
            catch (IOException)
            {
                // ignoring the exception
            }
        });

        Parallel.ForEach(folders, folder =>
        {
            try
            {
                var newFolder = new DirectoryInfo(folder.ToString());
                newFolder.Refresh();

                Interlocked.Increment(ref folderCount);
                multiThreaded(newFolder);
            }
            catch (UnauthorizedAccessException)
            {
                // ignoring the exception
            }
            catch (DirectoryNotFoundException)
            {
                // ignoring the exception
            }
            catch (IOException)
            {
                // ignoring the exception
            }
        });
    }

    /// <summary>
    /// Calls the single threaded program and prints the results after calculating 
    /// the folder count, file count, and byte count 
    ///
    /// Only uses one thread (performs sequentially)
    /// </summary>
    /// <param name="root"> the main root that we are heading down to run calculations </param>
    public void runSingleThreaded(DirectoryInfo root)
    {
        // creating stopwatch to get the correct timing
        var sw = new Stopwatch();
        sw.Start();
        singleThreaded(root);
        sw.Stop();

        // pretty print for the results
        printFormattedResults(sw.Elapsed.TotalSeconds, "Sequential");

        // resetting the stopwatch and the global variables
        sw.Reset();
        this.resetCounts();
    }

    /// <summary>
    /// Calls the multi threaded program and prints the results after calculating 
    /// the folder count, file count, and byte count 
    ///
    /// Uses multiple threads
    /// </summary>
    /// <param name="root"> the main root that we are heading down to run calculations </param>
    public void runMultiThreaded(DirectoryInfo root)
    {
        // creating stopwatch to get the correct timing
        var sw = new Stopwatch();
        sw.Start();
        multiThreaded(root);
        sw.Stop();

        // pretty print for the results
        printFormattedResults(sw.Elapsed.TotalSeconds, "Parallel");

        // resetting the stopwatch and the global variables
        sw.Reset();
        this.resetCounts();
    }

    /// <summary>
    /// Runs with:
    /// dotnet run -- [-s] [-p] [-b] <path>
    /// </summary>
    /// <param name = "args"> The system arguments</param>
    public static void Main(string[] args)
    {
        var program = new Program();
        
        if (args.Length == 2)
        {
            var mode = args[0];
            var root = new DirectoryInfo(args[1]); 

            Console.WriteLine("Directory " + args[1] + ":\n");

            if(mode == "-s")
            {
                program.runSingleThreaded(root);
            }
            else if(mode == "-p")
            {
                program.runMultiThreaded(root);
            }
            else if(mode == "-b")
            {
                program.runMultiThreaded(root);
                program.runSingleThreaded(root);
            }
            else
            {
                program.displayHelp();
            }
        }
    }
}