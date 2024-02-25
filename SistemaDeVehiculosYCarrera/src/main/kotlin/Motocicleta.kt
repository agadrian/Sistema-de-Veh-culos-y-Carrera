/**
 * Clase que representa una motocicleta.
 * @property cilindrada La cilindrada de la motocicleta en centímetros cúbicos (cc).
 * @constructor Crea una nueva motocicleta con los siguientes parámetros:
 * @param nombre El nombre de la motocicleta.
 * @param marca La marca de la motocicleta.
 * @param modelo El modelo de la motocicleta.
 * @param capacidadCombustible La capacidad total del tanque de combustible de la motocicleta en litros.
 * @param combustibleActual La cantidad actual de combustible en el tanque de la motocicleta en litros.
 * @param kilometrosActuales El total de kilómetros recorridos por la motocicleta.
 */
class Motocicleta(nombre: String, marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Float, private val cilindrada: Int) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {


    init {
        require(cilindrada in 125..1000){"La cilindrada debe ser de 125-1000cc"}
    }

    companion object{
        const val KM_LITRO_BASE_MOTO = 20f
    }


    /**
     * Calcula la eficiencia de combustible de la motocicleta en kilómetros por litro (km/l),
     * dependiendo de su cilindrada.
     * @return La eficiencia de combustible en km/l.
     */
    override fun calcularKmL():Float{
        return if (cilindrada == 1000) KM_LITRO_BASE_MOTO else KM_LITRO_BASE_MOTO - (cilindrada.toFloat() / 1000f)
    }


    /**
     * Calcula la autonomía de la motocicleta en kilómetros, considerando la cantidad actual de combustible
     * y su eficiencia en km/l.
     * @return La autonomía de la motocicleta en kilómetros.
     */
    override fun calcularAutonomia(): Float {
        return combustibleActual * calcularKmL()
    }


    /**
     * Resta la cantidad de combustible equivalente a la distancia pasada como paramtro
     * @param distanciaRecorrida Distancia a restar en combustible
     */
    override fun restarCombustible(distanciaRecorrida: Float) {
        combustibleActual -= distanciaRecorrida / calcularKmL()
    }


    /**
     * Simula realizar un caballito con la motocicleta, lo cual consume combustible adicional.
     * @return La cantidad restante de combustible después de realizar el caballito.
     */
    fun realizarCaballito(): Float{
        restarCombustible(6.5f)
        return combustibleActual
    }


    /**
     * Devuelve una representación en forma de cadena de Motocicleta.
     * @return String - La representación en forma de cadena del objeto.
     */
    override fun toString(): String {
        return super.toString() + " ; Cilindrada: $cilindrada "
    }

}