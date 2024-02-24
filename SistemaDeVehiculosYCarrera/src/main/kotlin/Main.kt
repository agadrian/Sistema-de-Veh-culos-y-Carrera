import kotlin.math.pow
import kotlin.math.roundToInt


fun Float.redondear(posiciones: Int): Float{
    val factor = 10.0.pow(posiciones.toDouble()).toFloat()
    return (this * factor).roundToInt() / factor
}

fun main() {


   /* val automovil = Automovil( true, false, "Automovil 1","Tesla", "s", 150f, 100f, 50f)

    val moto = Motocicleta(600, "MOto 1", "Honda", "CBR600RR", 150f, 100f, 50f)*/





/*
    println()

    println("****************AUTOMOVIL**************")
    println(automovil)
    automovil.realizarDerrape()
    println(automovil)
    automovil.realizarDerrape()
    println(automovil)
    automovil.repostar(5f)
    println(automovil)
    automovil.realizaViaje(1000f)
    println(automovil)
    automovil.realizarDerrape()
    println(automovil)
    automovil.repostar(15f)
    println(automovil)
    automovil.realizaViaje(500f)
    println(automovil)


    println()

    println("****************MOTO**************")
    println(moto)
    moto.repostar(3f)
    println(moto)
    moto.realizarCaballito()
    println(moto)
    moto.realizaViaje(50f)
    println(moto)
    moto.realizarCaballito()
    moto.realizarCaballito()
    moto.realizarCaballito()
    println(moto)
    moto.realizaViaje(1050f)
    println(moto)
    moto.realizaViaje(870f)
    println(moto)*/
}