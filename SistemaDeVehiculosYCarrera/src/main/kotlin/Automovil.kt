/**
 * Clase que representa un automovil.
 * @property esHibrido Indica si el automovil es hibrido (electrico + gasolina) o no (solo gasolina).
 * @property condicionBritanica Propiedad de clase que indica si los automoviles estan configurados para conduccion britanica (volante a la derecha) o no.
 *
 * @constructor Crea un nuevo automovil con los siguientes parametros:
 * @param marca La marca del automovil.
 * @param modelo El modelo del automovil.
 * @param capacidadCombustible La capacidad total del tanque de combustible del automovil en litros.
 * @param combustibleActual La cantidad actual de combustible en el tanque del automovil en litros.
 * @param kilometrosActuales El total de kilometros recorridos por el automovil.
 */
class Automovil(nombre: String, marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Float, private val esHibrido: Boolean, condicionBritanica: Boolean = false) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {


    companion object {
        var condicionBritanica: Boolean = false
        const val KM_LITRO_HIBRIDO = 15f

        /**
         * Metodo de clase que permite modificar la configuracion de conduccion britanica para todos los automoviles.
         */
        fun cambiarCondicionBritanica(nuevaCondicion: Boolean){
            condicionBritanica = nuevaCondicion
        }
    }


    /**
     * Calcula la cantidad de kilometros que el automovil puede recorrer por litro de combustible, dependiendo de si es hibrido o no.
     * @return La cantidad de kilometros por litro del automovil.
     */
    override fun calcularKmL(): Float {
        return if (esHibrido) KM_LITRO_HIBRIDO else KM_POR_LITRO
    }


    /**
     * Calcula la autonomia del automovil en kilometros, teniendo en cuenta la cantidad de combustible actual y su eficiencia en kilometros por litro.
     * @return La autonomia del automovil en kilometros.
     */
    override fun calcularAutonomia(): Float {
        return (combustibleActual * calcularKmL()).redondear(2)
    }


    /**
     * Resta la cantidad de combustible equivalente a la distancia pasada como paramtro
     * @param distanciaRecorrida Distancia a restar en combustible
     */
    override fun restarCombustible(distanciaRecorrida: Float) {
        combustibleActual -= distanciaRecorrida / calcularKmL()
    }


    /**
     * Metodo que simula un derrape. Realiza una gasto adicional en el combustible, retornando el combustible restante. El gasto equivale a haber realizado 7,5 km o 6,25 km si es hibrido.
     * @return El combustible restante despues de realizar el derrape.
     */
    fun realizarDerrape(): Float{
        if (esHibrido) restarCombustible(6.25f) else restarCombustible(7.5f)
        return combustibleActual
    }


    /**
     * Devuelve una representacion en forma de cadena de Automovil.
     * @return String - La representacion en forma de cadena del objeto.
     */
    override fun toString(): String {
        return super.toString() + " ; EsElectrico: $esHibrido ; CondicionBritanica: $condicionBritanica"
    }

}