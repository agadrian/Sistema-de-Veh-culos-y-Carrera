import kotlin.math.roundToInt

/**
 * Clase que representa un vehículo genérico.
 * @property marca La marca del vehículo.
 * @property modelo El modelo del vehículo.
 * @property capacidadCombustible La capacidad total del tanque de combustible del vehículo en litros.
 * @property combustibleActual La cantidad actual de combustible en el tanque del vehículo en litros.
 * @property kilometrosActuales El total de kilómetros recorridos por el vehículo.
 */
abstract class Vehiculo (nombre:String, private val marca: String, private val modelo: String, capacidadCombustible: Float, combustibleActual: Float, var kilometrosActuales: Float){

    val nombre:String = nombre.lowercase().trim()

    private val capacidadCombustible = capacidadCombustible.redondear(2)

    private var contRepostajes = 0

    var combustibleActual = combustibleActual
        set(value) {
            value.redondear(2)
            field = value
        }

    init {
        if (!nombres.add(nombre)) {
            throw IllegalArgumentException("El nombre de ese vehículo ya existe")
        }
    }


    companion object{
        const val KM_POR_LITRO = 10.0f
        private val nombres = mutableSetOf<String>()
    }

    /**
     * Metodo abstracto para calcular los kilometros por litro del vehiculo.
     */
    abstract fun calcularKmL():Float

    
    /**
     * Retorna los kilómetros que el vehículo puede recorrer con el combustible actual (suponemos que cada litro da para 10 km)
     * @return String - Cadena de texto inormando de los km que se pueden recorrer
     */
    fun obtenerInformacion(): String {
        return "El vehiculo actualmente puede recorrer: ${combustibleActual * KM_POR_LITRO} kms"
    }


    /**
     * Calcla la autonomia (Suponemos que cada litro da para 10 km.).
     * @return Int - La autonomia
     */
    open fun calcularAutonomia(): Float{
        return (combustibleActual * KM_POR_LITRO).redondear(2)
    }


    /**
     * Realiza un viaje hasta donde da el combustible actual.
     * @param distancia La distancia a recorrer en kilómetros.
     * @return Float - La distancia recorrida en kilómetros.
     */
    open fun realizaViaje(distancia: Float): Float{
        val autonomia = calcularAutonomia()

        val distanciaRecorrida = minOf(autonomia, distancia)
        restarCombustible(distanciaRecorrida)
        kilometrosActuales += distanciaRecorrida

        return distanciaRecorrida
    }


    /**
     * Reduce el combustible actual del vehículo según la distancia recorrida.
     * @param distanciaRecorrida La distancia recorrida en kilómetros.
     */
    open fun restarCombustible(distanciaRecorrida: Float){
        combustibleActual -= distanciaRecorrida / KM_POR_LITRO
    }



    /**
     * Incrementa combustibleActual hasta el máximo de capacidadCombustible si no se pasa
     * cantidad o si cantidad es O o negativa. Sino, incrementa en cantidad hasta llegar a capacidadCombustible.
     * @return Float - La cantidad repostada en litros.
     */
    fun repostar(cantidad: Float = 0f): Float{
        val espacioTanque = capacidadCombustible - combustibleActual
        val cantidadRepostada: Float

        if (cantidad <= 0 || cantidad >= espacioTanque) {
            combustibleActual = capacidadCombustible
            cantidadRepostada =  espacioTanque
        }
        else{
            combustibleActual += cantidad
            cantidadRepostada = cantidad
        }
        contRepostajes++

        return cantidadRepostada.redondear(2)
    }


    /**
     * Obtiene el número de veces que se ha repostado combustible.
     * @return Int - El número de repostajes realizados.
     */
    fun obtenerContRepostaje() = contRepostajes


    /**
     * Devuelve una representación en forma de cadena de Vehiculo.
     * @return String - La representación en forma de cadena del objeto.
     */
    override fun toString(): String {
        return "Marca: $marca ; Modelo: $modelo ; CapacidadCombustible: $capacidadCombustible ; CombustibleActual: ${combustibleActual.redondear(2)} ; KmActuales: ${kilometrosActuales.redondear(2)} ; Autonomia: ${calcularAutonomia()}"
    }

}