
class qN(object) :
	def __init__(self, low_lim, upper_bound) :
		self.start = low_lim
		self.fin = upper_bound
	def pr( self ) :
		print "s%d-d%d " % ( self.start, self.fin ),

class queue( object ) :
	def __init__( self, firstNode ) :
		self.ll = [ firstNode ]
	def gFirst( self ) :
		return self.ll.pop(0) # wow. no arg is last, not first
	def sLast(  self, node ) :
		self.ll.append( node )
	def containsAnElement( self ) :
		return len(self.ll)
	def pr(self) :
		print "  ::-",
		for nn in self.ll :
			nn.pr()
		print

# calculates one at a time
def mandolin( qu ) :
	# I'll assume client checks qu.containsAnElement() :
	#qu.pr() # 4TESTS
	intermed = qu.gFirst()
	if intermed.start >= intermed.fin :
		return intermed.fin, qu
	sta = intermed.start
	end = intermed.fin
	dist = end - sta
	if dist is 1 :
		qu.sLast( qN(sta, sta) )
		return end, qu
	if dist is 2 :
		qu.sLast( qN(sta, sta) )
		qu.sLast( qN(end, end) )
		return sta +1, qu # mid
	else :
		mid = dist / 2 + sta
		qu.sLast( qN(mid +1, end) )
		qu.sLast( qN(sta, mid -1) )
		return mid, qu

# NOTE exponential precalculation and storage, not good in my use case
def slice(start, end) :
	qu = queue( qN(start, end) )
	while qu.containsAnElement() :
		qu.pr() # 4TESTS
		intermed = qu.gFirst()
		if intermed.start >= intermed.fin :
			print "> %d\n" % ( intermed.start )
			continue # else
		st = intermed.start
		en = intermed.fin
		dist = en - st
		if dist is 1 :
			#print "\tst %d en %d" % (st, en)
			qu.sLast( qN(st, st) )
			qu.sLast( qN(en, en) )
		if dist is 2 :
			#print "\tst %d mid %d en %d" % (st, st +1, en)
			qu.sLast( qN(st +1, st +1) ) # mid
			qu.sLast( qN(st, st) )
			qu.sLast( qN(en, en) )
		else :
			mid = dist / 2 + st
			#print "\tst %d mid %d en %d" % (st, mid, en)
			qu.sLast( qN(mid, mid) )
			qu.sLast( qN(mid +1, en) )
			qu.sLast( qN(st, mid -1) )

def run_basic() :
	starter = 1
	ending = 15
	print "begin s1,e%d" % (ending)
	# slice(starter, ending)
	val = -1
	ku = queue( qN(starter, ending) )
	while ku.containsAnElement() :
		val,ku = mandolin(ku)
		raw_input( val )

# cause they make triple blades :B
def gilette() :
	low = 0
	hi = 255
	red = queue( qN(low, hi) )
	lue = queue( qN(low, hi) )
	een = queue( qN(low, hi) )
	rr = low
	bb = low
	gr = low
	while red.containsAnElement() :
		rr,red = mandolin(red)
		bb,lue = mandolin(lue)
		gr,een = mandolin(een)
		raw_input( str(rr) +" "+ str(bb) +" "+ str(gr) )

# specify here, as an inner class or object fields
def gap( val ) :
	if val <= 7 or val >= 12 :
		return val
	else :
		return -1

# this is how I'll avoid the background color
def chinked_blade() :
	starter = 1
	ending = 15
	print "begin s1,e%d" % (ending)
	# slice(starter, ending)
	val = -1
	ku = queue( qN(starter, ending) )
	while ku.containsAnElement() :
		val,ku = mandolin(ku)
		if gap( val ) < 0 :
			continue
		raw_input( val )

#run_basic()
#gilette()
chinked_blade()
"""
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
"""