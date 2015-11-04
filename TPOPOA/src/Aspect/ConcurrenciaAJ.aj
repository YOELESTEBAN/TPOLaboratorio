public aspect ConcurrenciaAJ{
pointcut Pepe(): execution(void hacerCola(..));
before():Pepe(){System.out.println("**********El hilo actual esta por entrar en la cola");}
after():Pepe(){System.out.println("**********todo ok aparentemente");}
}