package comun;


public interface IGrabable
{

    public abstract ErrorSistema grabar();

    public abstract ErrorSistema borrar();

    public abstract void cargar();

    public abstract void cargar(Resultados resultados);

    public abstract boolean existe();
}