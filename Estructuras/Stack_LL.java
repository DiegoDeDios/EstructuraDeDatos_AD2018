package Estructuras;

public class Stack_LL <T> {
	private Node <T> top = null;
	public Stack_LL(){
		
	}
	public void push(T data){ //Introduce un nuevo elemento al stack
		Node <T> newNode = new Node <T> (data);
		newNode.setNext(this.top); //Apuntador del Nodo
		this.top= newNode; 
		
	}
	public T pop(){ //Remueve y regresa el ultimo nodo en ser agregado.
		if(this.isEmpty()){
			System.out.println("El Stack está vacio"); //Válida que el Stack no esté vacío
			return null;
		}
		else{
			Node<T> temp = new Node<T>(this.top.getNodeData());
			this.top=this.top.getNext();
			return temp.getNodeData();
		}
	}
	public boolean isEmpty(){ //Valida si el stack está vacío o no
		if (this.top==null){
			return true;
		}
		else{
			return false;
		}
	}
	public T peak(){ //devuelve el nodo más reciente si eliminarlo
		if(this.isEmpty()==false){
		return this.top.getNodeData();
		}
		else{
			return null;
		}
	}
	public String imprimirStack() { //Imprime el Stack completo
		if(this.isEmpty()){
			System.out.println("El stack esta vacio");
			return null;
		}
		else{
	    Node current = this.top;
	    String sm ="";
	    while (current != null) {
	        sm+="+"+current.getNodeData() +"\n";
	        current = current.getNext();
	    	}
	    return sm;
		}
	}
}
