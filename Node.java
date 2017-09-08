package gameOfLife;

public class Node <T> {
	private int index;
	private T data;
	private int row, column;
	private Node <T> next;
	public Node (T value, int row, int column){ /*clase nodo, esta está caracterizada por tener tres atributos,
												   una fila, una columna y el valor asignado */
		this.data=value;
		this.row=row;
		this.column=column;
	}
	public Node(T value, int index){
		this.setValue(value);
		this.setIndex(index);
	}
	public void setValue(T value){
		this.data=value;
	}
	public void setRow(int row){
		this.row=row;
	}
	public void setColumn(int Column){
		this.column=column;
	}
	public void setNext(Node<T> next){
		this.next=next;
	}
	public T getData(){
		return this.data;
	}
	public int getRow(){
		return this.row;
	}
	public int getColumn(){
		return this.column;
	}
	public Node getNext(){
		return this.next;
	}
	public void setIndex(int index){
		this.index=index;
	}
	public int getIndex(){
		return this.index;
	}

}
