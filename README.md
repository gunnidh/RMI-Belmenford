Let's assume we want to find the shortest paths from node 0 to all other nodes in the graph.

1. Distributed Synchronous Version:

In the synchronous version, the algorithm updates distances in parallel during each iteration. Here's a step-by-step breakdown:

Initialize distances: distances = [0, ∞, ∞, ∞, ∞, ∞]
Iteration 1: Update distances after considering edges from node 0 (0 -> 1, 0 -> 3)
distances = [0, 1, ∞, 4, ∞, ∞]
Iteration 2: Update distances after considering edges from nodes 1, 3, 4
distances = [0, 1, 4, 4, 6, ∞]
Iteration 3: Update distances after considering edges from nodes 1, 2, 3, 4, 5
distances = [0, 1, 4, 4, 6, 9]
Iteration 4: No changes, algorithm terminates.
2. Distributed Asynchronous Version:

In the asynchronous version, each node updates its distance whenever it receives new information from its neighbors. Here's a step-by-step breakdown:

Initialize distances: distances = [0, ∞, ∞, ∞, ∞, ∞]
Iteration 1:
Node 0 updates distances (0 -> 1, 0 -> 3)
distances = [0, 1, ∞, 4, ∞, ∞]
Node 1 updates distances (1 -> 2, 1 -> 3, 1 -> 4)
distances = [0, 1, 4, 3, 5, ∞]
Node 3 updates distances (3 -> 4)
distances = [0, 1, 4, 3, 5, ∞]
Node 4 updates distances (4 -> 5)
distances = [0, 1, 4, 3, 5, 7]
Iteration 2:
Node 0 updates distances (0 -> 1, 0 -> 3)
distances = [0, 1, 4, 3, 5, 7]
Node 1 updates distances (1 -> 2, 1 -> 3, 1 -> 4)
distances = [0, 1, 4, 3, 5, 7]
Node 3 updates distances (3 -> 4)
distances = [0, 1, 4, 3, 5, 7]
Node 4 updates distances (4 -> 5)
distances = [0, 1, 4, 3, 5, 7]
Iterations continue until no updates are made.
At the end of both versions, you'll have the shortest paths from node 0 to all other nodes in the graph. The synchronous version converges after a fixed number of iterations, while the asynchronous version converges when no more updates are being made by any node.




Compiling of code
javac DistributedBellmanFordSynchronous.java
javac DistributedBellmanFordAsynchronous.java

Running of the code:
java DistributedBellmanFordSynchronous
java DistributedBellmanFordAsynchronous
