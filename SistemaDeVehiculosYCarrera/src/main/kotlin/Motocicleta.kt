class Motocicleta(val cilindrada: Int, nombre: String, marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Float) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    init {
        require(cilindrada in 125..1000){"La cilindrada debe ser de 125-1000cc"}
    }

    /**
     * Calcula la utonomia de la moto dependiendo de su cilindrada
     */
    override fun calcularAutonomia(): Float {
        return if (cilindrada == 1000) {
            //return combustibleActual * (KM_POR_LITRO + 10)
            (super.calcularAutonomia() * 2f).redondear(2)
        }else{
            (combustibleActual * ( 20f - (1000f/cilindrada.toFloat())))
        }
    }

    /**
     * Ajusta el cálculo de combustible necesario para viajes basándose en su autonomía específica.
     */
    override fun realizaViaje(distancia: Float): Float {
        val autonomia = calcularAutonomia()

        val distanciaRecorrida = if (autonomia >= distancia) distancia else autonomia

        val km_l = autonomia / combustibleActual

        combustibleActual -= (distanciaRecorrida / km_l).redondear(2)
        kilometrosActuales += distanciaRecorrida.redondear(2)

        return distancia - distanciaRecorrida
    }

    /**
     *  realiza una gasto adicional en el combustible, retornando el combustible restante. El gasto equivale a haber realizado 6,5 kilómetros.
     */
    fun realizarCaballito(): Float{
        println("Has hecho un wheelie")
        val combustibleGatado = (6.5f / calcularAutonomia()).redondear(2)
        combustibleActual -= combustibleGatado
        return combustibleActual
    }

    override fun toString(): String {
        return super.toString() + " ; Cilindrada: $cilindrada ; Autonomia: ${calcularAutonomia()} ; Kilometors Actuales : $kilometrosActuales"
    }

}