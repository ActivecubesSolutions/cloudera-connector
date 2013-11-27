## Qlikview Cloudera Connector

Qlikview is a data visualization tool that allows users to pull in data from all over the enterprise to be sliced and diced. Qlikview has a custom data connector SDK that makes it possible to bring external data into their product.

Our goal is to write a connector that can get data from Cloudera (Hadoop) and pull it into Qlikview.

### Fetching Data

Qlikview is a .NET app, so the SDK is .NET-based. Cloudera and Hadoop, on the other hand, are Java-based. 

Our architecture is simple: we have C# code that gets called through the SDK and that turns around and uses a bridge to invoke Java code, which is the JDBC driver to talk to the remote data source (right now either Hive or Impala).

This part is all working.

The remaining part is processing the result sets.

### Processing Results

Qlikview talks to the custom data source through a command pipe, which is a named pipe. The protocol is documented. The results are then pushed into another named pipe. The format of these results is a format called QVX, which is basically an XML metadata header with the basic data in binary format.


Relevant Links:

* [QVX File Format Documentation][1]
* [Interesting discussion of QVX and Named Pipes][2]


[1]: https://www.dropbox.com/l/BOrkyrnnsnJqYmBA5IMAGe
[2]: http://community.qlikview.com/docs/DOC-2677