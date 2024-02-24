class Motocicleta(nombre: String, marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Float, val cilindrada: Int) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {



    init {
        require(cilindrada in 125..1000){"La cilindrada debe ser de 125-1000cc"}
    }

    companion object{
        const val KM_LITRO_BASE_MOTO = 20f
    }


    /**
     * Calcula cuantos km hace por litro dependiendo de su cilindrada
     */
    override fun calcularKmL():Float{
        return if (cilindrada == 1000) KM_LITRO_BASE_MOTO else KM_LITRO_BASE_MOTO - (cilindrada.toFloat() / 1000f)
    }


    /**
     * Calcula la autonomia de kilometros de la moto dependiendo de su km/l
     */
    override fun calcularAutonomia(): Float {
        return combustibleActual * calcularKmL()
    }


    /**
     * Ajusta el cálculo de combustible necesario para viajes basándose en su autonomía específica.
     */
    override fun realizaViaje(distancia: Float): Float {
        val autonomia = calcularAutonomia()

        val distanciaRecorrida = if (autonomia >= distancia) distancia else autonomia

        val kmL = calcularKmL()

        combustibleActual -= (distanciaRecorrida / kmL).redondear(2)
        kilometrosActuales += distanciaRecorrida.redondear(2)

        return distancia - distanciaRecorrida
    }


    override fun restarCombustible(distanciaRecorrida: Float) {
        combustibleActual -= distanciaRecorrida / calcularKmL()
    }
    /**
     *  realiza una gasto adicional en el combustible, retornando el combustible restante. El gasto equivale a haber realizado 6,5 kilómetros.
     */
    fun realizarCaballito(): Float{
        println("Has hecho un wheelie")
        restarCombustible(6.5f)
        return combustibleActual
    }

    override fun toString(): String {
        return super.toString() + " ; Cilindrada: $cilindrada "
    }

}