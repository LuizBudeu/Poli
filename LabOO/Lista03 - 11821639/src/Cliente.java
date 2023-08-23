
public class Cliente {
	private String nome;
	private static int nconta = 1001;
	private int numero_conta;
	private double saldo;
	
	public Cliente(String no, double s) {
		nome = no;
		saldo = s;
		numero_conta = nconta++;
	}
	
	public Cliente(String no) {
		nome = no;
		saldo = 0;
		numero_conta = nconta++;
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
		System.out.print("Nome, número da conta, saldo: "+ toString());
	}
	
	public void saque(double s) {
		saldo -= s;
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