import java.util.*


class Carrera (val nombreCarrera: String, private val distanciaTotal: Float, private val participantes: List<Vehiculo>) {

    private val historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf<String, MutableList<String>>()
    private var estadoCarrera: Boolean = false

    /**
     * Inicia la carrera, estableciendo estadoCarrera a true y comenzando el ciclo de iteraciones donde los vehículos avanzan y realizan acciones.
     */
    fun iniciarCarrera() {
        estadoCarrera = true

        while (estadoCarrera) {
            val vehiculoRandom = participantes.random()
            avanzarVehiculo(vehiculoRandom)
            determinarGanador()
        }
    }


    /**
     * Identificado el vehículo, le hace avanzar una distancia aleatoria entre 10 y 200 km. Si el vehículo necesita repostar, se llama al método repostarVehiculo() antes de que pueda continuar. Este método llama a realizar filigranas.
     */
    private fun avanzarVehiculo(vehiculo: Vehiculo){
        val distanciaARecorrer = ((10..200).random()).toFloat()
        var distanciaRecorrida = 0f
        var distanciaUltimaFil = 0f

        añadirAccion(vehiculo.nombre, "*** Inicia recorrido. Recorrido: $distanciaARecorrer kms. Combustible: ${vehiculo.combustibleActual} Litros ***")

        while (distanciaRecorrida < distanciaARecorrer){
            val distanciaRestante = distanciaARecorrer - distanciaRecorrida
            val distanciaTramo = minOf(distanciaRestante, 20f)
            val numRandom = (1..2).random()

            // Atuotnomia
            val autonomia = vehiculo.calcularAutonomia()

            if (autonomia < distanciaTramo){
                // Si no tiene autonomia suficiente, gasta la que tiene y reposta
                if (autonomia > 0f) {
                    vehiculo.realizaViaje(autonomia)
                    distanciaRecorrida += autonomia
                    añadirAccion(vehiculo.nombre, "Recorre ${autonomia.redondear(2)} km. ")
                    distanciaUltimaFil += autonomia
                    repostarVehiculo(vehiculo)
                }
            }

            else{
                // Avanzar
                val tramoRecorrido = vehiculo.realizaViaje(distanciaTramo)
                distanciaRecorrida += tramoRecorrido
                añadirAccion(vehiculo.nombre, "Recorre ${tramoRecorrido.redondear(2)} km. ")

                // Actualizar distancia ultima filigrana
                distanciaUltimaFil += tramoRecorrido
            }

            // Realizar filigrana cada 20 km. Se comprueba si teienen combustible, si no repostan.
            if (distanciaUltimaFil >= 20f) {
                val autoFili = vehiculo.calcularAutonomia()

                if (numRandom == 1) {
                    if (autoFili >= 7.5f){
                        realizarFiligrana(vehiculo)
                    }else{
                        repostarVehiculo(vehiculo)
                        realizarFiligrana(vehiculo)
                    }

                } else {
                    if (autoFili >= 15f) {
                        realizarFiligrana(vehiculo)
                        realizarFiligrana(vehiculo)
                    }
                    else{
                        repostarVehiculo(vehiculo)
                        realizarFiligrana(vehiculo)
                        realizarFiligrana(vehiculo)
                    }
                }

                // Resetear el contador de la filigrana
                distanciaUltimaFil = 0f
            }
        }
    }


    /**
     * Añade una accion a un vehiculo en el historial de acciones. Si no existe el nombre, lo crea con una lista.
     * @param vehiculo Objeto de tipo Vehiculo
     * @param accion String de la accion que se registra en el historial de acciones
     */
    private fun añadirAccion(vehiculo: String, accion: String){
        // Si no esta en el historial, lo añadimos, y sis esta, solo añadimos la accion
        if (historialAcciones.containsKey(vehiculo)) {
            historialAcciones[vehiculo]!!.add(accion)
        } else {
            historialAcciones[vehiculo] = mutableListOf(accion)
        }
    }


    /**
     * Reposta el vehículo seleccionado, incrementando su combustibleActual y registrando la acción en historialAcciones.
     */
    private fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float = 0f){
        vehiculo.repostar(cantidad)
        añadirAccion(vehiculo.nombre, "Llena el tanque. ")
    }


    /**
     * Determina aleatoriamente si un vehículo realiza una filigrana (derrape o caballito) y registra la acción.
     */
    private fun realizarFiligrana(vehiculo: Vehiculo) {
        if (vehiculo is Automovil) {
            vehiculo.realizarDerrape()
            añadirAccion(vehiculo.nombre, "Ha derrapado. Combustible: ${vehiculo.combustibleActual.redondear(2)}. ")
        }
        else if (vehiculo is Motocicleta) {
            vehiculo.realizarCaballito()
            añadirAccion(vehiculo.nombre, "Ha hecho un wheelie. Combustible: ${vehiculo.combustibleActual.redondear(2)}. ")
        }
    }
    

    /**
     * Revisa posiciones para identificar al vehículo (o vehículos) que haya alcanzado o superado la distanciaTotal, estableciendo el estado de la carrera a finalizado y determinando el ganador.
     */
    private fun determinarGanador() {
        for (vehiculo in participantes) {
            if (vehiculo.kilometrosActuales >= distanciaTotal) {
                println("El ganador de '$nombreCarrera' es ... ${vehiculo.nombre.capitalizar()}!!")
                estadoCarrera = false
            }
        }
    }


    /**
     * Devuelve una clasificación final de los vehículos, cada elemento tendrá el nombre del vehiculo, posición ocupada, la distancia total recorrida, el número de paradas para repostar y el historial de acciones. La collección estará ordenada por la posición ocupada.
     */
    fun obtenerResultados(): List<ResultadoCarrera>{
        val resultados = mutableListOf<ResultadoCarrera>()
        val participantesOrdenados = participantes.sortedByDescending { it.kilometrosActuales }

        for ((index, vehiculo) in participantesOrdenados.withIndex()) {
            val paradasRepostaje = vehiculo.obtenerContRepostaje()
            val historialAcc = historialAcciones[vehiculo.nombre] ?: listOf()
            if (vehiculo.kilometrosActuales > 1000f) vehiculo.kilometrosActuales = 1000f
            resultados.add(ResultadoCarrera(vehiculo, index + 1, vehiculo.kilometrosActuales, paradasRepostaje, historialAcc))
        }
        return resultados
    }



    /**
     * Representa el resultado final de un vehículo en la carrera, incluyendo su posición final, el kilometraje total recorrido,
     * el número de paradas para repostar, y un historial detallado de todas las acciones realizadas durante la carrera.
     *
     * @property vehiculo El [Vehiculo] al que pertenece este resultado.
     * @property posicion La posición final del vehículo en la carrera, donde una posición menor indica un mejor rendimiento.
     * @property kilometraje El total de kilómetros recorridos por el vehículo durante la carrera.
     * @property paradasRepostaje El número de veces que el vehículo tuvo que repostar combustible durante la carrera.
     * @property historialAcciones Una lista de cadenas que describen las acciones realizadas por el vehículo a lo largo de la carrera, proporcionando un registro detallado de su rendimiento y estrategias.
     */
    data class ResultadoCarrera(
        val vehiculo: Vehiculo,
        val posicion: Int,
        val kilometraje: Float,
        val paradasRepostaje: Int,
        val historialAcciones: List<String>
    )
}