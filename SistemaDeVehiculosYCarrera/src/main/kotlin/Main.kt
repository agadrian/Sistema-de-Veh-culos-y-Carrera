import kotlin.math.pow
import kotlin.math.roundToInt


fun Float.redondear(posiciones: Int): Float{
    val factor = 10.0.pow(posiciones.toDouble()).toFloat()
    return (this * factor).roundToInt() / factor
}

fun main() {


    val aurora = Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0f, true) // Coche eléctrico con capacidad de 50 litros, inicia con el 10%
    val boreal = Automovil("Boreal", "BMW", "M8", 80f, 80f * 0.1f, 0f, false) // SUV híbrido con capacidad de 80 litros, inicia con el 10%
    val cefiro = Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0f, 500) // Motocicleta de gran cilindrada con capacidad de 15 litros, inicia con el 10%
    val dinamo = Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0f, true) // Camioneta eléctrica con capacidad de 70 litros, inicia con el 10%
    val eclipse = Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0f, false) // Coupé deportivo con capacidad de 60 litros, inicia con el 10%
    val fenix = Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 0f, 250) // Motocicleta eléctrica con capacidad de 20 litros, inicia con el 10%


    val listaCoches = listOf<Vehiculo>(aurora, boreal, cefiro, dinamo, eclipse, fenix)

    val carrera = Carrera("Grand Prix", 1000f, listaCoches)

    carrera.iniciarCarrera()

    val historialAcciones = carrera.historialAcciones

    for ((nombre, accion) in historialAcciones){
        print("$nombre -> ")
        for (descAccion in accion){
            print(descAccion)
        }
    }


















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