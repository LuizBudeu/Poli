
public class Cliente3 {
	private String nome;
	private static int nconta = 1001;
	public static int quantosBloqueados = 0;
	private int numero_conta;
	private double saldo;
	private boolean bloqueado;
	public static int contasAtivas = 0;
	
	private Cliente3(String no, double s) {
		nome = no;
		saldo = s;
		numero_conta = nconta++;
		contasAtivas++;
		checaBloqueado();
	}
	
	private Cliente3(String no) {
		nome = no;
		saldo = 0;
		numero_conta = nconta++;
		contasAtivas++;
		checaBloqueado();
	}
	
	
	//Esse método tem que ser estático porque para criar uma instância, é necessário chamar esse método. Isto quer dizer que o método vai ser chamado antes que exista a instância do objeto.
	public static Cliente3 criaCliente(String n) {
		if (contasAtivas < 5) {
			Cliente3 c = new Cliente3(n);
			return c;
		}
		
		return null;
	}
	
	public void fimCliente() throws Throwable{
		super.finalize();
		contasAtivas--;
		//System.gc();   
		//System.runFinalization();
        System.out.println("end of garbage collection");  
	}


	public void checaBloqueado() {
		if (saldo < 0){
			bloqueado = true;
			quantosBloqueados++;
		}
		else 
			bloqueado = false;
	}
	
	public boolean getBloqueado() {
		return bloqueado;
	}
	
	public void setNome(String n){
		nome = n;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNConta(int n) {
		nconta = n;
	}
	
	public int getNConta() {
		return numero_conta;
	}
	
	public void setSaldo(double s) {
		saldo = s;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public String toString() {
		return ""+ nome +", "+ numero_conta +", " + saldo;
	}
	
	public void imprime() {
		System.out.println("Nome, número da conta, saldo: "+ toString());
	}
	
	public int saque(double s) {
		if (saldo - s >= 0) {
			saldo -= s;
			return 1;
		}
		return 0;
	}
	
	public void deposito(double d) {
		saldo += d;
	}
	
	public boolean saldoMaiorIgual(double s) {
		return saldo >= s;
	}
	
	public boolean nomeIgual(String n) {
		return nome == n;
	}
}
