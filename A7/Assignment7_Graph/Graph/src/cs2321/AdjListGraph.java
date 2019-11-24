package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author Drew Rosales
 */
public class AdjListGraph<V, E> implements Graph<V, E> {
	private boolean isDirected;
	PositionalList<Vertex<V>> vertices= new DoublyLinkedList<Vertex<V>>();
	PositionalList<Edge<E>> edges = new DoublyLinkedList<Edge<E>>();
	
	//Inner class for inner vertices
	private class InnerVertex<V> implements Vertex<V>{
		private V element;
		private Position<Vertex<V>> pos;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
		
		public InnerVertex(V e, boolean graphIsDirected) {
			element = e;
			outgoing = new HashMap<>();
			if(graphIsDirected) {
				incoming = new HashMap<>();
			}else {
				incoming = outgoing;
			}
			
		}
		@TimeComplexity("O(1")
		public boolean validate(Graph<V,E> graph) {
			return (AdjListGraph.this == graph && pos != null);
		}
		@TimeComplexity("O(1")
		public void setElement(V v) {
			element = v;
		}
		@TimeComplexity("O(1")
		public V getElement() {
			return element;
		}
		@TimeComplexity("O(1")
		public void setPosition(Position<Vertex<V>> p) {
			pos = p;
		}
		@TimeComplexity("O(1")
		public Position<Vertex<V>> getPosition(){
			return pos;
		}
		
		@TimeComplexity("O(1")
		public Map<Vertex<V>, Edge<E>> getOutgoing(){
			return outgoing;
		}
		@TimeComplexity("O(1")
		public Map<Vertex<V>, Edge<E>> getIncoming(){
			return incoming;
		}
	}
	
	//
	private class InnerEdge<E> implements Edge<E>{
		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;
		
		public InnerEdge(Vertex<V> u, Vertex<V> v, E e) {
			element = e;
			endpoints = (Vertex<V>[]) new Vertex[]{u,v};
		}
		
		public E getElement() {
			return element;
		}
		public void setElement(E e) {
			element =e;
		}
		
		public Vertex<V> [] getEndpoints(){
			return endpoints;
		}
		
		public boolean validate(Graph<V,E> graph) {
			return AdjListGraph.this == graph && pos != null;
		}
		
		public void setPosition(Position<Edge<E>> p) {
			pos = p;
		}
		
		public Position<Edge<E>> getPosition(){
			return pos;
		}
	}
	public AdjListGraph(boolean directed) {
		isDirected = directed;
	}

	public AdjListGraph() {}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	public Iterable<Edge<E>> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		return edge.getEndpoints();
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		if(getEdge(u,v) == null) {
			InnerEdge<E> e = new InnerEdge<>(u, v, o);
			e.setPosition(edges.addLast(e));
			InnerVertex<V> initial = validate(u);
			InnerVertex<V> terminal = validate(v);
			initial.getOutgoing().put(v,e);
			terminal.getIncoming().put(u,e);
			return e;
		}else {
			throw new IllegalArgumentException("Edge from u to v exists");
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
		InnerVertex<V> v = new InnerVertex<>(o, isDirected);
		v.setPosition(vertices.addLast(v));
		return v;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	@TimeComplexity("O(1)")
	public int numEdges() {
		return edges.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	@TimeComplexity("O(1)")
	public int numVertices() {
		return vertices.size();
	}
	@TimeComplexity("O(1)")
	private InnerEdge<E> validate(Edge<E> e){
		if(!(e instanceof InnerEdge)){
			throw new IllegalArgumentException("Invalid edge");
		}
		InnerEdge<E> edge = (InnerEdge<E>) e;
		if(!edge.validate(this)) {
			throw new IllegalArgumentException("Invalid edge");
		}
		return edge;
	}
	@TimeComplexity("O(1)")
	private InnerVertex<V> validate(Vertex<V> v){
		if(!(v instanceof InnerVertex)) {
			throw new IllegalArgumentException("Invalid Edge");
		}
		InnerVertex<V> vertex = (InnerVertex<V>) v;
		if(!(vertex.validate(this))) {
			throw new IllegalArgumentException("Invalid edge");
		}
		return vertex;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		Vertex<V> [] endpoints = edge.getEndpoints();
		if(endpoints[0] == v) {
			return endpoints[1];
		} else if(endpoints[1] == v) {
			return endpoints[0];
		}else {
			throw new IllegalArgumentException("V is not incident to this edge");
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		InnerVertex<V>[] verts = (InnerVertex<V>[]) edge.getEndpoints();
		verts[0].getOutgoing().remove(verts[1]);
		verts[1].getIncoming().remove(verts[0]);
		edges.remove(edge.getPosition());
		edge.setPosition(null);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	@TimeComplexity("O(deg(v))")
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
	    // remove all incident edges from the graph
	    for (Edge<E> e : vert.getOutgoing().values())
	      removeEdge(e);
	    for (Edge<E> e : vert.getIncoming().values())
	      removeEdge(e);
	    // remove this vertex from the list of vertices
	    vertices.remove(vert.getPosition());
	    vert.setPosition(null);
	}

	/* 
     * replace the element in edge object, return the old element
     */
	@TimeComplexity("O(1)")
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		InnerEdge<E> edge  =(InnerEdge<E>) e;
	    E old=edge.getElement();
	    edge.setElement(o);
	    return old;
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	@TimeComplexity("O(1)")
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		 InnerVertex<V> vert1 = validate(v);
		    V old = vert1.getElement();
		    vert1.setElement(o);
		    return old;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	public Iterable<Vertex<V>> vertices() {
		return vertices;
	}

	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
	    return vert.getOutgoing().size();
	}

	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
	    return vert.getIncoming().size();
	}

	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		 InnerVertex<V> vert = validate(v);
		 return vert.getOutgoing().values();
	}

	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
	    return vert.getIncoming().values(); 
	}

	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> origin = validate(u);
	    return origin.getOutgoing().get(v);
	}
	
}
