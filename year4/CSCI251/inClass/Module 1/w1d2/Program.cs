// /// @author: Alex Iacob ai9388
// using System;
// using System.IO;

// namespace w1d2
// {
//     public class DirThing
//     {
//         /// <summary>
//         /// 
//         /// </summary>
//         /// <param name="args"></param>
//         public static void Main(string[] args)
//         {
//             var start = DateTime.Now;
//             var di = Directory.GetFiles(GetCommandLineArgs[0]);
//             foreach (var d in di)
//             {
//                 var f = new FileInfo(d);
//                 f.Refresh();
                
//                 if ((f.LastWriteTime < DateTime.Now.AddYears(-1)))
//                 {
//                     var fileTime = f.LastWriteTime.ToString("yyyy MMM dd");
//                     Console.WriteLine("{0, 10} {1}  {2}", f.Length, fileTime, f);
//                 }
//                 else
//                 {
//                     var fileTime = f.LastWriteTime.ToString("MMM dd HH:mm");
//                     Console.WriteLine("{0, 10} {1} {2}", f.Length, fileTime, f);
//                 }
//             }

//             var end = DateTime.Now;
//             Console.WriteLine(end-start);
//             Console.ReadKey();

//         }
//     }    
// }
