/**
 * Clase que representa un automóvil.
 * @property esHibrido Indica si el automóvil es híbrido (eléctrico + gasolina) o no (solo gasolina).
 * @property condicionBritanica Propiedad de clase que indica si los automóviles están configurados para conducción británica (volante a la derecha) o no.
 *
 * @constructor Crea un nuevo automóvil con los siguientes parámetros:
 * @param marca La marca del automóvil.
 * @param modelo El modelo del automóvil.
 * @param capacidadCombustible La capacidad total del tanque de combustible del automóvil en litros.
 * @param combustibleActual La cantidad actual de combustible en el tanque del automóvil en litros.
 * @param kilometrosActuales El total de kilómetros recorridos por el automóvil.
 */
class Automovil(nombre: String, marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Float, private val esHibrido: Boolean, condicionBritanica: Boolean = false) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {


    companion object {
        var condicionBritanica: Boolean = false
        const val KM_LITRO_HIBRIDO = 15f

        /**
         * Método de clase que permite modificar la configuración de conducción británica para todos los automóviles.
         */
        fun cambiarCondicionBritanica(nuevaCondicion: Boolean){
            condicionBritanica = nuevaCondicion
        }
    }


    /**
     * Calcula la cantidad de kilómetros que el automóvil puede recorrer por litro de combustible, dependiendo de si es híbrido o no.
     * @return La cantidad de kilómetros por litro del automóvil.
     */
    override fun calcularKmL(): Float {
        return if (esHibrido) KM_LITRO_HIBRIDO else KM_POR_LITRO
    }


    /**
     * Calcula la autonomía del automóvil en kilómetros, teniendo en cuenta la cantidad de combustible actual y su eficiencia en kilómetros por litro.
     * @return La autonomía del automóvil en kilómetros.
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
     * Método que simula un derrape. Realiza una gasto adicional en el combustible, retornando el combustible restante. El gasto equivale a haber realizado 7,5 km o 6,25 km si es híbrido.
     * @return El combustible restante después de realizar el derrape.
     */
    fun realizarDerrape(): Float{
        if (esHibrido) restarCombustible(6.25f) else restarCombustible(7.5f)
        return combustibleActual
    }


    /**
     * Devuelve una representación en forma de cadena de Automovil.
     * @return String - La representación en forma de cadena del objeto.
     */
    override fun toString(): String {
        return super.toString() + " ; EsElectrico: $esHibrido ; CondicionBritanica: $condicionBritanica"
    }

}