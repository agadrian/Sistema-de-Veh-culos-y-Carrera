import kotlin.math.pow
import kotlin.math.roundToInt


fun Float.redondear(posiciones: Int): Float{
    val factor = 10.0.pow(posiciones.toDouble()).toFloat()
    return (this * factor).roundToInt() / factor
}

fun main() {



    val automovil = Automovil( true, false, "Automovil 1","Tesla", "s", 150f, 100f, 50f)

    val moto = Motocicleta(600, "MOto 1", "Honda", "CBR600RR", 150f, 100f, 50f)



    println()

    println("****************AUTOMOVIL**************")
    println(automovil)
    println("Combustible actual: ${ automovil.combustibleActual }")
    automovil.repostar(-5f)
    println("Combustible actual: ${ automovil.combustibleActual }")
    println("Autonomia actual: ${automovil.calcularAutonomia()}")
    println("Combustible actual: ${ automovil.combustibleActual }")

    println()

    println("****************MOTO**************")
    println(moto.calcularAutonomia())
    println(moto)
    moto.repostar(3f)
    println(moto)
    moto.realizarCaballito()
    println(moto)
    moto.realizaViaje(50f)
    println(moto)

}