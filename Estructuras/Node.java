package Estructuras;

public class Node <T> {
	private T data; //Dato a guardar
	private Node <T> next; //Objeto de tipo nodo
	
	public Node(T data){ //Constructor, recibe cualquier tipo de dato
		this.data=data;
	}
	public T getNodeData(){ //Regresa lo que esté guardado en el Nodo
		return this.data;
	}
	public void setNext(Node<T> NextNode){ //Apuntador al siguiente Nodo
		this.next=NextNode;
	}
	public Node getNext(){ //Regresa el nodo siguiente
		return this.next;
	}
	

}
