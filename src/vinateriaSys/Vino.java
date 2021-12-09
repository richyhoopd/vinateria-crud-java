package vinateriaSys;

public class Vino {

	private int matricula;
	private String nome;
	private String endereco;
	private char sexo;
	
	public Vino(String nome, char sexo, int matricula, String endereco) {
		this.nome = nome;
		this.matricula = matricula;
		this.endereco = endereco;
		this.sexo = sexo;
	}
	
	public Vino() {
		
	}

	//Getters and Setters
	public int tomarPrecio() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String tomarNomVino() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String tomarDescripcion() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public char tomarAnejo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}


	/*
	 * @Override public int compare(Student s1, Student s2) { // TODO Auto-generated
	 * method stub return s1.getNome().compareTo(s2.getNome()); }
	 */
	
//	@Override
//	public int compareTo(Student s2) {
//		// TODO Auto-generated method stub
//		return this.nome.compareTo(s2.getNome());
//	}
}
