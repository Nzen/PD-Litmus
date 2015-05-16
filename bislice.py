
class qN(object) :
	def __init__(self, low_lim, difference) :
		self.start = low_lim
		self.dist = difference
	def pr( self ) :
		print "s%d-d%d  " % ( self.start, self.dist ),

class queue( object ) :
	def __init__( self, firstNode ) :
		self.ll = [ firstNode ]
	def gFirst( self ) :
		return self.ll.pop()
	def sLast(  self, node ) :
		self.ll.append( node )
	def containsAnElement( self ) :
		return len(self.ll)
	def pr(self) :
		print "::-",
		for nn in self.ll :
			nn.pr()
		print

class result( object ) :
	def __init__( self, equals, qq ) :
		self.answer = equals
		self.que = qq

def next( queu ) :
	answer = 0
	temp = queu.gFirst()
	print "n t.s %d t.d %d" % ( temp.start, temp.dist )
	if temp.dist < 1 :
		answer = temp.start
		print "- ans %d" % ( answer ) # 4TESTS
	else :
		answer = temp.dist / 2 + temp.start
		print ") ans %d" % ( answer ) # 4TESTS
		
		fin = temp.start +temp.dist -1
		dist_L = answer -temp.start -1
		dist_R = fin -answer -1
		
		#print "L sl.s %d sl.d %d" % ( temp.start, dist_L ) # 4TESTS
		queu.sLast(qN( temp.start, dist_L ))
		
		#print "R sl.s %d sl.d %d" % ( answer +1, dist_R ) # 4TESTS
		queu.sLast(qN( answer +1, dist_R ))
	return result( answer, queu )

def slice(start, end) :
	qu = queue(qN( start, end -1 ))
	while qu.containsAnElement() :
		intermed = next( qu )
		#print ">%d" % (intermed.answer), #4real, but I'm already printing everything else
		qu = intermed.que
		qu.pr()
		raw_input()

ending = 10
print "begin s1,e%d" % (ending)
slice(1, ending)
"""
fn(qu)
  answer;
  temp = qu.gFirst()
  if temp.dist < 1
	answer = qu.start
  else
    answer = temp.dist/2 + start // may be off and needs floor
    qu.sLast( new qN(temp.start, temp.dist-answer) ) // FIX
	qu.sLast( new qN(answer +1, temp.start + dist - answer) ) // CHECK
  return new result( answer, qu )

bislice( start, end )
  new qu( new qN(start, end -start) )
  while qu.containsAnElement()
    intermed = fn(qu)
	print intermed.answer
	qu = intermed.q

 * you may ask yourself, why distance? I say, my space is so big,
	I don't want to hold the leaves or full graph, like a bst would
	which is a bit of a cheat, given that N^ is my 'graph'

====
	CURRENT

begin s1,e10
n t.s 1 t.d 9
) ans 5
::- s1-d3   s6-d3

n t.s 6 t.d 3
) ans 7
::- s1-d3   s6-d0   s8-d0

n t.s 8 t.d 0
- ans 8
::- s1-d3   s6-d0

n t.s 6 t.d 0
- ans 6
::- s1-d3

n t.s 1 t.d 3
) ans 2
::- s1-d0   s3-d0

n t.s 3 t.d 0
- ans 3
::- s1-d0

n t.s 1 t.d 0
- ans 1
::-
--

  breadth first search
1  procedure BFS(G,v) is
2      let Q be a queue
3      Q.enqueue(v)
4      label v as discovered
5      while Q is not empty
6         v <- Q.dequeue()
7         for all edges from v to w in G.adjacentEdges(v) do
8             if w is not labeled as discovered
9                 Q.enqueue(w)
10                label w as discovered

The non-recursive implementation is similar to depth-first search but differs from it in two ways: it uses a queue instead of a stack, and it checks whether a vertex has been discovered before enqueueing the vertex rather than delaying this check until the vertex is dequeued from the queue.

;;
In order to print the binary tree in level order with newline in the end of each level, we can utilize two queues. The first queue stores the current level's nodes, while the second queue stores the next level's nodes (the current level nodes' children).
,
Is it possible that a solution exists using only one single queue? Yes, you bet. The single queue solution requires two extra counting variables which keep tracks of the number of nodes in the current level (nodesInCurrentLevel) and the next level (nodesInNextLevel). When we pop a node off the queue, we decrement nodesInCurrentLevel by one. When we push its child nodes to the queue, we increment nodesInNextLevel by two. When nodesInCurrentLevel reaches 0, we know that the current level has ended, therefore we print an endline here.

"""