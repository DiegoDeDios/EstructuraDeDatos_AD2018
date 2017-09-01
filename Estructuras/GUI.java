package Estructuras;

import java.text.Normalizer;

import javax.swing.JOptionPane;

public class GUI {
	public GUI(){
		this.menuPrincipal();
	}
	public void menuPrincipal(){
		String op;
		try{
		do{

			op = JOptionPane.showInputDialog(null, "Seleccione una utilidad para su Stack \n"
					+ "\n"
					+ "1. Palindromo \n"
					+ "2. Crear Stack Personal \n"
					+ "3. Salir\n"
					);

			switch(op){
			case "1": 
				this.menuPalindromos();
				break;
			case "2":
				this.menuStacks();
			case "3":
				JOptionPane.showMessageDialog(null, "Hasta luego :)");
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcion invalida");
				break;
			}      
		}while(!op.equals("3"));
	}
		catch(NullPointerException ex){
			JOptionPane.showMessageDialog(null, "Hasta luego :)");
			System.exit(0);
		}
	}
	
	
	
	public void menuPalindromos(){
		try{
		Stack_LL stack=new Stack_LL();
		String palindromo=JOptionPane.showInputDialog("Escriba su sentencia/palabra");
		String original=palindromo;
		String alReves="";
	    palindromo=palindromo.toLowerCase();
	    palindromo=palindromo.replaceAll("\\s+","");
	    palindromo = Normalizer.normalize(palindromo, Normalizer.Form.NFD);
	    palindromo = palindromo.replaceAll("[^\\p{ASCII}]", ""); //Elimina todos los acentos y caracteres innecesarios
	    for(int i=0;i<palindromo.length();i++){
				stack.push(palindromo.charAt(i));
		}
	    while(stack.isEmpty()!=true){
			alReves+=stack.pop();
		} 
	    if(palindromo.equals(alReves)){
	    	JOptionPane.showMessageDialog(null, "Efectivamente "+original+ " es un palindromo");
	    }
	    else{
	    	JOptionPane.showMessageDialog(null, original+" no es un palindromo");
	    }
	}
		catch(NullPointerException ex){
			this.menuPrincipal();
		}
	}
	public void menuStacks(){
		Stack_LL <String> stackPersonal= new Stack_LL();
		String StackM;
		do{
			StackM = JOptionPane.showInputDialog(null, "Creacion Stack nuevo \n"
					+ "\n"
					+ "1. Push \n"
					+ "2. Pop \n"
					+ "3. Peak \n"
					+ "4. Mostrar Stack \n"
					+ "5. Regresar\n"
					);

			switch(StackM){
			case "1": 
			String value = JOptionPane.showInputDialog("Introduzca un valor");
			stackPersonal.push(value);
				break;
			case "2":
				if(stackPersonal.isEmpty()){
				JOptionPane.showMessageDialog(null, "El stack esta vacio, como tu corazon");
				}
				else{
				JOptionPane.showMessageDialog(null, "Se ha eliminado el elemento "+stackPersonal.pop()+"\n El elemento "+stackPersonal.peak()+" ahora está hasta arriba");
				}
				break;
			case "3":
				if(stackPersonal.isEmpty()){
					JOptionPane.showMessageDialog(null, "No se encontro nada en el Stack");
					}
				else{
				JOptionPane.showMessageDialog(null, stackPersonal.peak()+" se encuentra hasta arriba");
				}
				break;
			case "4":
				if(stackPersonal.isEmpty()!=true){
					JOptionPane.showMessageDialog(null, stackPersonal.imprimirStack());
				}
				else{
					JOptionPane.showMessageDialog(null, "No hay nada que imprimir");
				}
				break;
			case "5":
				this.menuPrincipal();
			default:
				JOptionPane.showMessageDialog(null, "Opcion invalida");
				break;
			}      
		} while(!StackM.equals("5"));
	}
	
	public static void main(String [] args){
		GUI Gui=new GUI();
	}
	}
