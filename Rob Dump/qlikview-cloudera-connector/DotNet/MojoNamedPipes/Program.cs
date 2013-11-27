using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.IO.Pipes;

namespace MojoNamedPipes
{
    class Program
    {
        static void Main(string[] args)
        {
            while (true)
            {
                //Create pipe instance 
                NamedPipeServerStream pipeServer =
                new NamedPipeServerStream("mojotestpipe", PipeDirection.InOut, 4);
                Console.WriteLine(String.Format("[DOTNET] {0} NamedPipeServerStream thread created.", DateTime.Now.ToString("T")));

                //wait for connection 
                Console.WriteLine(String.Format("[DOTNET] {0} Wait for a client to connect...", DateTime.Now.ToString("T")));
                pipeServer.WaitForConnection();

                Console.WriteLine(String.Format("[DOTNET] {0} Client connected.", DateTime.Now.ToString("T")));
                try
                {
                    // Stream for the request.  
                    StreamReader sr = new StreamReader(pipeServer);
                    // Stream for the response.  
                    StreamWriter sw = new StreamWriter(pipeServer);
                    sw.AutoFlush = true;

                    // Read request from the stream.  
                    string echo = sr.ReadLine();

                    Console.WriteLine(String.Format("[DOTNET] {0} Request message: {1}", DateTime.Now.ToString("T"), echo));

                    // Write response to the stream. 
                    sw.WriteLine("[ECHO]: " + echo);

                    pipeServer.Disconnect();
                }
                catch (IOException e)
                {
                    Console.WriteLine(String.Format("[DOTNET] {0} Error: {1}", DateTime.Now.ToString("T"), e.Message));
                }
                pipeServer.Close();
            }  
        }
    }
}
