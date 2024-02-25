import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Metodo redondear para los floats
 */
fun Float.redondear(posiciones: Int): Float{
    val factor = 10.0.pow(posiciones.toDouble()).toFloat()
    return (this * factor).roundToInt() / factor
}

/**
 * Crear metodo capitalizar para los strings
 */
internal fun String.capitalizar(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun main() {

    // Creacion vehiculos

    val aurora = Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0f, true) // Coche electrico con capacidad de 50 litros, inicia con el 10%
    val boreal = Automovil("Boreal", "BMW", "M8", 80f, 80f * 0.1f, 0f, false) // SUV hibrido con capacidad de 80 litros, inicia con el 10%
    val cefiro = Motocicleta("Cefiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0f, 500) // Motocicleta de gran cilindrada con capacidad de 15 litros, inicia con el 10%
    val dinamo = Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0f, true) // Camioneta electrica con capacidad de 70 litros, inicia con el 10%
    val eclipse = Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0f, false) // Coupe deportivo con capacidad de 60 litros, inicia con el 10%
    val fenix = Motocicleta("Fenix", "Honda", "Vital", 20f, 20f * 0.1f, 0f, 250) // Motocicleta electrica con capacidad de 20 litros, inicia con el 10%




    // Lista con los vehiculos
    val listaCoches = listOf(aurora, boreal, cefiro, dinamo, eclipse, fenix)

    // Instancia de la carrera
    val carrera = Carrera("Grand Prix", 1000f, listaCoches)

    // Iniciar la carrera
    carrera.iniciarCarrera()

    val listadoresultaddos = carrera.obtenerResultados()

    println()

    // Resultados carrera
    println("Resultados de la carrera ${carrera.nombreCarrera}: ")
    for (resultado in listadoresultaddos) {
        print("Posicion ${resultado.posicion} - ")
        print(resultado.vehiculo.nombre.capitalizar())
        print(". Distancia total recorrida: ${resultado.kilometraje} km. ")
        print("Numero de repostajes: ${resultado.paradasRepostaje}. ")
        print("Historial de acciones -> ")
        for (accion in resultado.historialAcciones) {
            print(" - $accion")
        }
        println()
    }


}