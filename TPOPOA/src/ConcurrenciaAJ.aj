public aspect ConcurrenciaAJ{
pointcut hacerCola(HiloCliente c):
		execution (* hacerCola(HiloCliente c);
	before():hacerCola(HiloCliente c){
		System.out.println("**********El hilo actual esta por entrar en la cola");}
	after(): hacerCola(HiloCliente c){ 
		System.out.println("**********todo ok aparentemente");
	}
}
