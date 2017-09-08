package gameOfLife;

public class SparseArray <T>{
	private Node <T> top;
	private int rows, columns;
	public int size;
	public SparseArray(int rows, int columns){
		this.rows=rows;
		this.columns=columns;
		this.size=0;
	}
	public void addData(T data,int row,int column){ //agrega un nodo en su respectivo index en caso de que este exista
		if(row>this.rows || column>this.columns){
			System.out.println("Index out of bound");
		}
		else if(this.getData(row, column)!=null){
			System.out.println("Index ocupado");
		}
		else{
			Node <T> newNode= new Node(data, row, column);
			newNode.setNext(this.top);
			this.top=newNode;
			this.size++;
		}
	}
	public T getData(int row, int column){
		Node temp=this.top;
		for(int i=0;i<this.size;i++){
			if(temp.getRow()==row && temp.getColumn()==column){
				return (T) temp.getData();
			}
			else{
				temp=temp.getNext();
			}
		}
		return null;
	}
	public void deleteData(int row, int column){
		if(this.getData(row, column)==null){
			System.out.println("Ubicacion de memoria vacia");
		}
		else{
			Node temp=this.top;
			for(int i=0;i<this.size;i++){
				if(temp.getRow()==row && temp.getColumn()==column){
					
				}
				else{
					temp=temp.getNext();
				}
			}
		}
	}
	public void printArray(){
		if(this.top==null){
			System.out.println("Matriz vacia");
		}
		else{
			Node temp= this.top;
			String s="";
			while(temp!=null){
				s+="+"+temp.getData()+"\n";
				temp=temp.getNext();
				System.out.println(s);
			}
		}
	}
	public int getLength(){
		return rows*columns;
	}
	public int elementCount(){ //NZ elements
		return this.size;
	}
	public static void main(String [] args){
		SparseArray s = new SparseArray (5, 5);
		s.addData(4.0, 8, 4);
		s.addData(3.5, 4, 2);
		s.addData(4.5, 3, 4);
		System.out.println(s.getData(4, 2));
	}
}
