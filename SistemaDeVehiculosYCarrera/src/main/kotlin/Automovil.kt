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
class Automovil(nombre: String, marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Float, val esHibrido: Boolean, condicionBritanica: Boolean = false) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {


    override fun calcularKmL(): Float {
        return if (esHibrido) KM_LITRO_HIBRIDO else KM_POR_LITRO
    }


    override fun calcularAutonomia(): Float {
        return combustibleActual * calcularKmL()
    }


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
     * Método que simula un derrape. Realiza una gasto adicional en el combustible, retornando el combustible restante. El gasto equivale a haber realizado 7,5 km o 6,25 km si es híbrido.
     */
    fun realizarDerrape(): Float{
        println("Has derrapao")
        if (esHibrido) restarCombustible(6.25f) else restarCombustible(7.5f)
        return combustibleActual
    }


    override fun toString(): String {
        return super.toString() + " ; EsElectrico: $esHibrido ; CondicionBritanica: $condicionBritanica"
    }

}